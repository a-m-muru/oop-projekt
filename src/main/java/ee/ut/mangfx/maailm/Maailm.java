package ee.ut.mangfx.maailm;

import ee.ut.mangfx.abi.Koordinaat;
import ee.ut.mangfx.abi.Sonumid;
import ee.ut.mangfx.tegelased.Mangija;
import ee.ut.mangfx.tegelased.Tegelane;

import java.util.HashMap;

/**
 * Mänguala, mis hoiab viiteid tegelastele ja punktidele ning sisaldab ruudustikku (maastikku)
 */
public class Maailm {
    private char[][] maastik;
    private HashMap<Long, Punkt> tegelased = new HashMap<>();
    private HashMap<Long, Punkt> esemed = new HashMap<>();
    private Mangija mangija;

    public char[][] hangiMaastik() {
        return maastik;
    }

    public void seaMaastikuKoht(int x, int y, char taht) {
        if (!onMootmetes(x, y))
            throw new RuntimeException("koordinaadid maastiku mõõtmetest väljas");
        maastik[y][x] = taht;
    }

    public char hangiMaastikuKoht(int x, int y) {
        if (!onMootmetes(x, y))
            throw new RuntimeException("koordinaadid maastiku mõõtmetest väljas");
        return maastik[y][x];
    }

    public char hangiMaastikuKohtVoiNull(int x, int y) {
        if (!onMootmetes(x, y))
            return Character.MIN_VALUE;
        return maastik[y][x];
    }

    public boolean onMootmetes(int x, int y) {
        return !(x < 0 || x >= hangiSuurusX() || y < 0 || y >= hangiSuurusY());
    }

    public int hangiSuurusX() {
        return maastik[0].length;
    }

    public int hangiSuurusY() {
        return maastik.length;
    }

    public Mangija hangiMangija() {
        return mangija;
    }

    public Maailm(int suurus) {
        looMaastik(suurus, suurus);
    }

    public Maailm(int x, int y) {
        looMaastik(x, y);
    }

    public void seaMangija(Mangija mangija) {
        this.mangija = mangija;
        lisaTegelane(mangija);
    }
    
    private void looMaastik(int x, int y) {
        this.maastik = new char[y][x];
        for (int i = 0; i < maastik.length; i++) {
            for (int j = 0; j < maastik[i].length; j++) {
                maastik[i][j] = (Math.random() < 0.75) ? '.' : ' ';
            }
            //Arrays.fill(maastik[i], (Math.random() < 0.75) ? '.' : ' ');
        }
    }

    public void lisaTegelane(Tegelane tegelane) {
        if (tegelased.get(koordinaatArvuks(tegelane.hangiKoordinaat())) != null)
            throw new RuntimeException("tegelast ei saa sinna panna (teine on eees)");
        tegelased.put(koordinaatArvuks(tegelane.hangiKoordinaat()), tegelane);
    }

    public void kustutaTegelane(Tegelane tegelane) {
        tegelased.remove(koordinaatArvuks(tegelane.hangiKoordinaat()));
    }

    public Tegelane hangiTegelane(Koordinaat kus) {
        long mis = koordinaatArvuks(kus);
        return (Tegelane) tegelased.get(mis);
    }

    public Tegelane hangiTegelane(int x, int y) {
        return hangiTegelane(new Koordinaat(x, y));
    }

    public HashMap<Long, Punkt> hangiTegelased() {
        return tegelased;
    }

    public void lisaEse(Ese ese) {
        esemed.put(koordinaatArvuks(ese.hangiKoordinaat()), ese);
    }

    public void kustutaEse(Ese ese) {
        esemed.remove(koordinaatArvuks(ese.hangiKoordinaat()));
    }

    public Ese hangiEse(Koordinaat asukoht) {
        return (Ese) esemed.get(koordinaatArvuks(asukoht));
    }

    public Ese hangiEse(int x, int y) {
        return hangiEse(new Koordinaat(x, y));
    }

    public HashMap<Long, Punkt> hangiEsemed() {
        return esemed;
    }

    /**
     * Teisendab Koordinaat-tüüpi objekti väärtused arvuks, et neid oleks võimalik HashMapis võrrelda.
     * @param koordinaat sisendkoordinaat
     * @return arv, mis koordinaatidest saadud
     */
    public long koordinaatArvuks(Koordinaat koordinaat) {
        return (long) koordinaat.y * hangiSuurusY() + koordinaat.x;
    }

    public long koordinaatArvuks(int x, int y) {
        return (long) y * hangiSuurusY() + x;
    }
}
