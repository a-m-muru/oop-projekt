package ee.ut.mangfx.main;

import ee.ut.mangfx.Main;
import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.maailm.Maailm;
import ee.ut.mangfx.tegelased.*;
import ee.ut.mangfx.visuaal.Kuvaja;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Põhiline klass, mille kaudu toimub muuhulgas mängija suhtlemine mänguga
 */
public class Mang extends AnimationTimer {
    public boolean kasJookseb() {
        return jookseb;
    }

    public void seaJookseb(boolean jookseb) {
        this.jookseb = jookseb;
    }

    private boolean jookseb = false;
    private long viimaneUuendus;
    private Maailm maailm;
    private List<Tegelane> joosevad = new ArrayList<>();
    private List<Tegelane> lisada = new ArrayList<>();
    private long algusaeg;
    private Kuvaja kuvaja;
    private static List<String> sonumid = new ArrayList<>();
    private KeyEvent nupp;


    /**
     * Seab mängu mängimiseks valmis
     */
    public void alusta() {
        algusaeg = System.currentTimeMillis();
        this.maailm = new Maailm(500, 500, this);
        Mangija mangija = new Mangija(maailm, 50, 50);
        maailm.seaMangija(mangija);
        // testimiseks
        looLimuseid(400);
        looLuukered(100);
        looKummitused(100);
        Sonumid.kustutaSonumid();
        Sonumid.lisaSonum("Senine kõrgeim tulemus on " + korgeimTulemusFailist());
        jookseb = true;
        start();
    }

    public boolean onMangijaVaatevaljas(Koordinaat kus) {
        Koordinaat mangijaPos = hangiMangija().hangiKoordinaat();
        Koordinaat vahe = new Koordinaat(mangijaPos.x - kus.x, mangijaPos.y - kus.y);
        return vahe.x >= -(Kuvaja.X_AKNA_SUURUS / 2) && vahe.x <= Kuvaja.X_AKNA_SUURUS / 2 &&
                vahe.y >= -(Kuvaja.Y_AKNA_SUURUS / 2) && vahe.y <= Kuvaja.Y_AKNA_SUURUS / 2;
    }

    /**
     * Mängu põhitsükkel.
     */
    private void pohiTsykkel() {
        joosevad.addAll(lisada);
        lisada.clear();
        for (Tegelane tegelane : joosevad) {
            if (onMangijaVaatevaljas(tegelane.hangiKoordinaat()) && tegelane.hangiElud() > 0)
                tegelane.teeMidagi();
        }
        kuvaja.kuva(maailm);
    }

    public void tegevused() {
        if (nupp == null) return;
        if (maailm.hangiMangija().hangiElud() <= 0) {
            if (nupp.getCode() == KeyCode.F) {
                salvestaTulemused();
                alusta();
            }
            return;
        }

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
        if (System.nanoTime() - viimaneUuendus > 50000000 && jookseb) {
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
        Koordinaat tegAsukoht = new Koordinaat(50 + (int) (Math.random() * (maailm.hangiSuurusX() - 50)),
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

    public void looLuukered(int arv) {
        for (int i = 0; i < arv; i++) {
            Koordinaat asukoht = hangiSuvalineTegelaseAsukoht();
            Luukere luukere = new Luukere(maailm, asukoht.x, asukoht.y);
            joosevad.add(luukere);
        }
    }

    public void looKummitused(int arv) {
        for (int i = 0; i < arv; i++) {
            Koordinaat asukoht = hangiSuvalineTegelaseAsukoht();
            Kummitus kummitus = new Kummitus(maailm, asukoht.x, asukoht.y);
            joosevad.add(kummitus);
        }
    }

    public void looKivid(int arv) {
        for (int i = 0; i < arv; i++) {
            Koordinaat asukoht = hangiSuvalineTegelaseAsukoht();
            Kivi kivi = new Kivi(maailm, asukoht.x, asukoht.y);
            joosevad.add(kivi);
        }
    }

    public void lisaKivi(Kivi kivi) {
        try {
            wait(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        lisada.add(kivi);
    }

    public void salvestaTulemused() {
        int korgeimTulemus = korgeimTulemusFailist();

        korgeimTulemus = Math.max(maailm.hangiMangija().hangiSkoor(), korgeimTulemus);
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("tulemused.bin"))) {
            dos.writeInt(korgeimTulemus);
        } catch (IOException e) {
            throw new RuntimeException("Faili salvestamisel läks midagi valesti!", e);
        }
    }

    public int korgeimTulemusFailist() {
        if (!Files.exists(Path.of("tulemused.bin")))
            return 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream("tulemused.bin"))) {
            return dis.readInt();
        } catch (IOException e) {
            throw new RuntimeException("Failist lugemisel läks midagi valesti!", e);
        }
    }

    public Mangija hangiMangija() {
        return maailm.hangiMangija();
    }
}
