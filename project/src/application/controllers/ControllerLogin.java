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

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.CheckBox;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ControllerLogin implements Initializable {

	@FXML
	private Label l1;
	@FXML
	private Label upperBar;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField pField;
	@FXML
	private ImageView SOSImage;
	@FXML
	private Label contra;
	@FXML
	private CheckBox recuerda;
	@FXML
	private Button entrar;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		upperBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = Main.stage.getX() - event.getScreenX();
				yOffset = Main.stage.getY() - event.getScreenY();
			}
		});
		upperBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.stage.setX(event.getScreenX() + xOffset);
				Main.stage.setY(event.getScreenY() + yOffset);
			}
		});
	}

	public void partyMode() {
//----------TRANSITION--------------------------------------------------
		
		
		 //Creating scale Transition 
	      ScaleTransition scaleTransition = new ScaleTransition(); 
	      
	      //Setting the duration for the transition 
	      scaleTransition.setDuration(Duration.millis(1000)); 
	      
	      //Setting the node for the transition 
	      scaleTransition.setNode(userField); 
	      
	      //Setting the dimensions for scaling 
	      scaleTransition.setByY(1.5); 
	      scaleTransition.setByX(1.5); 
	      
	      //Setting the cycle count for the translation 
	      scaleTransition.setCycleCount(50); 
	      
	      //Setting auto reverse value to true 
	      scaleTransition.setAutoReverse(true); 
	      
	      //Playing the animation 
	      scaleTransition.play(); 
		
	      
	      
	      
	    //Creating a rotate transition    
	      RotateTransition rotateTransition = new RotateTransition(); 
	      
	      //Setting the duration for the transition 
	      rotateTransition.setDuration(Duration.millis(1000)); 
	      
	      //Setting the node for the transition 
	      rotateTransition.setNode(SOSImage);       
	      
	      //Setting the angle of the rotation 
	      rotateTransition.setByAngle(360); 
	      
	      //Setting the cycle count for the transition 
	      rotateTransition.setCycleCount(50); 
	      
	      //Setting auto reverse value to false 
	      rotateTransition.setAutoReverse(false); 
	      
	      //Playing the animation 
	      rotateTransition.play(); 
	      
	      
	      
	      
	    //Creating Translate Transition 
	      TranslateTransition translateTransition = new TranslateTransition(); 
	      
	      //Setting the duration of the transition  
	      translateTransition.setDuration(Duration.millis(1000)); 
	      
	      //Setting the node for the transition 
	      translateTransition.setNode(pField); 
	      
	      //Setting the value of the transition along the x axis. 
	      translateTransition.setByX(300); 
	      
	      //Setting the cycle count for the transition 
	      translateTransition.setCycleCount(50); 
	      
	      //Setting auto reverse value to false 
	      translateTransition.setAutoReverse(true); 
	      
	      //Playing the animation 
	      translateTransition.play(); 
	      
	      
	      
	    //Creating the fade Transition 
	      FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000)); 
	      
	      //Setting the node for Transition 
	      fadeTransition.setNode(contra); 
	      
	      //Setting the property fromValue of the transition (opacity) 
	      fadeTransition.setFromValue(1.0); 
	      
	      //Setting the property toValue of the transition (opacity) 
	      fadeTransition.setToValue(0.1); 
	      
	      //Setting the cycle count for the transition 
	      fadeTransition.setCycleCount(50); 
	      
	      //Setting auto reverse value to false 
	      fadeTransition.setAutoReverse(true); 
	  
	      //Playing the animation 
	      fadeTransition.play(); 
	      
	      
	      
	    //Creating a Path 
	      Path path = new Path(); 
	      
	      //Moving to the starting point 
	      MoveTo moveTo = new MoveTo(-200, -200);               
	      
	      //Creating 1st line 
	      LineTo line1 = new LineTo(-100, -150);        
	      
	      //Creating 2nd line 
	      LineTo line2 = new LineTo(40,-100); 
	      
	      //Creating 3rd line 
	      LineTo line3 = new LineTo(-50,0);        
	      
	      //Creating 4th line 
	      LineTo line4 = new LineTo(-100, 100);        
	      
	      //Creating 5th line 
	      LineTo line5 = new LineTo(34, 0);       
	      
	      //Adding all the elements to the path  
	      path.getElements().add(moveTo); 
	      path.getElements().addAll(line1, line2, line3, line4, line5);     
	      
	      //Creating the path transition 
	      PathTransition pathTransition = new PathTransition(); 
	      
	      //Setting the duration of the transition 
	      pathTransition.setDuration(Duration.millis(1000));       
	      
	      //Setting the node for the transition 
	      pathTransition.setNode(recuerda); 
	      
	      //Setting the path for the transition 
	      pathTransition.setPath(path); 
	      
	      //Setting the orientation of the path 
	      pathTransition.setOrientation(
	         PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
	      
	      //Setting the cycle count for the transition 
	      pathTransition.setCycleCount(50); 
	      
	      //Setting auto reverse value to true 
	      pathTransition.setAutoReverse(true); 
	      
	      //Playing the animation 
	      pathTransition.play(); 
	      
	      
	      
	      
	      //Creating a Pause Transition
	      PauseTransition pauseTransition = new PauseTransition(); 
	      
	      //Setting the duration for the transition 
	      pauseTransition.setDuration(Duration.millis(1000)); 
	       
	      //Creating Translate Transition 
	      TranslateTransition translateTransition2 = new TranslateTransition();  
	      
	      //Setting the duration for the transition 
	      translateTransition2.setDuration(Duration.millis(1000));       
	      
	      //Setting the node of the transition 
	      translateTransition2.setNode(entrar); 
	      
	      //Setting the value of the transition along the x axis 
	      translateTransition2.setByX(300); 
	      
	      //Setting the cycle count for the stroke 
	      translateTransition2.setCycleCount(5);  
	      
	      //Setting auto reverse value to true 
	      translateTransition2.setAutoReverse(true);  
	       
	      //Creating scale Transition 
	      ScaleTransition scaleTransition2 = new ScaleTransition(); 
	      
	      //Setting the duration for the transition 
	      scaleTransition2.setDuration(Duration.millis(1000)); 
	      
	      //Setting the node for the transition 
	      scaleTransition2.setNode(entrar); 
	      
	      //Setting the dimensions for scaling 
	      scaleTransition2.setByY(2); 
	      scaleTransition2.setByX(2); 
	      
	      //Setting the cycle count for the translation 
	      scaleTransition2.setCycleCount(5); 
	      
	      //Setting auto reverse value to true 
	      scaleTransition2.setAutoReverse(true);       
	       
	      //Applying Sequential transition to the circle 
	      SequentialTransition sequentialTransition = new SequentialTransition(
	         entrar, translateTransition2, pauseTransition, scaleTransition2);
	      
	      //Playing the animation 
	      sequentialTransition.play(); 
	      
	      
//----------TRANSITION--------------------------------------------------
		
	}
	
	
	public void checkUserPassword(){
		String user = userField.getText();
		String password = pField.getText();
		if (Main.admin.containsKey(user)) {
			if (Main.admin.get(user).get("password").equals(password)) {
				MessageWindow m = new MessageWindow(
						"ACCESS GRANTED\nLogin successfully completed\nWelcome Administrator", Main.stage);
				Main.loggedAdmin = Main.admin.get(user);
				try {
					Scene chatUScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/vistaAdmin.fxml")));
					Stage primaryStage = new Stage();
					primaryStage.initStyle(StageStyle.UNDECORATED);
					primaryStage.setScene(chatUScene);
					primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				MessageWindow m = new MessageWindow("INCORRECT PASSWORD\nPlease check the data introduced", Main.stage);
				pField.setText("");
			}
		} else if (Main.office.containsKey(user)) {
			if (Main.office.get(user).getPassword().equals(password)) {
				MessageWindow m = new MessageWindow(
						"ACCESS GRANTED\nLogin successfully completed\nWelcome office worker", Main.stage);
					Main.loggedUser = Main.office.get(user);
					try {
						Scene usuarioScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/vistaUsuario.fxml")));
						Main.stage.setScene(usuarioScene);
						Main.stage.setX((Main.primScreenBounds.getWidth() - Main.stage.getWidth()) / 2);
						Main.stage.setY((Main.primScreenBounds.getHeight() - Main.stage.getHeight()) / 2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				MessageWindow m = new MessageWindow("INCORRECT PASSWORD\nPlease check the data introduced", Main.stage);
				pField.setText("");
			}
		} else {
			MessageWindow m = new MessageWindow("INCORRECT USER\nPlease check the data introduced", Main.stage);
			pField.setText("");
		}
	}
	
	public void pressEnter(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			checkUserPassword();
		}
	}
	public void exit() {
		Main.exit();
	}
}