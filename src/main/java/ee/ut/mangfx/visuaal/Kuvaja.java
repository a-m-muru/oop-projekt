package ee.ut.mangfx.visuaal;

import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Loks;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Punkt;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Kuvaja {
    public static final int X_AKNA_SUURUS = 60;
    public static final int Y_AKNA_SUURUS = 60;

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
    private static PildiVarviPaar hangiPilt(Maailm maailm) {
        char[][] maastik = maailm.hangiMaastik();
        char[][] pilt = new char[Y_AKNA_SUURUS][X_AKNA_SUURUS];
        int[][] varvid = new int[Y_AKNA_SUURUS][X_AKNA_SUURUS];
        for (int y = 0; y < Y_AKNA_SUURUS; y++) {
            for (int x = 0; x < X_AKNA_SUURUS; x++) {
                int pildiY = y + maailm.hangiMangija().hangiKoordinaat().y - Y_AKNA_SUURUS / 2;
                int pildiX = x + maailm.hangiMangija().hangiKoordinaat().x - X_AKNA_SUURUS / 2;
                char osa = (maailm.hangiMaastikuKohtVoiNull(pildiX, pildiY) == Character.MIN_VALUE)
                        ? ' ' : maailm.hangiMaastikuKoht(pildiX, pildiY);
                pilt[y][x] = osa;
                if (osa == '#')
                    varvid[y][x] = 0x000044;
            }
        }
        HashMap<Long, Punkt> tegelased = maailm.hangiTegelased();
        for (Punkt p : tegelased.values()) {
            Tegelane tegelane = (Tegelane) p;
            if (tegelane == null || tegelane.kasPeidetud()) continue;
            int pildiY = tegelane.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + Y_AKNA_SUURUS / 2;
            int pildiX = tegelane.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + X_AKNA_SUURUS / 2;
            if (pildiX >= X_AKNA_SUURUS || pildiY >= Y_AKNA_SUURUS || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = tegelane.hangiSymbol();
            varvid[pildiY][pildiX] = p.hangiVarv();
        }

        HashMap<Long, Punkt> esemed = maailm.hangiEsemed();
        for (Punkt k : esemed.values()) {
            Ese ese = (Ese) k;
            if (ese == null || ese.kasPeidetud()) continue;
            int pildiY = ese.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + Y_AKNA_SUURUS / 2;
            int pildiX = ese.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + X_AKNA_SUURUS / 2;
            if (pildiX >= X_AKNA_SUURUS || pildiY >= Y_AKNA_SUURUS || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = ese.hangiSymbol();
            varvid[pildiY][pildiX] = k.hangiVarv();
        }
        HashMap<Long, Punkt> loksud = maailm.hangiLoksud();
        for (Punkt k : loksud.values()) {
            Loks loks = (Loks) k;
            if (loks == null || loks.kasPeidetud()) continue;
            int pildiY = loks.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + Y_AKNA_SUURUS / 2;
            int pildiX = loks.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + X_AKNA_SUURUS / 2;
            if (pildiX >= X_AKNA_SUURUS || pildiY >= Y_AKNA_SUURUS || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = loks.hangiSymbol();
            varvid[pildiY][pildiX] = loks.hangiVarv();
        }
        return new PildiVarviPaar(pilt, varvid);
    }

    public Color varvArvust(int varv) {
        //https://stackoverflow.com/a/39613661
        int blue = varv & 0xFF;
        int green = (varv >> 8) & 0xFF;
        int red = (varv >> 16) & 0xFF;
        // jagame 256ga
        return Color.color(red * 0.00390625, green * 0.00390625, blue * 0.00390625);
    }

    public void kuva(Maailm maailm) {
        PildiVarviPaar paar = hangiPilt(maailm);
        char[][] pilt = paar.pilt;
        int[][] varvid = paar.varvid;
        Mangija mangija = maailm.hangiMangija();
        GraphicsContext gc = louend.getGraphicsContext2D();
        gc.setFont(Font.font("Monospaced", 15));
        // tühjenda ekraan enne joonistamist
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, louend.getWidth(), louend.getHeight());
        for (int i = 0; i < pilt.length; i++) {
            //gc.strokeText(new String(pilt[i]), 8, i * 12 + 8);
            for (int j = 0; j < pilt[i].length; j++) {
                Color varv = varvArvust(varvid[i][j]);
                gc.setStroke(varv);
                gc.strokeText(Character.toString(pilt[i][j]), j * 12 + 4, i * 12 + 8);
            }
            //gc.fillRect(Math.random() * 100, Math.random() * 100, Math.random() * 200, Math.random() * 50);
        }
        // sonumid
        if (mangija != null) {
            kuvasilt.setText(
                    "elud: " + "❤".repeat(mangija.hangiElud())
                    + ((mangija.hangiElud() <= 0) ? "\nMäng läbi!\nVajuta nuppu F et uuesti proovida" : "")
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

    private static class PildiVarviPaar {
        public char[][] pilt;
        public int[][] varvid;

        public PildiVarviPaar(char[][] pilt, int[][] varvid) {
            this.pilt = pilt;
            this.varvid = varvid;
        }
    }
}
