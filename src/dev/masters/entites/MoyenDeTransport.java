/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

import java.time.LocalDate;

/**
 *
 * @author mouha
 */
public class MoyenDeTransport {

    private int id_MoyenTransport;
    private String Type;
    private String Num_ligne;
    private LocalDate Date_de_mise_en_circulations;
    private String Etat;
    private String Accessible_au_handicape ;
    private String Prix_achat;
    private int Poids ;
    private int Longueur;
    private int Largeur;
    private String Energie;
    private int Nombre_de_place;

    public int getId_MoyenTransport() {
        return id_MoyenTransport;
    }

    public String getType() {
        return Type;
    }

    public String getNum_ligne() {
        return Num_ligne;
    }

    public LocalDate getDate_de_mise_en_circulations() {
        return Date_de_mise_en_circulations;
    }

    public String getEtat() {
        return Etat;
    }

    public String getAccessible_au_handicape() {
        return Accessible_au_handicape;
    }

    public String getPrix_achat() {
        return Prix_achat;
    }

    public int getPoids() {
        return Poids;
    }

    public int getLongueur() {
        return Longueur;
    }

    public int getLargeur() {
        return Largeur;
    }

    public String getEnergie() {
        return Energie;
    }

    public int getNombre_de_place() {
        return Nombre_de_place;
    }

    public void setId_MoyenTransport(int id_MoyenTransport) {
        this.id_MoyenTransport = id_MoyenTransport;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public void setNum_ligne(String Num_ligne) {
        this.Num_ligne = Num_ligne;
    }

    public void setDate_de_mise_en_circulations(LocalDate Date_de_mise_en_circulations) {
        this.Date_de_mise_en_circulations = Date_de_mise_en_circulations;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setAccessible_au_handicape(String Accessible_au_handicape) {
        this.Accessible_au_handicape = Accessible_au_handicape;
    }

    public void setPrix_achat(String Prix_achat) {
        this.Prix_achat = Prix_achat;
    }

    public void setPoids(int Poids) {
        this.Poids = Poids;
    }

    public void setLongueur(int longueur) {
        this.Longueur = longueur;
    }

    public void setLargeur(int Largeur) {
        this.Largeur = Largeur;
    }

    public void setEnergie(String Energie) {
        this.Energie = Energie;
    }

    public void setNombre_de_place(int Nombre_de_place) {
        this.Nombre_de_place = Nombre_de_place;
    }

    public MoyenDeTransport(int id_MoyenTransport) {
        this.id_MoyenTransport = id_MoyenTransport;
    }

    public MoyenDeTransport(int id_MoyenTransport, String Type, String Num_ligne, LocalDate Date_de_mise_en_circulations, String Etat, String Accessible_au_handicape, String Prix_achat, int Poids, int Longueur, int Largeur, String Energie, int Nombre_de_place) {
        this.id_MoyenTransport = id_MoyenTransport;
        this.Type = Type;
        this.Num_ligne = Num_ligne;
        this.Date_de_mise_en_circulations = Date_de_mise_en_circulations;
        this.Etat = Etat;
        this.Accessible_au_handicape = Accessible_au_handicape;
        this.Prix_achat = Prix_achat;
        this.Poids = Poids;
        this.Longueur = Longueur;
        this.Largeur = Largeur;
        this.Energie = Energie;
        this.Nombre_de_place = Nombre_de_place;
    }

    public MoyenDeTransport(String type, String Num_ligne, LocalDate Date_de_mise_en_circulations, String Etat, String Accessible_au_handicape, String Prix_achat, int Poids, int longueur, int Largeur, String Energie, int Nombre_de_place) {
        this.Type = type;
        this.Num_ligne = Num_ligne;
        this.Date_de_mise_en_circulations = Date_de_mise_en_circulations;
        this.Etat = Etat;
        this.Accessible_au_handicape = Accessible_au_handicape;
        this.Prix_achat = Prix_achat;
        this.Poids = Poids;
        this.Longueur = longueur;
        this.Largeur = Largeur;
        this.Energie = Energie;
        this.Nombre_de_place = Nombre_de_place;
    }

    public MoyenDeTransport() {
    }
    
    

    @Override
    public String toString() {
        return  String.valueOf(id_MoyenTransport) ;
    }
}