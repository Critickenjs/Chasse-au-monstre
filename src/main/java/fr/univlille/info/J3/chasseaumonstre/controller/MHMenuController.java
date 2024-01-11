package fr.univlille.info.J3.chasseaumonstre.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import fr.univlille.info.J3.chasseaumonstre.App;
import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.server.UtilsServer;
import fr.univlille.info.J3.chasseaumonstre.views.GameEndView;
import fr.univlille.info.J3.chasseaumonstre.views.JVJView;
import fr.univlille.info.J3.chasseaumonstre.views.MHAIView;
import fr.univlille.info.J3.chasseaumonstre.views.MHHunterView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMonsterView;

/**
 * Classe représentant le contrôleur du menu principal
 * 
 * @param stage : la fenêtre principale
 * @param model : le modèle
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class MHMenuController {
    @FXML
    private ImageView imageView;
    @FXML
    private Button jvjBtn;
    @FXML
    private Button pvpBtn;
    @FXML
    private Button cviBtn;
    @FXML
    private Button mviBtn;
    @FXML
    private Button iviBtn;
    @FXML
    private Button parametres;
    @FXML
    private Button quitter;

    private Stage stage;
    private MonsterHunterModel model;
    private MHHunterView hunterView;
    private MHMonsterView monsterView;
    private Socket socket;
    private GameEndView gameEndView;

    public MHMenuController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    /**
     * Gère le clic sur le bouton "Jouer contre joueur", lance la vue JVJ pour
     * choisir les noms des joueurs
     */
    @FXML
    private void onPVP() {
        jvjBtn.setOnMouseClicked(e -> {
            JVJController controller = new JVJController(stage, model);
            new JVJView(stage, controller);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        });
    }

    /**
     * Gère le clic sur le bouton "Chasseur contre IA"
     */
    @FXML
    private void onHunterVAi() {
        cviBtn.setOnMouseClicked(e -> {
            startGame(false, true);
        });
    }

    /**
     * Gère le clic sur le bouton "Monstre contre IA"
     */
    @FXML
    private void onMonsterVAi() {
        mviBtn.setOnMouseClicked(e -> {
            startGame(true, false);
        });
    }

    /**
     * Gère le clic sur le bouton "IA contre IA"
     */
    @FXML
    private void onAiVAi() {
        iviBtn.setOnMouseClicked(e -> {
            startGame(true, true);
        });
    }

    /**
     * Génére un labyrinthe d'un fichier importé
     */
    private void loadLabyrinth() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un labyrinthe");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.stage);
        if (selectedFile != null) {
            try {
                this.model.importMaze(selectedFile);
            } catch (IOException e) {
                System.out.println("Erreur IO sur le fichier '" + selectedFile + "'");
            } catch (NumberFormatException e) {
                System.out.println("Erreur de parsing d'entier sur le fichier '" + selectedFile + "'");
            }
        }
    }

    /**
     * Ouvre une nouvelle fenêtre pour permettre à l'utilisateur de modifier les
     * paramètres du jeu
     */
    @FXML
    private void onParameter() {
        Stage stageParameter = new Stage();
        stageParameter.initStyle(StageStyle.UTILITY);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText(null);
        alert2.setTitle("Succès");
        alert2.setContentText("Changement effectué avec succès");

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Label label = new Label("Changer la taille du labyrinthe");
        label.setStyle("-fx-font-weight: bold");

        TextField width = new TextField("" + model.getWidth());
        width.setPromptText("Width");

        TextField height = new TextField("" + model.getHeight());
        height.setPromptText("Height");

        Button button = new Button("Valider");

        hbox.getChildren().addAll(width, height);

        HBox obstacleSettings = new HBox();
        Label obstacleLabel = new Label("Pourcentage d'obstacles :");
        TextField obstacle = new TextField("" + model.getObstacles());
        obstacleSettings.getChildren().addAll(obstacleLabel, obstacle);

        HBox fovSettings = new HBox();
        Label fovLabel = new Label("Champ de vision :");
        TextField fov = new TextField("" + model.getMonster().getFov());
        fovSettings.getChildren().addAll(fovLabel, fov);

        HBox aiSettings = new HBox();
        Label algorithmLabel = new Label("Algorithme :");
        ComboBox<String> algorithmComboBox = new ComboBox<>();
        aiSettings.getChildren().addAll(algorithmLabel, algorithmComboBox);
        algorithmComboBox.getItems().addAll(App.ALGORITHMS_MONSTER.stream().map(Class::getSimpleName).toArray(String[]::new));
        algorithmComboBox.setValue(model.getMonster().getAlgorithmClass().getSimpleName());

        button.setOnAction(e -> {
            if (!width.getText().equals("") && height.getText().equals("")) {
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir les 2 champs");
                alert.showAndWait();
            } else {
                try {
                    int widthVal = Integer.parseInt(width.getText());
                    int heightVal = Integer.parseInt(height.getText());
                    try {
                        model.setWidth(widthVal);
                        model.setHeight(heightVal);
                        model.setAlgorithm(algorithmComboBox.getValue());
                    } catch (IllegalArgumentException e1) {
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText(e1.getMessage());
                        alert.showAndWait();
                        return;
                    }
                } catch (NumberFormatException e2) {
                    alert.setContentText("Erreur de type sur l'un des 2 champs");
                    alert.showAndWait();
                    return;
                }
            }

            if (!obstacle.getText().equals("")) {
                try {
                    int obstacleVal = Integer.parseInt(obstacle.getText());
                    try {
                        model.setObstacles(obstacleVal);
                    } catch (IllegalArgumentException e1) {
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText(e1.getMessage());
                        alert.showAndWait();
                        return;
                    }
                } catch (NumberFormatException e2) {
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Erreur de type sur le champ");
                    alert.showAndWait();
                    return;
                }
            }

            if (!fov.getText().equals("")) {
                try {
                    int fovVal = Integer.parseInt(fov.getText());
                    try {
                        model.getMonster().setFov(fovVal);
                    } catch (IllegalArgumentException e1) {
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText(e1.getMessage());
                        alert.showAndWait();
                        return;
                    }
                } catch (NumberFormatException e2) {
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Erreur de type sur le champ");
                    alert.showAndWait();
                    return;
                }
            }

            alert2.showAndWait();
        });

        Button button2 = new Button("Charger un labyrinthe prédéfini");

        button2.setOnAction(e -> {
            this.loadLabyrinth();
        });

        vbox.getChildren().addAll(label, hbox, obstacleSettings, fovSettings, aiSettings, button, button2);

        vbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        obstacleSettings.setSpacing(10);
        obstacleSettings.setAlignment(Pos.CENTER_LEFT);
        fovSettings.setSpacing(10);
        fovSettings.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 450, 210);
        stageParameter.setScene(scene);
        stageParameter.setResizable(false);
        stageParameter.initOwner(this.stage);
        stageParameter.initModality(Modality.WINDOW_MODAL);
        stageParameter.setScene(scene);
        stageParameter.setResizable(false);
        stageParameter.show();
    }

    @FXML
    private void onQuit() {
        this.stage.close();
    }

    /**
     * Synchroniser les pseudos, la liste des observateurs (pour le Monstre et le
     * Chasseur) des utilisateurs sur chaque client
     * 
     * @param role
     * @param currentClientUsername
     */
    private void synchronize(String role, String currentClientUsername) {
        try {
            UtilsServer.send(this.socket, this.model);
            this.model = (MonsterHunterModel) UtilsServer.receive(socket);
            if (role.equals("Monster"))
                this.model.setMonsterName(currentClientUsername);
            else
                this.model.setHunterName(currentClientUsername);
            this.model.getMonster().attach(this.model);
            this.model.getHunter().attach(this.model);
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Démarre le jeu avec les configurations d'IA spécifiées pour le monstre et le
     * chasseur.
     *
     * @param hunterAI  Défini à true si le chasseur est contrôlé par l'IA, false
     *                  sinon.
     * @param monsterAI Défini à true si le monstre est contrôlé par l'IA, false
     *                  sinon.
     */
    private void startGame(boolean hunterAI, boolean monsterAI) {
        MHMonsterController mc = null;
        MHHunterController hc = null;

        GameEndController gc = new GameEndController(stage, model);
        this.gameEndView = new GameEndView(stage, gc);
        gc.setGameEndView(gameEndView);
        gc.setHunterView(hunterView);
        gc.setMonsterView(monsterView);

        model.initialize();
        if (!monsterAI) {
            mc = new MHMonsterController(stage, model);
            this.monsterView = new MHMonsterView(stage, mc);
            mc.setMonsterView(this.monsterView);
        }

        if (!hunterAI) {
            hc = new MHHunterController(stage, model);
            this.hunterView = new MHHunterView(stage, hc);
            hc.setHunterView(this.hunterView);
            hc.setMonsterView(this.monsterView);
            hc.setGameEndView(gameEndView);
        }

        if (mc != null) {
            mc.setHunterView(this.hunterView);
            mc.setGameEndView(gameEndView);
        }

        if (monsterAI && hunterAI) {
            model.getMonster().setAi(monsterAI);
            model.getHunter().setAi(hunterAI);

            MHAIController aiController = new MHAIController(stage, model);
            MHAIView aiView = new MHAIView(stage, aiController);
            aiController.setView(aiView);
            aiController.setGameEndView(gameEndView);
            aiView.render();
        } else if (monsterAI) {
            model.getMonster().setAi(monsterAI);
            this.hunterView.render();
        } else if (hunterAI) {
            model.getHunter().setAi(hunterAI);
            model.getHunter().play();
            this.monsterView.render();
        } else {
            this.hunterView.render();
        }
    }

    /**
     * bouton "PvP Multijoueur". Ouvre une connexion à un lobby de jeu
     * avec un nom d'utilisateur et une adresse de serveur spécifiée par
     * l'utilisateur. Affiche la vue correspondante selon le rôle du client attribué aléatoirement par le serveur
     */
    @FXML
    private void onPVPMulti() {
        Stage stageMulti = new Stage();
        VBox vbox = new VBox();

        HBox hbox = new HBox();
        Label label = new Label("Nom du joueur");
        TextField nom_joueur = new TextField();
        hbox.getChildren().addAll(label, nom_joueur);
        HBox.setMargin(label, new Insets(10, 10, 10, 10));
        HBox.setMargin(nom_joueur, new Insets(10, 10, 10, 10));

        HBox hbox2 = new HBox();
        Label label2 = new Label("ip:port");
        TextField ip = new TextField();
        hbox2.getChildren().addAll(label2, ip);
        HBox.setMargin(label2, new Insets(10, 10, 10, 10));
        HBox.setMargin(ip, new Insets(10, 10, 10, 64));

        Button button = new Button("Se connecter au lobby");
        button.setStyle("-fx-background-color: lightgrey;");
        VBox.setMargin(button, new Insets(10, 10, 10, 10));

        button.setOnAction(e -> {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText(null);
            error.setTitle("Erreur");

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText(null);
            success.setContentText("Vous êtes connecté avec succès au serveur");
            success.setTitle("En attente que le serveur lance la partie...");

            String username = nom_joueur.getText();
            String address = ip.getText();

            if (!username.equals("")) {
                if (!address.equals("")) {
                    if (UtilsController.checkAddressSyntax(address)) {
                        String[] addr = address.split(":");
                        if (addr[1].equals("8080")) {
                            try {
                                int port = Integer.parseInt(addr[1]);
                                this.socket = new Socket(addr[0], port);
                                Thread t = new Thread(() -> {
                                    try {
                                        String role = (String) UtilsServer.receive(this.socket);
                                        this.model = (MonsterHunterModel) UtilsServer.receive(this.socket);

                                        Platform.runLater(() -> {
                                            success.close();
                                            stageMulti.close();

                                            if (role.equals("Monster")) {
                                                this.model.setMonsterName(username);
                                                this.synchronize(role, username);
                                                MHMonsterController mc = new MHMonsterController(this.stage, this.model,
                                                        this.socket);
                                                this.monsterView = new MHMonsterView(this.stage, mc);
                                                mc.setMonsterView(this.monsterView);
                                                mc.setHunterView(this.hunterView);
                                                this.monsterView.render();
                                            } else {
                                                this.model.setHunterName(username);
                                                this.synchronize(role, username);
                                                MHHunterController hc = new MHHunterController(this.stage, this.model,
                                                        this.socket);
                                                this.hunterView = new MHHunterView(this.stage, hc);
                                                hc.setHunterView(this.hunterView);
                                                hc.setMonsterView(this.monsterView);
                                                this.hunterView.render();
                                            }
                                        });
                                    } catch (ClassNotFoundException | IOException e1) {
                                        e1.printStackTrace();
                                    }
                                });
                                t.start();
                                success.showAndWait();
                            } catch (NumberFormatException | IOException e2) {
                                System.out.println(e);
                            }
                        } else {
                            error.setContentText("Le port ne correspond à celui du serveur");
                            error.showAndWait();
                        }
                    } else {
                        error.setContentText("Le format de l'adresse est incorrecte");
                        error.showAndWait();
                    }
                } else {
                    error.setContentText("Veuillez vérifier que le champ 'ip:port' a été rempli");
                    error.showAndWait();
                }
            } else {
                error.setContentText("Veuillez vérifier que le champ 'nom d'utilisateur' a été rempli");
                error.showAndWait();
            }
        });

        vbox.getChildren().addAll(hbox, hbox2, button);
        stageMulti.setTitle("Connexion à un serveur");
        stage.setResizable(false);
        stageMulti.initOwner(this.stage);
        stageMulti.initModality(Modality.WINDOW_MODAL);
        stageMulti.setScene(new Scene(vbox, 350, 140));
        stageMulti.show();
    }

    /*
     * Initialise le contrôleur, affiche le fond d'écran et initialise le style des
     * boutons
     */
    public void initialize() {
        UtilsController.hovereffect(jvjBtn);
        UtilsController.hovereffect(pvpBtn);
        UtilsController.hovereffect(cviBtn);
        UtilsController.hovereffect(mviBtn);
        UtilsController.hovereffect(iviBtn);
        UtilsController.hovereffect(parametres);
        UtilsController.hovereffect(quitter);
    }

}