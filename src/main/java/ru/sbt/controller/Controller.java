package ru.sbt.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.model.QuizeQuastion;
import ru.sbt.service.QuizeServise;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Контроллер
 */
@Component
public class Controller extends Application {
    @Autowired
    private QuizeServise quizeServise = new QuizeServise();
    private List<QuizeQuastion> quizeQuastions;
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
            loader.setLocation(Controller.class.getResource("/Menu.fxml"));
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
            loader.setLocation(Controller.class.getResource("/LoadView.fxml"));
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
            loader.setLocation(Controller.class.getResource("/QuizView.fxml"));
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
        quizeQuastions = quizeServise.getAllQuize();
        //TODO исправить баг с первым нулевым илементом
        quizeQuastions.remove(0);
        Collections.shuffle(quizeQuastions);
        showQuizView();
    }


    /**
     * Метод для постепенной подгруки вопросов
     * @param index номер вопроса
     * @throws QuizeException если тест вышел за пределы
     */
    private void showQuiz(int index) {
		view.setProgress(currentIndex / (double) quizeQuastions.size());
		if (index < quizeQuastions.size())
			view.showQuiz(quizeQuastions.get(currentIndex));
		else
			throw new QuizeException("Тест вышел за прелелы: " + index + " из " + quizeQuastions.size());
    }

    /**
     * Метод для перехода на новый вопрос
     *
     * @param pick is the answer given for the last question
     */
    public void stepQuiz(String pick) {
		if (currentIndex < quizeQuastions.size()){
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
