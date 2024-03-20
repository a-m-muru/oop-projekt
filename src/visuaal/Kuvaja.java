package visuaal;

import maailm.Maailm;
import tegelased.Tegelane;

import java.util.Arrays;

public class Kuvaja {
    private static int xAknaSuurus = 60;
    private static int yAknaSuurus = 30;

    private static char[][] hangiPilt(Maailm maailm) {
        char[][] maastik = maailm.hangiMaastik();
        char[][] pilt = new char[yAknaSuurus][xAknaSuurus];
        for (int y = 0; y < yAknaSuurus; y++) {
            for (int x = 0; x < xAknaSuurus; x++) {
                int pildiY = y + maailm.hangiMangija().hangiYPos() - yAknaSuurus / 2;
                int pildiX = x + maailm.hangiMangija().hangiXPos() - xAknaSuurus / 2;
                pilt[y][x] = (maailm.hangiMaastikuKohtVoiNull(pildiX, pildiY) == Character.MIN_VALUE)
                        ? ' ' : maailm.hangiMaastikuKoht(pildiX, pildiY);
            }
        }
        Tegelane[] tegelased = maailm.hangiTegelased();
        for (int i = tegelased.length - 1; i >= 0; i--) {
            Tegelane tegelane = tegelased[i];
            if (tegelane == null) continue;
            int pildiY = tegelane.hangiYPos() - maailm.hangiMangija().hangiYPos() + yAknaSuurus / 2;
            int pildiX = tegelane.hangiXPos() - maailm.hangiMangija().hangiXPos() + xAknaSuurus / 2;
            if (pildiX >= xAknaSuurus || pildiY >= yAknaSuurus || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = tegelane.getSymbol();
        }
        return pilt;
    }

    public static void kustuta() {
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void kuva(Maailm maailm) {
        char[][] pilt = hangiPilt(maailm);
        for (int i = 0; i < pilt.length; i++) {
            System.out.println(pilt[i]);
        }
        System.out.println();
    }
}
