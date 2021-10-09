/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

import dev.masters.entites.Reservation;
import dev.masters.services.ServiceReservation;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mortadha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceReservation sr = new ServiceReservation();
        Reservation r =new Reservation(LocalDateTime.now(),"bbb");
        //sr.ajouter(r);
        try {
            //sr.modifier(3, r);
            //sr.supprimer(4);
            System.out.println(sr.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
