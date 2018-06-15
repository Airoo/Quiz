package ru.airo.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.airo.service.DataService;
import ru.airo.service.LoaderService;

import java.io.IOException;

public class MainApp extends Application {
    private static final String SETTINGS_FXML_PATH = "/views/tabs/Settings.fxml";
    private static final String TEST_FXML_PATH = "/views/tabs/Test.fxml";
    private static final String TRAIN_FXML_PATH = "/views/tabs/Train.fxml";
    private static final String STATISTICS_FXML_PATH = "/views/tabs/Statistics.fxml";

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    private LoaderService loaderService = new LoaderService();
    private DataService dataService = new DataService();

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
        dataService.setQuestions(loaderService.load());
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

    private static Object loadController(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(path));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.error("Ошибка ", e.getMessage());
        }
        return fxmlLoader.getController();
    }
}

