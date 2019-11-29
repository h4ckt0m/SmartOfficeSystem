package application.controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVistaRendimiento implements Initializable {
    @FXML
    LineChart<String,Number> lineChartRendimiento;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lineChartRendimiento.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        for (int i = 0; i < Main.loggedUser.getProductividad().size(); i++) {
            String fecha = Main.loggedUser.getProductividad().get(i).getFecha().toString();
            Number productividad = Main.loggedUser.getProductividad().get(i).getProductividad() * 100;
            series.getData().add(new XYChart.Data<String, Number>(fecha, productividad));
        }
        lineChartRendimiento.getData().add(series);
        for (final XYChart.Data<String, Number> data : series.getData()) {
            Tooltip.install(data.getNode(), new Tooltip("X : " + data.getXValue() + "\n Y : " + data.getYValue())); //ToolTip XY Nodos
        }
    }
}
