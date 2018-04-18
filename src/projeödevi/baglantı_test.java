package projeödevi;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baglantı_test {
    public static void main(String[] args) {
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            return;
        }
        Connection Con=null;
        try {
          Con  =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme","root","");
            System.out.println("baglantı kuruldu ");
            
        } catch (SQLException e) {
            System.out.println("baglantı kurulamadı");
        }
        finally{
            if(Con!=null){
                try {
                    Con.close();
                } catch (Exception e) {
                }
                
            }
            
        }
        
    
    }

   
}
