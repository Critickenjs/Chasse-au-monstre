package chasseaumonstre.views;

import java.io.IOException;
import java.net.URL;

import chasseaumonstre.controller.MHMonsterController;
import chasseaumonstre.controller.utils.UtilsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
            this.controller.getContentV().getChildren().add(maze);

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
        this.maze.getChildren().clear(); // TODO remplacer par update
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < heigth; y++) {
                Rectangle cell = new Rectangle(50, 50);
                cell.setStroke(Color.BLACK);

                cell.setOnMouseClicked(e -> {
                    if (this.controller.hasMoved()) {
                        return;
                    }
                    int cellX = GridPane.getColumnIndex(cell);
                    int cellY = GridPane.getRowIndex(cell);
                    controller.handleMove(cellX, cellY);
                });

                
                switch (this.controller.getModel().getMaze()[x][y]) {
                    case WALL:
                        cell.setFill(Color.BLACK);
                        break;
                    case EMPTY:
                        if (this.controller.getModel().getMonster().isVisited(x, y)) 
                            cell.setFill(Color.web("#1bde243c"));
                        else 
                            cell.setFill(Color.WHITE);
                        break;
                    case EXIT:
                        cell.setFill(Color.GREEN);
                        break;
                    case MONSTER:
                        cell.setFill(Color.web("#1bde243c"));
                        cell.setFill(new ImagePattern(new Image("https://media.tenor.com/dPsOXgYjb30AAAAi/pixel-pixelart.gif")));
                        break;
                    default:
                        break;
                }

                this.maze.add(cell, x, y);
            }
        }
    }

    public Rectangle getCell(int x, int y) {
        return (Rectangle) this.maze.getChildren().get(x * this.controller.getModel().getWidth() + y);
    }

    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
