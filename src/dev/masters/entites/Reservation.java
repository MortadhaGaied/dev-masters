/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

import java.time.LocalDateTime;

/**
 *
 * @author Mortadha
 */
public class Reservation {
    private long id_reservation ;
    private LocalDateTime date_reservation ;
    private String etat_reservation ;

    public Reservation() {
    }

    public Reservation(LocalDateTime date_reservation, String etat_reservation) {
        this.date_reservation = date_reservation;
        this.etat_reservation= etat_reservation;
    }

    public Reservation(long id_reservation, LocalDateTime date_reservation, String etat_reservation) {
        this.id_reservation = id_reservation;
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
    }

    public long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(long id_reservation) {
        this.id_reservation = id_reservation;
    }

    public LocalDateTime getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDateTime date) {
        this.date_reservation = date;
    }

    public String getEtat_reservation() {
        return etat_reservation;
    }

    public void setEtat_reservation(String etat) {
        this.etat_reservation = etat;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", date_reservation=" + date_reservation + ", etat_reservation=" + etat_reservation + '}'+"\n";
    }
    
}
