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
public class Voyage {

    private long id;
    private Position position_depart, position_arrive;
    private LocalDateTime date_de_voyage;
    private Station station;
    private MoyenTransport moyen_transport;
    private List<Station> stationsparVoyage = new ArrayList<Station>();

    public Voyage() {

    }

    //voyage sans ID
    public Voyage(Position position_depart, Position position_arrive, LocalDateTime date_de_voyage, Station station, MoyenTransport moyen_transport) {

        this.position_depart = position_depart;
        this.position_arrive = position_arrive;
        this.date_de_voyage = date_de_voyage;
        this.station = station;
        this.moyen_transport = moyen_transport;
    }

    //instantiation complete du voyage
    public Voyage(int id, Position position_depart, Position position_arrive, LocalDateTime date_de_voyage, Station station, MoyenTransport moyen_transport) {
        this.id = id;
        this.position_depart = position_depart;
        this.position_arrive = position_arrive;
        this.date_de_voyage = date_de_voyage;
        this.station = station;
        this.moyen_transport = moyen_transport;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the position_depart
     */
    public Position getPosition_depart() {
        return position_depart;
    }

    /**
     * @param position_depart the position_depart to set
     */
    public void setPosition_depart(Position position_depart) {
        this.position_depart = position_depart;
    }

    /**
     * @return the position_arrive
     */
    public Position getPosition_arrive() {
        return position_arrive;
    }

    /**
     * @param position_arrive the position_arrive to set
     */
    public void setPosition_arrive(Position position_arrive) {
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
    public MoyenTransport getMoyen_transport() {
        return moyen_transport;
    }

    /**
     * @param moyen_transport the moyen_transport to set
     */
    public void setMoyen_transport(MoyenTransport moyen_transport) {
        this.moyen_transport = moyen_transport;
    }

    public String toString() {
        return "Voyage{ \n id = " + this.getId() + " \n position de depart = ( " + this.getPosition_depart().getGoogleMapsPostionFormat() + " )" + "\n position d'arrive = ( " + this.getPosition_arrive().getGoogleMapsPostionFormat() + " )" + "\n Date = " + this.getDate_de_voyage() + "\n station = " + this.getStation().getRefStation() + "\n Moyen de transport = " + this.getMoyen_transport().getRef_mt() + "}";
    }
}
