package workspace.home.sampler.writing;

import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.stream.Stream;

public abstract class FileLimiter {
    public abstract void write(String dest, String name, Writer writer, int limit, Stream<Record> recordStream) throws IOException;
}
