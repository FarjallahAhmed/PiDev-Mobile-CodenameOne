/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author dell
 */
public class Workshop {
    
    private int id;
    private String nomCalendar;
    private String nomEvent;
    private String datedebut;
    private String datefin;
    private String hDebut;
    private String hFin;
    private String lieu;
    private int nbParticipant;
    private String type;
    private String description;
    private float prix;
    private ArrayList<Comment> comment ;

    public Workshop(String nomCalendar, String nomEvent, String datedebut, String datefin, String hDebut, String hFin, String lieu, int nbParticipant, String type, String description, float prix) {
        this.nomCalendar = nomCalendar;
        this.nomEvent = nomEvent;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.hDebut = hDebut;
        this.hFin = hFin;
        this.lieu = lieu;
        this.nbParticipant = nbParticipant;
        this.type = type;
        this.description = description;
        this.prix = prix;
    }
    
    

    public Workshop(int id, String nomCalendar, String nomEvent, String datedebut, String datefin, String hDebut, String hFin, String lieu, int nbParticipant, String type, String description, float prix) {
        this.id = id;
        this.nomCalendar = nomCalendar;
        this.nomEvent = nomEvent;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.hDebut = hDebut;
        this.hFin = hFin;
        this.lieu = lieu;
        this.nbParticipant = nbParticipant;
        this.type = type;
        this.description = description;
        this.prix = prix;
    }

    

    public Workshop() {
    }


    



    public int getId() {
        return id;
    }

    public String getNomEvent() {
        return nomEvent;
    }


    public String getLieu() {
        return lieu;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }



    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNomCalendar() {
        return nomCalendar;
    }

    public void setNomCalendar(String nomCalendar) {
        this.nomCalendar = nomCalendar;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public String gethDebut() {
        return hDebut;
    }

    public String gethFin() {
        return hFin;
    }

    public void setDatedebut(String datedebut) {
        
        this.datedebut = datedebut;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public void sethDebut(String hDebut) {
        this.hDebut = hDebut;
    }

    public void sethFin(String hFin) {
        this.hFin = hFin;
    }

    public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Workshop{" + "id=" + id + ", nomCalendar=" + nomCalendar + ", nomEvent=" + nomEvent + ", datedebut=" + datedebut + ", datefin=" + datefin + ", hDebut=" + hDebut + ", hFin=" + hFin + ", lieu=" + lieu + ", nbParticipant=" + nbParticipant + ", type=" + type + ", description=" + description + ", prix=" + prix + ", comment=" + comment + '}';
    }

 
    

    
    
    
}