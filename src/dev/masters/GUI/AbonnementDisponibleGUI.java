/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.AbonnementDisponible;
import dev.masters.services.ServiceAbonnementDisponible;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AbonnementDisponibleGUI implements Initializable {

    @FXML
    private TextField tfPrixAnnee;
    @FXML
    private TextField tfDesc;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private TextField tfPrixMois;
    @FXML
    private TextField tfPrixSemestre;
    @FXML
    private TableView<AbonnementDisponible> tvAbonnDispo;
    @FXML
    private TableColumn<AbonnementDisponible, Long> id;
    @FXML
    private TableColumn<AbonnementDisponible, String> desc;
    @FXML
    private TableColumn<AbonnementDisponible, String> type;
    @FXML
    private TableColumn<AbonnementDisponible, Double> prixm;
    @FXML
    private TableColumn<AbonnementDisponible, Double> prixs;
    @FXML
    private TableColumn<AbonnementDisponible, Double> prixa;
    @FXML
    private Label lbId;
    @FXML
    private Label lbAlert;
    @FXML
    private TextField tfRecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbType.getItems().addAll("Train", "Métro", "Bus");
        refreshlist(null);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercher(newValue);
        });
    }

    @FXML
    private void onAjoute() {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        AbonnementDisponible abonn = new AbonnementDisponible(tfDesc.getText(), cbType.getValue(), Double.parseDouble(tfPrixMois.getText()), Double.parseDouble(tfPrixSemestre.getText()), Double.parseDouble(tfPrixAnnee.getText()));
        sad.ajouter(abonn);
        refreshlist(null);
        lbAlert.setText("L'abonnement est ajoutée avec succée !");

    }

    public void refreshlist(List<AbonnementDisponible> listAbonnDispo) {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        ObservableList<AbonnementDisponible> data = FXCollections.observableArrayList();
        if (listAbonnDispo == null) {
            listAbonnDispo = new ArrayList(sad.afficher());
        }

        for (AbonnementDisponible item : listAbonnDispo) {
            data.add(item);
        }

        id.setCellValueFactory(new PropertyValueFactory<AbonnementDisponible, Long>("id_abonnement"));
        desc.setCellValueFactory(new PropertyValueFactory<AbonnementDisponible, String>("desc_abonnement"));
        type.setCellValueFactory(new PropertyValueFactory<AbonnementDisponible, String>("type_abonnement"));
        prixm.setCellValueFactory(new PropertyValueFactory<AbonnementDisponible, Double>("prix_moins_abonnement"));
        prixs.setCellValueFactory(new PropertyValueFactory<AbonnementDisponible, Double>("prix_semestre_abonnement"));
        prixa.setCellValueFactory(new PropertyValueFactory<AbonnementDisponible, Double>("prix_annuel_abonnement"));
        tvAbonnDispo.setItems(data);
    }

    @FXML
    private void modifier(MouseEvent event) {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        AbonnementDisponible abonn = new AbonnementDisponible(tfDesc.getText(), cbType.getValue(), Double.parseDouble(tfPrixMois.getText()), Double.parseDouble(tfPrixSemestre.getText()), Double.parseDouble(tfPrixAnnee.getText()));
        sad.modifier(Long.parseLong(lbId.getText()), abonn);
        refreshlist(null);
        lbAlert.setText("L'abonnement est modifiée avec succée !");

    }

    @FXML
    private void supprimer(MouseEvent event) {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        sad.supprimer(Long.parseLong(lbId.getText()));
        tfDesc.setText(null);
        tfPrixAnnee.setText(null);
        tfPrixMois.setText(null);
        tfPrixSemestre.setText(null);
        cbType.setValue(null);
        lbId.setText(null);
        refreshlist(null);
        lbAlert.setText("L'abonnement est supprimée avec succée !");
    }

    @FXML
    private void affecter(MouseEvent event) {
        AbonnementDisponible abonn_disp = tvAbonnDispo.getSelectionModel().getSelectedItem();
        if (abonn_disp == null) {
            return;
        }
        tfDesc.setText(abonn_disp.getDesc_abonnement());
        tfPrixAnnee.setText(abonn_disp.getPrix_annuel_abonnement().toString());
        tfPrixMois.setText(abonn_disp.getPrix_moins_abonnement().toString());
        tfPrixSemestre.setText(abonn_disp.getPrix_semestre_abonnement().toString());
        cbType.setValue(abonn_disp.getType_abonnement());
        lbId.setText(String.valueOf(abonn_disp.getId_abonnement()));
    }

    private void rechercher(String requete) {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        List<AbonnementDisponible> list = sad.rechercheParTypeOuDesc(requete);
        refreshlist(list);
    }

    @FXML
    private void menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dev/masters/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void retourne(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dev/masters/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
