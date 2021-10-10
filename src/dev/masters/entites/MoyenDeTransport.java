
package dev.masters.entites;


public class MoyenDeTransport {

  private int id_MoyenTransport  ;
    private String Nom_Station ;
    private int Num_ligne;
    private int Nombre_Station;
    private String Accessible_au_handicapes;

    public MoyenDeTransport() {
    }

    public int getId_MoyenTransport() {
        return id_MoyenTransport;
    }

    public void setId_MoyenTransport(int id_MoyenTransport) {
        this.id_MoyenTransport = id_MoyenTransport;
    }

    public String getNom_Station() {
        return Nom_Station;
    }

    public void setNom_Station(String Nom_Station) {
        this.Nom_Station = Nom_Station;
    }

    public int getNum_ligne() {
        return Num_ligne;
    }

    public void setNum_ligne(int Num_ligne) {
        this.Num_ligne = Num_ligne;
    }

    public int getNombre_Station() {
        return Nombre_Station;
    }

    public void setNombre_Station(int Nombre_Station) {
        this.Nombre_Station = Nombre_Station;
    }

    public String getAccessible_au_handicapes() {
        return Accessible_au_handicapes;
    }

    public void setAccessible_au_handicapes(String Accessible_au_handicapes) {
        this.Accessible_au_handicapes = Accessible_au_handicapes;
    }

    public MoyenDeTransport(String Nom_Station, int Num_ligne, int Nombre_Station, String Accessible_au_handicapes) {
        this.Nom_Station = Nom_Station;
        this.Num_ligne = Num_ligne;
        this.Nombre_Station = Nombre_Station;
        this.Accessible_au_handicapes = Accessible_au_handicapes;
    }

    public MoyenDeTransport(int id_MoyenTransport, String Nom_Station, int Num_ligne, int Nombre_Station, String Accessible_au_handicapes) {
        this.id_MoyenTransport = id_MoyenTransport;
        this.Nom_Station = Nom_Station;
        this.Num_ligne = Num_ligne;
        this.Nombre_Station = Nombre_Station;
        this.Accessible_au_handicapes = Accessible_au_handicapes;
    }

    @Override
    public String toString() {
        return "MoyenTransport{" + "id_MoyenTransport=" + id_MoyenTransport + ", Nom_Station=" + Nom_Station + ", Num_ligne=" + Num_ligne + ", Nombre_Station=" + Nombre_Station + ", Accessible_au_handicapes=" + Accessible_au_handicapes + '}';
    }


}
