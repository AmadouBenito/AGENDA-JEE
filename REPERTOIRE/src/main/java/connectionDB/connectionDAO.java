/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectionDB;

import enregistrement.Enregistrement;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author BABA SAIDOU DIEME
 */
public class connectionDAO {
     private String jdbcURL;
     private String jdbcUsername;
     private String jdbcPassword;
     private Connection jdbcConnection;

    public connectionDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, Connection jdbcConnection) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
        this.jdbcConnection = jdbcConnection;
    }

    public connectionDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

 
    
    public void connect() throws SQLException{
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            
        }
        jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/agenda_bd","root","");
        if (jdbcConnection!=null)
            System.out.print("new connection successsssss");
        else
            System.out.print("new connection errooorrrrr");
        
        }
   
    }
    
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
   public boolean Ajouter(Enregistrement enreg,HttpServletRequest request) throws SQLException, IOException, ServletException {
        
        String sql = "INSERT INTO enregistrement (date, heure, contenu) VALUES (?,?,?);";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, enreg.getDate());
        statement.setString(2, enreg.getHeure());
        statement.setString(3, enreg.getContenu());
   
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

   public List<Enregistrement> getAllRecords() throws SQLException {
        String sql = "SELECT * FROM enregistrement ORDER BY (date)  ";
        //GROUP BY date ORDER BY (heure)
        List<Enregistrement> listRecords = new ArrayList<>();
       
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String date = resultSet.getString("date");
            String time = resultSet.getString("heure");
            String contenu = resultSet.getString("contenu");
            
            Enregistrement enreg = new Enregistrement(id, date, time, contenu);
            listRecords.add(enreg);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listRecords;
    }

   
       
}
