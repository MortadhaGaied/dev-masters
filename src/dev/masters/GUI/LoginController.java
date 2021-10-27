/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;
/*
import com.restfb.*;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
*/
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private String app_Id="1198561063966256";
    private String app_Secret="fc8dc02838807fafeb2eb1c561c7c055";
    private String redirect_url="http://localhost/";
    private String redirect_url_encoded="http%3A%2F%2Flocalhost%2F";
    private String state="1919";
    private String authentification="https://www.facebook.com/v8.0/dialog/oauth?client_id="+app_Id+"&redirect_uri="+redirect_url_encoded+"&state="+state;
    private String graph="https://graph.facebook.com/v8.0/oauth/access_token?client_id"+app_Id+"&redirect_uri"+redirect_url_encoded+"&client_secret"+app_Secret+"&code=";
    
    
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
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/FXMLGSTuser.fxml"));
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
    }

    @FXML
    private void ConnectWithFacebook(ActionEvent event) {
        
         WebView webView =new WebView();
        WebEngine eg =webView.getEngine();
        eg.load(authentification);
        Pane wView = new Pane();
        wView.getChildren().add(webView);
        Stage stage =new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(wView,700,450));
        stage.show();
        eg.locationProperty().addListener((obs,oldlocation,newlocation)->{
            if(newlocation !=null && newlocation.startsWith("http://localhost")){
                int codeOffset = newlocation.indexOf("code=");
                String code = newlocation.substring(codeOffset+"code=".length());
                graph+=code;
                System.out.println(graph);
                DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
                FacebookClient.AccessToken accessToken =facebookClient.obtainUserAccessToken(app_Id, app_Secret,"http://localhost/",code);
                String access_token=accessToken.getAccessToken();
                FacebookClient fbClient =new DefaultFacebookClient(access_token,Version.LATEST);
                fbClient.createClientWithAccessToken(access_token);
                JsonObject profile_pic=fbClient.fetchObject("me/picture", JsonObject.class, Parameter.with("redirect", false));
                User user = fbClient.fetchObject("me", User.class);
                System.out.println(user.getName());
//                this.name.setText(user.getName());
//                int si=profile_pic.toString().indexOf("url\":\"");
//                int ei=profile_pic.toString().indexOf("\",\"");
//                String profile_url =profile_pic.toString().substring(si+6,ei);
//                this.profile_pic.setFill(new ImagePattern(new Image(profile_url)));
                
                
            }
        });

    }
    
}
