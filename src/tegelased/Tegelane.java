package tegelased;

import maailm.Maailm;

public class Tegelane {

    public int id;
    public Maailm maailm;
    protected int xPos;
    protected int yPos;
    protected char symbol = '@';

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int hangiXPos() {
        return xPos;
    }

    public void seaXPos(int xPos) {
        this.xPos = xPos;
    }

    public void muudaXPos(int vorra) {
        int liiguKuhu = Math.min(Math.max(this.xPos + vorra, 0), maailm.hangiSuurusX() - 1);
        if (!(maailm.hangiMaastikuKoht(liiguKuhu, yPos) == '#'))
            this.xPos = liiguKuhu;
    }

    public int hangiYPos() {
        return yPos;
    }

    public void seaYPos(int yPos) {
        this.yPos = yPos;
    }

    public void muudaYPos(int vorra) {
        int liiguKuhu = Math.min(Math.max(this.yPos + vorra, 0), maailm.hangiSuurusY() - 1);
        if (!(maailm.hangiMaastikuKoht(xPos, liiguKuhu) == '#'))
            this.yPos = liiguKuhu;
    }

    public Tegelane(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Tegelane() {
        this.xPos = 0;
        this.yPos = 0;
    }
}
