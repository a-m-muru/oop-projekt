import abi.Koordinaat;
import maailm.Maailm;
import tegelased.Mangija;
import tegelased.Tegelane;
import visuaal.Kuvaja;

import java.util.Scanner;

/**
 * Põhiline klass, mille kaudu toimub muuhulgas mängija suhtlemine mänguga
 */
public class Mang {
    private boolean jookseb = false;
    private long sammudMoodunud;
    private long viimaneUuendus;
    private Maailm maailm;
    private Tegelane[] joosevad;
    private final Scanner silm = new Scanner(System.in);
    private long algusaeg;

    /**
     * Seab mängu mängimiseks valmis
     */
    public void alusta() {
        algusaeg = System.currentTimeMillis();
        this.maailm = new Maailm(500, 500);
        Mangija mangija = new Mangija(maailm, 50, 50);
        maailm.seaMangija(mangija);
     // testimiseks mingid

        int tegelasteArv = 10000;
        joosevad = new Tegelane[tegelasteArv];
        for (int i = 0; i < tegelasteArv; i++) {
            Tegelane tegelane = new Tegelane(
                    maailm,
                    (int)(Math.random() * maailm.hangiSuurusX()),
                    (int)(Math.random() * maailm.hangiSuurusY())
            );
            tegelane.seaSymbol('Ö');
            maailm.lisaTegelane(tegelane);
            joosevad[i] = tegelane;
        }

        jookseb = true;
        while (jookseb) {
            pohiTsykkel(true);
        }
    }

    /**
     * Mängu põhitsükkel.
     * @param sisendiga tõeväärtus, kas peaks sisendit kontrollima.
     */
    private void pohiTsykkel(boolean sisendiga) {
        for (Tegelane tegelane : joosevad) {
            Koordinaat suund = new Koordinaat((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
            tegelane.muudaPos(suund);
        }
        if (sisendiga) {
            Kuvaja.kustuta();
            Kuvaja.kuva(maailm);
            System.out.printf("moodunud %d sammu\n", sammudMoodunud - viimaneUuendus);
            viimaneUuendus = sammudMoodunud;
            String n = silm.nextLine();
            haldaSisendit(n);
            return;
        }
        sammudMoodunud++;
    }

    /**
     * Tegeleb sisendiga.
     * @param sisend sisend sõne kujul
     */
    private void haldaSisendit(String sisend) {
        String[] kasklused = sisend.split(" ");
        for (String s : kasklused) {
            teeMidagi(s);
            pohiTsykkel(false);
        }
    }

    /**
     * Sisenditükkidele vastava tegevuse tegemine
     * @param n sisend
     */
    private void teeMidagi(String n) {
        switch (n) {
            case "d" -> maailm.hangiMangija().muudaPos(new Koordinaat(1, 0));
            case "a" -> maailm.hangiMangija().muudaPos(new Koordinaat(-1, 0));
            case "w" -> maailm.hangiMangija().muudaPos(new Koordinaat(0, -1));
            case "s" -> maailm.hangiMangija().muudaPos(new Koordinaat(0, 1));
        }
    }
}
