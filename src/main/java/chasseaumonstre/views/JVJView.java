package chasseaumonstre.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import chasseaumonstre.controller.JVJController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JVJView {
    private static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator + "maquette" + File.separator;
    private Stage stage;
    private JVJController controller;

    public JVJView(Stage stage, JVJController controller) {
        this.stage = stage;
        this.controller = controller;
        this.render();
    }

    private void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", FXML_LOCATION + "jvj.fxml"));
            loader.setController(this.controller);
            Scene scene = new Scene(loader.load(), 1080, 600);
            stage.setTitle("Joueur contre Joueur");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
