package ru.airo.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @FXML
    private TextArea textArea;

    @FXML
    private VBox vbox;

    @FXML
    private Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initMenu(primaryStage);
    }

    private void initMenu(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Quiz");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/views/Main.fxml"));
        AnchorPane rootLayout = fxmlLoader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        textArea.setText("Hello sdsd sssssssssssssssssssssssss World! ");
        vbox.getChildren().add(new Button());
    }
}

