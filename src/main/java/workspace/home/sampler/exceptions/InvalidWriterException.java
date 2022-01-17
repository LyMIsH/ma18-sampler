package workspace.home.sampler.exceptions;

import java.io.IOException;

public class InvalidWriterException extends IOException {
    public InvalidWriterException(String msg)
    {
        super(msg);
    }
}
