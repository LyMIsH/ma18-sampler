package workspace.home.sampler.modules;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Record {
    protected HashMap<String, String> columnValues;
    protected ArrayList<String> columns;

    public Record()
    {
        this.columnValues = new HashMap<>();
        this.columns = new ArrayList<>();
    }

    public void addColumn(int index, String value)
    {
        String columnName = this.columns.get(index);
        this.columnValues.put(columnName, value);
    }

    public abstract Record create();
}
