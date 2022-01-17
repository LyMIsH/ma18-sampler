package workspace.home.sampler.modules;

import java.util.ArrayList;
import java.util.Arrays;

public class LabTestRecord extends Record {
    public LabTestRecord()
    {
        super();
        this.columns = new ArrayList<>(Arrays.asList("IDNum",
                "IDType", "FirstName", "LastName", "ResultDate", "BirthDate", "Labcode", "StickerNumber",
                "ResultTestCorona", "Variant", "TestType"));
    }

    @Override
    public Record create() {
        return new LabTestRecord();
    }
}
