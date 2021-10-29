/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.Controles;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dev.masters.entites.MoyenDeTransport;
import dev.masters.services.ServiceMoyenDeTransport;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
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
public class ModifierMoyenTransportController implements Initializable {

    @FXML
     private JFXComboBox<String> TypeComboox;
    @FXML
    private JFXTextField NumLigneTf;
    @FXML
    private JFXDatePicker DateCirculations;
    @FXML
    private JFXRadioButton MarcheRadioButton;
    @FXML
    private JFXRadioButton PanneRadioButton;
    @FXML
    private JFXToggleButton AccessibleHandicapeToggle;
    @FXML
    private JFXTextField PrixAchatTf;
    @FXML
    private Spinner<Integer> LongueurSpinner;
    SpinnerValueFactory<Integer> longuer = new SpinnerValueFactory.IntegerSpinnerValueFactory(10,500 );
    @FXML
    private Spinner<Integer> LargeurSpinner;
    SpinnerValueFactory<Integer> largeur = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20 );
    @FXML
    private JFXCheckBox ElectriqueCheckBox;
    @FXML
    private JFXCheckBox DieselCheckBox;
    @FXML
    private JFXCheckBox EssenceCheckBox;
    @FXML
   private Spinner<Integer> PoidsSpinner;
   SpinnerValueFactory<Integer> poids = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
    @FXML
    private Spinner<Integer> NombrePlaceSpinner;
    SpinnerValueFactory<Integer> nombrePlace = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100 );
    @FXML
    private ImageView Exit;
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

    String Etat;
    String AccessibleHandicape ;
    Integer Longueur;
    Integer Largeur;
    Integer Poids;
    Integer NombrePlace;
    String energie;
    @FXML
    private Label LbId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist(null);        
        // Inisialisze le buttons radio
        final ToggleGroup group = new ToggleGroup();
        
        MarcheRadioButton.setToggleGroup(group);
        PanneRadioButton.setToggleGroup(group);
      //  MarcheRadioButton.setSelected(true);
        MarcheRadioButton.requestFocus();
        TypeComboox.getItems().addAll("Train", "Métro", "Bus");
        
        String Date = String.valueOf(DateCirculations.getValue());    
        
        //LonguerSpinner
                
                longuer.setValue(10);
                LongueurSpinner.setValueFactory(longuer);
                Longueur = LongueurSpinner.getValue();  
                
        //LargeurSpinner
                 
                largeur.setValue(10);
                LargeurSpinner.setValueFactory(largeur);
                 Largeur = LargeurSpinner.getValue(); 
        //PoidsSpinner
;
                //poids.setValue(10);
                PoidsSpinner.setValueFactory(poids);
                 Poids = PoidsSpinner.getValue(); 
         
         //nombrePlace 
            
           nombrePlace.setValue(10);
           NombrePlaceSpinner.setValueFactory(nombrePlace);
           NombrePlace = NombrePlaceSpinner.getValue();
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
    private void getEtat(ActionEvent event) {
                  if(MarcheRadioButton.isSelected()) {
              Etat="En_Marche";
            }
          else if (PanneRadioButton.isSelected()){
              Etat = "En Panne";}
    }

    @FXML
    private void AccessibleHandicapeToggle(ActionEvent event) {
             AccessibleHandicapeToggle.selectedProperty().addListener(new ChangeListener< Boolean > () {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (AccessibleHandicapeToggle.isSelected())
                {
                   AccessibleHandicape = "Oui" ;
                   
                }
                else AccessibleHandicape = "Non";
            }
        });
    }

    @FXML
    private void LongueurSpinner(MouseEvent event) {
       Longueur = LongueurSpinner.getValue();
    }

    @FXML
    private void EnergieCheckBox(ActionEvent event) {
         if (ElectriqueCheckBox.isSelected()){
            energie = "Electrique";}

        if (DieselCheckBox.isSelected()){
            energie = "Diesel";}
        
        if (EssenceCheckBox.isSelected()){
            energie = "Essence";}
    }

    @FXML
    private void Save(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MoyenDeTransport Mt = new MoyenDeTransport(TypeComboox.getValue(), NumLigneTf.getText(), DateCirculations.getValue() ,Etat, AccessibleHandicape, PrixAchatTf.getText(), PoidsSpinner.getValue(), LongueurSpinner.getValue(), LargeurSpinner.getValue(), energie , NombrePlaceSpinner.getValue());
        MtS.ajouter(Mt);
        
        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "modify Successful";
        message ="Moyen de Transport à été Modifier avec succes";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
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
    private void Affecter(MouseEvent event) {
        MoyenDeTransport Mt = tvMoyen_de_transport.getSelectionModel().getSelectedItem();
        if (Mt == null) {
            return;
        }
        TypeComboox.setValue(Mt.getType());
        NumLigneTf.setText(Mt.getNum_ligne());
        PrixAchatTf.setText(Mt.getPrix_achat());
        LbId.setText(String.valueOf(Mt.getId_MoyenTransport()));
    }

    @FXML
    private void LargeurSpinner(MouseEvent event) {
             Largeur = LargeurSpinner.getValue();
    }

    @FXML
    private void PoidsSpinner(MouseEvent event) {
        Poids = PoidsSpinner.getValue();
    }

    @FXML
    private void NombrePlaceSpinner(MouseEvent event) {
         NombrePlace = NombrePlaceSpinner.getValue();
    }
    
}
