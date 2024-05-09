package ee.ut.mangfx;

import ee.ut.mangfx.main.Mang;
import ee.ut.mangfx.visuaal.Kuvaja;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean kasEsemeteAkenLahti = false;

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
        Label kuvasilt = new Label();
        Label sonumisilt = new Label();
        Button esemedNupp = esemeteNupp(mang);
        Kuvaja kuvaja = new Kuvaja(louend, sonumisilt, kuvasilt);
        mang.seaKuvaja(kuvaja);
        mang.alusta();

        sonumidJaAsjad.getChildren().add(esemedNupp);
        sonumidJaAsjad.getChildren().add(kuvasilt);
        sonumidJaAsjad.getChildren().add(sonumisilt);
        hkast.getChildren().add(louend);
        hkast.getChildren().add(sonumidJaAsjad);
        juur.setCenter(hkast);

        Scene s = new Scene(juur, 1280, 720);
        lava.setScene(s);
        s.addEventHandler(KeyEvent.KEY_PRESSED, mang::seaNupp);
        lava.show();

    }

    private Button esemeteNupp(Mang mang) {
        Button esemedNupp = new Button("Vaata esemeid");
        esemedNupp.setOnMouseClicked(mouseEvent -> {
            if (kasEsemeteAkenLahti) return;
            BorderPane juur2 = new BorderPane();
            ScrollPane sp = new ScrollPane();
            HBox kast = new HBox();
            juur2.setCenter(sp);
            sp.setContent(kast);
            Label esemedSilt = new Label(mang.hangiMangija().esemeteNimekiri());
            kast.getChildren().add(esemedSilt);
            Stage lava2 = new Stage();
            lava2.setScene(new Scene(juur2, 300, 240));
            lava2.setTitle("esemed");
            lava2.setOnCloseRequest(windowEvent -> {
                mang.seaJookseb(true);
                kasEsemeteAkenLahti = false;
                lava2.hide();
            });
            mang.seaJookseb(false);
            lava2.show();
            kasEsemeteAkenLahti = true;
        }); return esemedNupp;
    }
}
