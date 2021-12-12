/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

/**
 *
 * @author mouha
 */
public class Station {
    
    private int id;
    
    private String ref_station;
    private String nom_station;
    private Position position_station;

    public Station() {
    }
    
    
    

    public Station(int id, String ref_station, String nom_station, Position position_station) {
        this.id = id;
        this.ref_station = ref_station;
        this.nom_station = nom_station;
        this.position_station = position_station;
    }

    public Station(String ref_station, String nom_station, Position position_station) {
        this.ref_station = ref_station;
        this.nom_station = nom_station;
        this.position_station = position_station;
    }

    public Station(String nom_station) {
        this.nom_station = nom_station;
    }

    public Station(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef_station() {
        return ref_station;
    }

    public void setRef_station(String ref_station) {
        this.ref_station = ref_station;
    }

    public String getNom_station() {
        return nom_station;
    }

    public void setNom_station(String nom_station) {
        this.nom_station = nom_station;
    }

    public Position getPosition_station() {
        return position_station;
    }

    public void setPosition_station(Position position_station) {
        this.position_station = position_station;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + '}';
    }

    

   

    
    
    
}