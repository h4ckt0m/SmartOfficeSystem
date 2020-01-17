package application.controllers.admin;

import application.Main;
import application.controllers.usuario.ControllerVistaConfiguracionUsuario;
import application.controllers.windows.LogOutWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerVistaAdmin implements Initializable {
    @FXML
    private AnchorPane anchor;
    @FXML
    private VBox pnItems = null;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Pane paneConfi;
    @FXML
    private Pane paneCalAire;
    @FXML
    private Pane paneTemperatura;
    @FXML
    private Pane paneHumedad;
    @FXML
    private Pane paneLuminosidad;
    @FXML
    private Pane paneRuido;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnConfiguracion;
    @FXML
    private Button btnTemperatura;
    @FXML
    private Button btnHumedad;
    @FXML
    private Button btnLumin;
    @FXML
    private Button btnCalAire;
    @FXML
    private Button btnRuido;
    @FXML
    private Label lbNomApe;
    @FXML
    private Label lbFecha;
    @FXML
    private ImageView image;
    @FXML
    private ImageView profileImg;

  //PROGRESS BARS---------------------------------------
    @FXML
    private ProgressBar pbTemp;
    @FXML
    private ProgressBar pbHum;
    @FXML
    private ProgressBar pbLum;
    @FXML
    private ProgressBar pbAire;
    @FXML
    private ProgressBar pbRuido;
    @FXML
    private Label lbTemp;
    @FXML
    private Label lbHum;
    @FXML
    private Label lbLum;
    @FXML
    private Label lbAire;
    @FXML
    private Label lbRuido;
    //-----------------------------------------------------
    
    
    public static int i;
    public static int selectAmb;
    private double xOffset = 0;
    private double yOffset = 0;
    public FileReader fr;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[Main.office.size()];
        for (i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/application/scenes/admin/listaVistaAdmin.fxml"));
                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #5B53AA");

                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color :  #3B3292");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lbNomApe.setText((Main.loggedAdmin.get("nombre")) + " " + Main.loggedAdmin.get("apellidos"));
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        lbFecha.setText(day + "/" + month + "/" + year);

        anchor.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = Main.stage.getX() - event.getScreenX();
                yOffset = Main.stage.getY() - event.getScreenY();
            }
        });
        anchor.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.stage.setX(event.getScreenX() + xOffset);
                Main.stage.setY(event.getScreenY() + yOffset);
            }
        });


        Timeline actualizarAmbientales = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateAmbient()));
        actualizarAmbientales.setCycleCount(Timeline.INDEFINITE);
        actualizarAmbientales.play();
		
		 try {
			 profileImg.setImage(ControllerVistaConfiguracionUsuario.requestImage(Main.loggedAdmin.get("usuario")+".jpg", "perfiles"));
			} catch (Exception e) {
				e.printStackTrace();
			}
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException { //Gestion de los botones condiciones ambientales
        if (actionEvent.getSource() == btnHome){
            panePrincipal.toFront();
        }
        else if(actionEvent.getSource() == btnConfiguracion){
            //to do
        }
        else if(actionEvent.getSource() == btnTemperatura){
            selectAmb = 1;
            Pane vistaPaneTemp = FXMLLoader.load(getClass().getResource("/application/scenes/admin/ambientalesAdmin.fxml"));
            paneTemperatura.getChildren().add(vistaPaneTemp);
            paneTemperatura.toFront();
        }
        else if(actionEvent.getSource() == btnHumedad){
            selectAmb = 2;
            Pane vistaPaneHum = FXMLLoader.load(getClass().getResource("/application/scenes/admin/ambientalesAdmin.fxml"));
            paneHumedad.getChildren().add(vistaPaneHum);
            paneHumedad.toFront();
        }
        else if(actionEvent.getSource() == btnLumin){
            selectAmb = 3;
            Pane vistaPaneLum = FXMLLoader.load(getClass().getResource("/application/scenes/admin/ambientalesAdmin.fxml"));
            paneLuminosidad.getChildren().add(vistaPaneLum);
            paneLuminosidad.toFront();
        }
        else if(actionEvent.getSource() == btnCalAire){
            selectAmb = 4;
            Pane vistaPaneCal = FXMLLoader.load(getClass().getResource("/application/scenes/admin/ambientalesAdmin.fxml"));
            paneCalAire.getChildren().add(vistaPaneCal);
            paneCalAire.toFront();
        }
        else if(actionEvent.getSource() == btnRuido){
            selectAmb = 5;
            Pane vistaPaneRuido = FXMLLoader.load(getClass().getResource("/application/scenes/admin/ambientalesAdmin.fxml"));
            paneRuido.getChildren().add(vistaPaneRuido);
            paneRuido.toFront();
        }
    }

    public void openChat() throws IOException {
        Scene chatUScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/admin/vistaChatAdmin.fxml")));
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(chatUScene);
        primaryStage.show();
    }
    
    public void updateAmbient() {
 	   double lumin = 0;  //en porcentaje
 	   double temp = 0; //en grados
        double humedad = 0;  //en porcentaje
        double ruido = 0;  //en porcentaje
        double aire = 0;  //en porcentaje de contaminacion
 	   
 	   try {
 		fr=new FileReader("project/ambientales.txt");
 		String cadena;
 		int counter = 0;
 	    BufferedReader b = new BufferedReader(fr);
 	      while((cadena = b.readLine())!=null) {
 	    	  switch(counter){
 	    	  case 0:
 	    		  lumin = Double.parseDouble(cadena.substring(0,5));
 	    		  break;
 	    	  case 1:
 	    		  temp = Double.parseDouble(cadena.substring(0,5));
 	    		  break;
 	    	  case 2:
 	    		  humedad = Double.parseDouble(cadena.substring(0,5));
 	    		  break;
 	    	  case 3:
 	    		  ruido = Double.parseDouble(cadena.substring(0,5));
 	    		  break;
 	    	  case 4:
 	    		  aire = Double.parseDouble(cadena.substring(0,5));
 	    		  break;
 	    	  }
 	    	  counter++;
 	      }
 	    b.close();
 		fr.close();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
 	   pbTemp.setProgress(temp/100);
        lbTemp.setText(temp+" ºC");
        pbHum.setProgress(humedad/100);
        lbHum.setText(humedad+" %");
        pbLum.setProgress(lumin/100);
        lbLum.setText(lumin+" %");
        pbAire.setProgress(aire/100);
        lbAire.setText(aire+" %");
        pbRuido.setProgress(ruido/100);
        lbRuido.setText(ruido+" %");
 	   
    }
    
    
    public void logOut() {
        LogOutWindow l = new LogOutWindow(Main.stage);
    }
    public void exit() {
        Main.exit();
    }
}
