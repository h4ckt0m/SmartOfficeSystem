package application.controllers;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import application.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerMensajesUsuario implements Initializable {

	@FXML
	private Pane upperBar;
	@FXML
	private TextFlow textFlow;
	@FXML
	private TextField textField;
	@FXML
	private ScrollPane sPane;

	private Stage stage;
	private double xOffset = 0;
	private double yOffset = 0;
	private String data;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			stage = (Stage) upperBar.getScene().getWindow();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
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
			TimerTask timerTask = new TimerTask() {
				public void run() {
					Platform.runLater(() -> {
						update();
		            });
				}
			};
			// Aquí se pone en marcha el timer cada segundo.
			Timer timer = new Timer();
			// Dentro de 0 milisegundos avísame cada 2500 milisegundos
			timer.scheduleAtFixedRate(timerTask, 0, 2500);
			sPane.setVvalue(1.0);

	}

	public void update() {
		try {
			data = Main.requestData(Main.loggedUser.getUsuario(),"chat");
			Main.ordenatedMessages = Main.gson.fromJson(data, TreeMap.class);
			printTree();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send() {
		LocalDateTime now = LocalDateTime.now();
		Main.ordenatedMessages.put(dtf.format(now), "-"+Main.loggedUser.getUsuario()+": " + textField.getText());
		printTree();
		textField.clear();
		Platform.runLater(() -> {
			try {
				System.out.println(Main.writeCommit(Main.gson.toJson(Main.ordenatedMessages), Main.loggedUser.getUsuario(),"chat"));
				System.out.println(Main.updateData("commitFile.json", Main.loggedUser.getUsuario()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void printTree() {
		ObservableList list = textFlow.getChildren();
		list.clear();
		Main.ordenatedMessages.keySet().forEach(key -> {
			String content = Main.ordenatedMessages.get(key).toString();
			Text text1 = new Text(content);
			text1.setFont(new Font(15));
			if (content.charAt(1) == 'A') {
				text1.setFill(Color.DARKSLATEBLUE);
			} else {
				text1.setFill(Color.MEDIUMVIOLETRED);
			}
			Text text2 = new Text("\t(" + key + ")\n");
			text2.setFont(new Font(11));
			text2.setFill(Color.DIMGREY);
			list.addAll(text1, text2);
		});
	}

	public void pressEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			send();
		}
	}
	public void exit() {
		   stage.close();
	   } 
}
