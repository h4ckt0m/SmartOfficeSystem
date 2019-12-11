package application.controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Pane paneHR;
    @FXML
    private Pane paneRendimiento;
    @FXML
    private Pane panePerfil;
    @FXML
    private Pane paneConfi;
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

    public static int i;

    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[Main.office.size()];
        for (i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/application/scenes/listaVistaAdmin.fxml"));
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

    }

    public void handleClicks(ActionEvent actionEvent){
        if (actionEvent.getSource() == btnHome){

        }
        else if(actionEvent.getSource() == btnConfiguracion){

        }
        else if(actionEvent.getSource() == btnConfiguracion){

        }
        else if(actionEvent.getSource() == btnConfiguracion){

        }
        else if(actionEvent.getSource() == btnConfiguracion){

        }
        else if(actionEvent.getSource() == btnConfiguracion){

        }
    }

    public void openChat() throws IOException {
        Scene chatUScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/vistaChatAdmin.fxml")));
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(chatUScene);
        primaryStage.show();
    }
    public void logOut() {
        LogOutWindow l = new LogOutWindow(Main.stage);
    }
    public void exit() {
        Main.exit();
    }
}
