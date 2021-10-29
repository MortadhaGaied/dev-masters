/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.Controles;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.services.ServiceMoyenDeTransport;
import dev.masters.utils.JfreeChartApi;
import dev.masters.utils.MailerApi;
import dev.masters.utils.SMSApi;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class GestionMoyenTransportController implements Initializable {

    @FXML
    private Label Type;
    @FXML
    private Label Num_ligne;
    @FXML
    private Label Date_de_mise_en_circulations;
    @FXML
    private Label Etat;
    @FXML
    private Label Assecible_au_handicape;
    @FXML
    private Label Prix_Achat;
    @FXML
    private Label Poids;
    @FXML
    private Label Longueur;
    @FXML
    private Label Largeur;
    @FXML
    private Label Energie;
    @FXML
    private Label Nombre_de_place;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfNum_ligne;
    @FXML
    private TextField tfDate_de_mise_en_circulations;
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfAccessible_au_handicapes;
    @FXML
    private TextField tfPrix_achat;
    @FXML
    private TextField tfPoids;
    @FXML
    private TextField tfLongueur;
    @FXML
    private TextField tfLargeur;
    @FXML
    private TextField tfEnergie;
    @FXML
    private TextField tfNombre_de_place;
    @FXML
    private TableView<MoyenDeTransport> tvMoyen_de_transport;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colId;
    @FXML
    private TableColumn<MoyenDeTransport, String> colType;
    @FXML
    private TableColumn<MoyenDeTransport, String> colNum_ligne;
    @FXML
    private TableColumn<MoyenDeTransport, String> colDate_de_mise_en_circulations;
    @FXML
    private TableColumn<MoyenDeTransport, String> colEtat;
    @FXML
    private TableColumn<MoyenDeTransport, String> colAccessible_au_handicapes;
    @FXML
    private TableColumn<MoyenDeTransport, String> colPrix_achat;
    @FXML
    private TableColumn<MoyenDeTransport, String> colPoids;
    @FXML
    private TableColumn<MoyenDeTransport, String> colLongueur;
    @FXML
    private TableColumn<MoyenDeTransport, String> colLargeur;
    @FXML
    private TableColumn<MoyenDeTransport, String> colEnergie;
    @FXML
    private TableColumn<MoyenDeTransport, String> colNombre_de_place;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Label LbId;
    @FXML
    private TextField tfRecherche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist(null);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("eazeza");
            rechercher(newValue);
        });
    }

    @FXML
    private void Insert(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        //MoyenDeTransport Mt = new MoyenDeTransport(tfType.getText(), tfNum_ligne.getText(), tfDate_de_mise_en_circulations.getText(), tfEtat.getText(), tfAccessible_au_handicapes.getText(), tfPrix_achat.getText(), tfPoids.getText(), tfLongueur.getText(), tfLargeur.getText(), tfEnergie.getText(), tfNombre_de_place.getText());
      //  MtS.ajouter(Mt);
        refreshlist(null);
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("aminedahmen14@gmail.com", "Admin.");

        //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21658932889", "Admin.");
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
        colDate_de_mise_en_circulations.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Date_de_mise_en_circulations"));
        colEtat.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Etat"));
        colAccessible_au_handicapes.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Accessible_au_handicape"));
        colPrix_achat.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Prix_achat"));
        colPoids.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Poids"));
        colLongueur.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Longueur"));
        colLargeur.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Largeur"));
        colEnergie.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Energie"));
        colNombre_de_place.setCellValueFactory(new PropertyValueFactory<MoyenDeTransport, String>("Nombre_de_place"));

        tvMoyen_de_transport.setItems(data);
    }

    @FXML
    private void Update(MouseEvent event) {
        /*ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MoyenDeTransport Mt = new MoyenDeTransport(tfType.getText(), tfNum_ligne.getText(), tfDate_de_mise_en_circulations.getText(), tfEtat.getText(), tfAccessible_au_handicapes.getText(), tfPrix_achat.getText(), tfPoids.getText(), tfLongueur.getText(), tfLargeur.getText(), tfEnergie.getText(), tfNombre_de_place.getText());
        MtS.modifier(Long.parseLong(LbId.getText()), Mt);
        refreshlist(null);*/
    }

    @FXML
    private void Delete(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MtS.supprimer(Long.parseLong(LbId.getText()));
        tfType.setText(null);
        tfNum_ligne.setText(null);
        tfDate_de_mise_en_circulations.setText(null);
        tfEtat.setText(null);
        tfAccessible_au_handicapes.setText(null);
        tfPrix_achat.setText(null);
        tfPoids.setText(null);
        tfLongueur.setText(null);
        tfLargeur.setText(null);
        tfEtat.setText(null);
        tfEnergie.setText(null);
        tfNombre_de_place.setText(null);
        refreshlist(null);

    }

    @FXML
    private void Affecter(MouseEvent event) {
        /*MoyenDeTransport Mt = tvMoyen_de_transport.getSelectionModel().getSelectedItem();
        if (Mt == null) {
            return;
        }
        tfType.setText(Mt.getType());
        tfNum_ligne.setText(Mt.getNum_ligne());
        tfDate_de_mise_en_circulations.setText(Mt.getDate_de_mise_en_circulations());
        tfEtat.setText(Mt.getEtat());
        tfAccessible_au_handicapes.setText(Mt.getAccessible_au_handicape());
        tfPrix_achat.setText(Mt.getPrix_achat());
        tfPoids.setText(Mt.getPoids());
        tfLongueur.setText(Mt.getLongueur());
        tfLargeur.setText(Mt.getLargeur());
        tfEtat.setText(Mt.getEtat());
        tfEnergie.setText(Mt.getEnergie());
        tfNombre_de_place.setText(Mt.getNombre_de_place());
        LbId.setText(String.valueOf(Mt.getId_MoyenTransport()));*/
    }

    private void rechercher(String req) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        List<MoyenDeTransport> list = MtS.SearchByReq(req);
        refreshlist(list);
    }

    @FXML
    private void Statistique(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        HashMap<String, Double> data = MtS.StatisqueParType();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }

}
