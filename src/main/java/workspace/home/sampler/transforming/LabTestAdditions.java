package workspace.home.sampler.transforming;

import health_care_provider.HealthCareInfoProvider;
import health_care_provider.errors.InvalidIdException;
import health_care_provider.models.PersonInsured;
import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LabTestAdditions extends Transformer{
    @Override
    public Stream<Record> transform(Stream<Record> recordStream) {
        List<Record> recordList = recordStream.toList();
        HealthCareInfoProvider healthCareInfoProvider = new HealthCareInfoProvider();
        Supplier<Stream<Record>> streamSupplier =
                recordList::stream;

        streamSupplier.get().parallel().forEach(record -> {
            try
            {
                int idNum = Integer.parseInt(record.getColumnValue("IDNum"));
                int idType =  Integer.parseInt(record.getColumnValue("IDType"));
                if (idType != 0 || idNum > 99999999 && idNum < 1000000000) { // Taken from healthCare library
                    PersonInsured person = healthCareInfoProvider.fetchInfo(idNum, idType);
                    record.addNewColumn("JOIN_DATE", person.getJoinDate().toString());
                    record.addNewColumn("HEALTH_CARE_ID", String.valueOf(person.getHealthCareId()));
                    record.addNewColumn("HEALTH_CARE_NAME", person.getHealthCareName());
                }
            }
            catch (InvalidIdException | IOException e)
            {
                e.printStackTrace();
            }
        });

        return streamSupplier.get();
    }
}
