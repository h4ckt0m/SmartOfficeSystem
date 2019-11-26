package application.controllers;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;

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
    private Button btnHR;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnRendimiento;
    @FXML
    private AnchorPane anchor;
    
	private double xOffset = 0;
	private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/application/scenes/listaVistaUsuario.fxml"));
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
        	Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/application/scenes/hRateUser.fxml"));
        	paneHR.getChildren().add(newLoadedPane);
            paneHR.toFront();
        }
        if (actionEvent.getSource() == btnRendimiento) {
            paneRendimiento.toFront();
        }
    }
    
   public void openChat() throws IOException {
	   Scene chatUScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/vistaChatUsuario.fxml")));
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
