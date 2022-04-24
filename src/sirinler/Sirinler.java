package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

public class Sirinler extends JFrame {

    public Sirinler() throws IOException {
        this.setSize(new Dimension(14 * 40 - 23, 13 * 40 - 10));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Harita cizim1 = new Harita();
        cizim1.addKeyListener(cizim1);
        cizim1.setFocusable(true);
        this.getContentPane().add(cizim1);
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Sirinler();

    }

}
