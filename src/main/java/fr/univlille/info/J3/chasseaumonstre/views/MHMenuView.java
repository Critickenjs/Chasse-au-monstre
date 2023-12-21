package fr.univlille.info.J3.chasseaumonstre.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import fr.univlille.info.J3.chasseaumonstre.controller.MHMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MHMenuView {
    private static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator + "maquette" + File.separator;
    private Stage stage;
    private MHMenuController controller;

    public MHMenuView(Stage stage, MHMenuController controller) {
        // Fenêtre
        this.stage = stage;
        this.controller = controller;
        // Affichage de la Vue
        this.render();
    }

    private void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", MHMenuView.FXML_LOCATION + "main.fxml"));
            loader.setController(this.controller);
            Scene scene = new Scene(loader.load(), 1080, 600);
            this.stage.setTitle("Chasse au Monstre");
            this.stage.setScene(scene);
            stage.centerOnScreen();
            stage.setFullScreen(true);
            this.stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
