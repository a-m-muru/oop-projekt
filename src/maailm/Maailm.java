package maailm;

import abi.Abi;
import abi.Koordinaat;
import tegelased.Mangija;
import tegelased.Tegelane;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Mänguala, mis hoiab viiteid tegelastele ja punktidele ning sisaldab ruudustikku (maastikku)
 */
public class Maailm {
    private char[][] maastik;
    private HashMap<Koordinaat, Tegelane> tegelased = new HashMap<>();
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
        return ! (x < 0 || x >= hangiSuurusX() || y < 0 || y >= hangiSuurusY());
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
        tegelased.put(tegelane.hangiKoordinaat(), tegelane);
    }

    public Tegelane hangiTegelane(Koordinaat kus) {
        return tegelased.get(kus);
    }

    public Tegelane hangiTegelane(int x, int y) {
        return hangiTegelane(new Koordinaat(x, y));
    }

    public HashMap<Koordinaat, Tegelane> hangiTegelased() {
        return tegelased;
    }
}
