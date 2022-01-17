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
    public Stream<Record> parse(String file, Record recordType) throws IOException {
        ArrayList<Record> recordsList = new ArrayList<>();
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        boolean firstLine = true;

        for (CSVRecord record: records)
        {
            if (!firstLine)  // The first line of a csv contains column names, not needed.
            {
                recordsList.add(getRecordObject(record, recordType));
            }
            firstLine = false;
        }

        return recordsList.stream();
    }


    private Record getRecordObject(CSVRecord record, Record recordType)
    {
        Record recordObject = recordType.create();

        for (int i = 0; i < record.size(); i++)
        {
            recordObject.addColumn(i, record.get(i));
        }

        return recordObject;
    }
}
