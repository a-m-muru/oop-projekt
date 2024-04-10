package ee.ut.mangfx;

import ee.ut.mangfx.main.Mang;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("tere");
        Mang mang = new Mang();
        mang.alusta();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
