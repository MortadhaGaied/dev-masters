/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.Controles;

import com.jfoenix.controls.JFXTextField;
import dev.masters.entites.MoyenDeTransport;
import dev.masters.services.ServiceMoyenDeTransport;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AfficherMoyenTransportController implements Initializable {

    @FXML
    private TableView<MoyenDeTransport> tvMoyen_de_transport;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colId;
    @FXML
    private TableColumn<MoyenDeTransport, String> colType;
    @FXML
    private TableColumn<MoyenDeTransport, String> colNum_ligne;
    @FXML
    private TableColumn<MoyenDeTransport, LocalDate> colDate_de_mise_en_circulations;
    @FXML
    private TableColumn<MoyenDeTransport, String> colEtat;
    @FXML
    private TableColumn<MoyenDeTransport, String> colAccessible_au_handicapes;
    @FXML
    private TableColumn<MoyenDeTransport, String> colPrix_achat;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colPoids;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colLongueur;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colLargeur;
    @FXML
    private TableColumn<MoyenDeTransport, String> colEnergie;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colNombre_de_place;

    @FXML
    private ImageView Exit;
    @FXML
    private ImageView btnUpdate;
    @FXML
    private ImageView btnDelete;
    @FXML
    private Label LbId;
    @FXML
    private JFXTextField tfRecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        refreshlist(null);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
        System.out.println("eazeza");
        rechercher(newValue);
    });  
         }

    public void refreshlist(List<MoyenDeTransport> listMoyenDeTransport) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        ObservableList<MoyenDeTransport> data = FXCollections.observableArrayList();
        List<MoyenDeTransport> listMoyen = new ArrayList(MtS.afficher());
        if (listMoyenDeTransport == null) {
            listMoyenDeTransport = new ArrayList(MtS.afficher());
        }
        for (MoyenDeTransport item : listMoyenDeTransport) {
            data.add(item);
        }

        colId.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, Integer>("id_MoyenTransport"));
        colType.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Type"));
        colNum_ligne.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Num_ligne"));
        colDate_de_mise_en_circulations.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, LocalDate>("Date_de_mise_en_circulations"));
        colEtat.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Etat"));
        colAccessible_au_handicapes.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Accessible_au_handicape"));
        colPrix_achat.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Prix_achat"));
        colPoids.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, Integer>("Poids"));
        colLongueur.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, Integer>("Longueur"));
        colLargeur.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, Integer>("Largeur"));
        colEnergie.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Energie"));
        colNombre_de_place.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, Integer>("Nombre_de_place"));

        tvMoyen_de_transport.setItems(data);
    }
        
    @FXML
    private void Affecter(MouseEvent event) {
                MoyenDeTransport Mt = tvMoyen_de_transport.getSelectionModel().getSelectedItem();
        if (Mt == null) {
            return;
        }

        LbId.setText(String.valueOf(Mt.getId_MoyenTransport()));
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void BackToMenu(MouseEvent event) {
            Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/MoyenTransport/Gui/MInterfaceMenuMoyenTransport.fxml"));
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
    private void Update(MouseEvent event) {
            Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/MoyenTransport/Gui/ModifierMoyenTransport.fxml"));
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
    private void Delete(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MtS.supprimer(Long.parseLong(LbId.getText()));
        refreshlist(null);
        
        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Delect Successful";
        message ="Moyen de Transport à été supprimer";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
    }

        private void rechercher(String req) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        List<MoyenDeTransport> list = MtS.SearchByReq(req);
        refreshlist(list);
    }  
        
    }
    

