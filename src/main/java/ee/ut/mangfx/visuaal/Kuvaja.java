package ee.ut.mangfx.visuaal;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Punkt;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.List;

public class Kuvaja {
    private static int xAknaSuurus = 60;
    private static int yAknaSuurus = 60;

    private Canvas louend;
    private Label sonumisilt;
    private Label kuvasilt;

    public Kuvaja(Canvas louend, Label sonumisilt, Label kuvasilt) {
        this.louend = louend;
        this.sonumisilt = sonumisilt;
        this.kuvasilt = kuvasilt;
    }

    /**
     * Genereerib maailma pildi, kombineerides sõneks kõik nähtavad ruudud
     *
     * @param maailm sisendmaailm
     * @return sõne kujul pilt
     */
    private static char[][] hangiPilt(Maailm maailm) {
        char[][] maastik = maailm.hangiMaastik();
        char[][] pilt = new char[yAknaSuurus][xAknaSuurus];
        for (int y = 0; y < yAknaSuurus; y++) {
            for (int x = 0; x < xAknaSuurus; x++) {
                int pildiY = y + maailm.hangiMangija().hangiKoordinaat().y - yAknaSuurus / 2;
                int pildiX = x + maailm.hangiMangija().hangiKoordinaat().x - xAknaSuurus / 2;
                pilt[y][x] = (maailm.hangiMaastikuKohtVoiNull(pildiX, pildiY) == Character.MIN_VALUE)
                        ? ' ' : maailm.hangiMaastikuKoht(pildiX, pildiY);
            }
        }
        HashMap<Long, Punkt> tegelased = maailm.hangiTegelased();
        for (Punkt p : tegelased.values()) {
            Tegelane tegelane = (Tegelane) p;
            if (tegelane == null) continue;
            int pildiY = tegelane.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + yAknaSuurus / 2;
            int pildiX = tegelane.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + xAknaSuurus / 2;
            if (pildiX >= xAknaSuurus || pildiY >= yAknaSuurus || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = tegelane.hangiSymbol();
        }

        HashMap<Long, Punkt> esemed = maailm.hangiEsemed();
        for (Punkt k : esemed.values()) {
            Ese ese = (Ese) k;
            if (ese == null) continue;
            int pildiY = ese.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + yAknaSuurus / 2;
            int pildiX = ese.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + xAknaSuurus / 2;
            if (pildiX >= xAknaSuurus || pildiY >= yAknaSuurus || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = ese.hangiSymbol();
        }

        return pilt;
    }

    /**
     * Tühjendab ekraani
     */
    public static void kustuta() {
        // töötab linuxis
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // "töötab" mujal ka (nt Windows)
        //System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void kuva(Maailm maailm) {
        char[][] pilt = hangiPilt(maailm);
        Mangija mangija = maailm.hangiMangija();
        GraphicsContext gc = louend.getGraphicsContext2D();
        gc.setFont(Font.font("Monospaced", 15));
        // tühjenda ekraan enne joonistamist
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, louend.getWidth(), louend.getHeight());
        gc.setFill(Color.BLACK);
        for (int i = 0; i < pilt.length; i++) {
            gc.strokeText(new String(pilt[i]), 8, i * 12 + 8);
            //gc.fillRect(Math.random() * 100, Math.random() * 100, Math.random() * 200, Math.random() * 50);
        }
        // sonumid
        if (mangija != null) {
            kuvasilt.setText(
                    "elud: %d\nskoor: %d".formatted(mangija.hangiElud(), 0)
            );
        }
        sonumisilt.setText(Sonumid.sonumiteSone());
        Sonumid.eluaeg();
    }

    /**
     * Prindib mängu seisu
     *
     * @param sonumid sõnumid, mida kuvada
     */
    public static void kuvaSeis(List<String> sonumid) {
        for (int i = 0; i < sonumid.size(); i++) {
            System.out.printf("%s ", sonumid.get(i));
        }
        System.out.println();
        sonumid.clear();
    }
}
