package chasseaumonstre.views;

import chasseaumonstre.controller.MonsterHunterController;
import chasseaumonstre.model.MonsterHunterModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MonsterHunterPartieVue extends Application{
    private MonsterHunterModel model;
    private MonsterHunterController controller;
    GridPane mazeGrid;

    public void MonsterHunterPartieVue(MonsterHunterModel model) {
        this.model = model;
    }

    public void setController(MonsterHunterController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Monster Hunter Game");

        mazeGrid = new GridPane();
        drawMaze();
        
        Scene scene = new Scene(mazeGrid, 1050, 850);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMaze() {
        int width = model.getMaze().length;
        int heigth = model.getMaze()[0].length;

        for(int x = 0; x < width; x++){
            for(int y=0; y<heigth; y++) {
                Rectangle cell = new Rectangle(50, 50);
                cell.setStroke(Color.BLACK);

                switch (model.getMaze()[x][y]) {
                    case WALL:
                        cell.setFill(Color.BLACK);
                        break;
                
                    case EMPTY:
                        cell.setFill(Color.WHITE);
                        break;
                    default:
                        break;
                }

                mazeGrid.add(cell, x, y);
            }
        }
    }

    public void updateMaze() {
        mazeGrid.getChildren().clear();
        drawMaze();
    }
}
