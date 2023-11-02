    package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.JVJView;

public class MHMenuController  {
    @FXML
    private ImageView imageView;
    @FXML
    private Button jvjBtn;
    @FXML
    private Button cviBtn;
    @FXML
    private Button mviBtn;
    @FXML
    private Button iviBtn;

    private Stage stage;
    private MonsterHunterModel model;

    public MHMenuController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.jvjBtn = new Button();
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }


    @FXML
    private void onPVP() {
        jvjBtn.setOnMouseClicked(e -> {
            JVJController controller = new JVJController(stage, model);
            new JVJView(stage, controller);
        });
       
    }

    @FXML
    private void onHunterVAi() {
        
    }

    @FXML
    private void onMonsterVAi() {
        
    }

    @FXML
    private void onAiVAi() {
    }
    public void initialize() {
        // Chargez l'image depuis l'URL et affectez-la à l'ImageView
        Image image = new Image("https://www.premiere.fr/sites/default/files/styles/scale_crop_border_1280x720/public/2020-02/m.jpg");
        imageView.setImage(image);
        UtilsController.hovereffect(jvjBtn);
        UtilsController.hovereffect(cviBtn);
        UtilsController.hovereffect(mviBtn);
        UtilsController.hovereffect(iviBtn);
        
    }
    
}


