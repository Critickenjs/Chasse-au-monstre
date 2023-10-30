package chasseaumonstre;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "maquette" + File.separator;

    public static Parent loadFXML(FXMLLoader loader, String fxmlFile) throws IOException {
        URL fxml = new URL("file", "", App.FXML_LOCATION + fxmlFile);
        loader.setLocation(fxml);
        return loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = App.loadFXML(loader, "main.fxml");

        Scene scene = new Scene(root, 1080, 600); 

        primaryStage.setTitle("Chasse au monstre");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
