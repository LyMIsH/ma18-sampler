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

    public void addColumn(String index, String value) throws IOException {
        if (!this.columns.contains(index))
        {
            throw new IOException("Column named '" + index + "' does not exist.");
        }
        this.columnValues.put(index, value);
    }

    public String getColumnValue(String columnName)
    {
        return this.columnValues.get(columnName);
    }

    public String getColumnValue(int index)
    {
        String columnName = this.columns.get(index);
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
