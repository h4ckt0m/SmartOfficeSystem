package application.controllers.usuario;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.controllers.windows.LogOutWindow;

public class ControllerVistaUsuario implements Initializable {
	 @FXML
	    private VBox pnItems = null;
	    @FXML
	    private Pane panePrincipal;
	    @FXML
	    private Pane paneHR;
	    @FXML
	    private Pane paneRendimiento;
	    @FXML
	    private Pane panePerfil;
	    @FXML
	    private Pane paneConfi;
	    @FXML
	    private Button btnHR;
	    @FXML
	    private Button btnHome;
	    @FXML
	    private Button btnRendimiento;
	    @FXML
	    private Button btnConfiguracion;
	    @FXML
	    private Button btnPerfil;
	    @FXML
	    private Label lbNomApe;
	    @FXML
	    private Label lbDepartamento;
	    @FXML
	    private Label lbFecha;
	    @FXML
	    private AnchorPane anchor;
	    @FXML
	    private ImageView image;
	    
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
	    public FileReader fr;
    
	private double xOffset = 0;
	private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    	Node[] nodes = new Node[Main.loggedUser.getEa().size()];
        for (i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/application/scenes/usuario/listaVistaUsuario.fxml"));
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

        lbNomApe.setText(Main.loggedUser.getNombre() + " " + Main.loggedUser.getApellidos());
        lbDepartamento.setText("Departamento: " + Main.loggedUser.getDepartamento());
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        lbFecha.setText(day + "/" + month + "/" + year);
        try {
			image.setImage(ControllerVistaConfiguracionUsuario.requestImage(Main.loggedUser.getUsuario()+".jpg", "perfiles"));
		} catch (Exception e) {
			e.printStackTrace();
		}

        //PROGRESS BARS------------------------------------------------------------------------------------------
        /*String fechaActual = "15/11/2019";
        double temp = (double)Main.ambientales.get("Temperatura").get(fechaActual); //en grados
        double humedad = (double)Main.ambientales.get("Humedad").get(fechaActual);  //en porcentaje
        double lumin = (double)Main.ambientales.get("Luminosidad").get(fechaActual);  //en porcentaje
        double aire = (double)Main.ambientales.get("Aire").get(fechaActual);  //en porcentaje de contaminacion
        double ruido = (double)Main.ambientales.get("Ruido").get(fechaActual);  //en porcentaje
       
        pbTemp.setProgress(temp/100);
        lbTemp.setText(temp+" ºC");
        pbHum.setProgress(humedad/100);
        lbHum.setText(humedad+" %");
        pbLum.setProgress(lumin/100);
        lbLum.setText(lumin+" %");
        pbAire.setProgress(aire/100);
        lbAire.setText(aire+" %");
        pbRuido.setProgress(ruido/100);
        lbRuido.setText(ruido+" %");*/
        
        TimerTask timerTask = new TimerTask() {
			public void run() {
				// Aquí el código que queremos ejecutar.
				Platform.runLater(() -> {
					updateAmbient();
				});
			}
		};
		Timer timer = new Timer();
		// Dentro de 0 milisegundos avísame cada 10000 milisegundos
		timer.scheduleAtFixedRate(timerTask, 0, 10000);
        
        //-------------------------------------------------------------------------------------------------------
        
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
        
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnHome) {
            panePrincipal.toFront();
        }
        if (actionEvent.getSource() == btnHR) {
            Pane vistaPaneHR = FXMLLoader.load(getClass().getResource("/application/scenes/usuario/vistaHR.fxml"));
            paneHR.getChildren().add(vistaPaneHR);
            paneRendimiento.toBack(); //prevents overlapping
            paneHR.toFront();
        }
        if (actionEvent.getSource() == btnRendimiento) {
            Pane vistaPaneRendimiento = FXMLLoader.load(getClass().getResource("/application/scenes/usuario/vistaRendimiento.fxml"));
            paneRendimiento.getChildren().add(vistaPaneRendimiento);
            paneHR.toBack(); //prevents overlapping
            paneRendimiento.toFront();
        }
        if(actionEvent.getSource() == btnPerfil){
            Pane vistaPanePerfil = FXMLLoader.load(getClass().getResource("/application/scenes/usuario/vistaPerfilUsuario.fxml"));
            panePerfil.getChildren().add(vistaPanePerfil);
            panePerfil.toFront();
        }
        if (actionEvent.getSource() == btnConfiguracion){
            Pane vistaPaneConfi = FXMLLoader.load(getClass().getResource("/application/scenes/usuario/vistaConfiguracionUsuario.fxml"));
            paneConfi.getChildren().add(vistaPaneConfi);
            paneConfi.toFront();
        }

    }
    
   public void openChat() throws IOException {
	   Scene chatUScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/usuario/vistaChatUsuario.fxml")));
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
		fr=new FileReader("ambientales.txt");
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

