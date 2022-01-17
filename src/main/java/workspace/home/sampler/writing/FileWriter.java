package workspace.home.sampler.writing;

import workspace.home.sampler.exceptions.InvalidWriterException;
import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileWriter {

    public static void write(String dest, String name, String type, int lineLimit, Stream<Record> recordStream) throws IOException{
        Writable writer = getWriter(type);
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<HashMap<String, String>> records = new ArrayList<>();
        recordStream.forEach(record -> records.add(record.getColumnValues()));
        int lines_written = 0;
        int fileNum = 0;

        for (int i = 0; i < records.size() / lineLimit; i++)
        {
            fileNum++;
            int start_index = i;
            int end_index = i + lineLimit;
            int finalFileNum = fileNum;
            Thread thread = new Thread(() -> writeThread(writer, dest + '/' + name + finalFileNum, records.subList(start_index, end_index)));
            threads.add(thread);
            thread.start();
            lines_written += lineLimit;
        }
        if (lines_written < records.size())
        {
            fileNum++;
            int start_index = lines_written;
            int finalFileNum = fileNum;
            Thread thread = new Thread(() -> writeThread(writer, dest + '/' + name + finalFileNum, records.subList(start_index, records.size())));
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

    private static Writable getWriter(String type) throws InvalidWriterException {
        HashMap<String, Writable> extToWriter = new HashMap<>();
        extToWriter.put("json", new jsonWriter());
        extToWriter.put("xml", new xmlWriter());

        Writable writer = extToWriter.get(type);
        if (writer == null)
        {
            throw new InvalidWriterException("Writer '" + type + "' does not exist.");
        }

        return writer;
    }
}
