package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster;

import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;

/*
 * Node représente un noeud dans l'algorithme A*
 * 
 * @see Coordinate
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
@SuppressWarnings("unused")
public class Node {
        private final Coordinate coordinate;
        private final int gScore; 
        private final int fScore; 

        public Node(Coordinate coordinate, int gScore, int fScore) {
            this.coordinate = coordinate;
            this.gScore = gScore;
            this.fScore = fScore;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public int getFScore() {
            return fScore;
        }
    }