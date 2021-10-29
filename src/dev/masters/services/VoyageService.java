/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.MoyenTransport;
import dev.masters.entites.Position;
import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    /******************************** Add Voyage *********************************/
    @Override
    public void ajouter(Voyage voyage) {
        
        //check for existance of the station and the MDP first 
        
         try {
           
            String query ="INSERT INTO `voyage`(`position_depart`, `position_arrive`, `date`,`station`, `moyen_de_transport`) VALUES ('"+voyage.getPosition_depart().getGoogleMapsPostionFormat()+"','"+voyage.getPosition_arrive().getGoogleMapsPostionFormat()+"','"+voyage.getDate_de_voyage()+"','"+voyage.getStation().getRefStation()+"','"+voyage.getMoyen_transport()+"')";
      
        st.executeUpdate(query);
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
/*****************************getAll Voyages***************************/
    @Override
    public List<Voyage> afficher() throws SQLException {
        List<Voyage> voyages = new ArrayList<Voyage>();
        
       
        
        String query = "SELECT * FROM voyage";
         ResultSet rs= st.executeQuery(query);
        while (rs.next()){
            Voyage voyage = new Voyage();
            voyage.setId(rs.getInt("id"));
            voyage.setPosition_depart(new Position(rs.getString("position_depart").split(",")[0],rs.getString("position_depart").split(",")[1].trim()));
            voyage.setPosition_arrive(new Position(rs.getString("position_arrive").split(",")[0],rs.getString("position_arrive").split(",")[1].trim()));
            voyage.setDate_de_voyage(rs.getTimestamp(4).toLocalDateTime());
            
            //get the station that contains the specific id 
            voyage.setStation(new Station(rs.getString("station")));
            voyage.setMoyen_transport(new MoyenTransport(rs.getLong("moyen_de_transport")));
            voyages.add(voyage);
        }
        return voyages;
    }

    @Override
    public void supprimer(long id) throws SQLException {
        
        String query = "delete from voyag where id="+id;
        st.executeUpdate(query);
    }
    
    
    
    /********************** Voyage Search By ID********************/
    public Voyage GetVoyageById(long id){
        
        Voyage voyage = new Voyage();
        String query
            = "select * from voyage where id= ?";
        
         try{
             PreparedStatement ps
            = cnx.prepareStatement(query);
  
        ps.setLong(1, id );
        
        ResultSet rs = ps.executeQuery();
        
  
        while (rs.next()) {
            
            voyage.setId(rs.getLong("id"));
            voyage.setPosition_depart(new Position(rs.getString("position_depart").split(",")[0],rs.getString("position_depart").split(",")[1].trim()));
            voyage.setPosition_arrive(new Position(rs.getString("position_arrive").split(",")[0],rs.getString("position_arrive").split(",")[1].trim()));
            voyage.setDate_de_voyage(rs.getTimestamp(4).toLocalDateTime());
            
            //get the station that contains the specific id 
            voyage.setStation(new Station(rs.getString("station")));
            voyage.setMoyen_transport(new MoyenTransport(rs.getLong("moyen_de_transport")));
        }
  
        
         }
         catch(SQLException e){
             System.out.println(e.getMessage());
         }
        
        return voyage;
    
    }
    
    /*****************************update Voyage****************************/
    @Override
    public void modifier(long id, Voyage updatedVoyage) throws SQLException {
        
         //check the avalability of the stations and the MDT(moyen de transport) if they exist 
         
        String query
            = "update voyage set position_depart=?, "
              + " position_arrive= ?, "
                +" date= ?, "
                +" station= ?, "
                +" moyen_de_transport= ?, "
                +" where id = ?";
        PreparedStatement ps
            = cnx.prepareStatement(query);
        ps.setString(1, updatedVoyage.getPosition_depart().getGoogleMapsPostionFormat());
        ps.setString(2, updatedVoyage.getPosition_arrive().getGoogleMapsPostionFormat());
        ps.setObject(3, updatedVoyage.getDate_de_voyage());
        ps.setLong(3, updatedVoyage.getMoyen_transport().getId());
        
        ps.executeUpdate();
        
        
       
        
            
    
        
    }
       
    
}
