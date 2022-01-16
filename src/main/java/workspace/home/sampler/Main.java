package workspace.home.sampler;

import workspace.home.sampler.modules.MadaRepRecord;
import workspace.home.sampler.parsing.CsvParser;
import workspace.home.sampler.parsing.Parser;
import workspace.home.sampler.writing.Writable;
import workspace.home.sampler.writing.jsonWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Parser csvParser = new CsvParser();
        Writable jsonWriter = new jsonWriter();
        try {
            csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord());
            jsonWriter.write("src/main/resources", csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
