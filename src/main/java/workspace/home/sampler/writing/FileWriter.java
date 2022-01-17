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
    public static void write(String dest, String name, Writable writer, long lineLimit, Stream<Record> recordStream) throws IOException {
        ArrayList<HashMap<String, String>> records = new ArrayList<>();
        recordStream.forEach(record -> records.add(record.getColumnValues()));
        writer.write(dest + '/' + name, records);
        /*for (int i = 0; i < records.size() / lineLimit; i++)
        {
            writer
        }*/
    }
}
