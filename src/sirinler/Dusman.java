package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
public class Dusman extends Karakter {

    public Dusman() {

    }

    public Dusman(String ad, String tur, Lokasyon l, int ID) {
        super(ad, tur, l, ID);
    }
    private String Kapi;

    public String getKapi() {
        return Kapi;
    }

    public void setKapi(String Kapi) {
        this.Kapi = Kapi;
    }

}
