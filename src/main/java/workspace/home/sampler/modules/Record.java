package workspace.home.sampler.modules;

import java.io.IOException;
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

    public String getColumnValue(String columnName)
    {
        return this.columnValues.get(columnName);
    }

    public void addNewColumn(String name, String value)
    {
        this.columns.add(name);
        this.columnValues.put(name, value);
    }

    public int recordSize()
    {
        return this.columns.size();
    }

    public abstract Record create();
}
