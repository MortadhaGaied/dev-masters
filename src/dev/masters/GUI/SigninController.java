/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.User;
import dev.masters.services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class SigninController implements Initializable {

    @FXML
    private TextField TFfirstname;
    @FXML
    private PasswordField PFpassword_signup;
    @FXML
    private TextField TFlastname;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFusername_signup;
    @FXML
    private TextField TFnumber;
    @FXML
    private DatePicker DPbirthday;
    
    ServiceUser su = new ServiceUser() ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Signin(ActionEvent event) {
        User u = new User(TFfirstname.getText(),TFlastname.getText(),TFemail.getText(),TFusername_signup.getText(),PFpassword_signup.getText(),Integer.parseInt(TFnumber.getText()),DPbirthday.getValue().atStartOfDay());
        
        su.ajouter(u);
    }
    
}
