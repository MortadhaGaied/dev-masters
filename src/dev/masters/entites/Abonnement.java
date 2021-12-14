/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Abonnement {

    private long id_abonnement;
    private long id_user_abonnement;
    private long id_abonn_dispo_abonnement;
    private Date date_debut_abonnement;
    private Date date_fin_abonnement;
    private long tel;
    private double prix;

    public Abonnement(long id_user, long id_abonn_dispo, Date date_debut, Date date_fin) {
        this.id_user_abonnement = id_user;
        this.id_abonn_dispo_abonnement = id_abonn_dispo;
        this.date_debut_abonnement = date_debut;
        this.date_fin_abonnement = date_fin;
    }

    public Abonnement(long id_abonnement, long id_user_abonnement, long id_abonn_dispo_abonnement, Date date_debut_abonnement, Date date_fin_abonnement) {
        this.id_abonnement = id_abonnement;
        this.id_user_abonnement = id_user_abonnement;
        this.id_abonn_dispo_abonnement = id_abonn_dispo_abonnement;
        this.date_debut_abonnement = date_debut_abonnement;
        this.date_fin_abonnement = date_fin_abonnement;
    }

    public Abonnement() {
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public long getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(long id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public void setId_user_abonnement(long id_user_abonnement) {
        this.id_user_abonnement = id_user_abonnement;
    }

    public void setId_abonn_dispo_abonnement(long id_abonn_dispo_abonnement) {
        this.id_abonn_dispo_abonnement = id_abonn_dispo_abonnement;
    }

    public void setDate_debut_abonnement(Date date_debut_abonnement) {
        this.date_debut_abonnement = date_debut_abonnement;
    }

    public void setDate_fin_abonnement(Date date_fin_abonnement) {
        this.date_fin_abonnement = date_fin_abonnement;
    }

    public long getId_user_abonnement() {
        return id_user_abonnement;
    }

    public long getId_abonn_dispo() {
        return id_abonn_dispo_abonnement;
    }

    public Date getDate_debut_abonnement() {
        return date_debut_abonnement;
    }

    public Date getDate_fin_abonnement() {
        return date_fin_abonnement;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id_abonnement=" + id_abonnement + ", id_user_abonnement=" + id_user_abonnement + ", id_abonn_dispo_abonnement=" + id_abonn_dispo_abonnement + ", date_debut_abonnement=" + date_debut_abonnement + ", date_fin_abonnement=" + date_fin_abonnement + '}'+"\n";
    }

}
