/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

import dev.masters.entites.MoyenTransport;
import dev.masters.services.MoyenTransportService;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mortadha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        MoyenTransportService mts = new MoyenTransportService();
        
        MoyenTransport x = new MoyenTransport("a","z","e","r","t","y","u","i","o","p","q");
        
       //mts.ajouter(x);
     // mts.supprimer(11);
     // mts.modifier(10, x);
      //List<MoyenTransport> list = mts.afficher();
        //System.out.println(list.toString());
      
        
       // System.out.println(mts.SearchById(9).toString());
    

       
    }
    
}
