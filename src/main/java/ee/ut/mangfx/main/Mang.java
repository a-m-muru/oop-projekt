package ee.ut.mangfx.main;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Punkt;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;
import ee.ut.mangfx.visuaal.Kuvaja;

import java.util.ArrayList;
import java.util.List;
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
    private static List<String> sonumid = new ArrayList<>();

    /**
     * Seab mängu mängimiseks valmis
     */
    public void alusta() {
        algusaeg = System.currentTimeMillis();
        this.maailm = new Maailm(500, 500);
        Mangija mangija = new Mangija(maailm, 50, 50);
        maailm.seaMangija(mangija);
        // testimiseks

        int tegelasteArv = 10000;
        joosevad = new Tegelane[tegelasteArv];
        for (int i = 0; i < tegelasteArv; i++) {
            Koordinaat tegAsukoht = new Koordinaat((int) (Math.random() * maailm.hangiSuurusX()),
                    (int) (Math.random() * maailm.hangiSuurusY()));
            if (maailm.hangiMaastikuKoht(tegAsukoht.x, tegAsukoht.y) == '#') {
                i--;
                continue;
            }
            Tegelane tegelane = new Tegelane(
                    maailm,
                    (int) (Math.random() * maailm.hangiSuurusX()),
                    (int) (Math.random() * maailm.hangiSuurusY())
            );
            tegelane.seaSymbol('Ö');
            joosevad[i] = tegelane;
        }
        for (int i = 0; i < 1; i++) {
            Ese ese = new Ese(
                    maailm,
                    50,
                    52
            );

            ese.seaSymbol('$');
        }

        jookseb = true;
        while (jookseb) {
            pohiTsykkel(true);
        }
    }

    /**
     * Mängu põhitsükkel.
     *
     * @param sisendiga tõeväärtus, kas peaks sisendit kontrollima.
     */
    private void pohiTsykkel(boolean sisendiga) {
        // debug
        for (Tegelane tegelane : joosevad) {
            int kuhu = (int) (Math.random() * 3) - 1;
            boolean xVoiY = Math.random() < 0.5;
            Koordinaat suund = new Koordinaat(xVoiY ? kuhu : 0, !xVoiY ? kuhu : 0);
            tegelane.muudaPos(suund);
        }
        if (sisendiga) {
            Kuvaja.kustuta();
            Kuvaja.kuva(maailm);
            lisaSonum("Möödunud " + (sammudMoodunud - viimaneUuendus) + " sammu\n");
            Kuvaja.kuvaSeis(sonumid);
            viimaneUuendus = sammudMoodunud;
            String n = silm.nextLine();
            haldaSisendit(n);
            return;
        }
        for (Punkt pt : maailm.hangiEsemed().values()) {
            Ese ese = (Ese) pt;
            ese.kontrolliKasKeegiSeisabPeal();
        }
        sammudMoodunud++;
    }

    /**
     * Tegeleb sisendiga.
     *
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
     *
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

    public static void lisaSonum(String sonum) {
        sonumid.add(sonum);
    }
}