package application.controllers;

import application.Oficinista;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerListaVistaUsuario implements Initializable {

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
        /*
        HashMap<String, Oficinista> ofi;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileReader jsonFileR = new FileReader("/application/database/Oficinistas.json");
            Type oficinistaHMType = new TypeToken<HashMap<String,Oficinista>>(){}.getType();
            ofi = gson.fromJson(jsonFileR,oficinistaHMType);
            Oficinista of1 = ofi.get("OFI1");




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */
    }
}
