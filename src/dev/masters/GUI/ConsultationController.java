/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Reclamation;
import dev.masters.services.ServiceReclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author vikta
 */
public class ConsultationController implements Initializable {

    @FXML
    private TableView<Reclamation> table_consultation;
    @FXML
    private TableColumn<Reclamation, String> username_consultation;
    @FXML
    private TableColumn<Reclamation, String> object_consultation;
    @FXML
    private TableColumn<Reclamation, String> description_consultation;
    @FXML
    private Button modifier_consultation;
    @FXML
    private Button supprimer_consultation;
    
    ServiceReclamation rs = new ServiceReclamation();
    static Stage stageModifier;
    static Reclamation d;
    ObservableList<Reclamation> 
    data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshList();
        
       
    } 
    public void refreshList(){
       data.clear();
        try {
            data=FXCollections.observableArrayList(rs.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
            username_consultation.setCellValueFactory(new PropertyValueFactory("username_reclamation"));
            object_consultation.setCellValueFactory(new PropertyValueFactory("object_reclamation"));
            description_consultation.setCellValueFactory(new PropertyValueFactory("description_reclamation"));
            
//            rs.afficher();
//            ServiceReclamation rs = new ServiceReclamation();
//            ArrayList arrayList = (ArrayList) rs.afficher();
//            ObservableList observableList = FXCollections.observableArrayList(arrayList);
            table_consultation.setItems(data);
            
            
            
            // TODO
        
        
    
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        
            List<Reclamation> reclamation = table_consultation.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette reclamation ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                try {
                    new ServiceReclamation().supprimer(reclamation.get(0).getId_reclamation());
                    System.out.println(reclamation.get(0).getId_reclamation());
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            refreshList();
        }

    @FXML
    private void RetourConsultation(ActionEvent event) {
               try {
             
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dev/masters/GUI/InterfaceG.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu");
            stage.show();
            
            
        } catch (IOException ex) {
            ex.getMessage();
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    
   
    
    }

    @FXML
    private void btnPDF(ActionEvent event) {
       
    }
    }

        
    
    
    

