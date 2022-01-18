package workspace.home.sampler.writing;

import workspace.home.sampler.modules.Record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class SizeLimiter extends FileLimiter {
    @Override
    public void write(String dest, String name, Writer writer, int sizeLimitBytes, Stream<Record> recordStream) throws IOException {
        int fileNum = 1;
        final long MARGIN = 100;
        int startIndex = 0;
        int endIndex = 1;

        List<HashMap<String, String>> records = new ArrayList<>();
        recordStream.parallel().forEach(record -> records.add(record.getColumnValues()));
        while (endIndex != records.size())
        {
            File file = writeThread(writer, dest + "/" + name + fileNum, records.subList(startIndex, endIndex));
            if (file.length() > sizeLimitBytes - MARGIN)
            {
                fileNum++;
                startIndex = endIndex;
            }
            endIndex++;
        }
    }

    private static File writeThread(Writer writer, String dest, List<HashMap<String, String>> data)
    {
        File file;
        try
        {
            file = writer.write(dest, data);
            return file;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
