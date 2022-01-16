package workspace.home.sampler.modules;

import java.util.HashMap;

public class Record {
    HashMap<String, String> columns;

    public Record()
    {
        this.columns = new HashMap<>();
    }

    public void addColumn(String name, String value)
    {
        this.columns.put(name, value);
    }
}
