package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import application.controllers.ExitWindow;
import application.controllers.MessageWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	public static JSONParser parser = new JSONParser();
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	
	public static HashMap<String, String> shaBlobs = new HashMap<String, String>();
	public static TreeMap<String, Object> ordenatedMessages;
	public static TreeMap<String, JSONObject> admin = new TreeMap<String, JSONObject>();
	public static TreeMap <String, Oficinista> office;
	public static TreeMap<String, JSONObject> ambientales;
	public static Oficinista loggedUser;
	public static JSONObject loggedAdmin;
	
	public static Stage stage;
	public static Scene loginScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Type ambientHMType = new TypeToken<TreeMap<String,JSONObject>>(){}.getType();
		ambientales = gson.fromJson(requestData("project/src/application/database/ambientales","master"), ambientHMType);
		stage = primaryStage;
		Type oficinistaHMType = new TypeToken<TreeMap<String,Oficinista>>(){}.getType();
		office = gson.fromJson(requestData("project/src/application/database/office","master"),oficinistaHMType);
		Type adminHMType = new TypeToken<TreeMap<String,JSONObject>>(){}.getType();
       	admin = gson.fromJson(requestData("project/src/application/database/admin","master"), adminHMType);
       	loginScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/login.fxml")));
       	//chatAScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/vistaChatAdmin.fxml")));
       	
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(loginScene);
		primaryStage.show();
		 
		//System.out.println(requestData("Ofi1"));
		// mensajes = (JSONObject) parser.parse(requestData("mensajes"));

		//String converJsonName = "mensajesOfi1";

		/*
		 * TimerTask timerTask = new TimerTask() { public void run() { try { data =
		 * requestData(converJsonName); ordenatedMessages = gson.fromJson(data,
		 * TreeMap.class); printTree(); } catch (Exception e) { e.printStackTrace(); } }
		 * };
		 * 
		 * // Aquí se pone en marcha el timer cada segundo. Timer timer = new Timer();
		 * // Dentro de 0 milisegundos avísame cada 1000 milisegundos
		 * timer.scheduleAtFixedRate(timerTask, 0, 5000);
		 */

		/*String data = requestData("Ofi1");
		ordenatedMessages = gson.fromJson(data, TreeMap.class);
		printTree();
		
		Scanner in = new Scanner(System.in);
		String message = in.nextLine();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		ordenatedMessages.put(dtf.format(now), "-Ofi: " + message);
		System.out.println(writeCommit(gson.toJson(ordenatedMessages), "Ofi1"));
		System.out.println(updateData("commitFile.json", "Ofi1"));
		printTree();*/
	}

	public static void main(String[] args) { launch(args);
	}

	public void printTree() {
		ordenatedMessages.keySet().forEach(key -> {
			System.out.println(ordenatedMessages.get(key) + "\t\t\t\t(Sent at:" + key + ")");
		});
	}

	public static String requestData(String name, String branch) throws Exception {

		// ------------------DATA TO MAKE REQUEST--------------------
		String user = "HectorSkm";
		String repo = "SmartOfficeSystem";
		String filepath = name + ".json";
		String personalToken = "6c57d8b48c27349e466bea91ebf15c926406ae67"; // token authorization only necessary if //
																			// repository is private

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

	public static String writeCommit(String normalContent, String name, String branch) throws Exception {
		FileWriter writer = new FileWriter("commitFile.json");
		String commitMessage = "message json file updated";
		String committerName = "Gonzalo Alcaide";
		String committerEmail = "gonzalo.alcaide10@gmail.com";
		String codedContent = Base64.getEncoder().encodeToString(normalContent.getBytes());
		writer.write("{\r\n" + "  \"message\": \"" + commitMessage + "\",\r\n" + "  \"committer\": {\r\n"
				+ "    \"name\": \"" + committerName + "\",\r\n" + "    \"email\": \"" + committerEmail + "\"\r\n"
				+ "  },\r\n" + "  \"content\": \"" + codedContent + "\",\r\n" + "  \"branch\": \"" + branch + "\",\r\n"
				+ "  \"sha\": \"" + shaBlobs.get(name) + "\"\r\n" + "}");
		writer.flush();
		writer.close();
		return "commit json written success";
	}

	public static String updateData(String commitJsonPath, String name) throws Exception {

		String user = "HectorSkm";
		String repo = "SmartOfficeSystem";
		String filepath = name + ".json";
		String personalToken = "6c57d8b48c27349e466bea91ebf15c926406ae67";

		String command = "curl --request PUT https://api.github.com/repos/" + user + "/" + repo + "/contents/"
				+ filepath + " -H \"Authorization: token " + personalToken + "\" -d @" + commitJsonPath;

		String response = "";
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader processInputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = processInputReader.readLine()) != null) {
			response += line;
		}
		processInputReader.close();
		process.destroy();
		return response;
	}
	
	public static void exit() {
		ExitWindow e = new ExitWindow(Main.stage);

	}
}
