/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.AbonnementDisponible;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceAbonnementDisponible implements IService<AbonnementDisponible> {

    Connection cnx;

    public ServiceAbonnementDisponible() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(AbonnementDisponible nouv_abonnement) {
        Statement st;
        try {
            st = cnx.createStatement();
            String query = "INSERT INTO abonnements_disponibe( descr, type, prix_mois,prix_semestre,prix_annuel ) VALUES ('" + nouv_abonnement.getDesc_abonnement() + "','" + nouv_abonnement.getType_abonnement() + "'," + nouv_abonnement.getPrix_moins_abonnement() + "," + nouv_abonnement.getPrix_semestre_abonnement() + "," + nouv_abonnement.getPrix_annuel_abonnement() + ")";
            System.out.println(query);
            st.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<AbonnementDisponible> afficher() {
        List<AbonnementDisponible> lp = new ArrayList<>();

        try {
            Statement stm = cnx.createStatement();

            String query = "SELECT * FROM abonnements_disponibe";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                AbonnementDisponible abonnement = new AbonnementDisponible();
                abonnement.setId_abonnement(rs.getLong("id"));
                abonnement.setDesc_abonnement(rs.getString("descr"));
                abonnement.setType_abonnement(rs.getString("type"));
                abonnement.setPrix_moins_abonnement(rs.getDouble("prix_mois"));
                abonnement.setPrix_semestre_abonnement(rs.getDouble("prix_semestre"));
                abonnement.setPrix_annuel_abonnement(rs.getDouble("prix_annuel"));
                lp.add(abonnement);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lp;
    }

    @Override
    public void supprimer(long id) {
        Statement stm;
        try {
            stm = cnx.createStatement();
            String query = "delete from abonnements_disponibe where id=" + id;
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public AbonnementDisponible SearchById(long id) throws SQLException {

        Statement stm = cnx.createStatement();
        AbonnementDisponible abonnement = new AbonnementDisponible();
        String query = "select * from abonnements_disponibe where id=" + id;
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            abonnement.setId_abonnement(rs.getInt("id"));
            abonnement.setDesc_abonnement(rs.getString("descr"));
            abonnement.setType_abonnement(rs.getString("type"));
            abonnement.setPrix_moins_abonnement(rs.getDouble("prix_mois"));
            abonnement.setPrix_semestre_abonnement(rs.getDouble("prix_semestre"));
            abonnement.setPrix_annuel_abonnement(rs.getDouble("prix_annuel"));
        }
        return abonnement;
    }

    @Override
    public void modifier(long id, AbonnementDisponible abonnement_modifier) {
        try {
            Statement stm = cnx.createStatement();
            AbonnementDisponible abonnementDisponible = SearchById(id);
            String query = "UPDATE abonnements_disponibe SET descr='" + abonnement_modifier.getDesc_abonnement() + "',type='" + abonnement_modifier.getType_abonnement() + "',prix_mois='" + abonnement_modifier.getPrix_moins_abonnement() + "',prix_semestre='" + abonnement_modifier.getPrix_semestre_abonnement() + "',prix_annuel=" + abonnement_modifier.getPrix_annuel_abonnement() + "where id = " + id;
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public List<AbonnementDisponible> rechercheParTypeOuDesc(String req) {
        List<AbonnementDisponible> list = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();

            String query = "select * from abonnements_disponibe where descr like '%" + req + "%' or type like '%" + req + "%' or id = '" + req + "'";
            System.out.println(query);
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                AbonnementDisponible abonnement = new AbonnementDisponible();
                abonnement.setId_abonnement(rs.getLong("id"));
                abonnement.setDesc_abonnement(rs.getString("descr"));
                abonnement.setType_abonnement(rs.getString("type"));
                abonnement.setPrix_moins_abonnement(rs.getDouble("prix_mois"));
                abonnement.setPrix_semestre_abonnement(rs.getDouble("prix_semestre"));
                abonnement.setPrix_annuel_abonnement(rs.getDouble("prix_annuel"));
                list.add(abonnement);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    public Double remise(Double prix, int re) {
        Double nouv_prix = prix * (100 / re);
        return nouv_prix;
    }
}
