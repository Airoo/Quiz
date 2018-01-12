package ru.sbt.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.sbt.model.AnswerVariant;
import ru.sbt.model.QuizeQuastion;

import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    public List<QuizeQuastion> parse(String text) {
        List<QuizeQuastion> quizeQuastions = new ArrayList<>();
        QuizeQuastion quizeQuastion;
        String[] quastions = text.split("Вопрос ");
        for (String quastion : quastions) {
            quizeQuastion = new QuizeQuastion();
            String[] lines = quastion.split("\n");
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].startsWith("№")) {
                    quizeQuastion.setNumber("Вопрос " + lines[i]);
                }
                if (lines[i].startsWith("<")) {
                    i++;
                    String description = "";
                    while (!lines[i].startsWith(">")) {
                        description += lines[i]+"\n";
                        i++;
                    }
                    quizeQuastion.setQuastionDescription(description);
                }
                if (lines[i].startsWith("-") || lines[i].startsWith("x")) {
                    String type = lines[i];
                    String quastionValue = "";
                    while (i + 1 < lines.length && !(lines[i + 1].startsWith("-") ||
                            lines[i + 1].startsWith("x") || lines[i + 1].startsWith("!"))) {
                        quastionValue += lines[i];
                        i++;
                    }
                    quastionValue += lines[i]+"\n";
                    AnswerVariant quast = new AnswerVariant();
                    quast.setQuastion(quastionValue.substring(1));
                    if (quastionValue.startsWith("x")) {
                        quast.setAnswer(true);
                    } else {
                        quast.setAnswer(false);
                    }
                    quizeQuastion.getAnswerVariants().add(quast);
                }
                if (lines[i].startsWith("!")) {
                    String help = "";
                    while (i < lines.length) {
                        help += lines[i]+"\n";
                        i++;
                    }
                    quizeQuastion.setHelp(help.substring(1));
                    break;
                }
            }
            quizeQuastions.add(quizeQuastion);
        }
        return quizeQuastions;
    }
}
