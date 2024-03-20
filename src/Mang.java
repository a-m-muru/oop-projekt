import maailm.Maailm;
import maailm.Tuba;
import netscape.javascript.JSObject;
import tegelased.Mangija;
import tegelased.Tegelane;
import visuaal.Kuvaja;

import java.util.Scanner;

public class Mang {
    private boolean jookseb = false;
    private long sammudMoodunud;
    private long viimaneUuendus;
    private Maailm maailm;
    private Tegelane[] joosevad;
    private final Scanner silm = new Scanner(System.in);
    private long algusaeg;

    public void alusta() {
        algusaeg = System.currentTimeMillis();
        Mangija mangija = new Mangija(25, 5);
        this.maailm = new Maailm(500, 500, mangija);
        maailm.lisaTegelane(mangija);
     // testimiseks mingid
        for (int i = 0; i < 25; i++) {
            Tuba t = new Tuba(
                    maailm,
                    (int)(Math.random() * maailm.hangiSuurusX()),
                    (int)(Math.random() * maailm.hangiSuurusY()),
                    (int)(Math.random() * 30),
                    (int)(Math.random() * 30));
            t.genereeriTuba();
        }
        int tegelasteArv = 100;
        joosevad = new Tegelane[tegelasteArv];
        for (int i = 0; i < tegelasteArv; i++) {
            Tegelane tegelane = new Tegelane(
                    (int)(Math.random() * maailm.hangiSuurusX()),
                    (int)(Math.random() * maailm.hangiSuurusY())
            );
            tegelane.setSymbol('Ö');
            maailm.lisaTegelane(tegelane);
            joosevad[i] = tegelane;
        }

        jookseb = true;
        while (jookseb) {
            pohiTsykkel(true);
        }
    }

    private void pohiTsykkel(boolean sisendiga) {
        for (Tegelane tegelane : joosevad) {
            tegelane.muudaYPos((int) (Math.random() * 3) - 1);
            tegelane.muudaXPos((int) (Math.random() * 3) - 1);
        }
        if (sisendiga) {
            Kuvaja.kustuta();
            System.out.printf("moodunud %d sammu\n", sammudMoodunud - viimaneUuendus);
            viimaneUuendus = sammudMoodunud;
            Kuvaja.kuva(maailm);
            String n = silm.nextLine();
            haldaSisendit(n);
            return;
        }
        sammudMoodunud++;
    }

    private void haldaSisendit(String sisend) {
        String[] kasklused = sisend.split(" ");
        for (String s : kasklused) {
            teeMidagi(s);
            pohiTsykkel(false);
        }
    }

    private void teeMidagi(String n) {
        switch (n) {
            case "d" -> maailm.hangiMangija().muudaXPos(1);
            case "a" -> maailm.hangiMangija().muudaXPos(-1);
            case "w" -> maailm.hangiMangija().muudaYPos(-1);
            case "s" -> maailm.hangiMangija().muudaYPos(1);
        }
    }
}
