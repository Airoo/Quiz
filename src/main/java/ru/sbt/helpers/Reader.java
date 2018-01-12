package ru.sbt.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class Reader {
    private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);

    public String readFile(String filePath) {
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
}
