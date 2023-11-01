package chasseaumonstre.views;

import chasseaumonstre.controller.MHHunterController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MHMonsterView {
    private Stage stage;
    private MHHunterController controller;
    private GridPane maze;

    public MHMonsterView(Stage stage, MHMonsterController controller) {
        // Fenêtre
        this.stage = stage;
        this.controller = controller;
        this.maze = new GridPane();
        // Connecter la Vue au Controller
        this.controller.setVue(this);
        // Affichage de la Vue
        this.render();
    }

    public void render() {
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
                    controller.handleShot(cellX, cellY);
                });

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
