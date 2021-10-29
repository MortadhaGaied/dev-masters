/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.voyage;




import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import dev.masters.api.MailApi;
import dev.masters.api.NotificationApi;
import dev.masters.api.SmsApi;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.entites.Position;
import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.services.ServiceMoyenDeTransport;
import dev.masters.services.StationService;
import dev.masters.services.VoyageService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class GestionVoyageController implements Initializable {

    @FXML
    private Label LbId;
    @FXML
    private TableColumn<Voyage, Long> col_Id;
    @FXML
    private TableColumn<Voyage, String> cold_pd;
    @FXML
    private TableColumn<Voyage, String> cold_pa;
    @FXML
    private TableColumn<Voyage, Date> col_date;
    @FXML
    private TableColumn<Voyage, String> col_station;
    @FXML
    private TableColumn<Voyage, String> col_mt;
    @FXML
    private TextField tf_pa;
    @FXML
    private Label tf_date;
    @FXML
    private Label tf_station;
    @FXML
    private TextField tf_pd;
    @FXML
    private TableView<Voyage> tv_voyage;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_archiver;

    @FXML
    private TextField tf_recherche;
    VoyageService vs;
    StationService ss;
    @FXML
    private Label Type;
    @FXML
    private Label Assecible_au_handicape;
    @FXML
    private ComboBox<Station> cb_station;
    @FXML
    private ComboBox<MoyenDeTransport> cb_mt;
    @FXML
    private DatePicker dp_date;
    @FXML
    private RadioButton rb_rechId;
    @FXML
    private RadioButton rb_rechStation;
    @FXML
    private RadioButton rb_rechMt;
    @FXML
    private Label Type1;
    @FXML
    private TextField tf_idVoyage;
    @FXML
    private JFXButton btn_retour;
    @FXML
    private ToggleGroup tg_filters;
    @FXML
    private JFXRadioButton rb_All;
    ServiceMoyenDeTransport smt;
    ObservableList<Voyage> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vs = new VoyageService();
        ss = new StationService();
        smt=new ServiceMoyenDeTransport();
        refreshlist();
        recherche_avance();
        populateComboBoxStation();
        populateComboBoxMt();
        

    }

    @FXML
    private void Insert(MouseEvent event) {
         try {
            Voyage voyage = new Voyage(new Position(tf_pd.getText().split(",")[0].trim(), tf_pd.getText().split(",")[1].trim()),new Position(tf_pa.getText().split(",")[0].trim(), tf_pa.getText().split(",")[1].trim()),dp_date.getValue().atStartOfDay(),cb_station.getValue(),cb_mt.getValue());
        vs.ajouter(voyage);
        String title = "Congratulations sir";
        String message = "Voyage Ajouté avec Succes";
        NotificationApi notification=new NotificationApi();
        notification.showNotif(title, message, Notifications.SUCCESS);
             MailApi mailApi = new MailApi();
             mailApi.SendMail("mouhamedaminerouatbi@gmail.com", "Voyage Ajouter Avec Succes", "Ajout Voyage");
             SmsApi sms = new SmsApi();
             sms.sendSMS("Ajout Voyage", "voyage a ete ajouté avec succes");
        refreshlist();
        } catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setContentText("verifier les champ");
                 alert.showAndWait();
        }
        
    }

    public void refreshlist() {

        try {
            data.clear();

            List<Voyage> voyages = new ArrayList(vs.afficher());

            if (voyages == null) {
                voyages = new ArrayList(vs.afficher());
            }

            for (Voyage voyage : voyages) {
                data.add(voyage);
                //System.out.println(voyage);

            }

            col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
            cold_pd.setCellValueFactory(new PropertyValueFactory<>("position_depart"));
            cold_pa.setCellValueFactory(new PropertyValueFactory<>("position_arrive"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_de_voyage"));
            col_station.setCellValueFactory(new PropertyValueFactory<>("station"));
            col_mt.setCellValueFactory(new PropertyValueFactory<>("moyen_transport"));

            tv_voyage.setItems(data);
            //Initial filtered list
            
        } catch (Exception e) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.showAndWait();
        }

    }

    public void populateComboBoxStation() {

        try {
            ObservableList<Station> data = FXCollections.observableArrayList();

            List<Station> stations = new ArrayList(ss.afficher());

            if (stations == null) {
                stations = new ArrayList(ss.afficher());
            }

            for (Station station : stations) {
                data.add(station);
                //System.out.println(voyage);

            }

            cb_station.setItems(data);

        } catch (Exception e) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.showAndWait();
        }

    }

    public void populateComboBoxMt() {

        try {
            ObservableList<MoyenDeTransport> data = FXCollections.observableArrayList();

            List<MoyenDeTransport> mts = new ArrayList(smt.afficher());

            if (mts == null) {
                mts = new ArrayList(smt.afficher());
            }

            for (MoyenDeTransport mt : mts) {
                data.add(mt);
                //System.out.println(voyage);

            }

            cb_mt.setItems(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void Update(MouseEvent event) {
        try {
            Voyage voyage = new Voyage(new Position(tf_pd.getText().split(",")[0].trim(), tf_pd.getText().split(",")[1].trim()),new Position(tf_pa.getText().split(",")[0].trim(), tf_pa.getText().split(",")[1].trim()),dp_date.getValue().atStartOfDay(),cb_station.getValue(),cb_mt.getValue());
        vs.modifier(Integer.parseInt(tf_idVoyage.getText()), voyage);
        String title = "Congratulations sir";
        String message = "Voyage Modifier avec Succes";
        NotificationApi notification=new NotificationApi();
        notification.showNotif(title, message, Notifications.SUCCESS);
        MailApi mailApi = new MailApi();
             mailApi.SendMail("mouhamedaminerouatbi@gmail.com", "Voyage A été modifier Avec Succes \n"+voyage, "Ajout Voyage");
             SmsApi sms = new SmsApi();
             sms.sendSMS("Modification Voyage", "voyage a ete modifier avec succes");
         refreshlist();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setContentText("verifier les champs");
                 alert.showAndWait();
        }
       
         
    }

    @FXML
    private void Delete(MouseEvent event) {
                System.out.println("ID = " +tf_idVoyage.getText());

        try {
            vs.supprimer(Long.parseLong(tf_idVoyage.getText()));
            MailApi mailApi = new MailApi();
             mailApi.SendMail("mouhamedaminerouatbi@gmail.com", "Voyage Ajouter Avec Succes id_voyage : "+tf_idVoyage.getText(), "suppression Voyage");
            tf_idVoyage.setText(null);
            tf_pd.setText(null);
            tf_pa.setText(null);
            dp_date.setValue(null);
            cb_mt.setValue(null);
            cb_station.setValue(null);
            String title = "Congratulations sir";
        String message = "Voyage supprimé avec succes";
        NotificationApi notification=new NotificationApi();
        notification.showNotif(title, message, Notifications.SUCCESS);
        SmsApi sms = new SmsApi();
             sms.sendSMS("Suprimer Voyage", "voyage a ete Supprime avec succes");
        
            refreshlist();
        } catch (Exception e) {
           Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setContentText("une erreur est surveny veuillez ressayer de nouveau");
                 alert.showAndWait();
        }
        
        
         
    }

    @FXML
    private void Affecter(MouseEvent event) {
        Voyage voyage = tv_voyage.getSelectionModel().getSelectedItem();
        if (voyage == null) {
            return;
        }
        tf_idVoyage.setText(String.valueOf(voyage.getId()));
        tf_pd.setText(voyage.getPosition_depart().getGoogleMapsPostionFormat());
        tf_pa.setText(voyage.getPosition_arrive().getGoogleMapsPostionFormat());
        dp_date.setValue(voyage.getDate_de_voyage().toLocalDate());
        cb_station.setValue(voyage.getStation());
        cb_mt.setValue(voyage.getMoyen_transport());

    }
    private void recherche_avance() {
        
        FilteredList<Voyage> filteredData = new FilteredList<>(data, b -> true);
            tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(voyage -> {
                    if (newValue == null || newValue.isEmpty()) {
			return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (voyage.getDate_de_voyage().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
			return true; // Filter matches first name.
                    } else if (String.valueOf(voyage.getMoyen_transport()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }else if (String.valueOf(voyage.getId()).indexOf(lowerCaseFilter) != -1) {
			return true;
                    } 
                    else if (String.valueOf(voyage.getStation()).indexOf(lowerCaseFilter) != -1) {
			return true;
                    }
                    else
			return false; // Does not match.
                    });
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Voyage> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv_voyage.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tv_voyage.setItems(sortedData);
         
        
    }


    private void rechercher(String requete) {

        if (rb_rechId.isSelected()) {
                 try {
                ObservableList<Voyage> data = FXCollections.observableArrayList();

                Voyage voyage = vs.GetVoyageById(Long.parseLong(requete));

                if (voyage == null) {
                    voyage = vs.GetVoyageById(Long.parseLong(requete));
                }

               data.add(voyage);

                col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
                cold_pd.setCellValueFactory(new PropertyValueFactory<>("position_depart"));
                cold_pa.setCellValueFactory(new PropertyValueFactory<>("position_arrive"));
                col_date.setCellValueFactory(new PropertyValueFactory<>("date_de_voyage"));
                col_station.setCellValueFactory(new PropertyValueFactory<>("station"));
                col_mt.setCellValueFactory(new PropertyValueFactory<>("moyen_transport"));

                tv_voyage.setItems(data);

            } catch (NumberFormatException e) {
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setContentText("L'id Doit Etre un Nombre");
                 alert.showAndWait();
            }
        } 
        
        if (rb_rechStation.isSelected()) {
            
                System.out.println("recherche par station selected");
            try {
                ObservableList<Voyage> data = FXCollections.observableArrayList();

                List<Voyage> voyages = vs.getVoyagesByRefStation(requete);

                if (voyages == null) {
                    voyages = vs.getVoyagesByRefStation(requete);
                }

                for (Voyage voyage : voyages) {
                    data.add(voyage);
                    //System.out.println(voyage);

                }

                col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
                cold_pd.setCellValueFactory(new PropertyValueFactory<>("position_depart"));
                cold_pa.setCellValueFactory(new PropertyValueFactory<>("position_arrive"));
                col_date.setCellValueFactory(new PropertyValueFactory<>("date_de_voyage"));
                col_station.setCellValueFactory(new PropertyValueFactory<>("station"));
                col_mt.setCellValueFactory(new PropertyValueFactory<>("moyen_transport"));

                tv_voyage.setItems(data);

            } catch (Exception e) {
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.showAndWait();
            }

        } 
        
        if (rb_rechMt.isSelected()) {
            
            System.out.println("recherche par mt selected");
            
             try {
                ObservableList<Voyage> data = FXCollections.observableArrayList();

                List<Voyage> voyages = vs.getVoyagesByMt(Long.parseLong(requete));
                 System.out.println();

                if (voyages == null) {
                    voyages = vs.getVoyagesByMt(Long.parseLong(requete));
                }

                for (Voyage voyage : voyages) {
                    data.add(voyage);
                    //System.out.println(voyage);

                }

                col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
                cold_pd.setCellValueFactory(new PropertyValueFactory<>("position_depart"));
                cold_pa.setCellValueFactory(new PropertyValueFactory<>("position_arrive"));
                col_date.setCellValueFactory(new PropertyValueFactory<>("date_de_voyage"));
                col_station.setCellValueFactory(new PropertyValueFactory<>("station"));
                col_mt.setCellValueFactory(new PropertyValueFactory<>("moyen_transport"));

                tv_voyage.setItems(data);

            } catch (Exception e) {
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.showAndWait();
            }
        }
        if (rb_All.isSelected()){
            System.out.println("refresh");
            refreshlist();

        }

    }

    /*@FXML
    private void Affecter(MouseEvent event) {
    }*/

    @FXML
    private void archiver(ActionEvent event) {
       
        
       try {
            Voyage voyage = new Voyage(Integer.parseInt(tf_idVoyage.getText()),new Position(tf_pd.getText().split(",")[0].trim(), tf_pd.getText().split(",")[1].trim()),new Position(tf_pa.getText().split(",")[0].trim(), tf_pa.getText().split(",")[1].trim()),dp_date.getValue().atStartOfDay(),cb_station.getValue(),cb_mt.getValue());
        vs.archiver(voyage);
            String title = "Congratulations sir";
        String message = "You've successfully created your first Tray Notification";
        NotificationApi notification=new NotificationApi();
        notification.showNotif(title, message, Notifications.SUCCESS);
           

         refreshlist();    
        } catch (Exception e) {
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Erreur");
                 alert.setContentText("une erreur est surveny veuillez ressayer de nouveau");
                 alert.showAndWait();
        }
    }

    @FXML
    private void backToDashBoard(ActionEvent event) {
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
}
