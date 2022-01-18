package workspace.home.sampler;

import workspace.home.sampler.modules.LabTestRecord;
import workspace.home.sampler.modules.MadaRepRecord;
import workspace.home.sampler.modules.Record;
import workspace.home.sampler.parsing.CsvParser;
import workspace.home.sampler.parsing.Parser;
import workspace.home.sampler.transforming.DefaultTransformer;
import workspace.home.sampler.transforming.LabTestAdditions;
import workspace.home.sampler.transforming.Transformer;
import workspace.home.sampler.writing.FileWriter;
import workspace.home.sampler.writing.jsonWriter;
import workspace.home.sampler.writing.xmlWriter;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Parser csvParser = new CsvParser();
        try
        {
            Stream<Record> recordStream = csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord());
            Transformer transformer = new DefaultTransformer();
            transformer.transform(recordStream);
            FileWriter.write("src/main/resources", "testJson", new jsonWriter(), 50000, recordStream);

            recordStream = csvParser.parse("src/main/resources/LabTests.csv", new LabTestRecord());
            transformer = new LabTestAdditions();
            recordStream = transformer.transform(recordStream);
            FileWriter.write("src/main/resources", "testXml",
                    new xmlWriter("labTests", "labTest"), 50000, recordStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
