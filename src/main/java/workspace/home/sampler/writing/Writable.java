package workspace.home.sampler.writing;

import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public interface Writable {
    void write(String destFolder, ArrayList<HashMap<String, String>> recordStream) throws IOException;
}
