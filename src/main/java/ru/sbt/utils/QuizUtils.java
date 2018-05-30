package ru.sbt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbt.model.Answer;
import ru.sbt.model.Question;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            LOGGER.error("Файл " + filePath + " не найден", fnfe);
        } catch (IOException ioe) {
            LOGGER.error("Ошибка ", ioe);
        }
        return String.valueOf(stringBuilder);
    }

    public static List<Question> parse(String text) {
        List<Question> questions = new ArrayList<>();
        Question question;
        String[] quastions = text.split("Вопрос ");
        for (String quastion : quastions) {
            question = new Question();
            String[] lines = quastion.split("\n");
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].startsWith("№")) {
                    question.setNumber("Вопрос " + lines[i]);
                }
                if (lines[i].startsWith("<")) {
                    i++;
                    String description = "";
                    while (!lines[i].startsWith(">")) {
                        description += lines[i] + "\n";
                        i++;
                    }
                    question.setDescription(description);
                }
                if (lines[i].startsWith("-") || lines[i].startsWith("x")) {
                    String type = lines[i];
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
                    question.getAnswerVariants().add(answer);
                }
                if (lines[i].startsWith("!")) {
                    String help = "";
                    while (i < lines.length) {
                        help += lines[i] + "\n";
                        i++;
                    }
                    question.setHelp(help.substring(1));
                    break;
                }
            }
            questions.add(question);
        }
        return questions;
    }
}
