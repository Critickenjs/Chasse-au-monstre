package chasseaumonstre.views;

import chasseaumonstre.controller.MHMonsterController;
import javafx.geometry.Pos;
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
        this.stage.setTitle("Chasse au Monstre - Tour du monstre");
        this.draw();
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        Label hunterName = new Label(this.controller.getModel().getMonsterName() + "'s Maze");
        hunterName.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        container.getChildren().addAll(hunterName, this.maze);
        this.stage.setScene(new Scene(container, this.maze.getWidth(), 900));
        this.stage.show();
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
                    controller.handleMove(cellX, cellY);
                });

                switch (this.controller.getModel().getMaze()[x][y]) {
                    case WALL:
                        cell.setFill(Color.BLACK);
                        break;
                    case EMPTY:
                        cell.setFill(Color.WHITE);
                        break;
                    default:
                        break;
                }

                cell.setFill(Color.WHITE);
                this.maze.add(cell, x, y);
            }
        }
    }

    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
