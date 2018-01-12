package ru.sbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbt.helpers.Importer;
import ru.sbt.helpers.Parser;
import ru.sbt.helpers.Reader;
import ru.sbt.model.QuizeQuastion;

import java.util.List;

@Service
public class QuizeServise {

    @Autowired
    private Parser parser = new Parser();

    @Autowired
    private Reader reader = new Reader();

    @Autowired
    private Importer importer = new Importer();


    public List<QuizeQuastion> getAllQuize(){
        String filePath = importer.getFilePath();
        String quizes = reader.readFile(filePath);
        List<QuizeQuastion> quizeQuastions = parser.parse(quizes);
        return quizeQuastions;
    }
}
