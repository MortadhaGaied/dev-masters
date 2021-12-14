/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;


public class Reclamation {
    private long id_reclamation;
    private String username_reclamation;
    private String object_reclamation;
    private String description_reclamation;
    private int category;

    public Reclamation() {
    }

    public Reclamation(String username_reclamation, String object_reclamation, String description_reclamation) {
        this.username_reclamation = username_reclamation;
        this.object_reclamation = object_reclamation;
        this.description_reclamation = description_reclamation;
    }

    public Reclamation(long id_reclamation, String username_reclamation, String object_reclamation, String description_reclamation) {
        this.id_reclamation = id_reclamation;
        this.username_reclamation = username_reclamation;
        this.object_reclamation = object_reclamation;
        this.description_reclamation = description_reclamation;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    
    public long getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(long id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getUsername_reclamation() {
        return username_reclamation;
    }

    public void setUsername_reclamation(String username_reclamation) {
        this.username_reclamation = username_reclamation;
    }

    public String getObject_reclamation() {
        return object_reclamation;
    }

    public void setObject_reclamation(String object_reclamation) {
        this.object_reclamation = object_reclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", username_reclamation=" + username_reclamation + ", object_reclamation=" + object_reclamation + ", description_reclamation=" + description_reclamation + '}'+"\n";
    }

    
}
