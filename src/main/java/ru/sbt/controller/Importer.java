package ru.sbt.controller;

import javafx.stage.FileChooser;

import java.io.File;

public class Importer {
    public String getFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import file");
        fileChooser.setInitialFileName("file.json");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}
