package workspace.home.sampler.parsing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

public class CsvParser extends Parser{
    @Override
    public Stream<Record> parse(String file) throws IOException {
        String columnVal;
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            for (int i = 0; i < record.size(); i++) {
                columnVal = record.get(i);
            }
        }

        return null;
    }
}
