
package application;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.rmi.CORBA.Util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyApplication extends Application {
	JSONParser parser = new JSONParser();
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static HashMap<String, String> shaBlobs = new HashMap<String, String>();
	public static JSONObject ambientales;
	public static JSONObject admin;
	public static JSONObject office;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Read file fxml and draw interface.
		Parent root = FXMLLoader.load(getClass().getResource("/application/scenes/login.fxml"));
		primaryStage.setTitle("SOS - Login");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		
		
		ambientales = (JSONObject) parser.parse(requestData("ambientales"));
		admin = (JSONObject) parser.parse(requestData("admin"));
		office = (JSONObject) parser.parse(requestData("office"));
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public String requestData(String name) throws Exception {

		// ------------------DATA TO MAKE REQUEST--------------------
		String user = "HectorSkm";
		String repo = "ProyectoInformatica_I";
		String filepath = "project/src/application/database/"+name+".json";
		String personalToken = "6c57d8b48c27349e466bea91ebf15c926406ae67"; // token authorization only necessary if //
																			// repository is private
		String branch = "ramaGonzalo"; // obligatory, or a branch or 'master'

		// -----------------EXECUTE REQUEST AND GET ANSWER-----------
		String command = "curl --request GET https://api.github.com/repos/" + user + "/" + repo + "/contents/"
				+ filepath + "?ref=" + branch + " -H \"Authorization: token " + personalToken;
		String response = "";
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader processInputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = processInputReader.readLine()) != null) {
			response += line;
		}
		processInputReader.close();
		process.destroy();

		// ----------------PARSE RESPONSE, GET SHA AND CONTENT-------
		JSONObject jsonResponse = (JSONObject) parser.parse(response);
		
		shaBlobs.put(name, jsonResponse.get("sha").toString());
		String codedContent = jsonResponse.get("content").toString();

		// ----------------FORMAT AND DECODE CONTENT-----------------
		codedContent = codedContent.replace("\n", "");
		byte[] decodedBytes = Base64.getDecoder().decode(codedContent);
		String decodedString = new String(decodedBytes, "UTF-8");
		return decodedString;
	}

	public String sortbykey(JSONObject object) {
		TreeMap<String, Object> map = gson.fromJson(object.toString(), TreeMap.class);
		String sortedJson = gson.toJson(map);
		return sortedJson;
	}
}


/*EXAMPLE MANAGE DATA

JSONObject luminosidad = (JSONObject) ambientales.get("Luminosidad");
JSONObject temperatura = (JSONObject) ambientales.get("Temperatura");
JSONObject humedad = (JSONObject) ambientales.get("Humedad");
JSONObject ruido = (JSONObject) ambientales.get("Ruido");
JSONObject aire = (JSONObject) ambientales.get("Aire");

luminosidad.put("16/11/2019", 100);
temperatura.put("18/11/2019", 2);
humedad.put("17/11/2019", 99);
ruido.put("19/11/2019", 120);
aire.put("20/11/2019", 10);

FileWriter writer = new FileWriter("ambient.json");
writer.write(gson.toJson(ambientales));
writer.flush();
writer.close();
// sortbykey(luminosidad);*/