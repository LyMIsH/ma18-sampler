package workspace.home.sampler;

import workspace.home.sampler.modules.MadaRepRecord;
import workspace.home.sampler.modules.Record;
import workspace.home.sampler.parsing.CsvParser;
import workspace.home.sampler.parsing.Parser;
import workspace.home.sampler.writing.FileWriter;
import workspace.home.sampler.writing.Writable;
import workspace.home.sampler.writing.jsonWriter;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Parser csvParser = new CsvParser();
        try {
            Stream<Record> recordStream = csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord());
            FileWriter.write("src/main/resources", "test", new jsonWriter(), 50000, recordStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
