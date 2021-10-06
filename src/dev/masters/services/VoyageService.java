/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mouha
 */
public class VoyageService implements IService<Voyage> {
    Connection cnx;
    Statement st;
    
    public VoyageService(){
        
        
        try{
            cnx = Myconnexion.getInstance().getCnx();
        st = cnx.createStatement();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        
    }
    @Override
    public void ajouter(Voyage voyage) {
        
         try {
           
            String query ="INSERT INTO `voyage`(`position_depart`, `position_arrive`, `date`,`station`, `moyen_de_transport`) VALUES ('"+voyage.getPosition_depart()+"','"+voyage.getPosition_arrive()+"','"+voyage.getDate_de_voyage()+"','"+voyage.getStation().getNom_station()+"','"+voyage.getMoyen_transport()+"')";
      
        st.executeUpdate(query);
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Voyage> afficher() throws SQLException {
        List<Voyage> voyages = new ArrayList<Voyage>();
        
       
        
        String query = "SELECT * FROM voyage";
         ResultSet rs= st.executeQuery(query);
        while (rs.next()){
            Voyage voyage = new Voyage();
            voyage.setId(rs.getInt("id"));
            voyage.setPosition_depart(rs.getString("position_depart"));
            voyage.setPosition_arrive(rs.getString("position_arrive"));
            voyage.setDate_de_voyage(rs.getTimestamp(7).toLocalDateTime());
            voyage.setStation(new Station(rs.getString("station")));
            
            voyages.add(voyage);
        }
        return voyages;
    }

    @Override
    public void supprimer(Long id) throws SQLException {
        
        String query = "delete from voyag where id="+id;
        st.executeUpdate(query);
    }
    public Voyage GetVoyageById(Long id){
        return new Voyage() ;
    }
    @Override
    public void modifier(Long id, Voyage t) throws SQLException {
        
        Voyage voyage = GetVoyageById(id);
        
    }
       
    
}
