package dev.masters.services;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceMoyenDeTransport implements IService<MoyenDeTransport> {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mouha
 */

    



    Connection cnx;

    public ServiceMoyenDeTransport() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(MoyenDeTransport MoyenDeTransport) {
        try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `moyentransport`(`Type`, `Num_ligne`, `Date_de_mise_en_circulations`,`Etat`, `Accessible_au_handicape`, `Prix_achat`, `Poids`, `Longueur`, `Largeur`, `Energie`,`Nombre_de_place`) VALUES ('" + MoyenDeTransport.getType() + "','" + MoyenDeTransport.getNum_ligne() + "', '" + MoyenDeTransport.getDate_de_mise_en_circulations() + "','" + MoyenDeTransport.getEtat() + "','" + MoyenDeTransport.getAccessible_au_handicape() + "','" + MoyenDeTransport.getPrix_achat() + "','" + MoyenDeTransport.getPoids() + "','" + MoyenDeTransport.getLongueur() + "','" + MoyenDeTransport.getLargeur() + "','" + MoyenDeTransport.getEnergie() + "','" + MoyenDeTransport.getNombre_de_place() + "')";
            System.out.println(query);
            st.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<MoyenDeTransport> afficher() {
        List<MoyenDeTransport> MT = new ArrayList<>();

        try {
            Statement stm = cnx.createStatement();

            String query = "SELECT * FROM Moyentransport";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                MoyenDeTransport moyenTransport = new MoyenDeTransport();
                moyenTransport.setId_MoyenTransport(rs.getInt("id_MoyenTransport"));
                moyenTransport.setType(rs.getString("Type"));
                moyenTransport.setNum_ligne(rs.getString("Num_ligne"));
                 moyenTransport.setDate_de_mise_en_circulations(rs.getTimestamp("Date_de_mise_en_circulations").toLocalDateTime().toLocalDate());
                moyenTransport.setEtat(rs.getString("Etat"));
                moyenTransport.setAccessible_au_handicape(rs.getString("Accessible_au_handicape"));
                moyenTransport.setPrix_achat(rs.getString("Prix_achat"));
                moyenTransport.setPoids(rs.getInt("Poids"));
                moyenTransport.setLongueur(rs.getInt("Longueur"));
                moyenTransport.setLargeur(rs.getInt("Largeur"));
                moyenTransport.setEnergie(rs.getString("Energie"));
                moyenTransport.setNombre_de_place(rs.getInt("Nombre_de_place"));

                MT.add(moyenTransport);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return MT;
    }

    @Override
    public void supprimer(long id) {
        try {
            Statement stm = cnx.createStatement();
            String query = "delete from Moyentransport where id_MoyenTransport=" + id;
            stm.executeUpdate(query);
        } catch (SQLException ex) {

        }

    }
    
    

    @Override
    public void modifier(long id_MoyenTransport, MoyenDeTransport MTmodifie) {
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE `MoyenTransport` SET `Type`='" + MTmodifie.getType() + "',`Num_ligne`='" + MTmodifie.getNum_ligne() + "',`Date_de_mise_en_circulations`='" + MTmodifie.getDate_de_mise_en_circulations() + "',`Etat`='" + MTmodifie.getEtat() + "',`Accessible_au_handicape`='" + MTmodifie.getAccessible_au_handicape() + "',`Prix_achat`='" + MTmodifie.getPrix_achat() + "',`Poids`='" + MTmodifie.getPoids() + "',`longueur`='" + MTmodifie.getLongueur() + "',`Largeur`='" + MTmodifie.getLargeur() + "',`Energie`='" + MTmodifie.getEnergie() + "',`Nombre_de_place`='" + MTmodifie.getNombre_de_place() + "' where id_MoyenTransport=" + id_MoyenTransport;
            System.out.println(query);
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public MoyenDeTransport SearchById(long id) throws SQLException {

        Statement stm = cnx.createStatement();
        MoyenDeTransport moyenTransport = new MoyenDeTransport();
        String query = "select * from MoyenTransport where id_MoyenTransport=" + id;
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            moyenTransport.setId_MoyenTransport(rs.getInt("id_MoyenTransport"));
            moyenTransport.setType(rs.getString("Type"));
            moyenTransport.setNum_ligne(rs.getString("Num_ligne"));
            moyenTransport.setDate_de_mise_en_circulations(rs.getTimestamp("Date_de_mise_en_circulations").toLocalDateTime().toLocalDate());
            moyenTransport.setEtat(rs.getString("Etat"));
            moyenTransport.setAccessible_au_handicape(rs.getString("Accessible_au_handicape"));
            moyenTransport.setPrix_achat(rs.getString("Prix_achat"));
            moyenTransport.setPoids(rs.getInt("Poids"));
            moyenTransport.setLongueur(rs.getInt("Longueur"));
            moyenTransport.setLargeur(rs.getInt("Largeur"));
            moyenTransport.setEnergie(rs.getString("Energie"));
            moyenTransport.setNombre_de_place(rs.getInt("Nombre_de_place"));
        }
        return moyenTransport;
    }

    public List<MoyenDeTransport> SearchByReq(String req) {
        List<MoyenDeTransport> MT = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from MoyenTransport where Type like '%" + req + "%' or num_ligne like '%" + req + "%' or Date_de_mise_en_circulations like '%" + req + "%' or Etat like '%" + req + "%' or Etat like Etat like '%" + req + "%' ";
            System.out.println(query);
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                MoyenDeTransport moyenTransport = new MoyenDeTransport();
                moyenTransport.setId_MoyenTransport(rs.getInt("id_MoyenTransport"));
                moyenTransport.setType(rs.getString("Type"));
                moyenTransport.setNum_ligne(rs.getString("Num_ligne"));
                moyenTransport.setDate_de_mise_en_circulations(rs.getTimestamp("Date_de_mise_en_circulations").toLocalDateTime().toLocalDate());
                moyenTransport.setEtat(rs.getString("Etat"));
                moyenTransport.setAccessible_au_handicape(rs.getString("Accessible_au_handicape"));
                moyenTransport.setPrix_achat(rs.getString("Prix_achat"));
                moyenTransport.setPoids(rs.getInt("Poids"));
                moyenTransport.setLongueur(rs.getInt("Longueur"));
                moyenTransport.setLargeur(rs.getInt("Largeur"));
                moyenTransport.setEnergie(rs.getString("Energie"));
                moyenTransport.setNombre_de_place(rs.getInt("Nombre_de_place"));
                MT.add(moyenTransport);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return MT;
    }

    public HashMap<String, Double> StatisqueParType() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT Type, COUNT(*) as nb FROM moyentransport GROUP BY Type;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("Type");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    

    

    
}

