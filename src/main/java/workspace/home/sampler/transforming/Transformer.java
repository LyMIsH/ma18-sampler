package workspace.home.sampler.transforming;

import workspace.home.sampler.modules.Record;

import java.util.stream.Stream;

public abstract class Transformer {
    public abstract Stream<Record> transform(Stream<Record> recordStream);
}
