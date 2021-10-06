/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

import java.time.LocalDateTime;

/**
 *
 * @author mouha
 */
public class Voyage <T> {
    private int id;
    private String position_depart,position_arrive;
    private LocalDateTime date_de_voyage;
    private Station station;
    private T moyen_transport;
    
    
    public Voyage(){
        
    }
    
    public Voyage(int id,String position_depart,String position_arrive,LocalDateTime date_de_voyage,Station station,T moyen_transport){
        this.id=id;
        this.position_depart=position_depart;
        this.position_arrive=position_arrive;
        this.date_de_voyage = date_de_voyage;
        this.station = station;
        this.moyen_transport=moyen_transport;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the position_depart
     */
    public String getPosition_depart() {
        return position_depart;
    }

    /**
     * @param position_depart the position_depart to set
     */
    public void setPosition_depart(String position_depart) {
        this.position_depart = position_depart;
    }

    /**
     * @return the position_arrive
     */
    public String getPosition_arrive() {
        return position_arrive;
    }

    /**
     * @param position_arrive the position_arrive to set
     */
    public void setPosition_arrive(String position_arrive) {
        this.position_arrive = position_arrive;
    }

    /**
     * @return the date_de_voyage
     */
    public LocalDateTime getDate_de_voyage() {
        return date_de_voyage;
    }

    /**
     * @param date_de_voyage the date_de_voyage to set
     */
    public void setDate_de_voyage(LocalDateTime date_de_voyage) {
        this.date_de_voyage = date_de_voyage;
    }

    /**
     * @return the station
     */
    public Station getStation() {
        return station;
    }

    /**
     * @param station the station to set
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * @return the moyen_transport
     */
    public T getMoyen_transport() {
        return moyen_transport;
    }

    /**
     * @param moyen_transport the moyen_transport to set
     */
    public void setMoyen_transport(T moyen_transport) {
        this.moyen_transport = moyen_transport;
    }
    
    
    public String toString(){
        return "Voyage{ \n id = "+this.getId()+" \n position de depart"+ this.getPosition_depart()+"\n position d'arrive"+this.getPosition_arrive()+"\n Date = "+this.getDate_de_voyage()+"\n station = "+this.getStation().getNom_station()+"\n Moyen de transport = "+this.getMoyen_transport().toString()+"}" ;
    }
}

