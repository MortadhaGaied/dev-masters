/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI;

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
    @FXML
    private Label SoldItem;
    @FXML
    private Label txtTotalCustomers;
    @FXML
    private Label TotalOrders;
    @FXML
    private Pane DashPane1;
    @FXML
    private AreaChart<String, Number> areaChart;
    @FXML
    private Label TotalSales;
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
        txtTotalCustomers.setText(String.valueOf(se.nbUser()));
    }
    public void loadchart(){
        XYChart.Series series =new XYChart.Series();
        series.getData().add(new XYChart.Data("1",23));
        series.getData().add(new XYChart.Data("2",65));
        series.getData().add(new XYChart.Data("3",68));
        series.getData().add(new XYChart.Data("4",32));
        series.getData().add(new XYChart.Data("5",56));
        series.getData().add(new XYChart.Data("6",76));
        series.getData().add(new XYChart.Data("7",44));
        areaChart.getData().add(series);
    }
    
}
