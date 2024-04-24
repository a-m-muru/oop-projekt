package ee.ut.mangfx.main;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.maailm.Ese;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.maailm.Punkt;
import ee.ut.mangfx.tegelased.Limus;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;
import ee.ut.mangfx.visuaal.Kuvaja;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Põhiline klass, mille kaudu toimub muuhulgas mängija suhtlemine mänguga
 */
public class Mang extends AnimationTimer {
    private boolean jookseb = false;
    private long viimaneUuendus;
    private Maailm maailm;
    private List<Tegelane> joosevad = new ArrayList<>();
    private long algusaeg;
    private Kuvaja kuvaja;
    private static List<String> sonumid = new ArrayList<>();
    private KeyEvent nupp;


    /**
     * Seab mängu mängimiseks valmis
     */
    public void alusta(Canvas louend) {
        algusaeg = System.currentTimeMillis();
        this.maailm = new Maailm(500, 500);
        Mangija mangija = new Mangija(maailm, 50, 50);
        maailm.seaMangija(mangija);
        // testimiseks
        looLimuseid(100);

        start();
    }

    /**
     * Mängu põhitsükkel.
     */
    private void pohiTsykkel() {
        // debug
        for (Tegelane tegelane : joosevad) {
            tegelane.teeMidagi();
        }
        for (Punkt pt : maailm.hangiEsemed().values()) {
            Ese ese = (Ese) pt;
            ese.kontrolliKasKeegiSeisabPeal();
        }
        kuvaja.kuva(maailm);
    }

    public void tegevused() {
        if (nupp == null) return;

        if (nupp.getCode() == KeyCode.D) {
            maailm.hangiMangija().muudaPos(new Koordinaat(1, 0));
        }
        if (nupp.getCode() == KeyCode.A) {
            maailm.hangiMangija().muudaPos(new Koordinaat(-1, 0));
        }
        if (nupp.getCode() == KeyCode.W) {
            maailm.hangiMangija().muudaPos(new Koordinaat(0, -1));
        }
        if (nupp.getCode() == KeyCode.S) {
            maailm.hangiMangija().muudaPos(new Koordinaat(0, 1));
        }
        nupp = null;
    }

    @Override
    public void handle(long l) {
        if (System.nanoTime() - viimaneUuendus > 50000000) {
            tegevused();
            pohiTsykkel();
            viimaneUuendus = System.nanoTime();
        }
    }

    public void seaNupp(KeyEvent nupp) {
        this.nupp = nupp;
    }

    public void seaKuvaja(Kuvaja kuvaja) {
        this.kuvaja = kuvaja;
    }

    private Koordinaat hangiSuvalineTegelaseAsukoht() {
        Koordinaat tegAsukoht = new Koordinaat((int) (Math.random() * maailm.hangiSuurusX()),
                (int) (Math.random() * maailm.hangiSuurusY()));
        while (maailm.hangiMaastikuKoht(tegAsukoht.x, tegAsukoht.y) == '#' ||
                maailm.hangiTegelane(tegAsukoht.x, tegAsukoht.y) != null) {
            tegAsukoht.x = (int) (Math.random() * maailm.hangiSuurusX());
            tegAsukoht.y = (int) (Math.random() * maailm.hangiSuurusY());
        }
        return tegAsukoht;
    }

    public void looLimuseid(int arv) {
        for (int i = 0; i < arv; i++) {
            Koordinaat asukoht = hangiSuvalineTegelaseAsukoht();
            Limus limus = new Limus(maailm, asukoht.x, asukoht.y);
            joosevad.add(limus);
        }
    }
}
