package visuaal;

import abi.Koordinaat;
import maailm.Maailm;
import tegelased.Tegelane;

import java.util.HashMap;

public class Kuvaja {
    private static int xAknaSuurus = 60;
    private static int yAknaSuurus = 30;

    /**
     * Genereerib maailma pildi, kombineerides sõneks kõik nähtavad ruudud
     * @param maailm sisendmaailm
     * @return sõne kujul pilt
     */
    private static char[][] hangiPilt(Maailm maailm) {
        char[][] maastik = maailm.hangiMaastik();
        char[][] pilt = new char[yAknaSuurus][xAknaSuurus];
        for (int y = 0; y < yAknaSuurus; y++) {
            for (int x = 0; x < xAknaSuurus; x++) {
                int pildiY = y + maailm.hangiMangija().hangiKoordinaat().y - yAknaSuurus / 2;
                int pildiX = x + maailm.hangiMangija().hangiKoordinaat().x - xAknaSuurus / 2;
                pilt[y][x] = (maailm.hangiMaastikuKohtVoiNull(pildiX, pildiY) == Character.MIN_VALUE)
                        ? ' ' : maailm.hangiMaastikuKoht(pildiX, pildiY);
            }
        }
        HashMap<Koordinaat, Tegelane> tegelased = maailm.hangiTegelased();
        for (Koordinaat k : tegelased.keySet()) {
            Tegelane tegelane = tegelased.get(k);
            if (tegelane == null) continue;
            int pildiY = tegelane.hangiKoordinaat().y - maailm.hangiMangija().hangiKoordinaat().y + yAknaSuurus / 2;
            int pildiX = tegelane.hangiKoordinaat().x - maailm.hangiMangija().hangiKoordinaat().x + xAknaSuurus / 2;
            if (pildiX >= xAknaSuurus || pildiY >= yAknaSuurus || pildiX < 0 || pildiY < 0)
                continue;
            pilt[pildiY][pildiX] = tegelane.hangiSymbol();
        }
        return pilt;
    }

    /**
     * Tühjendab ekraani
     */
    public static void kustuta() {
        // töötab linuxis
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // "töötab" mujal ka
        //System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void kuva(Maailm maailm) {
        char[][] pilt = hangiPilt(maailm);
        for (int i = 0; i < pilt.length; i++) {
            System.out.println(pilt[i]);
        }
        System.out.println();
    }
}
