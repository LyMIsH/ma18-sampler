package workspace.home.sampler.transforming;

import health_care_provider.HealthCareInfoProvider;
import health_care_provider.errors.InvalidIdException;
import health_care_provider.models.PersonInsured;
import workspace.home.sampler.modules.Record;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LabTestAdditions extends Transformer{
    @Override
    public Stream<Record> transform(Stream<Record> recordStream) {
        HealthCareInfoProvider healthCareInfoProvider = new HealthCareInfoProvider();
        Supplier<Stream<Record>> streamSupplier =
                () -> recordStream;

        streamSupplier.get().forEach(record -> {
            record.addNewColumn("JOIN_DATE", "JOIN_DATE");
            record.addNewColumn("HEALTH_CARE_ID", "HEALTH_CARE_ID");
            record.addNewColumn("HEALTH_CARE_NAME", "HEALTH_CARE_NAME");

            if (!record.getColumnValue("IDNum").equals("IDNum"))
            {
                try
                {
                    int idNum = Integer.parseInt(record.getColumnValue("IDNum"));
                    int idType =  Integer.parseInt(record.getColumnValue("IDType"));
                    if (idType != 0 || idNum > 99999999 && idNum < 1000000000) {
                        PersonInsured person = healthCareInfoProvider.fetchInfo(idNum, idType);
                        record.addColumn("JOIN_DATE", person.getJoinDate().toString());
                        record.addColumn("HEALTH_CARE_ID", String.valueOf(person.getHealthCareId()));
                        record.addColumn("HEALTH_CARE_NAME", person.getHealthCareName());
                    }
                }
                catch (InvalidIdException e)
                {
                    e.printStackTrace();
                }
            }
        });
        return streamSupplier.get();
    }
}
