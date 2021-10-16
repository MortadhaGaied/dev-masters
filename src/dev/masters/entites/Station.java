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
    
    private String nom_station,position,RefStation;

    
    public Station(){
        
    }
    public Station(String nom_station){
        this.nom_station=nom_station;
    }
    
    public Station(String nom_station,String position,String RefStation){
        this.setNom_station(nom_station);
        this.setPosition(position);
        this.RefStation=RefStation;
    }
    
    /**
     * @return the nom_station
     */
    public String getNom_station() {
        return nom_station;
    }

    /**
     * @param nom_station the nom_station to set
     */
    public void setNom_station(String nom_station) {
        this.nom_station = nom_station;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the RefStation
     */
    public String getRefStation() {
        return RefStation;
    }

    /**
     * @param RefStation the RefStation to set
     */
    public void setRefStation(String RefStation) {
        this.RefStation = RefStation;
    }
    
    
}