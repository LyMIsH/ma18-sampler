package workspace.home.sampler.writing;

import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileWriter {

    public static void write(String dest, String name, String type, int lineLimit, Stream<Record> recordStream) throws IOException {
        Writable writer = getWriter(type);
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<HashMap<String, String>> records = new ArrayList<>();
        recordStream.forEach(record -> records.add(record.getColumnValues()));
        int lines_written = 0;

        for (int i = 0; i < records.size() / lineLimit; i++)
        {
            int start_index = i;
            int end_index = i + lineLimit;
            Thread thread = new Thread(() -> writeThread(writer, dest + '/' + name, records.subList(start_index, end_index)));
            threads.add(thread);
            thread.start();
            lines_written += lineLimit;
        }
        if (lines_written < records.size())
        {
            int start_index = lines_written;
            Thread thread = new Thread(() -> writeThread(writer, dest + '/' + name, records.subList(start_index, records.size())));
            threads.add(thread);
            thread.start();
        }
        for (Thread thread: threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void writeThread(Writable writer, String dest, List<HashMap<String, String>> data)
    {
        try
        {
            writer.write(dest, data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static Writable getWriter(String type) throws IOException {
        HashMap<String, Writable> extToWriter = new HashMap<>();
        extToWriter.put("json", new jsonWriter());

        Writable writer = extToWriter.get(type);
        if (writer == null)
        {
            throw new IOException();
        }

        return writer;
    }
}
