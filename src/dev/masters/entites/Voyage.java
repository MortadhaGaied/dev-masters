/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mouha
 */
public class Voyage  {
    private long id;
    private String ref_voyage;
    private Station station_depart;
    private Station station_arrive;
    private MoyenDeTransport moyen_de_transport;
    private LocalDateTime date_depart;
    private LocalDateTime date_arrive;
    private List<Station> stationsparVoyage = new ArrayList<Station>();
    
    
    public Voyage(){
        
    }

    public Voyage(long id, String ref_voyage, Station station_depart, Station station_arrive, MoyenDeTransport moyen_de_transport, LocalDateTime date_depart, LocalDateTime date_arrive) {
        this.id = id;
        this.ref_voyage = ref_voyage;
        this.station_depart = station_depart;
        this.station_arrive = station_arrive;
        this.moyen_de_transport = moyen_de_transport;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
    }

    public Voyage(String ref_voyage, Station station_depart, Station station_arrive, MoyenDeTransport moyen_de_transport, LocalDateTime date_depart, LocalDateTime date_arrive) {
        this.ref_voyage = ref_voyage;
        this.station_depart = station_depart;
        this.station_arrive = station_arrive;
        this.moyen_de_transport = moyen_de_transport;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRef_voyage() {
        return ref_voyage;
    }

    public void setRef_voyage(String ref_voyage) {
        this.ref_voyage = ref_voyage;
    }

    public Station getStation_depart() {
        return station_depart;
    }

    public void setStation_depart(Station station_depart) {
        this.station_depart = station_depart;
    }

    public Station getStation_arrive() {
        return station_arrive;
    }

    public void setStation_arrive(Station station_arrive) {
        this.station_arrive = station_arrive;
    }

    public MoyenDeTransport getMoyen_de_transport() {
        return moyen_de_transport;
    }

    public void setMoyen_de_transport(MoyenDeTransport moyen_de_transport) {
        this.moyen_de_transport = moyen_de_transport;
    }

    public LocalDateTime getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(LocalDateTime date_depart) {
        this.date_depart = date_depart;
    }

    public LocalDateTime getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(LocalDateTime date_arrive) {
        this.date_arrive = date_arrive;
    }

    public List<Station> getStationsparVoyage() {
        return stationsparVoyage;
    }

    public void setStationsparVoyage(List<Station> stationsparVoyage) {
        this.stationsparVoyage = stationsparVoyage;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", ref_voyage=" + ref_voyage + ", station_depart=" + station_depart + ", station_arrive=" + station_arrive + ", moyen_de_transport=" + moyen_de_transport + ", date_depart=" + date_depart + ", date_arrive=" + date_arrive + ", stationsparVoyage=" + stationsparVoyage + '}';
    }
    
    
    
}