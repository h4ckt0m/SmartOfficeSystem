package application.controllers.usuario;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVistaPerfilUsuario implements Initializable {
    @FXML
    Label lbUsuario;
    @FXML
    Label lbNombre;
    @FXML
    Label lbApellidos;
    @FXML
    Label lbFecNac;
    @FXML
    Label lbDepart;
    @FXML
    Label lbSueldo;
    @FXML
    Label lbPM;
    @FXML
    Label lbProductM;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbUsuario.setText(Main.loggedUser.getUsuario());
        lbNombre.setText(Main.loggedUser.getNombre());
        lbApellidos.setText(Main.loggedUser.getApellidos());
        lbFecNac.setText(Main.loggedUser.getF_nac().toString());
        lbDepart.setText(Main.loggedUser.getDepartamento());
        lbSueldo.setText(String.valueOf(Main.loggedUser.getSueldo()) + " € mensuales");

        double hrMedio = 0;
        double prMedia = 0;
        for (int i = 0; i < Main.loggedUser.getPulsos().size(); i++) {
           hrMedio += Main.loggedUser.getPulsos().get(i).getDato();
        }
        for (int i = 0; i < Main.loggedUser.getProductividad().size(); i++){
            prMedia += Main.loggedUser.getProductividad().get(i).getProductividad();
        }
        hrMedio /= Main.loggedUser.getPulsos().size();
        prMedia /= Main.loggedUser.getProductividad().size();

        lbPM.setText(String.valueOf(hrMedio) + " ppm");
        lbProductM.setText(String.valueOf(prMedia*100) + " %");

    }
}
