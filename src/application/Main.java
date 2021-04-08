package application;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;



public class Main extends Application {
	private static Scene scene;
	public static Stage secondStage;
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../View/Root.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../View/application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/"+ fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void newStage(String fxml, String title) {
		try {
			secondStage = new Stage();
			Scene scene = new Scene(loadFXML(fxml));
			secondStage.setScene(scene);
			secondStage.setTitle(title);
			secondStage.initOwner(primaryStage);
			secondStage.initModality(Modality.WINDOW_MODAL);
			secondStage.centerOnScreen();
			secondStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setFXML(String fxml, String title)  {
		try {
			scene.setRoot(loadFXML(fxml));
			primaryStage.sizeToScene();
			primaryStage.centerOnScreen();
			primaryStage.setTitle(title);
		}catch(IOException e) {
			e.printStackTrace();

		}
	}


	public static void main(String[] args) {
		launch(args);

	}
}
