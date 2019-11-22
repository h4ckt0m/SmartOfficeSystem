package application.controllers;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerHRateUser implements Initializable {

	@FXML
	private Label l1;
	Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			stage = (Stage) l1.getScene().getWindow();
		});
		
		
		int max = 200;
		int min = 50;
		TimerTask timerTask = new TimerTask() {
			public void run() {
				
				// Aquí el código que queremos ejecutar.
				Platform.runLater(() -> {
					l1.setText((int) (Math.random() * ((max - min) + 1) + min)+" PPM");
				});
			}
		};

		// Aquí se pone en marcha el timer cada segundo.
		Timer timer = new Timer();
		// Dentro de 0 milisegundos avísame cada 1000 milisegundos
		timer.scheduleAtFixedRate(timerTask, 0, 1000);

		
		/*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();*/
	}

	public void change() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/application/scenes/login.fxml"));
			stage.setTitle("SOS - Login");
			double w = stage.getWidth();
			double h = stage.getHeight();
			stage.setScene(new Scene(root));
			stage.setWidth(w);
			stage.setHeight(h);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageWindow m = new MessageWindow("MESSAGE\nMessage window 2", stage);
	}
	public void exit() {
		/*Stage stage = (Stage) l1.getScene().getWindow();
		stage.close();*/
		System.exit(0);
		
	}
}
