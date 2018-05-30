package ru.sbt.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.sbt.model.Question;
import ru.sbt.service.Loader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    private Loader loader = new Loader();
    private Importer importer = new Importer();

    private List<Question> questions;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private QuizViewController view;
    private LoadViewController load;
    private MenuController menu;
    private int currentIndex;
    private int attempt;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Quiz");
        initRootLayout();
        showLoadView();
    }

    /**
     * Инициализация "Menu"
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Menu.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            MenuController viewController = loader.getController();
            viewController.setMainApp(this);
            menu = viewController;
            primaryStage.show();

        } catch (IOException e) {
            throw new QuizeException(e.getMessage());
        }
    }

    /**
     * Инициализация "LoadView"
     */
    private void showLoadView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/LoadView.fxml"));
            AnchorPane loadView = (AnchorPane) loader.load();
            rootLayout.setCenter(loadView);
            LoadViewController viewController = loader.getController();
            viewController.setMainApp(this);
            load = viewController;
        } catch (IOException e) {
            throw new QuizeException("" + e.getMessage());
        }
    }

    /**
     * Инициализация "QuizView"
     */
    private void showQuizView() {
        currentIndex = 0;
        attempt = attempt + 1;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/QuizView.fxml"));
            AnchorPane quizView = (AnchorPane) loader.load();
            rootLayout.setCenter(quizView);
            QuizViewController viewController = loader.getController();
            viewController.setMainApp(this);
            view = viewController;
            showQuiz(currentIndex);
        } catch (IOException e) {
            throw new QuizeException(e.getMessage());
        }
    }

    public void resumeQuiz() {
        showQuizView();
    }

    public void restartQuiz() {
        currentIndex = 0;
        showQuizView();
    }

    /**
     * Загрузка теста и его начало
     *
     * @throws Exception если файл не найден
     */
    public void loadQuiz() throws Exception {
        questions = loader.load(importer.getFilePath());
        //TODO исправить баг с первым нулевым илементом
        questions.remove(0);
        Collections.shuffle(questions);
        showQuizView();
    }


    /**
     * Метод для постепенной подгруки вопросов
     * @param index номер вопроса
     * @throws QuizeException если тест вышел за пределы
     */
    private void showQuiz(int index) {
		view.setProgress(currentIndex / (double) questions.size());
		if (index < questions.size())
			view.showQuiz(questions.get(currentIndex));
		else
			throw new QuizeException("Тест вышел за прелелы: " + index + " из " + questions.size());
    }

    /**
     * Метод для перехода на новый вопрос
     *
     * @param pick is the answer given for the last question
     */
    public void stepQuiz(String pick) {
		if (currentIndex < questions.size()){
            currentIndex = currentIndex + 1;
			showQuiz(currentIndex);
		} else {
			showResult();
		}
    }

    /**
     * Метод для вывода результата теста
     */
    private void showResult() {
		showLoadView();
		menu.enableRestartMenuItem();
		//load.showResult(model.getScore(), model.getTotalScore(), attempt);
    }


}
