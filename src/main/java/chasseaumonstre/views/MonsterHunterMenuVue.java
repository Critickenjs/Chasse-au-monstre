package chasseaumonstre.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import chasseaumonstre.controller.MonsterHunterMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonsterHunterMenuVue {
    private static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "maquette" + File.separator;
    private Stage stage;
    private MonsterHunterMenuController controller;

    public MonsterHunterMenuVue(Stage stage, MonsterHunterMenuController controller) {
        // Fenêtre
        this.stage = stage;
        this.controller = controller;
        // Connecter la Vue au Controller
        this.controller.setVue(this);
        // Affichage de la Vue
        this.render();
    }

    private void render() {
        try {
            
            FXMLLoader loader = new FXMLLoader(new URL("file", "", MonsterHunterMenuVue.FXML_LOCATION + "main.fxml"));
            System.out.println(loader.toString());
            loader.setController(this.controller);
            Scene scene = new Scene(loader.load(), 1080, 600); 
            this.stage.setTitle("Chasse au Monstre");
            this.stage.setScene(scene);
            this.stage.show();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
