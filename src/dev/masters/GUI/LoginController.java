/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;


import dev.masters.entites.Roles;

import dev.masters.oauth.OAuthAuthenticator;
import dev.masters.oauth.OAuthFacebookAuthenticator;
import dev.masters.oauth.OAuthGoogleAuthenticator;
import dev.masters.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username_login;
    @FXML
    private PasswordField password_login;

    ServiceUser su = new ServiceUser() ;
    @FXML
    private Text a;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void gotosignup(ActionEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/Signup.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            
            stage.show();
    }

    @FXML
    private void login(ActionEvent event) {
        StringBuilder errors = new StringBuilder();
        if(username_login.getText().trim().isEmpty()){
            errors.append("- Please enter a Username.\n");
        }
        if(password_login.getText().trim().isEmpty()){
            errors.append("- Please enter a Password.\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
        }
        else{
            try {
                try {
                    if(su.checkLogin(username_login.getText(), password_login.getText())){
                        System.out.println("Your loged in");
                    }
                    else{
                        System.out.println("Check ur username or password!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
                stageclose.close();
                if(su.SearchByUsername(username_login.getText()).getRole()==Roles.ADMIN){
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/dev/masters/GUI/Dashboard.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
                }
                else if(su.SearchByUsername(username_login.getText()).getRole()==Roles.RESPONSABLE_RECLAMATION){
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/dev/masters/GUI/FXMLGSTreclamation.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
                }
                else if(su.SearchByUsername(username_login.getText()).getRole()==Roles.RESPONSABLE_RESERVATION){
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/dev/masters/GUI/FXMLGSTreservation.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ConnectWithFacebook(ActionEvent event) {
        
        String FACEBOOK_clientID = "1198561063966256";
        String FACEBOOK_redirectUri = "http://localhost/";
        String FACEBOOK_fieldsString = "id,name,first_name,last_name,email,birthday,gender";
        String FACEBOOK_clientSecret = "fc8dc02838807fafeb2eb1c561c7c055";

        OAuthAuthenticator authFB = new OAuthFacebookAuthenticator(FACEBOOK_clientID, FACEBOOK_redirectUri, FACEBOOK_clientSecret, FACEBOOK_fieldsString);
        authFB.startLogin(event);
        
        


    }

    @FXML
    private void ConnectWithGoogle(ActionEvent event) {
        String gClientId = "970645450510-f29v5rrcbpgo8di0vr4luteslp15oi8j.apps.googleusercontent.com";
        String gRedir = "http://localhost/";
        String gScope = "https://www.googleapis.com/auth/userinfo.profile";
        String gSecret = "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";
        OAuthAuthenticator auth = new OAuthGoogleAuthenticator(gClientId, gRedir, gSecret, gScope);
        auth.startLogin(event);
    }
    
}
