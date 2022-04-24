package sirinler;
// Sara Naji 180201120  -  Kubilay Kaplan 180201123
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class EnKisaYol {

    ArrayList<Dugum> hepsi = new ArrayList();
    ArrayList<Dugum> silinenler = new ArrayList();
    public int matris_x = 11;
    public int matris_y = 13;

    //4 yöne de ilerleyebilmek için olan toplama islemleri
    int topla2[] = {0, -1, 1, 0};
    int topla1[] = {-1, 0, 0, 1};

    //ugranip ugranmadigini kontrol eder
    public boolean kontrol(int harita[][], boolean ugrananlar[][], int kontrol1, int kontrol2) {
        return (kontrol1 >= 0) && (kontrol1 < matris_x) && (kontrol2 >= 0) && (kontrol2 < matris_y)
                && harita[kontrol1][kontrol2] == 1 && !ugrananlar[kontrol1][kontrol2];
    }

    public void kisayolubul(int harita[][], int i, int j, int x, int y) {
        boolean[][] ugrananlar = new boolean[matris_x][matris_y];
        Queue<Dugum> sira = new ArrayDeque<>();

        //ilk yeri ugranmis olarak aliyoruz
        ugrananlar[i][j] = true;
        sira.add(new Dugum(i, j, 0));
        hepsi.add(new Dugum(i, j, 0));

        //enkisayi intin en büyük degeri yaptik ki kisa yolu kolay bulabilelim
        int enkisauzaklik = Integer.MAX_VALUE;

        //siranin bos olmama durumunda en kisa yolu bulma islemleri
        while (!sira.isEmpty()) {

            //ekledigimiz siradan birer birer çekme islemi
            Dugum node = sira.poll();
            //çevirde kontrol edebilmek için silinenlere de ekliyoruz
            silinenler.add(node);
            //yer belirleme islemi
            i = node.x;
            j = node.y;
            int uzaklik = node.dist;
            //ilk ulastigi zaman breakliyor
            if (i == x && j == y) {
                //koordinatlar tutarsa en kisa yolu düzenliyor
                enkisauzaklik = uzaklik;
                //enkisayoldan ulasildiysa breakler
                break;
            }

            //4 yön oldugu içine for 4 kez döner
            for (int k = 0; k < 4; k++) {
                if (kontrol(harita, ugrananlar, i + topla1[k], j + topla2[k])) {
                    //ugranmis mi kontrol islemi burada yapilir
                    ugrananlar[i + topla1[k]][j + topla2[k]] = true;
                    sira.add(new Dugum(i + topla1[k], j + topla2[k], uzaklik + 1));
                    hepsi.add(new Dugum(i + topla1[k], j + topla2[k], uzaklik + 1));
                }
            }
        }
        // enkisauzaklik degismisse 
        if (enkisauzaklik != Integer.MAX_VALUE) {

        } //enkisauzaklik degismemisse
        else {
            System.out.print("Ulasilamiyor");
        }
    }

    public ArrayList<Dugum> cevir(int[][] harita, int ix, int iy, int kx, int ky) throws IOException {

        kisayolubul(harita, kx, ky, ix, iy);

        ArrayList<Dugum> asil = new ArrayList();
        int t = 0;
        int i = silinenler.size() - 1;
        asil.add(silinenler.get(silinenler.size() - 1));
        while (i != 0 && t < silinenler.size()) {
            for (int j = i - 1; j >= 0; j--) {
                if ((silinenler.get(i).x + 1 == silinenler.get(j).x && silinenler.get(i).y == silinenler.get(j).y)
                        || (silinenler.get(i).x - 1 == silinenler.get(j).x && silinenler.get(i).y == silinenler.get(j).y)
                        || (silinenler.get(i).y + 1 == silinenler.get(j).y && silinenler.get(i).x == silinenler.get(j).x)
                        || (silinenler.get(i).y - 1 == silinenler.get(j).y && silinenler.get(i).x == silinenler.get(j).x)) {
                    asil.add(silinenler.get(j));
                    i = j;
                }

            }
            t++;

        }

        silinenler.clear();
        return asil;
    }
}
