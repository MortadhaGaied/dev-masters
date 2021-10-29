/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Roles;
import dev.masters.entites.User;
import dev.masters.services.ServiceUser;
import dev.masters.utils.JavaMail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    JavaMail mail = new JavaMail();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static boolean isNumeric(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

    @FXML
    private void Signin(ActionEvent event) {
        StringBuilder errors = new StringBuilder();
        if(TFfirstname.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name.\n");
        }
        if(TFlastname.getText().trim().isEmpty()){
            errors.append("- Please enter a Last Name.\n");
        }
        if(TFusername_signup.getText().trim().isEmpty()){
            errors.append("- Please enter a Username.\n");
        }
        if(PFpassword_signup.getText().trim().isEmpty()){
            errors.append("- Please enter a Password.\n");
        }
        if(TFemail.getText().trim().isEmpty()){
            errors.append("- Please enter a Email.\n");
        }
        if(TFnumber.getText().trim().isEmpty()){
            errors.append("- Please enter a Number.\n");
        }
        if(DPbirthday.getValue().toString().trim().isEmpty()){
            errors.append("- Please enter a Birthday.\n");
        }
        if(!isNumeric(TFnumber.getText())){
            errors.append("- Please enter a Valid Number.\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
        }
        else{
            User u = new User(TFfirstname.getText(),
                    TFlastname.getText(),
                    TFemail.getText(),
                    TFusername_signup.getText(),
                    PFpassword_signup.getText(),
                    Integer.parseInt(TFnumber.getText()),
                    Roles.CLIENT,
                    DPbirthday.getValue().atStartOfDay()
            );
            su.ajouter(u);
            
        }
    }
    
}
