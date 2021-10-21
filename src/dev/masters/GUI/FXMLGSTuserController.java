/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Roles;
import dev.masters.entites.User;
import dev.masters.services.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLGSTuserController implements Initializable {

    @FXML
    private TextField TFforum_FN;
    @FXML
    private TextField TFforum_LN;
    @FXML
    private TextField TFforum_E;
    @FXML
    private TextField TFforum_UN;
    @FXML
    private PasswordField PFforum_PWD;
    @FXML
    private TextField TFforum_Num;
    @FXML
    private DatePicker DPforum_B;
    @FXML
    private ComboBox<Roles> ComboRoles;
    @FXML
    private TableView<User> TableUserView;
    @FXML
    private TextField TFSearch;
    @FXML
    private TableColumn<User, Long> id_userc;
    @FXML
    private TableColumn<User, String> FN_userc;
    @FXML
    private TableColumn<User, String> LN_userc;
    @FXML
    private TableColumn<User, String> Email_userc;
    @FXML
    private TableColumn<User, String> UN_userc;
    @FXML
    private TableColumn<User, String> pwd_userc;
    @FXML
    private TableColumn<User, Integer> Num_userc;
    @FXML
    private TableColumn<User, Roles> Role_userc;
    @FXML
    private TableColumn<User, LocalDateTime> Birthday_userc;
    @FXML
    private TableColumn<User, LocalDateTime> ACD_userc;
    @FXML
    private TableColumn<User, LocalDateTime> AUD_userc;
    
    ServiceUser su =new ServiceUser();
    long id_user_modifier;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
            
        refreshlist();
        ComboRoles.getItems().setAll(Roles.values());
        
        
    }    
    public void refreshlist(){

         ObservableList<User> data=FXCollections.observableArrayList();
         data.clear();
        try {
            data=FXCollections.observableArrayList(su.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGSTuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        id_userc.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        FN_userc.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        LN_userc.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        Email_userc.setCellValueFactory(new PropertyValueFactory<>("email"));
        UN_userc.setCellValueFactory(new PropertyValueFactory<>("username"));
        pwd_userc.setCellValueFactory(new PropertyValueFactory<>("password"));
        Num_userc.setCellValueFactory(new PropertyValueFactory<>("number"));
        Role_userc.setCellValueFactory(new PropertyValueFactory<>("role"));
        Birthday_userc.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        ACD_userc.setCellValueFactory(new PropertyValueFactory<>("date_created_user"));
        AUD_userc.setCellValueFactory(new PropertyValueFactory<>("last_updated_user"));
        TableUserView.setItems(data);
        
         
        

    }
    @FXML
    private void Save_user(ActionEvent event) {
        
        User u =new User(
                TFforum_FN.getText(),
                TFforum_LN.getText(),
                TFforum_E.getText(),
                TFforum_UN.getText(),
                PFforum_PWD.getText(),
                Integer.parseInt(TFforum_Num.getText()),
                ComboRoles.getValue(),
                DPforum_B.getValue().atStartOfDay()
        );
        su.ajouter(u);
        refreshlist();
    }

    @FXML
    private void Delete_user(ActionEvent event) {
        long id_user;
        id_user=TableUserView.getSelectionModel().getSelectedItem().getId_user();
        try {
            su.supprimer(id_user);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGSTuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }

    @FXML
    private void Update_User(ActionEvent event) {
        
       User u =new User(
                TFforum_FN.getText(),
                TFforum_LN.getText(),
                TFforum_E.getText(),
                TFforum_UN.getText(),
                PFforum_PWD.getText(),
                Integer.parseInt(TFforum_Num.getText()),
                ComboRoles.getValue(),
                DPforum_B.getValue().atStartOfDay()
        );
        try {
            su.modifier(id_user_modifier, u);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGSTuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }

    @FXML
    private void Search_user(ActionEvent event) {
    }

    @FXML
    private void fillforum(MouseEvent event) {
        User u=TableUserView.getSelectionModel().getSelectedItem();
        id_user_modifier=u.getId_user();
        TFforum_FN.setText(u.getFirst_name());
        TFforum_LN.setText(u.getLast_name());
        TFforum_E.setText(u.getEmail());
        TFforum_UN.setText(u.getUsername());
        PFforum_PWD.setText(u.getPassword());
        TFforum_Num.setText(Integer.toString(u.getNumber()));
        ComboRoles.setValue(u.getRole());
        DPforum_B.setValue(u.getBirthday().toLocalDate());
    }
    
}
