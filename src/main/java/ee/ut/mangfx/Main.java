package ee.ut.mangfx;

import ee.ut.mangfx.main.Mang;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        System.out.println("tere");

    }

    @Override
    public void start(Stage lava) {

        BorderPane juur = new BorderPane();

        Mang mang = new Mang();
        Canvas louend = new Canvas(720, 720);
        mang.alusta(louend);

        juur.setCenter(louend);

        Scene s = new Scene(juur,1280, 720);
        lava.setScene(s);
        s.addEventHandler(KeyEvent.KEY_PRESSED, mang::seaNupp);
        lava.show();

    }
}
