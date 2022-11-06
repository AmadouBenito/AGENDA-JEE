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
        
        String sql = "INSERT INTO enregistrement (date, heure, dateEnd, timeEnd, libelle, participant, contenu, categorie) VALUES (?,?,?,?,?,?,?,?);";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, enreg.getDate());
        statement.setString(2, enreg.getHeure());
        statement.setString(3, enreg.getDateFin());
        statement.setString(4, enreg.getHeureFin());
        statement.setString(5, enreg.getLibelle());
        statement.setString(6, enreg.getParticipant());
        statement.setString(7, enreg.getContenu());
        statement.setString(8, enreg.getCategorie());
        
   
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
            String dateEnd = resultSet.getString("dateEnd");
            String timeEnd = resultSet.getString("timeEnd");
            String libelle = resultSet.getString("libelle");
            String participant = resultSet.getString("participant");
            String categorie = resultSet.getString("categorie");
            
            Enregistrement enreg = new Enregistrement(id, date, time,dateEnd, timeEnd, libelle,participant, contenu, categorie);
            listRecords.add(enreg);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listRecords;
    } 

   
   public boolean deleteEvent(Enregistrement enreg) throws SQLException {
        String sql = "DELETE FROM enregistrement where id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, enreg.getId());
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;

    }
   
   public boolean updateEvent(Enregistrement enreg, HttpServletRequest request) throws SQLException, IOException, ServletException {
         
       
        String sql = "UPDATE article SET contenu = ?";
        sql += " WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, enreg.getContenu());
        statement.setInt(2, enreg.getId());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;

    }
   
   public Enregistrement getEvent(int id) throws SQLException {
        Enregistrement enreg = null;
        String sql = "SELECT * FROM article WHERE idarticle = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int idEvent = resultSet.getInt("id");
            String contenu = resultSet.getString("contenu");
            String date = resultSet.getString("date");
            String dateEnd = resultSet.getString("dateEnd");
            String participant = resultSet.getString("participant");
            String libelle = resultSet.getString("libelle");
            String time = resultSet.getString("time");
            String timeEnd = resultSet.getString("timeEnd");
            String categorie = resultSet.getString("categorie");
            
            enreg = new Enregistrement(idEvent, date, time, dateEnd, timeEnd, libelle,participant,contenu, categorie);
        }
        resultSet.close();
        statement.close();
        return enreg;
    }
}
