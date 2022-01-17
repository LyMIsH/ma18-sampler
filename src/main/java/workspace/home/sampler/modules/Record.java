package workspace.home.sampler.modules;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Record {
    protected HashMap<String, String> columnValues;
    protected ArrayList<String> columns;

    public HashMap<String, String> getColumnValues() {
        return columnValues;
    }

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

    public void addNewColumn(String name)
    {
        this.columns.add(name);
    }

    public abstract Record create();
}
