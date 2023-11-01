package chasseaumonstre.views;

import chasseaumonstre.controller.MHHunterController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MHHunterView {
    private Stage stage;
    private MHHunterController controller;
    private GridPane maze;

    public MHHunterView(Stage stage, MHHunterController controller) {
        // Fenêtre
        this.stage = stage;
        this.controller = controller;
        this.maze = new GridPane();
        // Connecter la Vue au Controller
        this.controller.setVue(this);
    }

    /*
     * Afficher la vue du Chasseur dans le stage
     */
    public void render() {
        this.stage.setTitle("Chasse au Monstre");
        this.draw();
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        Label hunterName = new Label(this.controller.getModel().getHunterName() + "'s Maze");
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
                    controller.handleShot(cellX, cellY);
                });

                if(isOnBorder(x, y, width-1, heigth-1)) {
                    cell.setFill(Color.BLACK);
                } else {
                    cell.setFill(Color.WHITE);
                }

                this.maze.add(cell, x, y);
            }
        }
    }

    public boolean isOnBorder(int x, int y, int width, int heigth) {
        return x == 0 || x == width || y == 0 || y == heigth;
    }

    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
