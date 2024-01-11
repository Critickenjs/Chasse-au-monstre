/**
 * <p>Chasse au Monstre est un jeu graphique écrit en <b>Java</b> et <b>JavaFX</b> dans lequel 2 joueurs (ou IA) (un chasseur et monstre) s'affrontent, la partie du jeu débute sur un labyrinthe avec 2 vues différentes suivant le rôle incarné par le joueur:</p>
 * <ul>
 *  <li>Le chasseur n'a aucune visibilité du labyrinthe (que de soit les murs ou le chasseur) cependant lors qu'on appuie sur une cellule du labyrinthe, on "tire" sur la cellule et on peut obtenir des informations (si c'est un mur, si c'est un chemin ou si le monstre est déjà passé par là)</li>
 *  <li>Le monstre peut avoir une visibilité complète come partielle (c'est paramétrable (cf: les paramètres du jeu dans le menu)), il doit se déplacer sur les 4 axes (haut, bas, gauche, droite) dans le labyrinthe et trouver le chemin de sortie (colorié en vert) avant que le chasseur le trouve</li>
 * </ul>
 * 
 * <p>La partie se termine si le monstre trouve le chemin (il gagne) sinon c'est le chasseur qui gagne en trouvant le monstre</p>
 * 
 * <p>Il existe des modes <b>Chasseur vs IA</b>, <b>Monstre vs IA</b>, <b>IA vs IA</b> . l'IA est basé sur des algos de <b>Pathfinding</b> (cf: les paramètres du jeu dans le menu)
 * (dans le cas du mode <b>Monstre vs IA</b>, l'algo est basé sur de l'aléatoire mais affiné suivant les informations trouvés lors des tirs du chasseurs (en clair les cases visitées par le monstre pourront servir au chasseur à affiner sa recherche du monstre))</p>
 * 
 * <p>Un mode multi (Joueur vs Joueur) est aussi dispo basé sur les sockets TCP, c'est plus ou monins la même chose que le mode classique Joueur vs Joueur sauf que 2 clients communiquent ensemble à chaque tour en envoyant son modèle pour qu'il soit synchronisé</p>
 * 
 * <i>Auteurs : Anas Ouhdda Atilla Tas Karim Aoulad-Tayab Selim Hamza Yliess El Atifi</i>
 */
package fr.univlille.info.J3.chasseaumonstre;