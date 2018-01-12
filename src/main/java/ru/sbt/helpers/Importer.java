package ru.sbt.helpers;

import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.model.QuizeQuastion;

import java.io.File;
import java.util.List;

@Component
public class Importer {

    public String getFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Quiz");
        fileChooser.setInitialFileName("QuizFile.txt");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}
