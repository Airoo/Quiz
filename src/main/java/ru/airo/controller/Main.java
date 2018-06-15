package ru.airo.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.airo.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    private Importer importer = new Importer();

    private List<Question> questions;
    private BorderPane rootLayout;
    private QuizView view;
    private LoadView load;
    private Menu menu;
    private int currentIndex;
    private int attempt;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initMenu(primaryStage);
        initLoadView();
    }

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

    private void showQuizView() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        currentIndex = 0;
        attempt = attempt + 1;
        fxmlLoader.setLocation(Main.class.getResource("/views/QuizView.fxml"));
        AnchorPane quizView = null;
        try {
            quizView = fxmlLoader.load();
        } catch (IOException e) {
            new AlertView(e.getMessage());
        }
        rootLayout.setCenter(quizView);
        QuizView viewController = fxmlLoader.getController();
        viewController.setMainApp(this);
        view = viewController;
        showQuiz(currentIndex);
    }

    public void resumeQuiz() {
        showQuizView();
    }

    public void restartQuiz() {
        currentIndex = 0;
        showQuizView();
    }

    //TODO Временно путь захардкожен
    public void loadQuiz() {
        //questions = loader.load(importer.getFilePath());
        File file = new File("");
//        questions = loader.load(file.getAbsolutePath() + "/src/text/data.txt");
//        try {
//            questions = QuizUtils.parseJson(file.getAbsolutePath() + "/src/data/data.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Collections.shuffle(questions);
        showQuizView();
    }

    private void showQuiz(int index) {
        view.setProgress(currentIndex / (double) questions.size());
        if (index < questions.size())
            view.showQuiz(questions.get(currentIndex));
        else
            new AlertView("Тест вышел за пределы: " + index + " из " + questions.size());
    }

    public void stepQuiz(String pick) {
        if (currentIndex < questions.size()) {
            currentIndex = currentIndex + 1;
            showQuiz(currentIndex);
        } else {
            showResult();
        }
    }

    private void showResult() {
        initLoadView();
        menu.enableRestartMenuItem();
        //load.showResult(model.getScore(), model.getTotalScore(), attempt);
    }

}
