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
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.airo.controller.tabs.Settings;
import ru.airo.controller.tabs.Statistics;
import ru.airo.controller.tabs.Test;
import ru.airo.controller.tabs.Train;

import java.io.IOException;

public class MainApp extends Application {
    private static final String SETTINGS_FXML_PATH = "/views/tabs/Settings.fxml";
    private static final String TEST_FXML_PATH = "/views/tabs/Test.fxml";
    private static final String TRAIN_FXML_PATH = "/views/tabs/Train.fxml";
    private static final String STATISTICS_FXML_PATH = "/views/tabs/Statistics.fxml";

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    @Getter
    private Settings settingsController;
    @Getter
    private Test testController;
    @Getter
    private Train trainController;
    @Getter
    private Statistics statisticsController;

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
        settingsController = (Settings) loadController(SETTINGS_FXML_PATH);
        testController = (Test) loadController(TEST_FXML_PATH);
        trainController = (Train) loadController(TRAIN_FXML_PATH);
        statisticsController = (Statistics) loadController(STATISTICS_FXML_PATH);
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

    private Object loadController(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(path));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.error("Ошибка ", e.getMessage());
        }
        return fxmlLoader.getController();
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

