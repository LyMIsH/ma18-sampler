package workspace.home.sampler.writing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jsonWriter extends Writer {
    @Override
    public File write(String dest, List<HashMap<String, String>> records) throws IOException {
        String ext = this.getExt();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(dest + ext);
        objectMapper.writeValue(file, records);
        return file;
    }

    @Override
    protected String getExt() {
        return ".json";
    }

}
