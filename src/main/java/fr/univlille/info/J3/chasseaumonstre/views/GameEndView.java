package fr.univlille.info.J3.chasseaumonstre.views;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import fr.univlille.info.J3.chasseaumonstre.controller.GameEndController;
import fr.univlille.info.J3.chasseaumonstre.views.utils.UtilsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * La classe représentant la vue de l'écran de fin de jeu
 * 
 * @see GameEndController
 * 
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class GameEndView {
    private Stage stage;
    private GameEndController controller;
    private GridPane hunterMaze, monsterMaze;
    private List<Label> hunterHistory, monsterHistory;


    /**
     * @param stage      La fenêtre de l'application.
     * @param controller Le contrôleur associé à cette vue.
     */
    public GameEndView(Stage stage, GameEndController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    /**
     * Affichee l'écran de fin de jeu.
     */
    public void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", UtilsView.FXML_LOCATION + "GameEndView.fxml"));
            loader.setController(this.controller);
            Scene scene = new Scene(loader.load(), 1080, 600);

            if(hunterMaze != null)
            resizeMazeContent(hunterMaze);

            if(monsterMaze != null)
            resizeMazeContent(monsterMaze);

            if(hunterHistory != null)
            addHistory(hunterHistory, controller.getHunterHistory());

            if(monsterHistory != null)
            addHistory(monsterHistory, controller.getMonsterHistory());

            if(hunterMaze != null)
            controller.getContentHunterMaze().getChildren().add(hunterMaze);

            if(monsterMaze != null)
            controller.getContentMonsterMaze().getChildren().add(monsterMaze);

            stage.setTitle("Chasse au monstre");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHunterMaze(GridPane maze) {
        this.hunterMaze = maze;
    }

    public void setMonsterMaze(GridPane maze) {
        this.monsterMaze = maze;
    }

    public void setHunterHistory(List<Label> history) {
        this.hunterHistory = history;
    }

    public void setMonsterHistory(List<Label> history) {
        this.monsterHistory = history;
    }


    /**
     * Redimensionne le contenu du labyrinthe.
     *
     * @param maze Le labyrinthe à redimensionner.
     */
    private void resizeMazeContent(GridPane maze) {
        for (Node child : maze.getChildren()) {
            if (child instanceof StackPane) {
                StackPane stackPane = (StackPane) child;

                Node rectangleNode = stackPane.getChildren().get(0);
                if (rectangleNode instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) rectangleNode;

                    rectangle.setWidth(410 / controller.getModel().getWidth());
                    rectangle.setHeight(450 / controller.getModel().getHeight());
                }
            }
        }

        maze.setDisable(true);
    }

    /**
     * Ajoute l'historique à la vue.
     *
     * @param alerts        La liste des alertes (actions).
     * @param contentAlerts Le conteneur d'affichage des alertes.
     */
    private void addHistory(List<Label> alerts, VBox contentAlerts) {
        if (alerts != null) {
            for (Label action : alerts) {
                contentAlerts.getChildren().addAll(action, new Separator());
            }
        }
    }
}
