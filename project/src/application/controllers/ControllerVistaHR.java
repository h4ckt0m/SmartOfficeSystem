package application.controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVistaHR implements Initializable {
    @FXML
    LineChart<String,Number> lineChartHR;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lineChartHR.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        for (int i = 0; i < Main.loggedUser.getPulsos().size(); i++) {
            String fecha = Main.loggedUser.getPulsos().get(i).getFecha().toString();
            Number ppm = Main.loggedUser.getPulsos().get(i).getDato();
            series.getData().add(new XYChart.Data<String, Number>(fecha, ppm));
        }
        series.setName("Estadisticas Pulso Cardiaco");
        lineChartHR.getData().add(series);
        for (final XYChart.Data<String, Number> data : series.getData()) {
            Tooltip.install(data.getNode(), new Tooltip("X : " + data.getXValue() + "\n Y : " + data.getYValue())); //ToolTip XY Nodos
        }
    }
}
