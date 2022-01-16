package workspace.home.sampler.parsing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import workspace.home.sampler.modules.Record;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class CsvParser extends Parser{
    @Override
    public Stream<Record> parse(String file, Record type) throws IOException {
        ArrayList<Record> recordsList = new ArrayList<>();
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);


        return null;
    }


    private Record getRecordObject(CSVRecord record, Record recordType)
    {
        Record recordObject = recordType;

        for (int i = 0; i < record.size(); i++)
        {
            record.get(i);
        }
    }
}
