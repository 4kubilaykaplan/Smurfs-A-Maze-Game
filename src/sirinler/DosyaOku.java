package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class DosyaOku extends JFrame {

    ArrayList<Dusman> Kotuler = new ArrayList();

    public void oku() throws IOException {

        File file = new File("Harita.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String temp;
        String temp2;
        temp = reader.readLine();

        while (temp != null) {
            if (temp.contains("0") || temp.contains("1")) {
                break;
            }
            int t1;
            int t2;
            t2 = temp.lastIndexOf(':');
            String kapi = temp.substring(t2 + 1);
            t1 = temp.indexOf(',');
            temp2 = temp.substring(9, t1);

            if (temp2.equalsIgnoreCase("azman")) {
                Azman k1 = new Azman();
                k1.setKapi(kapi);
                k1.setAd("azman");
                k1.setTur("kotu");
                Kotuler.add(k1);
            } else if (temp2.equalsIgnoreCase("gargamel")) {

                Gargamel k1 = new Gargamel();
                k1.setKapi(kapi);
                k1.setAd("gargamel");
                k1.setTur("kotu");
                Kotuler.add(k1);
            }

            temp = reader.readLine();
        }
        reader.close();

    }
}
