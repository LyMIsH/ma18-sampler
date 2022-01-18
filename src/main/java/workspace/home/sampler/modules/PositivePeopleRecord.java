package workspace.home.sampler.modules;

import java.util.ArrayList;
import java.util.Arrays;

public class PositivePeopleRecord extends Record {

    public PositivePeopleRecord()
    {
        super();
        this.columns = new ArrayList<>(Arrays.asList( "IDNum", "IDType", "FirstName", "LastName", "City",
                "Street", "BuildingNumber", "Barcode", "BirthDate", "Labcode", "ResultDate", "TakeDate",
                "StickerNumber", "ResultTestCorona", "Variant", "TestType"));
    }

    @Override
    public Record create() {
        return new PositivePeopleRecord();
    }
}
