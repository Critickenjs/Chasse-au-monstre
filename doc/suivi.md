# Suivi de l'avancée

## Semaine 45

6 Novembre - 12 Novembre 2023

**Karim Aoulad-Tayab** :
- Mise à jour du fichier "Rapport_Analyse_1.md".
- Ajout de nouveaux fichiers, réorganisation de certains dossiers, mise à jour de la documentation et de l'UML du projet.

**Anas Ouhdda** :
- Correction du diagramme de classes du rapport 1.
- Ajout du patron de conception Observer/Subject.
- Suppression de fichiers et dossiers inutiles.
- Refactoring des contrôleurs.
- Ajout d'héritages et d'implémentations de l'Observer/Subject à certains fichiers.
- Ajout de classes Subject et Observer depuis un jar.
- Modifications dans le fichier de test "HunterTest.java".
- Ajout d'un brouillard en dehors de la vision du monstre.

**Selim Hamza** :
- Ajout de plusieurs fichiers et dossiers au projet, notamment des ressources audio, des fichiers de maquette, et un JAR.

**Yliess El atifi** :
- Ajout de la methode estvisible qui change la vue du monstre, il ne voit plus que 2 case autout de lui.+ monstre apparait aleatoirement.
- Test sur tout les models disponibles

**Atilla Tas** :
- Modifications du style des alertes.
- Affichage en format `hand` du curseur
- Traduction du texte anglais en français




## Semaine 46

13 Novembre - 19 Novembre 2023

**Karim Aoulad-Tayab** :
- Mettre une taille de labyrinthe s'adaptant a la taille des cellules
- Chargement d'un labyrinthe prédéfini

**Anas Ouhdda** :
- Champs de vision du monstre définissable dans les paramètres du jeu.
- Renommage du package principal.
- Résolution du bug du monstre invisible au premier tour.
- Ajout du pourcentage d'obstacles dans les paramètres.
- Ajustement de la taille minimale et correction des tests du modèle.
- Suppression de paramètres de constructeur inutiles dans la documentation de MonsterHunterModel.
- Ajout des tâches dans le fichier de suivi.
- Mise à jour de la validation des paramètres d'entrée avec des exceptions.
- Enregistrement/chargement de la hauteur/largeur depuis les préférences.
- Possibilité de changer la taille.

**Selim Hamza** :
  

**Yliess El atifi** : 
- Documentation et test de a*

**Atilla Tas** :
  



## Semaine 47

20 Novembre - 26 Novembre 2023

**Karim Aoulad-Tayab** :
- Aucune contribution enregistrée cette semaine.

**Anas Ouhdda** :
- Retrait de fonctionnalité : case visitable qu'une seule fois + ajout d'une bordure pour le chasseur (hunter).
- Mise à jour des tests pour le changement de type.
- Modification du type du labyrinthe de CellInfo à boolean dans toutes les classes.

**Selim Hamza** :
- Aucune contribution enregistrée cette semaine.

**Yliess El atifi** :
- Aucune contribution enregistrée cette semaine.

**Atilla Tas** :
- Aucune contribution enregistrée cette semaine.




## Semaine 48

27 Novembre - 3 Décembre 2023

**Karim Aoulad-Tayab** :
- Aucune contribution enregistrée cette semaine.

**Anas Ouhdda** :
- Aucune contribution enregistrée cette semaine.

**Selim Hamza** :
- Aucune contribution enregistrée cette semaine.

**Yliess El atifi** :
- Aucune contribution enregistrée cette semaine.

**Atilla Tas** :
- Aucune contribution enregistrée cette semaine.




## Semaine 49

4 Décembre - 10 Décembre 2023

**Karim Aoulad-Tayab** :
- Aucune contribution enregistrée cette semaine.

**Anas Ouhdda** :
- Ajout du compteur de tours.
- Possibilité de jouer en mode Chasseur VS IA.
- Refactoring des classes d'algorithme pour le monstre.
- Implémentation fonctionnelle de l'algorithme A*.

**Selim Hamza** :
- Correction d'un problème avec le déplacement du monstre.

**Yliess El atifi** : 
- Début d'implémentation de l'algorithme A*.
- Corrections et mises à jour dans la classe Monster.

**Atilla Tas** :
- Fixe bug sur la méthode d'importation d'un labyrinthe
- Fixe bug sur l'affichage après une importation d'un labyrinthe




## Semaine 50

11 Décembre - 17 Décembre 2023

**Karim Aoulad-Tayab** :
- Automatisation de l'interaction 'passage au tour suivant' pour le mode 'ia vs ia'

**Anas Ouhdda** :
- Refactorisation de la logique du jeu et correction de certains bugs.
- Suppression des appels à winAlert() des contrôleurs et amélioration des appels de mise à jour (observers).
- Suppression des imports et des variables inutilisés dans la classe Hunter.
- Retrait de JVJView pour les modes de jeu Joueur contre IA.
- Changement de version du FXML pour éliminer l'avertissement au lancement.
- Suppression de déclarations de sortie de la méthode isValid() dans MazeValidator.
- Suppression de l'affichage de débogage dans la méthode isValid() de MazeValidator.

**Selim Hamza** :
- Ajout de l'archive JAR du jeu
- Refactoring de la classe `MazeValidator`
- Mise à jour des tests de la classe `MazeValidator` pour qu'ils soient plus pertinents et complets
- Mise à jour de la javadoc pour toutes les classes du projet
- Suppression de quelques redondances dans le code

**Yliess El atifi** : 
- Suite de l'implementation de a*
- Fix le MazeValidator pour exécuter l'algorithme AStar afin de verifier qu'il y a bien un chemin
- Refactor + tests

**Atilla Tas** :
- Ajout d'un bouton retour dans la vue `jvj`
- Fixe bug où lorsque l'on appuyé sur `ENTER`, bypass la nécessité d'entrer les deux pseudonymes.
- Refactor code: clean code
- Ajout d'un menu pause en pleine partie
- Modification des placements des boutons dans les menus (`main` et `jvj`)
- Ajout d'un bouton `quitter`
- Commence l'issue [#18](https://gitlab.univ-lille.fr/sae302/2023/J3_SAE3A/-/issues/18)




## Semaine 51

18 Décembre - 24 Décembre 2023

**Karim Aoulad-Tayab** :
- Aucune contribution enregistrée cette semaine.

**Anas Ouhdda** :
- Aucune contribution enregistrée cette semaine.

**Selim Hamza** :
- Aucune contribution enregistrée cette semaine.

**Yliess El atifi** :
- Aucune contribution enregistrée cette semaine.

**Atilla Tas** :
- Aucune contribution enregistrée cette semaine.




## Semaine 52

25 Décembre - 31 Décembre 2023

**Karim Aoulad-Tayab** :
- Aucune contribution enregistrée cette semaine.

**Anas Ouhdda** :
- Aucune contribution enregistrée cette semaine.

**Selim Hamza** :
- Aucune contribution enregistrée cette semaine.

**Yliess El atifi** :
- Aucune contribution enregistrée cette semaine.

**Atilla Tas** :
- Aucune contribution enregistrée cette semaine.




## Semaine 53

1 Janvier - 7 Janvier 2024

**Karim Aoulad-Tayab** :
- Implémentation du mode multi-joueur en réseau.

**Anas Ouhdda** :
- Aucune contribution enregistrée cette semaine.

**Selim Hamza** :
- Refactoring des algorithmes
- Implémentation de l'algorithme de Dijkstra

**Yliess El atifi** :
- Implémentation de l'algorithme de parcours en largeur
- Possibilité de choisir l'algorithme de l'IA dans les paramètres du jeu

**Atilla Tas** :
- Aucune contribution enregistrée cette semaine.

## Semaine 54

8 Janvier - 14 Janvier

**Anas Ouhdda** :
- Ajout de conditions manquantes dans les algorithmes
- Création de tests pour le DFS et l'objet Node

**Selim Hamza** :
- Ajout de tests pour augmenter le coverage (package `algorithm` et `strategy`)
- Refactoring de l'attribut d'algorithme *(Class remplace le type String)*

**Karim Aoulad-Tayab** :
- Aucune contribution enregistrée cette semaine.
