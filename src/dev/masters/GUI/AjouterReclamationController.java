/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Reclamation;
import dev.masters.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author vikta
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtObject;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnEnvoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveReclamation(ActionEvent event) {
        try {
            String raUsername = txtUsername.getText();
            String raObject = txtObject.getText();
            String raDescription = txtDescription.getText();
            Reclamation rr = new Reclamation(raUsername, raObject, raDescription);
            ServiceReclamation rs = new ServiceReclamation();
            rs.ajouter(rr);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dev/masters/GUI/InterfaceG.fxml"));
            Parent root = loader.load();
            Stage stage =new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Ajouter Reclamation");
            stage.show();
//            ReclamationListController rl = loader.getController();
//            rl.setLReclamation(rs.afficherReclamation().toString());
            
//            AfficherReclamationController ac = loader.getController();
//            ac.setUsername1(raUsername);
//            ac.setObject1(raObject);
//            ac.setDescription1(raDescription) ;
//            txtUsername.getScene().setRoot(root);
             
             

             } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
