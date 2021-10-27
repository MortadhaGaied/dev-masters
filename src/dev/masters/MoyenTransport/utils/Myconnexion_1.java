/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.MoyenTransport.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mortadha
 */
public class Myconnexion_1 {

    final String URL = "jdbc:mysql://127.0.0.1/dev-masters"; //jdbc:mysql://127.0.0.1:3307/dev-masters
    final String USER = "root";
    final String PWD = "";
    private static Connection cnx;
    private static Myconnexion_1 instance;

    private Myconnexion_1() {

        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("connexion etablie ......");
        } catch (SQLException ex) {
            System.out.println("Error while connecting to db");
            //System.out.println(ex.getMessage());
        }
    }

    public static Myconnexion_1 getInstance() {

        if (instance == null) {
            instance = new Myconnexion_1();
        }

        return instance;
    }

    public static Connection getCnx() {
        return cnx;
    }

}
