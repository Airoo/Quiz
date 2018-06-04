package ru.airo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.airo.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoaderTest {
    private String pathTxt;
    private String pathJson;
    private Loader loader = new Loader();

    public void setUp() {
        File file = new File("");
        pathTxt = file.getAbsolutePath() + "/src/text/data.txt";
        pathJson = file.getAbsolutePath() + "/src/text/data.josn";
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