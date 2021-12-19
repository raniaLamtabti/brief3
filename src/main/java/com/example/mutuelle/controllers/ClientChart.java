package com.example.mutuelle.controllers;

import com.example.mutuelle.DAO.Clients;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ClientChart implements Initializable {

    @FXML
    BarChart<String,Number> chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Clients clients = new Clients();
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        HashMap<String, Integer> statistic = clients.clientsByDate();
        for (String key : statistic.keySet()) {
            series.getData().add(new XYChart.Data<>(key, statistic.get(key)));
        }

        chart.getData().add(series);
    }
}
