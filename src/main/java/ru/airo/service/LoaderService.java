package ru.airo.service;

import ru.airo.model.Question;

import java.io.File;
import java.util.List;

import static ru.airo.utils.QuizUtils.*;

public class LoaderService {
    public List<Question> load(String filePath) {
        String questions = readFile(filePath);
        return parseTxt(questions);
    }

    public List<Question> load() {
        File file = new File("");
        String jsonPath = file.getAbsolutePath() + "/src/data/data.json";
        return parseJson(jsonPath);
    }

    public List<Question> loadFromUrl() {
        return parseJson("https://raw.githubusercontent.com/Airoo/Quiz/master/src/data/data.json");
    }

}
