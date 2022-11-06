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
      private String dateFin;
      private String heureFin;
      private String libelle;
      private String participant;
      private String contenu;
      private String categorie;

    public Enregistrement(int id) {
        this.id = id;
    }

    public Enregistrement(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
      
    
    //constructeur
    

    public Enregistrement(String date, String heure, String dateFin, String heureFin, String libelle, String participant, String contenu,String categorie) {
        this.date = date;
        this.heure = heure;
        this.dateFin = dateFin;
        this.heureFin = heureFin;
        this.libelle = libelle;
        this.participant = participant;
        this.contenu = contenu;
        this.categorie = categorie;
    }

    public Enregistrement(int id, String date, String heure, String dateFin, String heureFin, String libelle, String participant, String contenu,String categorie ) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.dateFin = dateFin;
        this.heureFin = heureFin;
        this.libelle = libelle;
        this.participant = participant;
        this.contenu = contenu;
         this.categorie = categorie;
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
