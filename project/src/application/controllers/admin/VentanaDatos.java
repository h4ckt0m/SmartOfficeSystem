package application.controllers.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Oficinista;
import application.controllers.usuario.ControllerVistaConfiguracionUsuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VentanaDatos{

	@FXML
	AnchorPane anchor;
	@FXML
	Label lbUsuario;
	@FXML
	Label lbNombre;
	@FXML
	Label lbApellidos;
	@FXML
	Label lbFechaNacimiento;
	@FXML
	Label lbSueldo;
	@FXML
	Label lbDepartamento;
	@FXML
	Button btnHR;
	@FXML
	Button btnEA;
	@FXML
	Button btnPR;
	@FXML
	Pane paneHR;
	@FXML
	Pane paneEA;
	@FXML
	Pane panePR;
	@FXML
	Pane panePDatos;
	@FXML
	LineChart<String, Number> lcHR;
	@FXML
	LineChart<String, Number> lcEA; // Pillar tiempo en hora para la x
	@FXML
	LineChart<String, Number> lcPR;
	@FXML
	ImageView profileImage;

	private Oficinista ofi;

	private double xOffset = 0;
	private double yOffset = 0;
	Stage primaryStage;

	public VentanaDatos(Oficinista ofi) {
		try {
			this.ofi = ofi;
			//SET ROOT AND CONTROLLER (THIS CLASS IS THE CONTROLLER)
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/scenes/admin/vistaDatosOfi.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			
			//BLOCK MAIN STAGE
			primaryStage = new Stage();
			//primaryStage.initModality(Modality.WINDOW_MODAL); //blocks owner window
			//primaryStage.initModality(Modality.APPLICATION_MODAL); //blocks all windows
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initOwner(Main.stage);
			
			//SHOW MESSAGE WINDOW
			//primaryStage.setTitle("Message");
			Scene sc = new Scene(root);
			primaryStage.setScene(sc);
			primaryStage.show();
			
			//SET LABELS TEXTS
			lbUsuario.setText(this.ofi.getUsuario());
			lbNombre.setText(this.ofi.getNombre());
			lbApellidos.setText(this.ofi.getApellidos());
			lbFechaNacimiento.setText(this.ofi.getF_nac().toString());
			lbSueldo.setText(String.valueOf(this.ofi.getSueldo()));
			lbDepartamento.setText(this.ofi.getDepartamento());
			
			try {
				profileImage.setImage(ControllerVistaConfiguracionUsuario.requestImage(this.ofi.getUsuario()+".jpg", "perfiles"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		anchor.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = primaryStage.getX() - event.getScreenX();
				yOffset = primaryStage.getY() - event.getScreenY();
			}
		});
		anchor.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() + xOffset);
				primaryStage.setY(event.getScreenY() + yOffset);
			}
		});
	}

	public void handleClicks(ActionEvent actionEvent){
		if (actionEvent.getSource() == btnHR) {
			panePDatos.toFront();
			lcHR.getData().clear();
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			for (int i = 0; i < ofi.getPulsos().size(); i++){
				String fecha = ofi.getPulsos().get(i).getFecha().toString();
				Number ppm = ofi.getPulsos().get(i).getDato();
				series.getData().add(new XYChart.Data<String, Number>(fecha, ppm));
			}
			lcHR.setTitle("Estadisticas Pulso Cardiaco");
			lcHR.getData().add(series);
			for (final XYChart.Data<String, Number> data : series.getData()) {
				Tooltip.install(data.getNode(), new Tooltip("X : " + data.getXValue() + "\n Y : " + data.getYValue())); //ToolTip XY Nodos
			}
			paneHR.toFront();
		}
		else if(actionEvent.getSource() == btnEA){
			panePDatos.toFront();
			lcEA.getData().clear();
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			for (int i = 0; i < ofi.getEa().size(); i++){
				String fecha = ofi.getEa().get(i).getEntrada().toString();
				Number horas = ofi.getEa().get(i).getTiempo();
				series.getData().add(new XYChart.Data<String, Number>(fecha, horas));
			}
			lcEA.setTitle("Estadisticas Horas trabajadas");
			lcEA.getData().add(series);
			for (final XYChart.Data<String, Number> data : series.getData()) {
				Tooltip.install(data.getNode(), new Tooltip("X : " + data.getXValue() + "\n Y : " + data.getYValue())); //ToolTip XY Nodos
			}
			paneEA.toFront();
		}
		else if(actionEvent.getSource() == btnPR){
			panePDatos.toFront();
			lcPR.getData().clear();
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			for (int i = 0; i < ofi.getProductividad().size(); i++){
				String fecha = ofi.getProductividad().get(i).getFecha().toString();
				Number tareasCompletadas = ofi.getProductividad().get(i).getProductividad();
				series.getData().add(new XYChart.Data<String, Number>(fecha, tareasCompletadas));
			}
			lcPR.setTitle("Estadisticas productividad");
			lcPR.getData().add(series);
			for (final XYChart.Data<String, Number> data : series.getData()) {
				Tooltip.install(data.getNode(), new Tooltip("X : " + data.getXValue() + "\n Y : " + data.getYValue())); //ToolTip XY Nodos
			}
			panePR.toFront();
		}
	}

	public void exit() {
		primaryStage.close();
	}
}
