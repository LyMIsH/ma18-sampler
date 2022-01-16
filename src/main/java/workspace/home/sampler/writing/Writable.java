package workspace.home.sampler.writing;

import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.stream.Stream;

public interface Writable {
    void write(String destFolder, Stream<Record> recordStream) throws IOException;
}
