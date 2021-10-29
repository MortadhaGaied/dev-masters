/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

/**
 *
 * @author LENOVO
 */
public class AbonnementDisponible {

    private long id_abonnement;
    private String desc_abonnement;
    private String type_abonnement;
    private Double prix_moins_abonnement;
    private Double prix_semestre_abonnement;
    private Double prix_annuel_abonnement;

    public AbonnementDisponible() {
    }

    public AbonnementDisponible(String desc_abonnement, String type_abonnement, Double prix_moins_abonnement, Double prix_semestre_abonnement, Double prix_annuel_abonnement) {
        this.desc_abonnement = desc_abonnement;
        this.type_abonnement = type_abonnement;
        this.prix_moins_abonnement = prix_moins_abonnement;
        this.prix_semestre_abonnement = prix_semestre_abonnement;
        this.prix_annuel_abonnement = prix_annuel_abonnement;
    }

    public AbonnementDisponible(long id_abonnement, String desc_abonnement, String type_abonnement, Double prix_moins_abonnement, Double prix_semestre_abonnement, Double prix_annuel_abonnement) {
        this.id_abonnement = id_abonnement;
        this.desc_abonnement = desc_abonnement;
        this.type_abonnement = type_abonnement;
        this.prix_moins_abonnement = prix_moins_abonnement;
        this.prix_semestre_abonnement = prix_semestre_abonnement;
        this.prix_annuel_abonnement = prix_annuel_abonnement;
    }

    public long getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(long id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public void setDesc_abonnement(String desc_abonnement) {
        this.desc_abonnement = desc_abonnement;
    }

    public void setType_abonnement(String type_abonnement) {
        this.type_abonnement = type_abonnement;
    }

    public void setPrix_moins_abonnement(Double prix_moins_abonnement) {
        this.prix_moins_abonnement = prix_moins_abonnement;
    }

    public void setPrix_semestre_abonnement(Double prix_semestre_abonnement) {
        this.prix_semestre_abonnement = prix_semestre_abonnement;
    }

    public void setPrix_annuel_abonnement(Double prix_annuel_abonnement) {
        this.prix_annuel_abonnement = prix_annuel_abonnement;
    }

    public String getDesc_abonnement() {
        return desc_abonnement;
    }

    public String getType_abonnement() {
        return type_abonnement;
    }

    public Double getPrix_moins_abonnement() {
        return prix_moins_abonnement;
    }

    public Double getPrix_semestre_abonnement() {
        return prix_semestre_abonnement;
    }

    public Double getPrix_annuel_abonnement() {
        return prix_annuel_abonnement;
    }

    @Override
    public String toString() {
        return "AbonnementDisponible{" + "id_abonnement=" + id_abonnement + ", desc_abonnement=" + desc_abonnement + ", type_abonnement=" + type_abonnement + ", prix_moins_abonnement=" + prix_moins_abonnement + ", prix_semestre_abonnement=" + prix_semestre_abonnement + ", prix_annuel_abonnement=" + prix_annuel_abonnement + '}';
    }

}
