/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Reservation;
import dev.masters.entites.User;
import dev.masters.services.ServiceReservation;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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
public class FXMLGSTresController implements Initializable {

    @FXML
    private TextField TFEtat;
    @FXML
    private DatePicker DPdr;
    @FXML
    private TableView<Reservation> TableReservationView;
    @FXML
    private TableColumn<Reservation, Long> id_resc;
    @FXML
    private TableColumn<Reservation, String> Etat_resc;
    @FXML
    private TableColumn<Reservation, String> Date_resc;
    @FXML
    private TextField TFSearch;
    ServiceReservation sr =new ServiceReservation();
    long id_res_modifier;
    ObservableList<Reservation> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
    }   
    public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(sr.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
        id_resc.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        
        Date_resc.setCellValueFactory(new PropertyValueFactory<>("date"));

        Etat_resc.setCellValueFactory(new PropertyValueFactory<>("etat"));
        TableReservationView.setItems(data);
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Reservation r=TableReservationView.getSelectionModel().getSelectedItem();
        id_res_modifier=r.getId_reservation();
        TFEtat.setText(r.getEtat());
        DPdr.setValue(r.getDate().toLocalDate());
    }

    @FXML
    private void Save_res(ActionEvent event) {
        
        Reservation r=new Reservation(DPdr.getValue().atStartOfDay(),TFEtat.getText());
        sr.ajouter(r);
        refreshlist();
        
    }

    @FXML
    private void Delete_res(ActionEvent event) {
        try {
            Reservation r=TableReservationView.getSelectionModel().getSelectedItem();
            sr.supprimer(r.getId_reservation());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGSTresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }

    @FXML
    private void Update_res(ActionEvent event) {
        try {
            Reservation r=new Reservation(id_res_modifier,DPdr.getValue().atStartOfDay(),TFEtat.getText());
            sr.modifier(id_res_modifier, r);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGSTresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }
    
}
