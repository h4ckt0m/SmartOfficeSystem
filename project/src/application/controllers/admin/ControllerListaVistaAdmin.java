package application.controllers.admin;

import application.Main;
import application.Oficinista;
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
        Oficinista ofi = Main.office.get("Ofi".concat(String.valueOf((ControllerVistaAdmin.i)+1)));
        lbUser.setText(ofi.getUsuario());
        lbNombre.setText(ofi.getNombre());
        lbApell.setText(ofi.getApellidos());
        lbDep.setText(ofi.getDepartamento());
        btnCons.setId(String.valueOf(ControllerVistaAdmin.i));

        btnCons.setOnAction(event -> {
            VentanaDatos v = new VentanaDatos(ofi);
        });
    }
}
