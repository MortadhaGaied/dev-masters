/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Reclamation;
import dev.masters.entites.User;
import dev.masters.services.ServiceReclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLGSTrecController implements Initializable {

    @FXML
    private TextField TFforum_UN;
    @FXML
    private TextField TFobj;
    @FXML
    private TextField TFdesc;
    @FXML
    private TableView<Reclamation> TableRecView;
    @FXML
    private TableColumn<Reclamation, Integer> id_recc;
    @FXML
    private TableColumn<Reclamation, String> UN_recc;
    @FXML
    private TableColumn<Reclamation, String> DESC_recc;
    @FXML
    private TableColumn<Reclamation, String> Obj_rec;
    @FXML
    private TextField TFSearch;
    long id_rec_modifier;
    ServiceReclamation rs = new ServiceReclamation();

    ObservableList<Reclamation> 
    data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshList();
    }    
    public void refreshList(){
       data.clear();
        try {
            data=FXCollections.observableArrayList(rs.afficher());
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
            id_recc.setCellValueFactory(new PropertyValueFactory("id_reclamation"));
            UN_recc.setCellValueFactory(new PropertyValueFactory("username_reclamation"));
            DESC_recc.setCellValueFactory(new PropertyValueFactory("object_reclamation"));
            Obj_rec.setCellValueFactory(new PropertyValueFactory("description_reclamation"));
            

            TableRecView.setItems(data);
            
            
        
        
    
    }
    

    @FXML
    private void fillforum(MouseEvent event) {
        Reclamation r=TableRecView.getSelectionModel().getSelectedItem();
        id_rec_modifier=r.getId_reclamation();
        TFforum_UN.setText(r.getUsername_reclamation());
        TFdesc.setText(r.getDescription_reclamation());
        TFobj.setText(r.getObject_reclamation());
        
    }

    @FXML
    private void Save_rec(ActionEvent event) {
        String raUsername = TFforum_UN.getText();
            String raObject = TFobj.getText();
            String raDescription = TFdesc.getText();
            Reclamation rr = new Reclamation(raUsername, raObject, raDescription);
            ServiceReclamation rs = new ServiceReclamation();
            rs.ajouter(rr);
            refreshList();
            
    }

    @FXML
    private void Delete_rec(ActionEvent event) {
        long id_rec;
        id_rec=TableRecView.getSelectionModel().getSelectedItem().getId_reclamation();
        try {
            rs.supprimer(id_rec);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        refreshList();
    }

    @FXML
    private void Update_rec(ActionEvent event) {
        String raUsername = TFforum_UN.getText();
            String raObject = TFobj.getText();
            String raDescription = TFdesc.getText();
            Reclamation rr = new Reclamation(id_rec_modifier,raUsername, raObject, raDescription);
            ServiceReclamation rs = new ServiceReclamation();
            rs.ajouter(rr);
            refreshList();
    }
    
}
