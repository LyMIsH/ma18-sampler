package workspace.home.sampler.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MadaRepRecord extends Record {

    public MadaRepRecord()
    {
        super();
        this.columns = new ArrayList<>(Arrays.asList("MDACODE",
                "IDNum", "IDType", "FirstName", "LastName", "City", "Street", "BuildingNumber",
                "Barcode", "GetDate", "TakeDate", "ResultDate"));
    }
    @Override
    public MadaRepRecord create()
    {
        return new MadaRepRecord();
    }
}
