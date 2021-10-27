/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.voyage;


import java.net.URL;
import java.util.ArrayList;
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
public class GestionVoyageController implements Initializable {

    @FXML
    private Label Type;
    @FXML
    private Label Assecible_au_handicape;
    @FXML
    private TextField tfNum_ligne;
    @FXML
    private TextField tfDate_de_mise_en_circulations;
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfAccessible_au_handicapes;
    @FXML
    private TableView<?> tvMoyen_de_transport;
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
    @FXML
    private TableColumn<?, ?> col_Id;
    @FXML
    private TableColumn<?, ?> cold_pd;
    @FXML
    private TableColumn<?, ?> cold_pa;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_station;
    @FXML
    private TableColumn<?, ?> col_mt;
    @FXML
    private Label tf_pa;
    @FXML
    private Label tf_date;
    @FXML
    private Label tf_station;
    @FXML
    private TextField tf_pd;
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        refreshlist(null);
       tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
       rechercher(newValue);
        });
    }

    @FXML
    private void Insert(MouseEvent event) {
               /* ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MoyenDeTransport Mt = new MoyenDeTransport(tfType.getText(),tfNum_ligne.getText(),tfDate_de_mise_en_circulations.getText(),tfEtat.getText(),tfAccessible_au_handicapes.getText(),tfPrix_achat.getText(),tfPoids.getText(),tfLongueur.getText(),tfLargeur.getText(),tfEnergie.getText(),tfNombre_de_place.getText());
        MtS.ajouter(Mt);
        refreshlist(null);*/
    }

        public void refreshlist(List<?> listMoyenDeTransport)  {
            /*
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
        
        tvMoyen_de_transport.setItems(data);*/
        }
        
    @FXML
    private void Update(MouseEvent event) {
        /*
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
       MoyenDeTransport Mt = new MoyenDeTransport(tfType.getText(),tfNum_ligne.getText(),tfDate_de_mise_en_circulations.getText(),tfEtat.getText(),tfAccessible_au_handicapes.getText(),tfPrix_achat.getText(),tfPoids.getText(),tfLongueur.getText(),tfLargeur.getText(),tfEnergie.getText(),tfNombre_de_place.getText());
        MtS.modifier(Long.parseLong(LbId.getText()), Mt);
         refreshlist(null);     */
    }

    @FXML
    private void Delete(MouseEvent event) {/*
                System.out.println("ID = " +LbId.getText());

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
        */
    }
/*
    @FXML
    private void Affecter(MouseEvent event) {
                MoyenDeTransport Mt = tvMoyen_de_transport.getSelectionModel().getSelectedItem();
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
        LbId.setText(String.valueOf(Mt.getId_MoyenTransport()));
    }
    */

    
        private void rechercher(String requete) {/*
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        List<MoyenDeTransport> list = MtS.rechercheParTypeOuPrix_achat(requete);
        refreshlist(list);*/
    }

    @FXML
    private void Affecter(MouseEvent event) {
    }
    
}
