package application.controllers.usuario;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerListaVistaUsuario implements Initializable{

    @FXML
    private Label lbFecha;
    @FXML
    private Label lbHE;
    @FXML
    private Label lbHS;
    @FXML
    private Label lbAP;
    @FXML
    private Label lbAC;
    @FXML
    private Label lbProductividad;
    @FXML
    private CheckBox cbCompletado;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbFecha.setText(Main.loggedUser.getEa().get(ControllerVistaUsuario.i).getEntrada().toString());
        lbHE.setText(Main.loggedUser.getEa().get(ControllerVistaUsuario.i).getH_entrada());
        lbHS.setText(Main.loggedUser.getEa().get(ControllerVistaUsuario.i).getH_salida());
        lbAP.setText(String.valueOf(Main.loggedUser.getProductividad().get(ControllerVistaUsuario.i).getAct_plan()));
        lbAC.setText(String.valueOf(Main.loggedUser.getProductividad().get(ControllerVistaUsuario.i).getAct_compl()));
        lbProductividad.setText(String.valueOf((Main.loggedUser.getProductividad().get(ControllerVistaUsuario.i).getProductividad())*100) + "%");
        cbCompletado.setDisable(true);
        cbCompletado.setOpacity(100);
        if(Main.loggedUser.getProductividad().get(ControllerVistaUsuario.i).getAct_compl() >= Main.loggedUser.getProductividad().get(ControllerVistaUsuario.i).getAct_plan()){
            cbCompletado.setSelected(true);
        }
        else{
            cbCompletado.setSelected(false);
        }
    }


}
