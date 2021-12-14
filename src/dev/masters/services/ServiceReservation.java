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
import java.time.LocalDateTime;
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

            String query ="INSERT INTO `reservation`(`id`, `iduser`, `date`, `ref`, `etat` ,`price`, `name`) VALUES ('"+reservation.getId_reservation()+"','"+reservation.getId_user()+"','"+reservation.getDate()+"','"+reservation.getRef()+"', '"+reservation.getEtat()+"', '"+reservation.getPrice()+"', '"+reservation.getName()+"')";

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
            reservation.setId_reservation(rs.getLong("id"));
            reservation.setId_user(rs.getInt("iduser"));
            
            reservation.setDate(rs.getTimestamp(3).toLocalDateTime());
            reservation.setRef(rs.getString("ref"));
            
            reservation.setEtat(rs.getString("etat"));
            reservation.setPrice(rs.getLong("price"));
            reservation.setName(rs.getString("name"));

            lr.add(reservation);
        }
        return lr;
    }

    @Override
    public void supprimer(long id_reservation) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from reservation where id="+id_reservation;
        stm.executeUpdate(query);
        
    } 
    
    public void modifier(long id_reservation,Reservation reservation) throws SQLException {
        Statement stm = cnx.createStatement();

        String query = "UPDATE `reservation` SET `iduser`='"+reservation.getId_user()+"',`date`='"+reservation.getDate()+"',`ref`='"+reservation.getRef()+"',`etat`='"+reservation.getEtat()+"',`price`='"+reservation.getPrice()+"',`name`='"+reservation.getName()+"' WHERE id="+id_reservation;

        stm.executeUpdate(query);
    }
}




