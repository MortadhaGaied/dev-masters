/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;



import dev.masters.entites.MoyenTransport;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mahdi
 */
public class MoyenTransportService implements IService<MoyenTransport> {
    
    Connection cnx;

    public MoyenTransportService() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(MoyenTransport MoyenTransport) {
                try {
            Statement st = cnx.createStatement();
            String query ="INSERT INTO `MoyenTransport`(`type`, `Num_ligne`, `Date_de_mise_en_circulations`,`Etat`, `Accessible_au_handicape`, `Prix_achat`, `Poids`, `longueur`, `Largeur`, `Energie`,`Nombre_de_place`) VALUES ('"+MoyenTransport.getType()+"','"+MoyenTransport.getNum_ligne()+"', '"+MoyenTransport.getDate_de_mise_en_circulations()+"','"+MoyenTransport.getEtat()+"','"+MoyenTransport.getAccessible_au_handicape()+"','"+MoyenTransport.getPrix_achat()+"','"+MoyenTransport.getPoids()+"','"+MoyenTransport.getLongueur()+"','"+MoyenTransport.getLargeur()+"','"+MoyenTransport.getEnergie()+"','"+MoyenTransport.getNombre_de_place()+"')";
            System.out.println(query);
            st.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<MoyenTransport> afficher() throws SQLException {
                Statement stm = cnx.createStatement();
        List<MoyenTransport> MT = new ArrayList<>();
    
    String query = "SELECT * FROM Moyentransport";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
          MoyenTransport moyenTransport = new MoyenTransport();
            moyenTransport.setId_MoyenTransport(rs.getInt("id_MoyenTransport"));
            moyenTransport.setType(rs.getString("Type"));
            moyenTransport.setNum_ligne(rs.getString("Num_ligne"));
            moyenTransport.setDate_de_mise_en_circulations(rs.getString("Date_de_mise_en_circulations"));
            moyenTransport.setAccessible_au_handicape(rs.getString("Accessible_au_handicape"));
            moyenTransport.setPrix_achat(rs.getString("Prix_achat"));
            moyenTransport.setPoids(rs.getString("Poids"));
            moyenTransport.setLongueur(rs.getString("Longueur"));
            moyenTransport.setLargeur(rs.getString("Largeur"));
            moyenTransport.setEnergie(rs.getString("Energie"));
            moyenTransport.setNombre_de_place(rs.getString("Nombre_de_place"));

            MT.add(moyenTransport);
        }
        return MT;
    }

    @Override
    public void supprimer(long id_MoyenTransport) throws SQLException {
                Statement stm = cnx.createStatement();
        String query = "delete from Moyentransport where Id_MoyenTransport="+id_MoyenTransport;
        stm.executeUpdate(query);
    }

    @Override
    public void modifier(long id_MoyenTransport, MoyenTransport MTmodifie) throws SQLException {
                try {
            Statement stm = cnx.createStatement();
            String query  = "UPDATE `MoyenTransport` SET `type`='"+MTmodifie.getType()+"',`Num_ligne`='"+MTmodifie.getNum_ligne()+"',`Date_de_mise_en_circulations`='"+MTmodifie.getDate_de_mise_en_circulations()+"',`Etat`='"+MTmodifie.getEtat()+"',`Accessible_au_handicape`='"+MTmodifie.getAccessible_au_handicape()+"',`Prix_achat`='"+MTmodifie.getPrix_achat()+"',`Poids`='"+MTmodifie.getPoids()+"',`longueur`='"+MTmodifie.getLongueur()+"',`Largeur`='"+MTmodifie.getLargeur()+"',`Energie`='"+MTmodifie.getEnergie()+"',`Nombre_de_place`='"+MTmodifie.getNombre_de_place()+"' where id_MoyenTransport="+id_MoyenTransport;
            System.out.println(query);
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        
    }
    
     public MoyenTransport SearchById(long id) throws SQLException {

        Statement stm = cnx.createStatement();
        MoyenTransport moyenTransport = new MoyenTransport();
        String query = "select * from MoyenTransport where id_MoyenTransport=" + id;
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            moyenTransport.setId_MoyenTransport(rs.getInt("id_MoyenTransport"));
            moyenTransport.setType(rs.getString("Type"));
            moyenTransport.setNum_ligne(rs.getString("Num_ligne"));
            moyenTransport.setDate_de_mise_en_circulations(rs.getString("Date_de_mise_en_circulations"));
            moyenTransport.setAccessible_au_handicape(rs.getString("Accessible_au_handicape"));
            moyenTransport.setPrix_achat(rs.getString("Prix_achat"));
            moyenTransport.setPoids(rs.getString("Poids"));
            moyenTransport.setLongueur(rs.getString("Longueur"));
            moyenTransport.setLargeur(rs.getString("Largeur"));
            moyenTransport.setEnergie(rs.getString("Energie"));
            moyenTransport.setNombre_de_place(rs.getString("Nombre_de_place"));
        }
        return moyenTransport;
     }
     
        public List<MoyenTransport> rechercheParType(String type) throws SQLException {
        Statement stm = cnx.createStatement();
        MoyenTransport moyenTransport = new MoyenTransport();
        String query = "select * from MoyenTransport where id_MoyenTransport=" + type;
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            moyenTransport.setId_MoyenTransport(rs.getInt("id_MoyenTransport"));
            moyenTransport.setType(rs.getString("Type"));
            moyenTransport.setNum_ligne(rs.getString("Num_ligne"));
            moyenTransport.setDate_de_mise_en_circulations(rs.getString("Date_de_mise_en_circulations"));
            moyenTransport.setAccessible_au_handicape(rs.getString("Accessible_au_handicape"));
            moyenTransport.setPrix_achat(rs.getString("Prix_achat"));
            moyenTransport.setPoids(rs.getString("Poids"));
            moyenTransport.setLongueur(rs.getString("Longueur"));
            moyenTransport.setLargeur(rs.getString("Largeur"));
            moyenTransport.setEnergie(rs.getString("Energie"));
            moyenTransport.setNombre_de_place(rs.getString("Nombre_de_place"));
        }
        
       return (List<MoyenTransport>) moyenTransport;
        }
    
}
