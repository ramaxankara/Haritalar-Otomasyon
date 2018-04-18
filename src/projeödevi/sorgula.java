package projeödevi;

import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//mehmetsemih1997@gmail
public class sorgula {

    int i;
    boolean varmı;

    public String adres() throws ClassNotFoundException, SQLException {

        String sonuc = "kişi yok";
        Class.forName("com.mysql.jdbc.Driver");
        Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musteribilgileri", "root", "");
        Statement statement = (Statement) Con.createStatement();
        ResultSet rs = statement.executeQuery("Select * From musteri");
        String ad = JOptionPane.showInputDialog("ADI...");
        String soyad = JOptionPane.showInputDialog("SOYADI...");
        while (rs.next()) {

            if (rs.getString("Isim").equalsIgnoreCase(ad) && rs.getString("SoyIsim").equalsIgnoreCase(soyad)) {
                sonuc = rs.getString("Adres");
            }

        }
        return sonuc;

    }

    public void göster() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musteribilgileri", "root", "ibrahim21");
        Statement statement = (Statement) Con.createStatement();
        ResultSet rs = statement.executeQuery("Select * From musteri");

        JFrame frame = new JFrame("table");
        Object[] stünadları = {"İSİM", "SOYİSİM", "ADRES"};
        rs = statement.executeQuery("SELECT * FROM musteri");
        Vector satırlar = new Vector();
        while (rs.next()) {
            Object[] degerler = {rs.getString("Isim"), rs.getString("SoyIsim"), rs.getString("Adres")};
            satırlar.add(degerler);

        }
        Object[][] satırdegerleri = new Object[satırlar.size()][3];
        for (int i = 0; i < satırlar.size(); i++) {
            satırdegerleri[i] = (Object[]) satırlar.get(i);

        }
        JTable tablo = new JTable(satırdegerleri, stünadları);
        JScrollPane sp = new JScrollPane(tablo);
        frame.add(sp, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void elamanekle() throws ClassNotFoundException, SQLException {
        varmı = false;
               String İSİM = JOptionPane.showInputDialog("ADI...");
        String SOYADI = JOptionPane.showInputDialog("SOYADI...");

        Class.forName("com.mysql.jdbc.Driver");
        Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musteribilgileri", "root", "ibrahim21");
        String sorgu = "insert into musteri(Isim,SoyIsim,Adres)"
                + "values(?,?,?)";

        PreparedStatement pr = (PreparedStatement) Con.prepareStatement(sorgu);
        pr.setString(1, İSİM);
        pr.setString(2, SOYADI);

        Statement statement = (Statement) Con.createStatement();
        ResultSet rs = statement.executeQuery("Select * From musteri");
        while (rs.next()) {
            if (İSİM.equalsIgnoreCase(rs.getString("Isim")) && SOYADI.equalsIgnoreCase(rs.getString("SoyIsim"))) {
                varmı = true;
            }

        }
        if (!varmı) {
            String ADRES = JOptionPane.showInputDialog("ADRESİ...");

            pr.setString(3, ADRES);
            int executeUptade = pr.executeUpdate();
            JOptionPane.showMessageDialog(null, " Kişi Başarıyla Eklenmiştir   ");
        } else {
            JOptionPane.showMessageDialog(null, "Aynı İsimde Başka Kayıt Var   ");
        }

    }

    public void kayıtsil() throws ClassNotFoundException, SQLException {
        if (kişisayısı() > 0) {
            varmı = false;
            String İSİM = JOptionPane.showInputDialog("ADI...");
            String SOYADI = JOptionPane.showInputDialog("SOYADI...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musteribilgileri", "root", "ibrahim21");
            String sorgu = "DELETE FROM musteri WHERE Isim=?";
            PreparedStatement pr = (PreparedStatement) Con.prepareStatement(sorgu);
            String sorgu2 = "DELETE FROM musteri WHERE SoyIsim=?";
            PreparedStatement pr2 = (PreparedStatement) Con.prepareStatement(sorgu2);
            int executeUptade;
            int executeUptade2;
            Statement statement = (Statement) Con.createStatement();
            ResultSet rs = statement.executeQuery("Select * From musteri");
            while (rs.next()) {
                if (İSİM.equalsIgnoreCase(rs.getString("Isim")) && SOYADI.equalsIgnoreCase(rs.getString("SoyIsim"))) {
                    varmı = true;
                }

            }

            if (varmı) {
                pr.setString(1, İSİM);
                pr2.setString(1, SOYADI);

                executeUptade = pr.executeUpdate();
                pr.close();

                executeUptade2 = pr2.executeUpdate();
                pr2.close();
                JOptionPane.showMessageDialog(null, " Kişi Başarıyla Silinmiştir  ");

            } else {
                JOptionPane.showMessageDialog(null, "" + İSİM + "  " + SOYADI + " Kayıtlı Deyil");
            }
        } else {
            JOptionPane.showMessageDialog(null, " Kayıtlı Kimse YOK");
        }

    }

    public void kayıtGuncelle() throws ClassNotFoundException, SQLException {

        varmı = false;

        String İSİM = JOptionPane.showInputDialog("ADI...");
        String SOYADI = JOptionPane.showInputDialog("SOYADI...");

        Class.forName("com.mysql.jdbc.Driver");
        Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musteribilgileri", "root", "ibrahim21");
        String sorgu = "UPDATE musteri SET Adres=?  WHERE Isim=?";
        String sorgu2 = "UPDATE musteri SET Adres=?  WHERE SoyIsim=?";
        PreparedStatement pr = (PreparedStatement) Con.prepareStatement(sorgu);
        PreparedStatement pr2 = (PreparedStatement) Con.prepareStatement(sorgu2);

        Statement statement = (Statement) Con.createStatement();
        ResultSet rs = statement.executeQuery("Select * From musteri");
        while (rs.next()) {
            if (İSİM.equalsIgnoreCase(rs.getString("Isim")) && SOYADI.equalsIgnoreCase(rs.getString("SoyIsim"))) {
                varmı = true;
            }

        }

        if (!varmı) {
            JOptionPane.showMessageDialog(null, " Kişi Kayıtlı Değil ");
        } else {
            String ADRES = JOptionPane.showInputDialog("YENİ ADRESİ...");
            pr.setString(2, İSİM);
            pr.setString(1, ADRES);
            pr2.setString(2, SOYADI);
            pr2.setString(1, ADRES);
            int executeUptade = pr.executeUpdate();
            pr.close();
            int executeUptade2 = pr2.executeUpdate();
            pr2.close();
            JOptionPane.showMessageDialog(null, " Kişi Başarıyla Güncelleştir  ");
        }

    }

    public int kişisayısı() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musteribilgileri", "root", "ibrahim21");
        Statement statement = (Statement) Con.createStatement();
        ResultSet rs = statement.executeQuery("Select * From musteri");
        i = 0;
        while (rs.next()) {
            i++;
        }
        return i;

    }

    public void adresgöster() throws ClassNotFoundException, SQLException, IOException {
        String Adres = adres();
        if (Adres.equalsIgnoreCase("kişi yok")) {
            JOptionPane.showMessageDialog(null, " Aradığınız Kişi Bulunamadı:  ");
        } else {
            Process p = Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.exe https://www.google.com.tr/maps/search/" + Adres);

        }

    }
}
