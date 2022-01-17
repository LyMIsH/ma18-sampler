package workspace.home.sampler.writing;

import com.fasterxml.jackson.databind.ObjectMapper;
import workspace.home.sampler.modules.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class jsonWriter implements Writable{
    @Override
    public void write(String dest, List<HashMap<String, String>> records) throws IOException {
        String ext =".json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(dest + ext), records);
    }
}
