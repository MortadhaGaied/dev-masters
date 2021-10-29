/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.entites.Abonnement;
import dev.masters.entites.AbonnementDisponible;
import dev.masters.services.ServiceAbonnement;
import dev.masters.services.ServiceAbonnementDisponible;
import dev.masters.utils.Mailer_Abonnement;
import dev.masters.utils.SMS;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AbonnementFormulaire implements Initializable {

    @FXML
    private ComboBox<String> cbAbonn;
    @FXML
    private TextField tfTel;
    private Map<String, AbonnementDisponible> mp = new HashMap<>();
    @FXML
    private RadioButton rdMois;
    @FXML
    private RadioButton rdSemestre;
    @FXML
    private RadioButton rdAnnee;
    @FXML
    private Button retourne;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        List<AbonnementDisponible> list = sad.afficher();
        for (AbonnementDisponible item : list) {
            mp.put(item.getDesc_abonnement(), item);
        }
        for (Map.Entry<String, AbonnementDisponible> entry : mp.entrySet()) {
            cbAbonn.getItems().add(entry.getValue().getDesc_abonnement());
        }
        // Inisialisze le buttons radio
        final ToggleGroup group = new ToggleGroup();
        rdMois.setToggleGroup(group);
        rdSemestre.setToggleGroup(group);
        rdAnnee.setToggleGroup(group);

    }

    @FXML
    private void getSelectedItem(ActionEvent event) {
        String key = cbAbonn.getValue();
        AbonnementDisponible abonn = mp.get(key);
        //System.out.println(key);
        rdMois.setText(abonn.getPrix_moins_abonnement().toString() + " DT");
        rdSemestre.setText(abonn.getPrix_semestre_abonnement().toString() + " DT");
        rdAnnee.setText(abonn.getPrix_annuel_abonnement().toString() + " DT");
    }

    @FXML
    private void onSubmit(MouseEvent event) {
        Date date_debut = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, (rdMois.isSelected() ? 1 : (rdSemestre.isSelected() ? 6 : 12)));
        Date date_fin = cal.getTime();
        ServiceAbonnement sa = new ServiceAbonnement();
        Abonnement nouv_abonn = new Abonnement(51, mp.get(cbAbonn.getValue()).getId_abonnement(), date_debut, date_fin);
        sa.ajouter(nouv_abonn);
        String message = "Votre abonnement est valable de " + nouv_abonn.getDate_debut_abonnement() + " à " + nouv_abonn.getDate_fin_abonnement() + ".";
        //Send SMS
        SMS sms = new SMS();
        sms.sendSMS(tfTel.getText(), message);
        //Send Mail
        Mailer_Abonnement mailer = new Mailer_Abonnement();
        mailer.SendMail("mohamedsaid.bouchouicha@esprit.tn", message);
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
