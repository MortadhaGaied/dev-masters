/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author vikta
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TextField txtUsername1;
    @FXML
    private TextField txtObject1;
    @FXML
    private TextField txtDescription1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setUsername1(String username1){
        this.txtUsername1.setText(username1);
    
    
    }
    public void setObject1(String object1){
        this.txtObject1.setText(object1);
    
    }
    public void setDescription1(String Description1){
    this.txtDescription1.setText(Description1);
    }
    
    
}
