package maailm;

import tegelased.Mangija;
import tegelased.Tegelane;

import java.util.Arrays;

public class Maailm {
    private char[][] maastik;
    private Tegelane[] tegelased = new Tegelane[255];
    private Mangija mangija;

    public char[][] hangiMaastik() {
        return maastik;
    }

    public void seaMaastikuKoht(int x, int y, char taht) {
        if (x < 0 || x >= hangiSuurusX() || y < 0 || y >= hangiSuurusY())
            throw new RuntimeException("koordinaadid maastiku mõõtmetest väljas");
        maastik[y][x] = taht;
    }

    public char hangiMaastikuKoht(int x, int y) {
        if (x < 0 || x >= hangiSuurusX() || y < 0 || y >= hangiSuurusY())
            throw new RuntimeException("koordinaadid maastiku mõõtmetest väljas");
        return maastik[y][x];
    }

    public char hangiMaastikuKohtVoiNull(int x, int y) {
        if (x < 0 || x >= hangiSuurusX() || y < 0 || y >= hangiSuurusY())
            return Character.MIN_VALUE;
        return maastik[y][x];
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

    public Maailm(int suurus, Mangija mangija) {
        looMaastik(suurus, suurus);
        this.mangija = mangija;
        lisaTegelane(mangija);
    }

    public Maailm(int x, int y, Mangija mangija) {
        looMaastik(x, y);
        this.mangija = mangija;
        lisaTegelane(mangija);
    }

    private void looMaastik(int x, int y) {
        this.maastik = new char[y][x];
        for (int i = 0; i < maastik.length; i++) {
            Arrays.fill(maastik[i], '.');
        }
    }

    public void lisaTegelane(Tegelane tegelane) {
        for (int i = 0; i < tegelased.length; i++) {
            if (tegelased[i] == tegelane) {
                return;
            }
            if (tegelased[i] == null) {
                tegelased[i] = tegelane;
                tegelane.id = i;
                tegelane.maailm = this;
                return;
            }
        }
    }

    public Tegelane hangiTegelane(int id) {
        return tegelased[id];
    }

    public Tegelane[] hangiTegelased() {
        return tegelased;
    }
}
