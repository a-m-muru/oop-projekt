package visuaal;

import maailm.Maailm;
import tegelased.Tegelane;

import java.util.Arrays;

public class Kuvaja {

    private static char[][] hangiPilt(Maailm maailm) {
        char[][] maastik = maailm.hangiMaastik();
        char[][] pilt = new char[maastik.length][maastik[0].length];
        for (int y = 0; y < pilt.length; y++) {
            pilt[y] = Arrays.copyOf(maastik[y], maastik[y].length);
        }
        Tegelane[] tegelased = maailm.hangiTegelased();
        for (Tegelane tegelane : tegelased) {
            if (tegelane == null) continue;
            pilt[tegelane.hangiYPos()][tegelane.hangiXPos()] = tegelane.getSymbol();
        }
        return pilt;
    }

    private static void kustuta() {
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void kuva(Maailm maailm) {
        char[][] pilt = hangiPilt(maailm);
        kustuta();
        for (int i = 0; i < pilt.length; i++) {
            System.out.println(pilt[i]);
        }
    }
}
