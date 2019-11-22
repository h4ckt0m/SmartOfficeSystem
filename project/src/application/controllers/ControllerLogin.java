package application.controllers;

import application.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ControllerLogin implements Initializable {

	@FXML
	private Label l1;
	@FXML
	private Label upperBar;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField pField;

	private Stage stage;
	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			stage = (Stage) l1.getScene().getWindow();
		});

		upperBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = stage.getX() - event.getScreenX();
				yOffset = stage.getY() - event.getScreenY();
			}
		});
		upperBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() + xOffset);
				stage.setY(event.getScreenY() + yOffset);
			}
		});
	}

	public void checkUserPassword() {
		if (MyApplication.admin.containsKey(userField.getText())) {
			if (((JSONObject) MyApplication.admin.get(userField.getText())).get("password").toString().equals(pField.getText())) {
				MessageWindow m = new MessageWindow(
						"ACCESS GRANTED\nLogin successfully completed\nWelcome Administrator", stage);
				change("Escena");
			} else {
				MessageWindow m = new MessageWindow("INCORRECT PASSWORD\nPlease check the data introduced", stage);
				pField.setText("");
			}
		} else if (MyApplication.office.containsKey(userField.getText())) {
			if (((JSONObject) MyApplication.office.get(userField.getText())).get("password").toString().equals(pField.getText())) {
				MessageWindow m = new MessageWindow(
						"ACCESS GRANTED\nLogin successfully completed\nWelcome office worker", stage);
				change("hRateUser");
			} else {
				MessageWindow m = new MessageWindow("INCORRECT PASSWORD\nPlease check the data introduced", stage);
				pField.setText("");
			}
		} else {
			MessageWindow m = new MessageWindow("INCORRECT USER\nPlease check the data introduced", stage);
			pField.setText("");
		}
	}

	public void change(String scene) {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/application/scenes/"+scene+".fxml"));
			stage.setTitle("SOS - Heart Rate User");
			/*double w = stage.getWidth();
			double h = stage.getHeight();*/
			stage.setScene(new Scene(root));
			/*stage.setWidth(w);
			stage.setHeight(h);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void pressEnter(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			checkUserPassword();
		}
	}
	public void exit() {
		/*
		 * Stage stage = (Stage) l1.getScene().getWindow(); stage.close();
		 */
		System.exit(0);
	}
}