package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Oficinista;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VentanaDatos{

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
	LineChart<String, Number> lcHR;
	@FXML
	LineChart<String, Number> lcEA; //Mirar como hacer este.
	@FXML
	LineChart<String, Number> lcPR;


	public VentanaDatos(Oficinista ofi, Stage st) {
		try {
			
			//SET ROOT AND CONTROLLER (THIS CLASS IS THE CONTROLLER)
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/scenes/vistaDatosOfi.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			
			//BLOCK MAIN STAGE
			Stage primaryStage = new Stage();
			//primaryStage.initModality(Modality.WINDOW_MODAL); //blocks owner window
			//primaryStage.initModality(Modality.APPLICATION_MODAL); //blocks all windows
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.initOwner(st);
			
			//SHOW MESSAGE WINDOW
			//primaryStage.setTitle("Message");
			Scene sc = new Scene(root);
			primaryStage.setScene(sc);
			primaryStage.show();
			
			//SET LABELS TEXTS
			lbUsuario.setText(ofi.getUsuario());
			lbNombre.setText(ofi.getNombre());
			lbApellidos.setText(ofi.getApellidos());
			lbFechaNacimiento.setText(ofi.getF_nac().toString());
			lbSueldo.setText(String.valueOf(ofi.getSueldo()));
			lbDepartamento.setText(ofi.getDepartamento());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleClicks(ActionEvent actionEvent){
		if (actionEvent.getSource() == btnHR) {

			paneHR.toFront();
		}
		else if(actionEvent.getSource() == btnEA){

			paneEA.toFront();
		}
		else if(actionEvent.getSource() == btnPR){

			panePR.toFront();
		}
	}

	public void pressEnter(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			close();
		}
	}
	public void close() {
		Stage s = (Stage) lbNombre.getScene().getWindow();
		s.close();
	}
}
