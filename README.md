# Chasse Au Monstre

Jeu vidéo graphique basé sur JavaFX, où l'on incarne un chasseur ou un monstre, le chasseur doit tirer sur un labyrinthe afin de trouver le monstre sans connaître ce dernier et le monstre doit pouvoir se déplacer jusqu'à la sortie pour gagner.

| Auteurs  |
| :--------------- |
| EL ATIFI Yliess |
| AOULAD-TAYAB Karim | 
| OUHDDA Anas |
| TAS Atilla |
| HAMZA Sélim|

### Sommaire

- [Livrable 1](#livrable-1)
- [Livrable 2](#livrable-2)

#### Lancement de l'application

Décompressez le fichier 'ChasseAuMonstre-V2.zip' (dernière version du jeu) dans le répertoire 'releases' et à l'intérieur du fichier décompressé éxecutez cette commande:

```
java --module-path [CHEMIN_ABSOLU_VERS_LE_DOSSIER_LIB_DE_JAVAFX] --add-modules javafx.controls,javafx.fxml,javafx.media -jar ChasseAuMonstre.jar
```

#### Consigne de compilation de la doc

Ouvrir le fichier 'doc/livrable-1/chasseaumonstre/chasseaumonstre/package-summary.html'

```
javadoc -private -author -d doc/chasseaumonstre -cp src/main/resources/cam-player-api-base.jar:src/main/resources/junit-platform-console-standalone-1.10.0.jar --module-path [CHEMIN_VERS_LE_DOSSIER_LIB_DU_SDK_JAVAFX] --add-modules javafx.controls,javafx.fxml,javafx.media -subpackages chasseaumonstre
```

#### Importer des algorithmes

Vous pouvez importer autant d'algorithmes que vous le souhaitez en modifiant le fichier [`App.java`](./src/main/java/fr/univlille/info/J3/chasseaumonstre/App.java) :

Pour le monstre :

```java
public class App extends Application {
    public final static Preferences PREFERENCES = Preferences.userNodeForPackage(App.class);
    private final String ICON_URL = "https://4.bp.blogspot.com/-boWx8QCf9bA/UYrk_pyI0aI/AAAAAAAAAoo/936FQO4QlNQ/s1600/dj.png";

    public final static List<Class<? extends IMonsterStrategy>> ALGORITHMS_MONSTER = Arrays.asList(
        AStar.class, Dijkstra.class, DepthFirstSearch.class // Ajouter ici les nouveaux algorithmes du monstre
    );
    ...
```

Pour le chasseur :

```java
public class Hunter extends Subject implements IHunterStrategy, Serializable {
    private static final Class<? extends IHunterStrategy> DEFAULT_ALGORITHM = RandomControlled.class; // Remplacer par le nouvel algorithme
    ...
```

## Livrable 1

UML

![uml](doc/livrable-1/rapports/img/Diagramme_de_classes.png)

## Livrable 2

Consignes pour utiliser le mode multi:

- Lancer le serveur MonsterHunterServer au préalable
- Appuyer sur le bouton 'Multijoueur' lors du lancement du jeu
- Écrire le nom du joueur A
- Pour l'ip il y a 2 situations possibles:
    - En localhost (boucle locale): **127.0.0.1:8080** (port en écoute par le serveur)
    - En local (dans le réseau local): **[ipv4_de_la_machine]:8080**
    - À distance (la machine hébergeant le serveur doit configurer un serveur dédié/VPS ou tout simplement faire une redirection de ports à configurer via le routeur) : **[ip_public]:8080**

![example_multi](doc/livrable-3/img/example_multi.png)