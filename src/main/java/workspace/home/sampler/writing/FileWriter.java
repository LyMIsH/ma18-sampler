package workspace.home.sampler.writing;

import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileWriter {
    // map ext to instance.
    public static void write(String dest, String name, String type, int lineLimit, Stream<Record> recordStream) throws IOException {
        Writable writer = getWriter(type);

        ArrayList<HashMap<String, String>> records = new ArrayList<>();
        recordStream.forEach(record -> records.add(record.getColumnValues()));
        int lines_written = 0;

        for (int i = 0; i < records.size() / lineLimit; i++)
        {
            writer.write(dest + '/' + name, records.subList(i, i + lineLimit));
            lines_written += lineLimit;
        }
        if (lines_written < records.size())
        {
            writer.write(dest + '/' + name, records.subList(lines_written, records.size()));
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
