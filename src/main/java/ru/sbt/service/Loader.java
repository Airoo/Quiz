package ru.sbt.service;

import static ru.sbt.utils.QuizUtils.parse;
import static ru.sbt.utils.QuizUtils.readFile;

import ru.sbt.model.Question;

import java.util.List;

public class Loader {
    public List<Question> load(String filePath){
        String questions = readFile(filePath);
        return parse(questions);
    }
}
