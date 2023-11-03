package chasseaumonstre.views;

import java.io.IOException;
import java.net.URL;
import chasseaumonstre.controller.JVJController;
import chasseaumonstre.views.utils.UtilsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Classe représentant la vue du jeu Joueur contre Joueur
 * où les joueurs peuvent choisir leurs noms
 * 
 * @param stage : la fenêtre principale
 * @param controller : le contrôleur
 * @see JVJController
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class JVJView {
    private Stage stage;
    private JVJController controller;

    public JVJView(Stage stage, JVJController controller) {
        this.stage = stage;
        this.controller = controller;
        this.render();
    }

    private void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", UtilsView.FXML_LOCATION + "jvj.fxml"));
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
