/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.entites.Position;
import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.services.VoyageService;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mortadha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Position posisiton_depart=new Position("123","13154");
        Position position_arrive=new Position("123","1315");
        Station station = new Station("sousse", "123465,123465", "13b");
        
        MoyenDeTransport mt = new MoyenDeTransport(53,"Métro", "2","2021-10-07", "En_Marche", "Oui", "123", 0, 0, 0, "En_Marche", 0);
        Voyage voyage = new Voyage(posisiton_depart, position_arrive, LocalDateTime.now(), station, mt);
        
        
        VoyageService vs = new VoyageService();
        vs.ajouter(voyage);
        
       // System.out.println("voyage by id :  \n");
        // System.out.println(vs.GetVoyageById(17L));
         //System.out.println("recherche avancee by date");
         //System.out.println(vs.getVoyagesByRefStation("13b"));
         //System.out.println(vs.getVoyagesByDate(LocalDateTime.now()));
         
        try {
            //vs.modifier(17, voyage);
           // vs.archiver(voyage);
            //vs.desarchiver(voyage);
            System.out.println(vs.afficher());
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
    }

    /**
     *
     * @author mouha
     */
    public static class voyageMain extends Application {

        @Override
        public void start(Stage primaryStage) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/dev/masters/GUI/voyage/VoyageMenu.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            launch(args);
        }
    }
    
}