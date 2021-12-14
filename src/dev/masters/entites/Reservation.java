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
    private int id_user;
    private String ref;
    private long price;
    private String name ;

    private LocalDateTime date ;
    private String etat ;


    public Reservation() {
    }


    public Reservation(LocalDateTime date, String etat) {
        this.date = date;
        this.etat = etat;
    }

    public Reservation(long id_reservation, LocalDateTime date, String etat) {
        this.id_reservation = id_reservation;
        this.date = date;
        this.etat = etat;

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(long id_reservation) {
        this.id_reservation = id_reservation;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;

    }

    @Override
    public String toString() {

        return "Reservation{" + "id_reservation=" + id_reservation + ", date=" + date + ", etat=" + etat + '}'+"\n";

    }
    
}
