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

public class GameEndView {
    private Stage stage;
    private GameEndController controller;
    private GridPane hunterMaze, monsterMaze;
    private List<Label> hunterHistory, monsterHistory;

    public GameEndView(Stage stage, GameEndController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", UtilsView.FXML_LOCATION + "GameEndView.fxml"));
            loader.setController(this.controller);
            Scene scene = new Scene(loader.load(), 1080, 600);

            resizeMazeContent(hunterMaze);
            resizeMazeContent(monsterMaze);
            addHistory(hunterHistory, controller.getHunterHistory());
            addHistory(monsterHistory, controller.getMonsterHistory());

            controller.getContentHunterMaze().getChildren().add(hunterMaze);
            controller.getContentMonsterMaze().getChildren().add(monsterMaze);

            stage.setTitle("Chasse au monstre");
            stage.setScene(scene);
            stage.centerOnScreen();
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

    private void resizeMazeContent(GridPane maze) {
        for (Node child : maze.getChildren()) {
            if (child instanceof StackPane) {
                StackPane stackPane = (StackPane) child;

                Node rectangleNode = stackPane.getChildren().get(0);
                if (rectangleNode instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) rectangleNode;

                    rectangle.setWidth(330 / controller.getModel().getWidth());
                    rectangle.setHeight(300 / controller.getModel().getHeight());
                }
            }
        }
    }

    private void addHistory(List<Label> alerts, VBox contentAlerts) {
        for (Label action : alerts) {
            contentAlerts.getChildren().addAll(action, new Separator());
        }
    }
}
