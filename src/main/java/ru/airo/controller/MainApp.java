package ru.airo.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    

    @FXML
    private VBox vbox;

    @FXML
    private Button button;



    @FXML
    private Tab settings;

    @FXML
    private Tab train;

    @FXML
    private Tab test;

    @FXML
    private Tab statisticsTab;

    @FXML
    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initMenu(primaryStage);
//        initSettings();
    }


    private void initMenu(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Quiz");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/views/Main.fxml"));
        AnchorPane rootLayout = fxmlLoader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initSettings() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/views/tabs/Settings.fxml"));
        AnchorPane settingsAnchorPane = null;
        try {
            settingsAnchorPane = fxmlLoader.load();
        } catch (IOException e) {
            new AlertView(e.getMessage());
        }
        settings.setContent(settingsAnchorPane);
//        LoadView viewController = fxmlLoader.getController();
//        viewController.setMainApp(this);
//        load = viewController;
    }

    /*
    private void initMenu(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Quiz");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/views/Menu.fxml"));
        rootLayout = fxmlLoader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        Menu viewController = fxmlLoader.getController();
        viewController.setMainApp(this);
        menu = viewController;
        primaryStage.show();
    }

    private void initLoadView() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/views/LoadView.fxml"));
        AnchorPane loadView = null;
        try {
            loadView = fxmlLoader.load();
        } catch (IOException e) {
            new AlertView(e.getMessage());
        }
        rootLayout.setCenter(loadView);
        LoadView viewController = fxmlLoader.getController();
        viewController.setMainApp(this);
        load = viewController;
    }
     */

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

