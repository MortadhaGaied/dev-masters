/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;


import dev.masters.entites.Reservation;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceReservation implements IService<Reservation>{
    Connection cnx;
    
    public ServiceReservation (){
        cnx = Myconnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Reservation reservation) {
      
        Statement st;
        try {
            st = cnx.createStatement();

            String query ="INSERT INTO `reservation`(`id_reservation`, `date_reservation`, `etat_reservation`) VALUES ('"+reservation.getId_reservation()+"','"+reservation.getDate()+"','"+reservation.getEtat()+"')";

        st.executeUpdate(query);
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
    }

    @Override
    public List<Reservation> afficher() throws SQLException {
    Statement stm = cnx.createStatement();
    List<Reservation> lr = new ArrayList<>();
    
    String query = "SELECT * FROM reservation";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            Reservation reservation = new Reservation();
            reservation.setId_reservation(rs.getLong("id_reservation"));

            reservation.setDate(rs.getTimestamp(2).toLocalDateTime());
            reservation.setEtat(rs.getString("etat_reservation"));

            lr.add(reservation);
        }
        return lr;
    }

    @Override
    public void supprimer(long id_reservation) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from reservation where id_reservation="+id_reservation;
        stm.executeUpdate(query);
        
    } 
    
    public void modifier(long id_reservation,Reservation reservation) throws SQLException {
        Statement stm = cnx.createStatement();

        String query = "UPDATE `reservation` SET `date_reservation`='"+reservation.getDate()+"',`etat_reservation`='"+reservation.getEtat()+"' WHERE id_reservation="+id_reservation;

        stm.executeUpdate(query);
    }



}

}

