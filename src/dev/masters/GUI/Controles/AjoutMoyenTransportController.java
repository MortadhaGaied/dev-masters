
package dev.masters.GUI.Controles;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dev.masters.entites.MoyenDeTransport;
import dev.masters.services.ServiceMoyenDeTransport;
import dev.masters.utils.MailerApi;
import dev.masters.utils.SMSApi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


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
    private Label radioButtonLabel;

    String Etat;
    String AccessibleHandicape ;
    Integer Longueur;
    Integer Largeur;
    Integer Poids;
    Integer NombrePlace;
    String energie;
    @FXML
    private StackPane EtatStackPanne;
    @FXML
    private StackPane EnergieStackPanne;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
    private void Insert(MouseEvent event) {
        ServiceMoyenDeTransport MtS = new ServiceMoyenDeTransport();
        MoyenDeTransport Mt = new MoyenDeTransport(TypeComboox.getValue(), NumLigneTf.getText(), DateCirculations.getValue() ,Etat, AccessibleHandicape, PrixAchatTf.getText(), PoidsSpinner.getValue(), LongueurSpinner.getValue(), LargeurSpinner.getValue(), energie , NombrePlaceSpinner.getValue());
        MtS.ajouter(Mt);
        
        //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Moyen de Transport ajoutée avec succes";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("mahdi.homrani@esprit.tn", "Admin.");

        //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21658932889", "Admin.");
       

        

  
    }
    

    @FXML
    private void clean(MouseEvent event) {
        TypeComboox.setValue(null);
        NumLigneTf.setText(null);
        Etat=null;
        energie=null;
        PrixAchatTf.setText(null);
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
    private void EnergieCheckBox(ActionEvent event) {
         if (ElectriqueCheckBox.isSelected()){
            energie = "Electrique";}

        if (DieselCheckBox.isSelected()){
            energie = "Diesel";}
        
        if (EssenceCheckBox.isSelected()){
            energie = "Essence";}
        
        
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


    private void LargeurSpinner(MouseEvent event) {
         Largeur = LargeurSpinner.getValue();
    }

    private void PoidsSpinner(MouseEvent event) {
        Poids = PoidsSpinner.getValue();
    }

    @FXML
    private void LongueurSpinner(MouseEvent event) {
        Longueur = LongueurSpinner.getValue();
    }

    private void NombrePlaceSpinner(MouseEvent event) {   
          NombrePlace = NombrePlaceSpinner.getValue();
    }

}
