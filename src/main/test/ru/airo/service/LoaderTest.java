package ru.airo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import ru.airo.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoaderTest {
    private String pathTxt;
    private String pathJson;
    private String pathJsonUrl;
    private Loader loader = new Loader();

    @Before
    public void setUp() {
        File file = new File("");
        pathTxt = file.getAbsolutePath() + "/src/data/data.txt";
        pathJson = file.getAbsolutePath() + "/src/data/data.json";
    }

    @Test
    public void generateJson() throws IOException {
        List<Question> questions = loader.load(pathTxt);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(pathJson), questions);
    }

    @Test
    public void parseJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = objectMapper.readValue(new File(pathJson), new TypeReference<List<Question>>() {
        });
    }

}