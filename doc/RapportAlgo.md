# Rapport sur les Algorithmes et Structures de Données utilisés dans la Chasse au Monstre

## Construction du Labyrinthe
Le labyrinthe est généré de manière aléatoire à l'aide de la classe MazeGenerator. L'algorithme de génération crée un tableau représentant le labyrinthe.
Le processus de génération commence par initialiser toutes les cellules du labyrinthe comme des murs (1). Ensuite, un point d'entrée (2) et une sortie (4) sont déterminés aléatoirement sur les bords du labyrinthe. Enfin, un chemin est créé entre ces deux points en utilisant une variante de l'algorithme de recherche en profondeur (DFS). Le résultat final est un labyrinthe avec des chemins, des murs, un point d'entrée pour le monstre, un point de sortie, et éventuellement des zones ouvertes pour le chasseur.

## IA du Monstre
L'IA du monstre est basée sur l'algorithme A*. L'objectif du monstre est de trouver le chemin optimal de l'entrée vers la sortie du labyrinthe tout en évitant les obstacles. L'algorithme A* est utilisé pour explorer les différentes possibilités de déplacement et choisir le chemin le plus court.
Cet algorithme permet de trouver le chemin le plus court entre un point d'entrée et un point de sortie dans un graphe pondéré. Il utilise une heuristique (ici, la distance de Manhattan) pour estimer le coût restant.


## IA du Chasseur
L'IA du chasseur est est implémentée dans la méthode `play()` de la classe `Hunter`. Le chasseur adopte une stratégie pour tirer sur les cases du labyrinthe. La stratégie actuelle consiste à explorer les cases voisines d'une case déjà visitée. Si le monstre a traversé l'une de ces cases voisines, le chasseur tire sur cette case. Sinon, le chasseur choisit une case aléatoire non encore explorée.

## Structures de Données utilisées
- **Graphes :** Le labyrinthe est représenté comme un graphe où chaque cellule est un nœud. Les arêtes du graphe représentent les déplacements possibles entre les cellules.
- **Listes :** Utilisées pour stocker les coordonnées du chemin dans l'algorithme A*.
- **Maps :** Utilisées pour stocker les distances réeles à la sortie `gScore` des nœuds dans l'algorithme A*.
- **PriorityQueue :** Utilisée dans l'algorithme A* pour choisir le nœud avec l'heuristique la plus basse `fScore`.

## Optimisations et Points Importants
- L'algorithme A* est privilégié à Dijkstra en raison de son efficacité accrue dans la recherche de chemins, grâce à l'incorporation d'une heuristique de     distance de Manhattan. Cette heuristique permet à A* de prioriser les nœuds qui semblent être plus proches du but, réduisant ainsi le nombre total de nœuds à explorer et améliorant les performances globales de l'algorithme par rapport à Dijkstra.
- L'utilisation d'une PriorityQueue permet d'optimiser la recherche du nœud avec le coût le plus bas dans A*.
- Pour le chasseur, l'exploration des voisins est utilisée pour essayer de localiser le monstre plus efficacement.

Ces éléments offrent une dynamique intéressante et équilibrée au jeu de la Chasse au Monstre. Cependant pour les IA, nous constatons que l'algorithme A* est plus optimisé que celui d'exploration des cases voisines impléménter pour l'IA du chasseur.
