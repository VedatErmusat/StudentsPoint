import java.util.ArrayList;
import java.util.Scanner;

public class StudentsPointConsole {
    // Ders, öğrenci ve not bilgilerini saklamak için ArrayList'ler tanımlanır.
    private ArrayList<String[]> dersListesi = new ArrayList<>();
    private ArrayList<String[]> ogrenciListesi = new ArrayList<>();
    private ArrayList<String[]> notListesi = new ArrayList<>();
    // Kullanıcıdan girdi almak için Scanner kullanılır.
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Uygulama başlatılır.
        new StudentsPointConsole().run();
    }

    public void run() {
        // Ana menü sonsuz bir döngü içinde çalışır.
        while (true) {
            System.out.println("Seçenekler:");
            System.out.println("1: Yeni Ders Ekle");
            System.out.println("2: Yeni Öğrenci Ekle");
            System.out.println("3: Dersleri Listele");
            System.out.println("4: Öğrencileri Listele");
            System.out.println("5: Not Ekle");
            System.out.println("6: Notları Listele");
            System.out.println("7: Çıkış");

            // Kullanıcıdan seçim yapması istenir.
            String secim = scanner.nextLine();

            // Kullanıcının seçimine göre ilgili metod çağrılır.
            switch (secim) {
                case "1":
                    yeniDersEkle();
                    break;
                case "2":
                    yeniOgrenciEkle();
                    break;
                case "3":
                    listeleDers();
                    break;
                case "4":
                    listeleOgrenci();
                    break;
                case "5":
                    notEkle();
                    break;
                case "6":
                    listeleNot();
                    break;
                case "7":
                    // Uygulama sonlandırılır.
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    // Geçersiz seçim durumunda kullanıcı bilgilendirilir.
                    System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
    }

    private void yeniDersEkle() {
        // Yeni ders eklemek için kullanıcıdan ders kodu ve başlığı alınır.
        System.out.print("Ders Kodu: ");
        String dersKodu = scanner.nextLine();
        System.out.print("Ders Başlığı: ");
        String dersBasligi = scanner.nextLine();
        // Alınan bilgiler ders listesine eklenir.
        dersListesi.add(new String[]{dersKodu, dersBasligi});
        System.out.println("Ders başarıyla eklendi.");
    }

    private void yeniOgrenciEkle() {
        // Yeni öğrenci eklemek için kullanıcıdan öğrenci numarası, adı ve soyadı alınır.
        System.out.print("Öğrenci No: ");
        String ogrenciNo = scanner.nextLine();
        System.out.print("Öğrenci Adı: ");
        String ogrenciAd = scanner.nextLine();
        System.out.print("Öğrenci Soyadı: ");
        String ogrenciSoyad = scanner.nextLine();
        // Alınan bilgiler öğrenci listesine eklenir.
        ogrenciListesi.add(new String[]{ogrenciNo, ogrenciAd, ogrenciSoyad});
        System.out.println("Öğrenci başarıyla eklendi.");
    }

    private void listeleDers() {
        // Dersler listelenir.
        System.out.println("Ders Listesi:");
        for (String[] ders : dersListesi) {
            System.out.println("Ders Kodu: " + ders[0] + ", Ders Başlığı: " + ders[1]);
        }
    }

    private void listeleOgrenci() {
        // Öğrenciler listelenir.
        System.out.println("Öğrenci Listesi:");
        for (String[] ogrenci : ogrenciListesi) {
            System.out.println("Öğrenci No: " + ogrenci[0] + ", Adı: " + ogrenci[1] + ", Soyadı: " + ogrenci[2]);
        }
    }

    private void notEkle() {
        // Not eklemek için kullanıcıdan öğrenci numarası, ders kodu, yıl ve not alınır.
        System.out.print("Öğrenci No: ");
        String ogrenciNo = scanner.nextLine();
        System.out.print("Ders Kodu: ");
        String dersKodu = scanner.nextLine();
        System.out.print("Yıl: ");
        String yil = scanner.nextLine();
        System.out.print("Not: ");
        String not = scanner.nextLine();

        // Ders kodu ve öğrenci numarasının mevcut olup olmadığını kontrol ederiz.
        boolean dersBulundu = false;
        boolean ogrenciBulundu = false;

        for (String[] ders : dersListesi) {
            if (ders[0].equals(dersKodu)) {
                dersBulundu = true;
                break;
            }
        }

        for (String[] ogrenci : ogrenciListesi) {
            if (ogrenci[0].equals(ogrenciNo)) {
                ogrenciBulundu = true;
                break;
            }
        }

        // Eğer ders ve öğrenci mevcutsa not listesine eklenir.
        if (dersBulundu && ogrenciBulundu) {
            notListesi.add(new String[]{ogrenciNo, dersKodu, yil, not});
            System.out.println("Not başarıyla eklendi.");
        } else {
            // Aksi halde hata mesajı gösterilir.
            System.out.println("Not eklenemedi. Lütfen geçerli bir ders kodu ve öğrenci numarası girin.");
        }
    }

    private void listeleNot() {
        // Notlar listelenir.
        System.out.println("Not Listesi:");
        for (String[] not : notListesi) {
            System.out.println("Öğrenci No: " + not[0] + ", Ders Kodu: " + not[1] + ", Yıl: " + not[2] + ", Not: " + not[3]);
        }
    }
}
