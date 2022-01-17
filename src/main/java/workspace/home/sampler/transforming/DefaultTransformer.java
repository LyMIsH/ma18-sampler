package workspace.home.sampler.transforming;

import java.util.stream.Stream;

public class DefaultTransformer extends Transformer {
    @Override
    public Stream<Record> transform(Stream<Record> recordStream) {
        return recordStream;
    }
}
