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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String query = "INSERT INTO abonnemnt(abonnement_disponible_id,user_id , date_debut, date_fin, tel, prix) VALUES (" + nouv_abonnee.getId_abonn_dispo()+ "," + nouv_abonnee.getId_user_abonnement()+ ",'" + sdf.format(nouv_abonnee.getDate_debut_abonnement()) + "','" + sdf.format(nouv_abonnee.getDate_fin_abonnement()) +"','"+nouv_abonnee.getTel()+"','"+nouv_abonnee.getPrix()+ "')";
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
            abonnement.setId_abonnement(rs.getLong("id"));
            abonnement.setId_user_abonnement(rs.getLong("user_id"));
            abonnement.setId_abonn_dispo_abonnement(rs.getLong("abonnement_disponible_id"));
            abonnement.setDate_debut_abonnement(rs.getDate("date_debut"));
            abonnement.setDate_fin_abonnement(rs.getDate("date_fin"));
            abonnement.setTel(rs.getInt("tel"));
            abonnement.setPrix(rs.getDouble("prix"));
            lp.add(abonnement);
        }
        return lp;
    }

    @Override
    public void modifier(long id_abonnement, Abonnement abonnee_modifier) throws SQLException {
        try {
            Statement stm = cnx.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String query = "UPDATE abonnemnt SET abonnement_disponible_id=" + abonnee_modifier.getId_abonn_dispo() + ",date_debut='" + sdf.format(abonnee_modifier.getDate_debut_abonnement()) + "',date_fin='" + sdf.format(abonnee_modifier.getDate_fin_abonnement()) + "' where id_abonnement = " + id_abonnement;
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
            String query = "delete from abonnemnt where id=" + id_abonnement;
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Double remise(Double prix, int re) {
        Double nouv_prix = prix * (100 / re);
        return nouv_prix;
    }
    public List<Abonnement> abonnementparmois(int mois){
       
        
            List<Abonnement> lp = new ArrayList<>();
            try {
            Statement stm = cnx.createStatement();
            
            String query = "SELECT * FROM abonnemnt WHERE MONTH(date_debut)="+mois;
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
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }

}
