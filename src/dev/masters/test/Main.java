
package dev.masters.test;

import dev.masters.services.ServiceUser;
import java.sql.SQLException;

public class Main {


    
    public static void main(String[] args) {
        ServiceUser su = new ServiceUser();
        try {
            System.out.println(su.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
