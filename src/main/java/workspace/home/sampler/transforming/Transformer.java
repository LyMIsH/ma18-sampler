package workspace.home.sampler.transforming;

import java.util.stream.Stream;

public abstract class Transformer {
    public abstract Stream<Record> transform(Stream<Record> recordStream);
}
