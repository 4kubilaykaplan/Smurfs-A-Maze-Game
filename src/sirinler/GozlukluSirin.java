package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
public class GozlukluSirin extends Oyuncu {

    public GozlukluSirin() {
        this.setSkor(20);
    }

    public GozlukluSirin(String ad, String tur, Lokasyon l, int ID) {
        super(ad, tur, l, ID);
    }
}
