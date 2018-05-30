package ru.sbt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.sbt.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoaderTest {

    private Loader loader = new Loader();

    @Test
    public void generateJson() throws IOException {
        File file = new File("");
        List<Question> questions = loader.load(file.getAbsolutePath() + "/src/text/test.txt");

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(file.getAbsolutePath() + "/src/text/test.json"), questions);
    }

}