/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.MoyenTransport.Controles;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dev.masters.MoyenTransport.entites.MoyenDeTransport;
import dev.masters.MoyenTransport.services.ServiceMoyenDeTransport;
import dev.masters.MoyenTransport.utils.MailerApi;
import dev.masters.MoyenTransport.utils.SMSApi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AjoutMoyenTransportController implements Initializable {

    @FXML
    private ImageView Exit;
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
    private StackPane EtatStackPanne;
    @FXML
    private JFXToggleButton AccessibleHandicapeToggle;
    @FXML
    private JFXTextField PrixAchatTf;
    @FXML
    private Spinner<?> LonguerSpinner;
    @FXML
    private Spinner<?> LargeurSpinner;
    @FXML
    private JFXCheckBox ElectriqueCheckBox;
    @FXML
    private JFXCheckBox DieselCheckBox;
    @FXML
    private JFXCheckBox EssenceCheckBox;
    @FXML
    private StackPane EnergieStackPanne;
    @FXML
    private Spinner<?> PoidsSpinner;
    @FXML
    private Spinner<?> NombrePlaceSpinner;
    private Label radioButtonLabel;

    String Etat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Inisialisze le buttons radio
        final ToggleGroup group = new ToggleGroup();
        MarcheRadioButton.setToggleGroup(group);
        PanneRadioButton.setToggleGroup(group);
        MarcheRadioButton.setSelected(true);
        MarcheRadioButton.requestFocus();
        TypeComboox.getItems().addAll("Train", "Métro", "Bus");
        String Date = String.valueOf(DateCirculations.getValue());
        
    }    

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void BackToMenu(MouseEvent event) {
    }


    @FXML
    private void Insert(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MoyenDeTransport Mt = new MoyenDeTransport(TypeComboox.getValue(), NumLigneTf.getText(), Date ,Etat, tfAccessible_au_handicapes.getText(), PrixAchatTf.getText(), tfPoids.getText(), tfLongueur.getText(), tfLargeur.getText(), tfEnergie.getText(), tfNombre_de_place.getText());
        MtS.ajouter(Mt);
        //refreshlist(null);
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("aminedahmen14@gmail.com", "Admin.");

        //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21658932889", "Admin.");
    }

    @FXML
    private void clean(MouseEvent event) {
    }


    @FXML
    private void getEtat(ActionEvent event) {
        
        
          if(MarcheRadioButton.isSelected()) {
              Etat="En_Marche";
            }
          else  Etat = "En Panne";
                 
      }

    @FXML
    private void radioButtonChanged(ActionEvent event) {
    }
    
}
