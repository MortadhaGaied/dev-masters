
package dev.masters.MoyenTransport.entites;


public class MoyenDeTransport {

    private int id_MoyenTransport;
    private String Type;
    private String Num_ligne;
    private String Date_de_mise_en_circulations;
    private String Etat;
    private String Accessible_au_handicape ;
    private String Prix_achat;
    private String Poids ;
    private String Longueur;
    private String Largeur;
    private String Energie;
    private String Nombre_de_place;

    public int getId_MoyenTransport() {
        return id_MoyenTransport;
    }

    public String getType() {
        return Type;
    }

    public String getNum_ligne() {
        return Num_ligne;
    }

    public String getDate_de_mise_en_circulations() {
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

    public String getPoids() {
        return Poids;
    }

    public String getLongueur() {
        return Longueur;
    }

    public String getLargeur() {
        return Largeur;
    }

    public String getEnergie() {
        return Energie;
    }

    public String getNombre_de_place() {
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

    public void setDate_de_mise_en_circulations(String Date_de_mise_en_circulations) {
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

    public void setPoids(String Poids) {
        this.Poids = Poids;
    }

    public void setLongueur(String longueur) {
        this.Longueur = longueur;
    }

    public void setLargeur(String Largeur) {
        this.Largeur = Largeur;
    }

    public void setEnergie(String Energie) {
        this.Energie = Energie;
    }

    public void setNombre_de_place(String Nombre_de_place) {
        this.Nombre_de_place = Nombre_de_place;
    }

    public MoyenDeTransport(String type, String Num_ligne, String Date_de_mise_en_circulations, String Etat, String Accessible_au_handicape, String Prix_achat, String Poids, String longueur, String Largeur, String Energie, String Nombre_de_place) {
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
        return "MoyenDeTransport{" + "id_MoyenTransport=" + id_MoyenTransport + ", type=" + Type + ", Num_ligne=" + Num_ligne + ", Date_de_mise_en_circulations=" + Date_de_mise_en_circulations + ", Etat=" + Etat + ", Accessible_au_handicape=" + Accessible_au_handicape + ", Prix_achat=" + Prix_achat + ", Poids=" + Poids + ", longueur=" + Longueur + ", Largeur=" + Largeur + ", Energie=" + Energie + ", Nombre_de_place=" + Nombre_de_place + '}';
    }
}
