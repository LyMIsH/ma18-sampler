package workspace.home.sampler.transforming;

import workspace.home.sampler.modules.Record;

import java.util.stream.Stream;

public class DefaultTransformer extends Transformer {
    @Override
    public Stream<Record> transform(Stream<Record> recordStream) {
        return recordStream;
    }
}
