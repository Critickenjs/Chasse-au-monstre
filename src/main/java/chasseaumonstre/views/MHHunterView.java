package chasseaumonstre.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import chasseaumonstre.controller.MHHunterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MHHunterView {
    private static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator + "maquette" + File.separator;
    private Stage stage;
    private MHHunterController controller;
    private GridPane maze;

    public MHHunterView(Stage stage, MHHunterController controller) {
        // Fenêtre
        this.stage = stage;
        this.controller = controller;
        this.maze = new GridPane();
    }

    /*
     * Afficher la vue du Chasseur dans le stage
     */
    public void render() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file", "", FXML_LOCATION + "gameView.fxml"));
            loader.setController(this.controller);
            Parent root = loader.load();

            this.draw();
            controller.getContentV().getChildren().add(maze);

            Scene scene = new Scene(root, 1300, 900);

            stage.setTitle("Chasse au Monstre");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void draw() {
        maze.getChildren().clear();

        int width = this.controller.getModel().getWidth();
        int heigth = this.controller.getModel().getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < heigth; y++) {
                Rectangle cell = new Rectangle(50, 50);
                cell.setStroke(Color.BLACK);

                cell.setOnMouseClicked(e -> {
                    int cellX = GridPane.getColumnIndex(cell);
                    int cellY = GridPane.getRowIndex(cell);
                    
                    switch (controller.handleShot(cellX, cellY)) {
                        case WALL:
                            cell.setFill(Color.web("#a8a8a8"));
                            break;

                        case EMPTY:
                            cell.setFill(Color.web("#de1b1b3c"));
                            break;

                        case MONSTER:
                            cell.setFill(Color.web("#1bde243c"));
                            break;
                    
                        default:
                            break;
                    }
                });

                if (isOnBorder(x, y, width - 1, heigth - 1)) {
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

    public GridPane getMaze() {
        return maze;
    }

    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
