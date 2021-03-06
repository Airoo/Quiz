package ru.airo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.airo.model.Answer;
import ru.airo.model.AnswerType;
import ru.airo.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizUtils.class);

    private QuizUtils() {
    }

    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            LOGGER.error("Файл " + filePath + " не найден", fnfe.getMessage());
        } catch (IOException ioe) {
            LOGGER.error("Ошибка ", ioe.getMessage());
        }
        return String.valueOf(stringBuilder);
    }

    public static List<Question> parseTxt(String text) {
        List<Question> questionResults = new ArrayList<>();
        Question questionResult;
        String[] questions = text.split("Вопрос ");
        for (String question : questions) {
            questionResult = new Question();
            String[] lines = question.split("\n");
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].startsWith("№")) {
                    questionResult.setNumber("Вопрос " + lines[i]);
                }
                if (lines[i].startsWith("<")) {
                    i++;
                    String description = "";
                    while (!lines[i].startsWith(">")) {
                        description += lines[i] + "\n";
                        i++;
                    }
                    questionResult.setDescription(description);
                }
                if (lines[i].startsWith("-") || lines[i].startsWith("x")) {
                    String questionValue = "";
                    while (i + 1 < lines.length && !(lines[i + 1].startsWith("-") ||
                            lines[i + 1].startsWith("x") || lines[i + 1].startsWith("!"))) {
                        questionValue += lines[i];
                        i++;
                    }
                    questionValue += lines[i] + "\n";
                    Answer answer = new Answer();
                    answer.setAnswer(questionValue.substring(1));
                    answer.setCorrect(questionValue.startsWith("x"));
                    questionResult.getAnswers().add(answer);
                }
                if (lines[i].startsWith("!")) {
                    String help = "";
                    while (i < lines.length) {
                        help += lines[i] + "\n";
                        i++;
                    }
                    questionResult.setHelp(help.substring(1));
                    break;
                }
            }
            long countCorrectAnswers = questionResult.getAnswers()
                    .stream()
                    .filter(Answer::isCorrect)
                    .count();
            if (countCorrectAnswers > 1) {
                questionResult.setType(AnswerType.MULTIPLE);
            } else if (countCorrectAnswers == 1) {
                questionResult.setType(AnswerType.SINGLE);
            } else {
                questionResult.setType(AnswerType.EMPTY);
            }

            questionResults.add(questionResult);
        }
        return questionResults;
    }

    public static List<Question> parseJson(String jsonPath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = null;
        try {
            questions = objectMapper.readValue(new File(jsonPath), new TypeReference<List<Question>>() {
            });
        } catch (IOException e) {
            LOGGER.error("Ошибка ", e.getMessage());
        }
        return questions;
    }

}
