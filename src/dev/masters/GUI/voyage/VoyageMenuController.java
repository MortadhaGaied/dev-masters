/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.voyage;

import com.jfoenix.controls.JFXButton;
import dev.masters.api.JfreeChartApi;
import dev.masters.entites.MoyenDeTransport;
import dev.masters.entites.Position;
import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.services.VoyageService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Moha
 */
public class VoyageMenuController implements Initializable {

    @FXML
    private Label Menu;
    @FXML
    private ImageView Exit;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label MenuClose;
    @FXML
    private TableView<Voyage> tv_voyages;
    @FXML
    private TableColumn<Voyage, Long> col_id;
    private TableColumn<Voyage, String> col_p_d;
    private TableColumn<Voyage, String> col_p_a;
    private TableColumn<Voyage, java.sql.Date> col_date;
    private TableColumn<Voyage, String> col_station;
    private TableColumn<Voyage, String> col_mt;
    VoyageService sv ;
    @FXML
    private JFXButton btn_dash;
    @FXML
    private JFXButton btn_statistiques;
    @FXML
    private JFXButton btn_quitter;
    @FXML
    private JFXButton btn_archive;
    @FXML
    private JFXButton btn_stat;
    @FXML
    private JFXButton btn_q;
    @FXML
    private TableColumn<Voyage, String> col_ref_Voy;
    @FXML
    private TableColumn<Voyage, Integer> col_Mt;
    @FXML
    private TableColumn<Voyage, Integer> col_sd;
    @FXML
    private TableColumn<Voyage, Integer> col_station_arrive;
    @FXML
    private TableColumn<Voyage, LocalDateTime> col_date_depart;
    @FXML
    private TableColumn<Voyage, LocalDateTime> col_date_arrive;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slider.setTranslateX(-250);
       sv = new VoyageService();
        // TODO
        try {
            refreshlist();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
        
    }    

    
    public void refreshlist() throws Exception {
            
        
            ObservableList<Voyage> data = FXCollections.observableArrayList();
            
        List<Voyage> voyages = new ArrayList(sv.afficher());
            System.out.println(voyages);
        if (voyages == null) {
            voyages = new ArrayList(sv.afficher());
        }
 
        
        for (Voyage voyage : voyages) {
            data.add(voyage);
            //System.out.println(voyage);
  
         }
        
        col_id.setCellValueFactory(new PropertyValueFactory<Voyage, Long>("id"));
        col_ref_Voy.setCellValueFactory(new PropertyValueFactory<Voyage, String>("ref_voyage"));
        col_Mt.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("moyen_de_transport"));
        col_sd.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("station_depart"));
        col_station_arrive.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("station_arrive"));
        col_date_depart.setCellValueFactory(new PropertyValueFactory<Voyage, LocalDateTime>("date_depart"));
        col_date_arrive.setCellValueFactory(new PropertyValueFactory<Voyage, LocalDateTime>("date_arrive"));
        
        tv_voyages.setItems(data);
            System.out.println(data);
       
        
       
        
        }
    
    @FXML
    private void Menu(MouseEvent event) {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-250);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
       
    }

    @FXML
    private void MenuClose(MouseEvent event) {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-250);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        
    }
    

    @FXML
    private void BackToMenu(MouseEvent event) {
         Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/voyage/VoyageHome.fxml"));
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

    @FXML
    private void goToDashBoard(ActionEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/voyage/GestionVoyage.fxml"));
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
    private void showStatistiques(ActionEvent event) {
         
        HashMap<String, Double> data = sv.StatistiquesParStation();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }

    @FXML
    private void quitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void goToArchive(ActionEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/dev/masters/GUI/voyage/Archive.fxml"));
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