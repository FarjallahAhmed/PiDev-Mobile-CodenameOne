/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author AMINE N
 */
public class Evaluation {
    private int id;
    private int note;
     private float moyenne;
    private String description;
    private Formations idFormations;

    public Evaluation(int id, int note, String description, Formations idFormations) {
        this.id = id;
        this.note = note;
        this.description = description;
        this.idFormations = idFormations;
    }

    public Evaluation(int id, int note, float moyenne, String description, Formations idFormations) {
        this.id = id;
        this.note = note;
        this.moyenne = moyenne;
        this.description = description;
        this.idFormations = idFormations;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Formations getIdFormations() {
        return idFormations;
    }

    public void setIdFormations(Formations idFormations) {
        this.idFormations = idFormations;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", note=" + note + ", moyenne=" + moyenne + ", description=" + description + ", idFormations=" + idFormations + '}';
    }



    public Evaluation() {
    }
    
}
