/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.voyage;

import com.jfoenix.controls.JFXButton;
import dev.masters.entites.Voyage;
import dev.masters.services.VoyageService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mouha
 */
public class ArchiveController implements Initializable {

    @FXML
    private TableView<Voyage> tv_archive;
    @FXML
    private TableColumn<Voyage, Long> col_id_archive;
    @FXML
    private TableColumn<Voyage, String> col_pd_archive;
    @FXML
    private TableColumn<Voyage, Date> cold_date_archive;
    @FXML
    private TableColumn<Voyage, String> col_station_archive;
    @FXML
    private TableColumn<Voyage, Long> col_mt_archive;
    @FXML
    private JFXButton btn_desarchive;
    @FXML
    private TableColumn<Voyage, String> cold_pa_archive;
    VoyageService vs;
    @FXML
    private ImageView btn_back;
    @FXML
    private ImageView btn_exit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vs=new VoyageService();
        refreshlist();
        
        // TODO
    }    

    @FXML
    private void affecter(MouseEvent event) {
        Voyage voyage = tv_archive.getSelectionModel().getSelectedItem();
        if (voyage == null) {
            return;
        }
        try {
             vs.desarchiver(voyage);
             System.out.println("voyage desarchive avec succes");
             refreshlist();
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
        

    }
    
    
    @FXML
    private void desarchiver(ActionEvent event) {
    }
    public void refreshlist() {

        try {
            ObservableList<Voyage> data = FXCollections.observableArrayList();

            List<Voyage> voyages = new ArrayList(vs.afficherArchive());

            if (voyages == null) {
                voyages = new ArrayList(vs.afficherArchive());
            }

            for (Voyage voyage : voyages) {
                data.add(voyage);
                //System.out.println(voyage);

            }

            col_id_archive.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_pd_archive.setCellValueFactory(new PropertyValueFactory<>("position_depart"));
            cold_pa_archive.setCellValueFactory(new PropertyValueFactory<>("position_arrive"));
            cold_date_archive.setCellValueFactory(new PropertyValueFactory<>("date_de_voyage"));
            col_station_archive.setCellValueFactory(new PropertyValueFactory<>("station"));
            col_mt_archive.setCellValueFactory(new PropertyValueFactory<>("moyen_transport"));

            tv_archive.setItems(data);
            //Initial filtered list
            
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }

    }

    @FXML
    private void BackToMenu(MouseEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/voyage/VoyageMenu.fxml"));
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
    private void close(MouseEvent event) {
        System.exit(0);
    }
}
