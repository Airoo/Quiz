package ru.airo.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

    @FXML
    private CheckBox processing;

    @FXML
    private CheckBox errorAlert;

    @FXML
    private CheckBox statistic;

    @FXML
    private CheckBox timing;

    @FXML
    private ComboBox questionsCount;

    @FXML
    private Tab settings;

    @FXML
    private Tab train;

    @FXML
    private Tab test;

    @FXML
    private Tab statistics;

    @FXML
    private TabPane tabPane;


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

    @FXML
    private void startTest(ActionEvent event) {
        tabPane.getSelectionModel().select(test);
        test.setDisable(false);
        train.setDisable(true);
    }

    @FXML
    private void startTrain(ActionEvent event) {
        tabPane.getSelectionModel().select(train);
        train.setDisable(false);
        test.setDisable(true);
    }
}

