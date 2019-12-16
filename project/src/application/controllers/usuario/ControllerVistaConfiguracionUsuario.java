package application.controllers.usuario;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.Main;
import application.controllers.windows.MessageWindow;

public class ControllerVistaConfiguracionUsuario implements Initializable {
	
	@FXML
	private TextField pField;
	@FXML
	private ImageView img;
	@FXML
	private CheckBox showbox;
	
	private String imgPath;
	private FileChooser fileChooser = new FileChooser();
	private File file;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	fileChooser.setInitialDirectory(new File("src/resources"));
    	imgPath = Main.loggedUser.getImagen();
    	try {
			img.setImage(ControllerVistaConfiguracionUsuario.requestImage(Main.loggedUser.getUsuario()+".jpg", "perfiles"));
		} catch (Exception e) { 
			e.printStackTrace();
		}
    	pField.setText("******");
    }
    
    public void selectImage() {
    	fileChooser.setTitle("Selecciona la nueva imagen");
		file = fileChooser.showOpenDialog(Main.stage);
		if (file != null) {
			imgPath = file.getAbsolutePath();
        }
		File file = new File(imgPath);
		img.setImage(new Image(file.toURI().toString()));
    }
    
    public void check() {
    	if(showbox.isSelected()) {
    		pField.setText(Main.loggedUser.getPassword());
    	}else{
    		pField.setText("******");
    	}
    }
    
    public void post() {
    	if(pField.getText().equals("******")) {
    	}else {
    		Main.loggedUser.setPassword(pField.getText());
    	}
    	
    	try {
    		requestImage(Main.loggedUser.getUsuario()+".jpg", "perfiles");
        	commitImage(encodeImage(imgPath),Main.loggedUser.getUsuario()+".jpg", "perfiles");
        	updateImage("commitFile.json", Main.loggedUser.getUsuario()+".jpg");
        	
			Main.writeCommit(Main.gson.toJson(Main.office), "project/src/application/database/document", "ramaGonzalo");
			Main.updateData("commitFile.json", "project/src/application/database/document");
			MessageWindow m = new MessageWindow("Cambios guardados", Main.stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    //----------COMMIT IMAGE TO GITHUB--------------------------------------------------------------------------------------------
    
    public static Image requestImage(String name, String branch) throws Exception {

		// ------------------DATA TO MAKE REQUEST--------------------
		String user = "HectorSkm";
		String repo = "SmartOfficeSystem";
		String personalToken = "6c57d8b48c27349e466bea91ebf15c926406ae67"; // token authorization only necessary if //
																			// repository is private

		// -----------------EXECUTE REQUEST AND GET ANSWER-----------
		String command = "curl --request GET https://api.github.com/repos/" + user + "/" + repo + "/contents/" + name
				+ "?ref=" + branch + " -H \"Authorization: token " + personalToken;
		String response = "";
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader processInputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = processInputReader.readLine()) != null) {
			response += line;
		}
		processInputReader.close();
		process.destroy();

		// ----------------PARSE RESPONSE, GET SHA AND CONVERT TO JAVAFX IMAGE-------
		JSONObject jsonResponse = (JSONObject) Main.parser.parse(response);
		Main.shaBlobs.put(name, jsonResponse.get("sha").toString());

		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(jsonResponse.get("content").toString());
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		Image image = SwingFXUtils.toFXImage(img, null);
		return image;
	}

	public String encodeImage(String path) {
		String encodedFile = null;
		try {
			File f = new File(path);
			FileInputStream fileInputStreamReader = new FileInputStream(f);
			byte[] bytes = new byte[(int) f.length()];
			fileInputStreamReader.read(bytes);
			encodedFile = Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedFile;
	}

	public String commitImage(String codedContent, String name, String branch) throws Exception {
		FileWriter writer = new FileWriter("commitFile.json");
		String commitMessage = "json file updated";
		String committerName = "Gonzalo Alcaide";
		String committerEmail = "gonzalo.alcaide10@gmail.com";
		writer.write("{\r\n" + "  \"message\": \"" + commitMessage + "\",\r\n" + "  \"committer\": {\r\n"
				+ "    \"name\": \"" + committerName + "\",\r\n" + "    \"email\": \"" + committerEmail + "\"\r\n"
				+ "  },\r\n" + "  \"content\": \"" + codedContent + "\",\r\n" + "  \"branch\": \"" + branch + "\",\r\n"
				+ "  \"sha\": \"" + Main.shaBlobs.get(name) + "\"\r\n" + "}");
		writer.flush();
		writer.close();
		return "commit json written success";
	}

	public String updateImage(String commitJsonPath, String name) throws Exception {

		String user = "HectorSkm";
		String repo = "SmartOfficeSystem";
		String personalToken = "6c57d8b48c27349e466bea91ebf15c926406ae67";

		String command = "curl --request PUT https://api.github.com/repos/" + user + "/" + repo + "/contents/"
				+ name + " -H \"Authorization: token " + personalToken + "\" -d @" + commitJsonPath;

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
}
