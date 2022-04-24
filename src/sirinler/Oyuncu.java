package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
public class Oyuncu extends Karakter {

    private int skor;

    public Oyuncu() {

    }

    public Oyuncu(String ad, String tur, Lokasyon l, int ID) {
        super(ad, tur, l, ID);
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

}
