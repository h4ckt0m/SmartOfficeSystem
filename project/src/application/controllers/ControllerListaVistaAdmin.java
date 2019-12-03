package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerListaVistaAdmin implements Initializable {
    @FXML
    private Label lbUser;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApell;
    @FXML
    private Label lbDep;
    @FXML
    private Button btnCons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //btnCons.setId();
    }
}
