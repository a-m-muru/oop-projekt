package maailm;

import tegelased.Mangija;
import tegelased.Tegelane;

import java.util.Arrays;

public class Maailm {
    private char[][] maastik;
    private Tegelane[] tegelased = new Tegelane[100];
    private Mangija mangija;

    public char[][] hangiMaastik() {
        return maastik;
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
