import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class StudentsPoint extends JFrame implements ItemListener, ActionListener {
    // Farklı seçeneklerin adları
    String[] s2 = {"yeni ders", "yeni öğrenci", "listele ders", "listele öğrenci", "ekle not", "listele not"};
    // Radyo düğmeleri için dizi
    JRadioButton[] r = new JRadioButton[s2.length];
    // BorderFactory nesnesi (kullanılmıyor, kaldırılabilir)
    BorderFactory b;
    // Kullanılacak paneller
    JPanel p1, p2, p3, p4, p5, p6, p7;
    // Etiketler
    JLabel l1, l2, lempty1, lempty2, l3, l4, l5, lempty3, lempty4, l6, l7, l8, l9, lempty5, lempty6;
    // Butonlar
    JButton btn1, btn2, btn3, btn4, btn5, btn6;
    // Metin alanları
    JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9;
    // Veri listeleri
    ArrayList<String[]> dersListesi = new ArrayList<>();
    ArrayList<String[]> ogrenciListesi = new ArrayList<>();
    ArrayList<String[]> notListesi = new ArrayList<>();
    // Metin alanları
    JTextArea textArea1, textArea2, textArea3;
    // Buton grubu
    ButtonGroup btngrp;
    // Katmanlı panel
    JLayeredPane layeredPane;

    public StudentsPoint() {
        // Kullanıcıdan girdi alarak GUI veya Konsol modunu seçme
        String s1 = JOptionPane.showInputDialog(null, "1:Konsol 2:GUI", "Input", JOptionPane.QUESTION_MESSAGE);

        if (s1.equals("1")) {
            new StudentsPointConsole().run();
            System.exit(0); // Konsol uygulaması başlatıldığında GUI uygulamasını sonlandır
        } else if (s1.equals("2")) {
            // Buton grubu oluşturma
            btngrp = new ButtonGroup();
            // Panel oluşturma ve ayarlama
            p1 = new JPanel();
            p1.setBorder(BorderFactory.createTitledBorder("Seçenekler"));
            p1.setBounds(15, 30, 330, 300);
            // Radyo düğmelerini ekleme
            for (int i = 0; i < 6; i++) {
                r[i] = new JRadioButton(s2[i]);
                btngrp.add(r[i]);
                p1.add(r[i]);
                r[i].addItemListener(this);
            }

            // Panel düzeni ayarlama
            p1.setLayout(new GridLayout(6, 1));
            add(p1);

            // Katmanlı panel oluşturma ve ekleme
            layeredPane = new JLayeredPane();
            layeredPane.setBounds(400, 30, 330, 300);
            add(layeredPane);

            // Panelleri oluşturma
            yeniders();
            yeniogrenci();
            listeleDers();
            listeleOgrenci();
            ekleNot();
            listeleNot();

            // Ana pencere ayarları
            setLayout(null);
            setTitle("Öğrenci İşlemleri");
            setSize(800, 400);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    public void yeniders() {
        // Yeni ders paneli oluşturma ve ayarlama
        p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder(s2[0]));
        l1 = new JLabel("Ders Kodu");
        l2 = new JLabel("Ders Başlığı");
        lempty1 = new JLabel();
        lempty2 = new JLabel();
        btn1 = new JButton("Kaydet");
        btn2 = new JButton("Temizle");
        txt1 = new JTextField();
        txt2 = new JTextField();
        btn1.addActionListener(this);
        btn2.addActionListener(this);

        // Panel düzeni ayarlama
        p2.setLayout(new GridLayout(4, 2));
        p2.add(l1);
        p2.add(txt1);
        p2.add(l2);
        p2.add(txt2);
        p2.add(lempty1);
        p2.add(btn1);
        p2.add(lempty2);
        p2.add(btn2);
        p2.setBounds(0, 0, 330, 300);

        // Paneli katmanlı panele ekleme
        layeredPane.add(p2, Integer.valueOf(0));
        p2.setVisible(false);
    }

    public void yeniogrenci() {
        // Yeni öğrenci paneli oluşturma ve ayarlama
        p3 = new JPanel();
        p3.setBorder(BorderFactory.createTitledBorder(s2[1]));
        l3 = new JLabel("Ogrenci No");
        l4 = new JLabel("Ogrenci Ad ");
        l5 = new JLabel("Ogrenci Soyad");
        lempty3 = new JLabel();
        lempty4 = new JLabel();
        btn3 = new JButton("Kaydet");
        btn4 = new JButton("Temizle");
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        txt3 = new JTextField();
        txt4 = new JTextField();
        txt5 = new JTextField();

        // Panel düzeni ayarlama
        p3.setLayout(new GridLayout(5, 2));
        p3.add(l3);
        p3.add(txt3);
        p3.add(l4);
        p3.add(txt4);
        p3.add(l5);
        p3.add(txt5);
        p3.add(lempty3);
        p3.add(btn3);
        p3.add(lempty4);
        p3.add(btn4);
        p3.setBounds(0, 0, 330, 300);

        // Paneli katmanlı panele ekleme
        layeredPane.add(p3, Integer.valueOf(1));
        p3.setVisible(false);
    }

    public void listeleDers() {
        // Dersleri listeleme paneli oluşturma ve ayarlama
        p4 = new JPanel();
        p4.setBorder(BorderFactory.createTitledBorder(s2[2]));
        textArea1 = new JTextArea();
        textArea1.setEditable(false);

        // Ders listesini güncelleme
        guncelleDersListesi();
        p4.setLayout(new BorderLayout());
        p4.add(new JScrollPane(textArea1), BorderLayout.CENTER);
        p4.setBounds(0, 0, 330, 300);

        // Paneli katmanlı panele ekleme
        layeredPane.add(p4, Integer.valueOf(2));
        p4.setVisible(false);
    }

    private void guncelleDersListesi() {
        // Ders listesini metin alanında güncelleme
        StringBuilder sb = new StringBuilder();
        sb.append("Ders ID          Ders Başlık\n");
        for (String[] ders : dersListesi) {
            sb.append(String.format("%s                        %s\n", ders[0], ders[1]));
        }
        textArea1.setText(sb.toString());
    }

    public void yeniDersEkle(String dersID, String dersBaslik) {
        // Yeni ders ekleme ve listeyi güncelleme
        dersListesi.add(new String[]{dersID, dersBaslik});
        guncelleDersListesi();
    }

    public void listeleOgrenci() {
        // Öğrencileri listeleme paneli oluşturma ve ayarlama
        p5 = new JPanel();
        p5.setBorder(BorderFactory.createTitledBorder(s2[2]));
        textArea2 = new JTextArea();
        textArea2.setEditable(false);

        // Öğrenci listesini güncelleme
        guncelleOgrenciListesi();
        p5.setLayout(new BorderLayout());
        p5.add(new JScrollPane(textArea2), BorderLayout.CENTER);
        p5.setBounds(0, 0, 330, 300);

        // Paneli katmanlı panele ekleme
        layeredPane.add(p5, Integer.valueOf(2));
        p5.setVisible(false);
    }

    public void guncelleOgrenciListesi() {
        // Öğrenci listesini metin alanında güncelleme
        StringBuilder sbu = new StringBuilder();
        sbu.append("Ogrenci ID          Ogrenci Adı          Ogrenci Soyadı\n");
        for (String[] ogrenci : ogrenciListesi) {
            sbu.append(String.format("%s                           %s                           %s\n", ogrenci[0], ogrenci[1], ogrenci[2]));
        }
        textArea2.setText(sbu.toString());
    }

    public void yeniOgrenciEkle(String ogrenciID, String ogrenciAd, String ogrenciSoyad) {
        // Yeni öğrenci ekleme ve listeyi güncelleme
        ogrenciListesi.add(new String[]{ogrenciID, ogrenciAd, ogrenciSoyad});
        guncelleOgrenciListesi();
    }

    public void ekleNot() {
        // Not ekleme paneli oluşturma ve ayarlama
        p6 = new JPanel();
        p6.setBorder(BorderFactory.createTitledBorder(s2[4]));
        l6 = new JLabel("Ogrenci No");
        l7 = new JLabel("Ders Kodu");
        l8 = new JLabel("Yıl");
        l9 = new JLabel("Not");
        lempty5 = new JLabel();
        lempty6 = new JLabel();
        btn5 = new JButton("Kaydet");
        btn6 = new JButton("Temizle");
        txt6 = new JTextField();
        txt7 = new JTextField();
        txt8 = new JTextField();
        txt9 = new JTextField();
        btn5.addActionListener(this);
        btn6.addActionListener(this);

        // Panel düzeni ayarlama
        p6.setLayout(new GridLayout(6, 2));
        p6.add(l6);
        p6.add(txt6);
        p6.add(l7);
        p6.add(txt7);
        p6.add(l8);
        p6.add(txt8);
        p6.add(l9);
        p6.add(txt9);
        p6.add(lempty5);
        p6.add(btn5);
        p6.add(lempty6);
        p6.add(btn6);

        p6.setBounds(0, 0, 330, 300);

        // Paneli katmanlı panele ekleme
        layeredPane.add(p6, Integer.valueOf(0));
        p6.setVisible(false);
    }

    private void guncelleNotListesi() {
        // Not listesini metin alanında güncelleme
        StringBuilder sbl = new StringBuilder();
        sbl.append("Ogrenci No          Ders Kod          Yıl          Not\n");
        for (String[] not : notListesi) {
            sbl.append(String.format("%s                           %s                        %s            %s\n", not[0], not[1], not[2], not[3]));
        }
        textArea3.setText(sbl.toString());
    }

    public void yeniNotEkle(String ogrenciNo, String dersKod, String yil, String not) {
        // Yeni not ekleme ve listeyi güncelleme
        notListesi.add(new String[]{ogrenciNo, dersKod, yil, not});
        guncelleNotListesi();
    }

    public void listeleNot() {
        // Notları listeleme paneli oluşturma ve ayarlama
        p7 = new JPanel();
        p7.setBorder(BorderFactory.createTitledBorder(s2[5]));
        textArea3 = new JTextArea();
        textArea3.setEditable(false);

        // Not listesini güncelleme
        guncelleNotListesi();
        p7.setLayout(new BorderLayout());
        p7.add(new JScrollPane(textArea3), BorderLayout.CENTER);
        p7.setBounds(0, 0, 330, 300);

        // Paneli katmanlı panele ekleme
        layeredPane.add(p7, Integer.valueOf(2));
        p7.setVisible(false);
    }

    public static void main(String[] args) {
        // Programın giriş noktası
        new StudentsPoint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Radyo düğmesi durumu değiştiğinde çağrılır
        for (int i = 0; i < s2.length; i++) {
            if (e.getSource() == r[i]) {
                // Tüm panelleri görünmez yap
                p2.setVisible(false);
                p3.setVisible(false);
                p4.setVisible(false);
                p5.setVisible(false);
                p6.setVisible(false);
                p7.setVisible(false);

                // Seçilen paneli görünür yap
                switch (i) {
                    case 0:
                        p2.setVisible(true);
                        break;
                    case 1:
                        p3.setVisible(true);
                        break;
                    case 2:
                        p4.setVisible(true);
                        break;
                    case 3:
                        p5.setVisible(true);
                        break;
                    case 4:
                        p6.setVisible(true);
                        break;
                    case 5:
                        p7.setVisible(true);
                        break;
                }
                // Pencere boyutunu ve konumunu ayarlama
                setSize(800, 400);
                setLocationRelativeTo(null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Buton tıklama olaylarını işleme
        if (e.getSource() == btn1) {
            // Yeni ders ekleme işlemi
            yeniDersEkle(txt1.getText(), txt2.getText());
            JOptionPane.showMessageDialog(null, "Ders başarılı bir şekilde eklendi!", "Sonuç...", JOptionPane.INFORMATION_MESSAGE);
            txt1.setText("");
            txt2.setText("");
        }
        if (e.getSource() == btn2) {
            // Ders ekleme formunu temizleme
            txt1.setText("");
            txt2.setText("");
        }
        if (e.getSource() == btn3) {
            // Yeni öğrenci ekleme işlemi
            yeniOgrenciEkle(txt3.getText(), txt4.getText(), txt5.getText());
            JOptionPane.showMessageDialog(null, "Öğrenci başarılı bir şekilde eklendi!", "Sonuç...", JOptionPane.INFORMATION_MESSAGE);
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
        }
        if (e.getSource() == btn4) {
            // Öğrenci ekleme formunu temizleme
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
        }
        if (e.getSource() == btn5) {
            // Yeni not ekleme işlemi
            int a = 0;
            for (String[] derss : dersListesi) {
                if (derss[0].equals(txt7.getText())) {
                    a++;
                    break;
                }
            }
            for (String[] no : ogrenciListesi) {
                if (no[0].equals(txt6.getText())) {
                    a++;
                    break;
                }
            }
            if (a == 2) {
                // Ders ve öğrenci bulunduğunda not ekleme
                yeniNotEkle(txt6.getText(), txt7.getText(), txt8.getText(), txt9.getText());
                JOptionPane.showMessageDialog(null, "Not başarılı bir şekilde eklendi!", "Sonuç...", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Ders veya öğrenci bulunamadığında hata mesajı gösterme
                JOptionPane.showMessageDialog(null, "Not eklenemedi, ogrenci no hatalı!", "Sonuç...", JOptionPane.INFORMATION_MESSAGE);
            }

            txt6.setText("");
            txt7.setText("");
            txt8.setText("");
            txt9.setText("");
        }
        if (e.getSource() == btn6) {
            // Not ekleme formunu temizleme
            txt6.setText("");
            txt7.setText("");
            txt8.setText("");
            txt9.setText("");
        }
    }
}

