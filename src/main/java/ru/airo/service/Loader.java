package ru.airo.service;

import ru.airo.model.Question;

import java.io.File;
import java.util.List;

import static ru.airo.utils.QuizUtils.*;

public class Loader {
    public List<Question> load(String filePath) {
        String questions = readFile(filePath);
        return parseTxt(questions);
    }

    public List<Question> load() {
        File file = new File("");
        String json = readFile(file.getAbsolutePath() + "/src/data/data.json");
        return parseJson(json);
    }
}
