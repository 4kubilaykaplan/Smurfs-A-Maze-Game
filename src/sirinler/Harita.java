package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Harita extends JPanel implements KeyListener {

    public int[][] harita = new int[11][13];
    int x = 5;
    int y = 6;
    ArrayList<Dusman> Kotuler = new ArrayList();
    boolean asagi, yukari, sag, sol;
    boolean gameBoole = true;
    int mSkor;
    int secim;
    int adim;
    Scanner scan1;
    int SirineX = 12 * 40;
    int SirineY = 7 * 40;
    int SirineXX = 40;
    int SirineYY = 40;
    DosyaOku o1;
    boolean galip = false;

    public Harita() throws IOException {

        Scanner klavye = new Scanner(System.in);

        while (true) {
            System.out.println("1-TembelSirin\n2-GozlukluSirin");
            secim = klavye.nextInt();
            if (secim == 1) {
                mSkor = new TembelSirin().getSkor();
                adim = 1;
                break;
            } else if (secim == 2) {
                mSkor = new GozlukluSirin().getSkor();
                adim = 2;
                break;
            } else {
                System.out.println("gecersiz islem");
            }

        }

        Yapilacaklar();

    }

    public void Yapilacaklar() {
        try {
            DosyaOku1();
        } catch (IOException ex) {
            Logger.getLogger(Harita.class.getName()).log(Level.SEVERE, null, ex);
        }

        o1 = new DosyaOku();
        try {
            o1.oku();
        } catch (IOException ex) {
            Logger.getLogger(Harita.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.Kotuler = o1.Kotuler;

        if (gameBoole == true) {
            for (int i = 0; i < Kotuler.size(); i++) {
                if (Kotuler.get(i).getAd().equalsIgnoreCase("azman")) {
                    switch (Kotuler.get(i).getKapi()) {
                        case "A":
                            Kotuler.get(i).l.setX(3);
                            Kotuler.get(i).l.setY(0);
                            break;

                        case "B":
                            Kotuler.get(i).l.setX(10);
                            Kotuler.get(i).l.setY(0);
                            break;
                        case "C":
                            Kotuler.get(i).l.setX(0);
                            Kotuler.get(i).l.setY(5);
                            break;
                        case "D":
                            Kotuler.get(i).l.setX(3);
                            Kotuler.get(i).l.setY(10);
                            break;
                    }
                }
                if (Kotuler.get(i).getAd().equalsIgnoreCase("gargamel")) {

                    switch (Kotuler.get(i).getKapi()) {
                        case "A":
                            Kotuler.get(i).l.setX(3);
                            Kotuler.get(i).l.setY(0);
                            break;

                        case "B":
                            Kotuler.get(i).l.setX(10);
                            Kotuler.get(i).l.setY(0);
                            break;
                        case "C":
                            Kotuler.get(i).l.setX(0);
                            Kotuler.get(i).l.setY(5);
                            break;
                        case "D":
                            Kotuler.get(i).l.setX(3);
                            Kotuler.get(i).l.setY(10);
                            break;

                    }

                }

            }
            gameBoole = false;
        }
    }

    public void skorHesapla(int i) {
        if (Kotuler.get(i).getAd().equalsIgnoreCase("Gargamel")) {
            mSkor -= 15;
        } else {
            mSkor -= 5;
        }
    }

    public void DosyaOku1() throws FileNotFoundException, IOException {
        File file = new File("Harita.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        int x = reader.read();
        int i1 = 0, j1 = 0;
        while (x != -1) {
            if (x == 48) {
                harita[i1][j1] = x - 48;
                j1++;
                if (j1 == 13) {
                    i1++;
                    j1 = 0;
                }
            } else if (x == 49) {
                harita[i1][j1] = x - 48;
                j1++;
                if (j1 == 13) {
                    i1++;
                    j1 = 0;
                }

            }

            x = reader.read();

        }

        reader.close();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == e.VK_DOWN) {
            asagi = true;
        }
        if (e.getKeyCode() == e.VK_UP) {
            yukari = true;
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            sag = true;
        }
        if (e.getKeyCode() == e.VK_LEFT) {
            sol = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage imggozluklu = null;
        BufferedImage imgsirine = null;
        BufferedImage imgtembel = null;
        BufferedImage imgazman = null;
        BufferedImage imggargamel = null;
        BufferedImage imggameover = null;
        try {
            imggozluklu = ImageIO.read(new File("gozluklu.png"));
            imgtembel = ImageIO.read(new File("tembel.png"));
            imgazman = ImageIO.read(new File("azman.png"));
            imggargamel = ImageIO.read(new File("gargamel.png"));
            imgsirine = ImageIO.read(new File("sirine.png"));
            imggameover = ImageIO.read(new File("gameover.png"));
        } catch (IOException e) {
        }

        for (int j = 0; j < 13; j++) {
            for (int i = 0; i < 11; i++) {
                if (harita[i][j] == 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * 40, 0 + i * 40, 40, 40);

                } else if (harita[i][j] == 1) {
                    g.setColor(Color.gray);
                    g.fillRect(j * 40, 0 + i * 40, 40, 40);

                }

            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                g.drawRect(0, 0 + i * 40, 40 + j * 40, 40);
                g.setColor(Color.white);
            }
        }
        g.drawRect(0, 0, 40, 40);

        for (int j = Kotuler.size() - 1; j >= 0; j--) {
            try {
                Kotuler.get(j).EnKisa(harita, Kotuler.get(j).l.getY(), Kotuler.get(j).l.getX(), x, y);
            } catch (IOException ex) {
                Logger.getLogger(Harita.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 1; i < Kotuler.get(j).kisaYol.size() - 1; i++) {
                g.setColor(Color.red);
                g.fillRect(Kotuler.get(j).kisaYol.get(i).y * 40, Kotuler.get(j).kisaYol.get(i).x * 40, 40, 40);

            }
        }

        //oyuncu karakterleri cizimi
        if (galip == false) {
            if (secim == 1) {
                g.drawImage(imgtembel, y * 40, x * 40, 40, 40, this);
            } else if (secim == 2) {
                g.drawImage(imggozluklu, y * 40, x * 40, 40, 40, this);
            }

            //dusman karakter cizimi ve oyun akisi
            for (int i = 0; i < Kotuler.size(); i++) {
                if (Kotuler.get(i).getAd().equalsIgnoreCase("gargamel")) {
                    g.drawImage(imggargamel, Kotuler.get(i).l.getX() * 40, Kotuler.get(i).l.getY() * 40, 40, 40, this);
                } else if (Kotuler.get(i).getAd().equalsIgnoreCase("azman")) {
                    g.drawImage(imgazman, Kotuler.get(i).l.getX() * 40, Kotuler.get(i).l.getY() * 40, 40, 40, this);

                }
            }
            g.drawImage(imgsirine, 12 * 40, 7 * 40, 40, 40, this);
            g.drawString("Skor:" + mSkor, 6 * 40, 12 * 40 - 20);
        }
        if (galip == true) {
            JOptionPane.showMessageDialog(this, "Tebrikler Kazandiniz");
            System.exit(0);
        }

        if (mSkor == 0 || mSkor < 0) {
            repaint();
            g.drawImage(imggameover, 0, 0, 14 * 40 - 23, 12 * 40, this);
        }

        if (galip == false) {

            if (yukari) {
                boolean gecici = true;
                if (harita[x - 1][y] != 0 && x - adim > 0) {
                    if (secim == 2) {
                        x -= 2;
                    } else {
                        x--;
                    }
                    for (int i = 0; i < Kotuler.size(); i++) {
                        if (((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX()))) {
                            x = 5;
                            y = 6;
                            gameBoole = true;
                            Yapilacaklar();
                            skorHesapla(i);
                            gecici = false;
                            repaint();
                            break;
                        }
                    }

                }
                for (int i = 0; i < Kotuler.size(); i++) {
                    if (gecici == false) {
                        break;
                    }
                    try {
                        Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).y);
                        Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).x);
                        if (Kotuler.get(i).getAd().equalsIgnoreCase("Gargamel")) {
                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }
                            g.drawImage(imggargamel, Kotuler.get(i).l.getX() * 40, Kotuler.get(i).l.getY() * 40, 40, 40, this);
                            Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).y);
                            Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).x);
                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException t) {
                        x = 5;
                        y = 6;
                        gameBoole = true;
                        Yapilacaklar();
                        skorHesapla(i);
                        repaint();
                        break;
                    } finally {
                        Kotuler.get(i).kisaYol.clear();
                    }
                }
                yukari = false;
            } else if (asagi) {
                boolean gecici = true;
                if (harita[x + 1][y] != 0 && x + adim < 10) {
                    if (secim == 2) {
                        x += 2;
                    } else {
                        x++;
                    }
                    for (int i = 0; i < Kotuler.size(); i++) {
                        if (((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX()))) {
                            x = 5;
                            y = 6;
                            gameBoole = true;
                            Yapilacaklar();
                            skorHesapla(i);
                            gecici = false;
                            repaint();
                            break;
                        }
                    }

                }
                for (int i = 0; i < Kotuler.size(); i++) {
                    if (gecici == false) {
                        break;
                    }
                    try {
                        Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).y);
                        Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).x);
                        if (Kotuler.get(i).getAd().equalsIgnoreCase("Gargamel")) {
                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }
                            g.drawImage(imggargamel, Kotuler.get(i).l.getX() * 40, Kotuler.get(i).l.getY() * 40, 40, 40, this);
                            Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).y);
                            Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).x);
                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }

                        }
                    } catch (ArrayIndexOutOfBoundsException t) {

                        x = 5;
                        y = 6;
                        gameBoole = true;
                        Yapilacaklar();
                        skorHesapla(i);
                        repaint();
                        break;

                    } finally {
                        Kotuler.get(i).kisaYol.clear();
                    }
                }

                asagi = false;
            } else if (sag) {
                boolean gecici = true;
                if (x == 7 && y + adim >= 12 && y + adim <= 12) {
                    repaint();
                    System.out.println("Tebrikler kazandiniz");
                    SirineX = 0;
                    SirineY = 0;
                    SirineXX = 13 * 40 - 38;
                    SirineYY = 12 * 40 - 10;
                    if (secim == 2) {
                        y += 2;
                    } else {
                        y++;
                    }
                    galip = true;

                } else if (y != 12 && harita[x][y + 1] != 0 && y + adim < 12) {
                    if (secim == 2) {
                        y += 2;
                    } else {
                        y++;
                    }
                    for (int i = 0; i < Kotuler.size(); i++) {
                        if (((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX()))) {
                            x = 5;
                            y = 6;
                            gameBoole = true;
                            Yapilacaklar();
                            skorHesapla(i);
                            gecici = false;
                            repaint();
                            break;
                        }
                    }

                }

                for (int i = 0; i < Kotuler.size(); i++) {
                    if (gecici == false) {
                        break;
                    }
                    try {
                        Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).y);
                        Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).x);
                        if (Kotuler.get(i).getAd().equalsIgnoreCase("Gargamel")) {
                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }
                            g.drawImage(imggargamel, Kotuler.get(i).l.getX() * 40, Kotuler.get(i).l.getY() * 40, 40, 40, this);
                            Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).y);
                            Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).x);
                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException t) {

                        x = 5;
                        y = 6;
                        gameBoole = true;
                        Yapilacaklar();
                        skorHesapla(i);
                        repaint();
                        break;
                    } finally {
                        Kotuler.get(i).kisaYol.clear();
                    }
                }

                sag = false;

            } else if (sol) {
                boolean gecici = true;
                if (harita[x][y - 1] != 0 && y - adim > 0) {
                    if (secim == 2) {
                        y -= 2;
                    } else {
                        y--;
                    }
                    for (int i = 0; i < Kotuler.size(); i++) {
                        if (((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX()))) {
                            x = 5;
                            y = 6;
                            gameBoole = true;
                            Yapilacaklar();
                            skorHesapla(i);
                            gecici = false;
                            repaint();
                            break;
                        }
                    }
                }

                for (int i = 0; i < Kotuler.size(); i++) {

                    if (gecici == false) {
                        break;
                    }
                    try {
                        Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).y);
                        Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 2).x);
                        if (Kotuler.get(i).getAd().equalsIgnoreCase("Gargamel")) {

                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;

                            }
                            g.drawImage(imggargamel, Kotuler.get(i).l.getX() * 40, Kotuler.get(i).l.getY() * 40, 40, 40, this);
                            Kotuler.get(i).l.setX(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).y);
                            Kotuler.get(i).l.setY(Kotuler.get(i).kisaYol.get(Kotuler.get(i).kisaYol.size() - 3).x);

                            if ((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX())) {
                                x = 5;
                                y = 6;
                                gameBoole = true;
                                Yapilacaklar();
                                skorHesapla(i);
                                repaint();
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException t) {

                        x = 5;
                        y = 6;
                        gameBoole = true;
                        Yapilacaklar();
                        skorHesapla(i);
                        repaint();
                        break;
                    } finally {
                        Kotuler.get(i).kisaYol.clear();
                    }

                }
                sol = false;
            }
        }
        //oyun bitimi
        for (int i = 0; i < Kotuler.size(); i++) {
            if (((x == Kotuler.get(i).l.getY()) && (y == Kotuler.get(i).l.getX()))) {
                x = 5;
                y = 6;
                gameBoole = true;
                Yapilacaklar();
                skorHesapla(i);
                repaint();
            }
        }

        repaint();

    }

}
