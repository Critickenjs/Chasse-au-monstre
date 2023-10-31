package chasseaumonstre.views;

import chasseaumonstre.controller.MonsterHunterController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MonsterHunterPartieVue {
    private Stage stage;
    private MonsterHunterController controller;
    private GridPane maze;

    public MonsterHunterPartieVue(Stage stage, MonsterHunterController controller) {
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
        this.stage.setTitle("Chasse au Monstre");
        this.draw();
        this.stage.setScene(new Scene(this.maze, 1050, 850));
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
                    controller.handleShot(cellX, cellY);
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
                this.maze.add(cell, x, y);
            }
        }
    }

    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
