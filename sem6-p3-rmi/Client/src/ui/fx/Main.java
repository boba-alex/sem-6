package ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <p>Класс на основе <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html">{@link Application}</a> для запуска клиентского приложения.</p>
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Метод, который вызывает система, когда готова запустить приложение.
     *
     * @param primaryStage основная сцена для данного приложения
     * @throws Exception исключение
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }

    /**
     * Метод для запуска клиентского приложения.
     *
     * @param args параметры, передаваемые аргументами в коммандной строке
     */
    public static void main(String[] args) {
        launch(args);
    }
}
