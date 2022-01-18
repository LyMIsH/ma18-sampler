package workspace.home.sampler.transforming;

import workspace.home.sampler.modules.LabTestRecord;
import workspace.home.sampler.modules.MadaRepRecord;
import workspace.home.sampler.modules.PositivePeopleRecord;
import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PositivePeopleMerger extends Transformer {
    @Override
    public Stream<Record> transform(Stream<Record> recordStream) {
        List<Record> recordList = recordStream.toList();
        Supplier<Stream<Record>> streamSupplier = recordList::stream;
        List<Record> finalRecordList = new ArrayList<>();
        streamSupplier.get().parallel().forEach(record -> {
            if (record instanceof LabTestRecord)
            {
                streamSupplier.get().parallel().forEach(record2 -> {
                    if (record2.hashCode() != record.hashCode() && record2 instanceof MadaRepRecord)
                    {
                        if (record.getColumnValue("ResultTestCorona").equals("1") &&
                            record.getColumnValue("StickerNumber").equals(record2.getColumnValue("Barcode")))
                        {
                            PositivePeopleRecord positivePeopleRecord = createRecord((LabTestRecord) record, (MadaRepRecord) record2);
                            finalRecordList.add(positivePeopleRecord);
                        }
                    }
                });
            }
        });

        return finalRecordList.stream();
    }

    private PositivePeopleRecord createRecord(LabTestRecord labTest, MadaRepRecord madaRep)
    {
        PositivePeopleRecord positivePeopleRecord = new PositivePeopleRecord();
        try {
            for (int i = 1; i <= 8; i++) {  // Similar columns till 8'th index.
                positivePeopleRecord.addColumn(i - 1, madaRep.getColumnValue(i));
            }
            positivePeopleRecord.addColumn("BirthDate", labTest.getColumnValue("BirthDate"));
            positivePeopleRecord.addColumn("Labcode", labTest.getColumnValue("Labcode"));
            positivePeopleRecord.addColumn("ResultDate", madaRep.getColumnValue("ResultDate"));
            positivePeopleRecord.addColumn("TakeDate", madaRep.getColumnValue("TakeDate"));
            positivePeopleRecord.addColumn("StickerNumber", labTest.getColumnValue("StickerNumber"));
            positivePeopleRecord.addColumn("ResultTestCorona", labTest.getColumnValue("ResultTestCorona"));
            positivePeopleRecord.addColumn("Variant", labTest.getColumnValue("Variant"));
            positivePeopleRecord.addColumn("TestType", labTest.getColumnValue("TestType"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return positivePeopleRecord;
    }
}
