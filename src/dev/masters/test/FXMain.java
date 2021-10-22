/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Mortadha
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/dev/masters/GUI/FXMLGSTuser.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/dev/masters/GUI/CascadeStyleSheet.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
