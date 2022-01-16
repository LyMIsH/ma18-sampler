package workspace.home.sampler.parsing;

import workspace.home.sampler.modules.Record;

import java.io.IOException;
import java.util.stream.Stream;

public abstract class Parser {
    public abstract Stream<Record> parse(String file, Record recordType) throws IOException;
}
