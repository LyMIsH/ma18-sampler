package workspace.home.sampler.writing;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public abstract class Writer {
    public abstract void write(String destFolder, List<HashMap<String, String>> recordStream) throws IOException;
}
