
package dev.masters.test;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.services.ServiceMoyenDeTransport;
import java.sql.SQLException;


public class MainMoyenTransport {
         public static void main(String[] args) {
        // TODO code application logic here
        
       MoyenDeTransport moyenTransport = new MoyenDeTransport();
       MoyenDeTransport moyenTransport1=new MoyenDeTransport("Denden",4,10,"oui");
         ServiceMoyenDeTransport  MT= new ServiceMoyenDeTransport ();
        MT.ajouter(moyenTransport);
        
        try {
            MT.supprimer(5L);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        try {
            System.out.println(MT.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
        try {
            System.out.println(MT.SearchById(9L));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            MT.modifier(8L, moyenTransport1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            System.out.println(MT.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }
}
