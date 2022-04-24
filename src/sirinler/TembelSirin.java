package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
public class TembelSirin extends Oyuncu {

    public TembelSirin() {
        this.setSkor(20);
    }

    public TembelSirin(String ad, String tur, Lokasyon l, int ID) {
        super(ad, tur, l, ID);
    }

}
