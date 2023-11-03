package chasseaumonstre.views;

import java.io.IOException;
import java.net.URL;

import chasseaumonstre.controller.MHHunterController;
import chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Classe représentant la vue du Chasseur
 * 
 * @param stage : la fenêtre principale
 * @param controller : le contrôleur
 * @see MHHunterController
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class MHHunterView implements IPlayerView {
    private Stage stage;
    private MHHunterController controller;
    private GridPane maze;
    private static final String HUNTER_CROSSHAIR_PATH = "https://media.discordapp.net/attachments/1159749679353974806/1169753431930572870/7506753.png?ex=65568cc7&is=654417c7&hm=c5381360ebf9dcac106f92196dfa1e036710f61740b72c980cae199d8e0b395f&=";
    
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
            FXMLLoader loader = new FXMLLoader(new URL("file", "", UtilsController.FXML_LOCATION + "gameView.fxml"));
            loader.setController(this.controller);
            Parent root = loader.load();

            this.update();
            controller.getContentV().getChildren().add(maze);
            controller.showHistory();

            Scene scene = new Scene(root, 1300, 900);
            Image image = new Image(HUNTER_CROSSHAIR_PATH);
            scene.setCursor(new ImageCursor(image));
                    
            stage.setTitle("Tour du Chasseur");
            stage.setScene(scene);
            stage.centerOnScreen();
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
                StackPane stack = new StackPane();
                Rectangle cell = new Rectangle(50, 50);
                cell.setStroke(Color.BLACK);
                Text text = new Text();

                cell.setOnMouseClicked(e -> {
                    if (this.controller.hasShot()) {
                        return;
                    }
                    int cellX = GridPane.getColumnIndex(stack);
                    int cellY = GridPane.getRowIndex(stack);
                    controller.handleShot(cellX, cellY, cell, text);
                });

                if (this.controller.getModel().getHunter().hasShot(x, y)) {
                    if (this.controller.getModel().getMaze()[x][y] == CellInfo.WALL) {
                        cell.setFill(Color.web("#a8a8a8"));
                    } else {
                        cell.setFill(Color.web("#de1b1b3c"));
                        if (this.controller.getModel().getHunter().isVisited(x, y) && (this.controller.getModel().getHunter().getVisitedTurn(x,y)>= 0)) {
                            text.setText("" + this.controller.getModel().getHunter().getVisitedTurn(x,y));
                        }
                    }
                } else {
                    if (MHHunterView.isOnBorder(x, y, width - 1, heigth - 1)) {
                        cell.setFill(Color.BLACK);
                    } else {
                        cell.setFill(Color.WHITE);
                    }
                }
                
                stack.getChildren().addAll(cell,text);
                this.maze.add(stack, x, y);
            }
        }
    }

    /*
     * Connaitre si une cellule est sur le bord du labyrinthe
     * 
     * @param x la ligne de la cellule
     * @param y la colonne de la cellule
     * @param width la largeur du labyrinthe
     * @return true si la cellule est sur le bord du labyrinthe, false sinon
     */
    public static boolean isOnBorder(int x, int y, int width, int heigth) {
        return x == 0 || x == width || y == 0 || y == heigth;
    }

    public GridPane getMaze() {
        return maze;
    }

    /*
     * Met à jour la vue du Chasseur
     */
    public void update() {
        this.maze.getChildren().clear();
        this.draw();
    }
}
