package ru.airo.service;

import static ru.airo.utils.QuizUtils.parseTxt;
import static ru.airo.utils.QuizUtils.readFile;

import ru.airo.model.Question;

import java.util.List;

public class Loader {
    public List<Question> load(String filePath){
        String questions = readFile(filePath);
        return parseTxt(questions);
    }
}
