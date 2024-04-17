package ee.ut.mangfx;

import ee.ut.mangfx.main.Mang;
import ee.ut.mangfx.visuaal.Kuvaja;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        System.out.println("tere");

    }

    @Override
    public void start(Stage lava) {

        BorderPane juur = new BorderPane();
        HBox hkast = new HBox();
        VBox sonumidJaAsjad = new VBox();

        Mang mang = new Mang();
        Canvas louend = new Canvas(720, 720);
        Label sonumisilt = new Label();
        Kuvaja kuvaja = new Kuvaja(louend, sonumisilt);
        mang.seaKuvaja(kuvaja);
        mang.alusta(louend);

        sonumidJaAsjad.getChildren().add(sonumisilt);
        hkast.getChildren().add(louend);
        hkast.getChildren().add(sonumidJaAsjad);
        juur.setCenter(hkast);

        Scene s = new Scene(juur,1280, 720);
        lava.setScene(s);
        s.addEventHandler(KeyEvent.KEY_PRESSED, mang::seaNupp);
        lava.show();

    }
}
