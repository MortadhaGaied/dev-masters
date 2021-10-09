
package dev.masters.test;

import dev.masters.entites.User;
import dev.masters.services.ServiceUser;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {


    
    public static void main(String[] args) {
        ServiceUser su = new ServiceUser();
        User u =new User("bb","b","bb","bb",5555,LocalDateTime.now());
        //su.ajouter(u);
        try {
            //su.modifier(10L, u);
            //su.supprimer(10L);
            System.out.println(su.SearchById(2L));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
