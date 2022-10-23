/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enregistrement;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author BABA SAIDOU DIEME
 */
public class Enregistrement {
    
      private int id;
      private String date;
      private String heure;
      private String contenu;
    
    //constructeur

    public Enregistrement(int id, String date, String heure, String contenu) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.contenu = contenu;
    }

    

    public Enregistrement(String date, String heure, String contenu) {
        this.date = date;
        this.heure = heure;
        this.contenu = contenu;
    }   
      
    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    
   

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
}
