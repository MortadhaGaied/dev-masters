/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

import dev.masters.services.ServiceReclamation;
import dev.masters.services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class test implements Initializable {

    @FXML
    private Pane DashPane;
    private Label txtTotalCustomers;
    private Label TotalOrders;
    @FXML
    private Pane DashPane1;
    @FXML
    private AreaChart<String, Number> areaChart;
    @FXML
    private BarChart<String, Number> TransportChart;
    @FXML
    private NumberAxis nbabonnementvendueareachart;
    @FXML
    private CategoryAxis moisareachart;
    @FXML
    private NumberAxis nbmoyenBarchart;
    @FXML
    private CategoryAxis MoyenBarchart;
    ServiceUser se = new ServiceUser();
    ServiceReclamation sr =new ServiceReclamation();
    @FXML
    private Label nbRec;
    @FXML
    private Label nbUser;
    @FXML
    private Label nbMDT;
    @FXML
    private Label totalvente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Userinfo();
        ChartMDT();
        
    }  
    public void ChartMDT(){
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("Len Nombres Des Moyens De Transport");
        series.getData().add(new XYChart.Data<>("Metro",30));
        series.getData().add(new XYChart.Data<>("Train",20));
        series.getData().add(new XYChart.Data<>("Bus",100));
        TransportChart.getData().add(series);
        loadchart();
    }
    public void Userinfo(){
        nbUser.setText(String.valueOf(se.nbUser()));
        nbRec.setText(String.valueOf(sr.nbReclamation()));
        
    }
    public void loadchart(){
        XYChart.Series series =new XYChart.Series();
        series.getData().add(new XYChart.Data("Janvier",23));
        series.getData().add(new XYChart.Data("Fevrier",65));
        series.getData().add(new XYChart.Data("Mars",68));
        series.getData().add(new XYChart.Data("Avril",32));
        series.getData().add(new XYChart.Data("Mai",56));
        series.getData().add(new XYChart.Data("Juin",76));
        series.getData().add(new XYChart.Data("Juillet",44));
        areaChart.getData().add(series);
    }
    
}
