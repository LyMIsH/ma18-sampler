package workspace.home.sampler.writing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public abstract class Writer {
    protected String ext = ".txt";

    public abstract File write(String destFolder, List<HashMap<String, String>> recordStream) throws IOException;

    protected abstract String getExt();
}
