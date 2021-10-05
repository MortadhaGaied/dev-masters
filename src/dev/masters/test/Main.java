/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

import dev.masters.services.ServiceUser;
import java.sql.SQLException;

/**
 *
 * @author Mortadha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        ServiceUser su = new ServiceUser();
        try {
            System.out.println(su.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
