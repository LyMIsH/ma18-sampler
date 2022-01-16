package workspace.home.sampler;

import workspace.home.sampler.modules.MadaRepRecord;
import workspace.home.sampler.parsing.CsvParser;
import workspace.home.sampler.parsing.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Parser csvParser = new CsvParser();
        try {
            csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
