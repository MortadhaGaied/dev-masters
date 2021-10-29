/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class dashboardController implements Initializable {

    @FXML
    private AnchorPane root1;
    @FXML
    private Pane context;
    @FXML
    private JFXButton dtnDashBoard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            DashBoardOnAction();
            new FadeIn(context).play();
        
    }    
    private void setUi(String location) {
        try {
            context.getChildren().clear();
            context.getChildren().add(FXMLLoader.load(this.getClass().
                    getResource("/dev/masters/GUI/" + location + ".fxml")));
        } catch (IOException ex) {
            Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DashBoardOnAction() {
        setUi("DashboardForm");
        new FadeIn(context).play();
    }
    @FXML
    private void btnLogOut() {
         Stage stage = (Stage) context.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void gotoGestionUtilisateur() {
        setUi("FXMLGSTuser");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoGestionVoyage() {
        setUi("DashboardForm");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoGestionMoyenTransport() {
        setUi("DashboardForm");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoGestionReclamation() {
        setUi("DashboardForm");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoGestionAbonnement() {
        setUi("DashboardForm");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoGestionReservation() {
        setUi("DashboardForm");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoAbout() {
    }
    
}
