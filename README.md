# Chasse Au Monstre

Jeu vidéo graphique basé sur JavaFX, où l'on incarne un chasseur ou un monstre, le chasseur doit tirer sur un labyrinthe afin de trouver le monstre sans connaître ce dernier et le monstre doit pouvoir se déplacer jusqu'à la sortie pour gagner.

| Auteurs  |
| :--------------- |
| EL ATIFI Yliess |
| AOULAD-TAYAB Karim | 
| OUHDDA Anas |
| TAS Atilla |
| HAMZA Sélim|

#### Consigne de compilation de la doc (+ UML)

```
javadoc -private -author -d doc/chasseaumonstre -cp src/main/resources/cam-player-api-base.jar:src/main/resources/junit-platform-console-standalone-1.10.0.jar --module-path [CHEMIN_VERS_LE_DOSSIER_LIB_DU_SDK_JAVAFX] --add-modules javafx.controls,javafx.fxml,javafx.media -sourcepath src/main/java -docletpath src/main/resources/umldoclet-2.1.0.jar -doclet nl.talsmasoftware.umldoclet.UMLDoclet -subpackages chasseaumonstre
```

## Livrable 1

#### Lancement de l'application

Décompressez le fichier 'ChasseAuMonstre.zip' et à l'intérieur du dossier décompressé éxecutez cette commande:

```
java --module-path [CHEMIN_ABSOLU_VERS_LE_DOSSIER_LIB_DE_JAVAFX] --add-modules javafx.controls,javafx.fxml,javafx.media -jar ChasseAuMonstre.jar
```

UML

![uml](doc/livrable-1/rapports/img/Diagramme_de_classes.png)
