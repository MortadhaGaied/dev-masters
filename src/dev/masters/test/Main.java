
package dev.masters.test;

import dev.masters.entites.Position;
import dev.masters.entites.User;
import dev.masters.services.ServiceUser;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    
    public static void main(String[] args) {
        ServiceUser su = new ServiceUser();
        User u =new User("aa","ba","bab","aza","acca",5465,LocalDateTime.now());
        //su.ajouter(u);
        try {        
            //su.modifier(10L, u);
            //su.supprimer(10L);
            System.out.println(su.afficher());
            //System.out.println(su.checkLogin("aa", "0000"));
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
