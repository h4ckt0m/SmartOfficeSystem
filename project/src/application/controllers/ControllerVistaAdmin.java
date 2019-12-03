package application.controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVistaAdmin implements Initializable {
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
    private Button btnPerfil;
    @FXML
    private Label lbNomApe;
    @FXML
    private Label lbFecha;
    @FXML
    private AnchorPane anchor;
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
    }

    public void handleClicks(ActionEvent event){

    }
}
