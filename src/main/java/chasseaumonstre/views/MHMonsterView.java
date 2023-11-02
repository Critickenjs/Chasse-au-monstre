package chasseaumonstre.views;

import java.io.IOException;
import java.net.URL;

import chasseaumonstre.controller.MHMonsterController;
import chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MHMonsterView {
    private Stage stage;
    private MHMonsterController controller;
    private GridPane maze;

    public MHMonsterView(Stage stage, MHMonsterController controller) {
        // Fenêtre
        this.stage = stage;
        this.controller = controller;
        this.maze = new GridPane();
        // Connecter la Vue au Controller
        this.controller.setVue(this);
    }

    /*
     * Afficher la vue du Monstre dans le stage
     */
    public void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", UtilsController.FXML_LOCATION + "gameView.fxml"));
            loader.setController(this.controller);
            Parent root = loader.load();

            this.draw();
            controller.getContentV().getChildren().add(maze);

            Scene scene = new Scene(root, 1300, 900);

            stage.setTitle("Tour du monstre");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void draw() {

        int width = this.controller.getModel().getWidth();
        int heigth = this.controller.getModel().getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < heigth; y++) {
                Rectangle cell = new Rectangle(50, 50);
                cell.setStroke(Color.BLACK);

                cell.setOnMouseClicked(e -> {
                int cellX = GridPane.getColumnIndex(cell);
                int cellY = GridPane.getRowIndex(cell);
                CellInfo type = controller.handleMove(cellX, cellY);

                if (type == CellInfo.WALL) {
                    cell.setFill(Color.web("#a8a8a8"));
                } else {
                    cell.setFill(Color.web("#1bde243c"));
                }
                });

                if (this.controller.getModel().getMonster().isVisited(x, y)) {
                    if (this.controller.getModel().getMaze()[x][y] == CellInfo.WALL) {
                        cell.setFill(Color.web("#a8a8a8"));
                    } else {
                        cell.setFill(Color.web("#1bde243c"));
                    }
                } else {
                    switch (this.controller.getModel().getMaze()[x][y]) {
                        case WALL:
                            cell.setFill(Color.BLACK);
                            break;
                        case EMPTY:
                            cell.setFill(Color.WHITE);
                            break;
                        case EXIT:
                            cell.setFill(Color.GREEN);
                            break;
                        case MONSTER:
                            cell.setFill(Color.BLUE);
                            break;
                        default:
                            break;
                    }
                }

                this.maze.add(cell, x, y);
                System.out.println(this.maze.toString());
            }
        }
    }

    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
