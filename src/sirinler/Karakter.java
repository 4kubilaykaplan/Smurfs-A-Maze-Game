package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
import java.io.IOException;
import java.util.ArrayList;

public abstract class Karakter {

    private String ad;
    private String tur;
    private int ID;
    ArrayList<Dugum> kisaYol;
    Lokasyon l = new Lokasyon();

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String Tur) {
        this.tur = Tur;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Karakter(String ad, String tur, Lokasyon l, int ID) {
        this.ad = ad;
        this.tur = tur;
        this.l = l;
        this.ID = ID;
    }

    public void EnKisa(int[][] harita, int kx, int ky, int ix, int iy) throws IOException {
        EnKisaYol ek1 = new EnKisaYol();

        kisaYol = ek1.cevir(harita, ix, iy, kx, ky);
    }

    public Karakter() {
        kisaYol = new ArrayList();
    }

}
