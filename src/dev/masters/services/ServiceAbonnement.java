/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.Abonnement;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceAbonnement implements IService<Abonnement> {

    Connection cnx;

    public ServiceAbonnement() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Abonnement nouv_abonnee) {
        try {
            Statement st = cnx.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String query = "INSERT INTO abonnemnt(id_user_abonnement, id_abonn_dispo_abonnement, date_debut_abonnement, date_fin_abonnement) VALUES (" + nouv_abonnee.getId_user_abonnement() + "," + nouv_abonnee.getId_abonn_dispo() + ",'" + sdf.format(nouv_abonnee.getDate_debut_abonnement()) + "','" + sdf.format(nouv_abonnee.getDate_fin_abonnement()) + "')";
            System.out.println(query);
            st.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Abonnement> afficher() throws SQLException {
        Statement stm = cnx.createStatement();
        List<Abonnement> lp = new ArrayList<>();

        String query = "SELECT * FROM abonnemnt";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            Abonnement abonnement = new Abonnement();
            abonnement.setId_abonnement(rs.getLong("id_abonnement"));
            abonnement.setId_user_abonnement(rs.getLong("id_user_abonnement"));
            abonnement.setId_abonn_dispo_abonnement(rs.getLong("id_abonn_dispo_abonnement"));
            abonnement.setDate_debut_abonnement(rs.getDate("date_debut_abonnement"));
            abonnement.setDate_fin_abonnement(rs.getDate("date_fin_abonnement"));
            lp.add(abonnement);
        }
        return lp;
    }

    @Override
    public void modifier(long id_abonnement, Abonnement abonnee_modifier) throws SQLException {
        try {
            Statement stm = cnx.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String query = "UPDATE abonnemnt SET id_abonn_dispo=" + abonnee_modifier.getId_abonn_dispo() + ",date_debut_abonnement='" + sdf.format(abonnee_modifier.getDate_debut_abonnement()) + "',date_fin_abonnement='" + sdf.format(abonnee_modifier.getDate_fin_abonnement()) + "' where id_abonnement = " + id_abonnement;
            System.out.println(query);
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void supprimer(long id_abonnement) throws SQLException {
        Statement stm;
        try {
            stm = cnx.createStatement();
            String query = "delete from abonnemnt where id_abonnement=" + id_abonnement;
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
