package workspace.home.sampler.parsing;

import java.util.stream.Stream;

public abstract class Parser {
    public abstract Stream<Record> parse(String file);
}
