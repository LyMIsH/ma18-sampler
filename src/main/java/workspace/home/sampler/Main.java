package workspace.home.sampler;

import workspace.home.sampler.modules.LabTestRecord;
import workspace.home.sampler.modules.MadaRepRecord;
import workspace.home.sampler.modules.Record;
import workspace.home.sampler.parsing.CsvParser;
import workspace.home.sampler.parsing.Parser;
import workspace.home.sampler.transforming.DefaultTransformer;
import workspace.home.sampler.transforming.LabTestAdditions;
import workspace.home.sampler.transforming.PositivePeopleMerger;
import workspace.home.sampler.transforming.Transformer;
import workspace.home.sampler.writing.*;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void level1() throws IOException
    {
        Parser csvParser = new CsvParser();
        FileLimiter limiter = new RecordLimiter();
        Stream<Record> recordStream = csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord());
        Transformer transformer = new DefaultTransformer();
        transformer.transform(recordStream);
        limiter.write("src/main/resources/mada_reports", "testJson", new JsonWriter(), 50000, recordStream);
    }
    public static void level2() throws IOException
    {
        Parser csvParser = new CsvParser();
        FileLimiter limiter = new RecordLimiter();
        Stream<Record> recordStream = csvParser.parse("src/main/resources/LabTests.csv", new LabTestRecord());
        Transformer transformer = new LabTestAdditions();
        recordStream = transformer.transform(recordStream);
        limiter.write("src/main/resources/LABTESTS", "testXml",
                new XmlWriter("labTests", "labTest"), 50000, recordStream);
    }
    public static void level3() throws IOException
    {
        Parser csvParser = new CsvParser();
        FileLimiter limiter = new SizeLimiter();
        Stream<Record> recordStreamMada = csvParser.parse("src/main/resources/MadaReports.csv", new MadaRepRecord());
        Stream<Record> recordStreamLab = csvParser.parse("src/main/resources/LabTests.csv", new LabTestRecord());
        Transformer transformer = new PositivePeopleMerger();
        Stream<Record> recordStream = transformer.transform(Stream.concat(recordStreamMada, recordStreamLab));
        limiter.write("src/main/resources/POSITIVE_CORONA_PEOPLE", "test",
                new JsonWriter(), 20000000, recordStream);
    }
    public static void main(String[] args) {
        try
        {
            level1();
            level2();
            level3();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
