package workspace.home.sampler.transforming;

import workspace.home.sampler.modules.Record;

import java.util.stream.Stream;

public class LabTestAdditions extends Transformer{
    @Override
    public Stream<Record> transform(Stream<Record> recordStream) {
        recordStream.forEach(record -> {record.addNewColumn("JOIN_DATE");
                                        record.addNewColumn("HEALTH_CARE_ID");
                                        record.addNewColumn("HEALTH_CARE_NAME");});
    }
}
