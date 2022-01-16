package workspace.home.sampler;

import workspace.home.sampler.parsing.CsvParser;
import workspace.home.sampler.parsing.Parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Parser csvParser = new CsvParser();
        try {
            csvParser.parse("src/main/resources/MadaReports.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
