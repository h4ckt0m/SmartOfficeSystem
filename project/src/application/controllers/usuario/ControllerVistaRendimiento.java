package application.controllers.usuario;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerVistaRendimiento implements Initializable {
	@FXML
	private LineChart<String, Number> lineChartRendimiento;
	@FXML
	private DatePicker inicio;
	@FXML
	private DatePicker fin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		fin.setValue(localDate.minusDays(1));
		inicio.setValue(localDate.minusDays(10));
		dibujar();
	}
	
	public int formatFecha(String year, String month, String day) {
		if(Integer.parseInt(day)<10) {
			day="0"+day;
		}
		if(Integer.parseInt(month)<10) {
			month="0"+month;
		}
		return Integer.parseInt(year+month+day);
	}
	public void dibujar() {
		lineChartRendimiento.getData().clear();

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		for (int i = 0; i < Main.loggedUser.getProductividad().size(); i++) {
			int anio = Main.loggedUser.getProductividad().get(i).getFecha().getYear();
			int mes = Main.loggedUser.getProductividad().get(i).getFecha().getMes();
			int dia = Main.loggedUser.getProductividad().get(i).getFecha().getDia();

			int result = formatFecha(anio+"", mes+"", dia+"");
			int init = formatFecha(inicio.getValue().getYear()+"", inicio.getValue().getMonthValue()+"", inicio.getValue().getDayOfMonth()+"");
			int end = formatFecha(fin.getValue().getYear()+"", fin.getValue().getMonthValue()+"", fin.getValue().getDayOfMonth()+"");
			
			if((result>=init)&&(result<=end)) {
				String fecha = Main.loggedUser.getProductividad().get(i).getFecha().toString();
				Number productividad = Main.loggedUser.getProductividad().get(i).getProductividad() * 100;
				series.getData().add(new XYChart.Data<String, Number>(fecha, productividad));
			}
		}
		lineChartRendimiento.getData().add(series);
		for (final XYChart.Data<String, Number> data : series.getData()) {
			Tooltip.install(data.getNode(), new Tooltip("X : " + data.getXValue() + "\n Y : " + data.getYValue())); // ToolTip
																													// XY
																													// Nodos
		}
	}
}
