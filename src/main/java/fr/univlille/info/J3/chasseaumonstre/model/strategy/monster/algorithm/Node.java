package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Node représente un noeud dans l'algorithme
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
        private final ICoordinate coordinate;
        private final int gScore; 
        private final int fScore; 

        public Node(ICoordinate coordinate, int gScore, int fScore) {
            this.coordinate = coordinate;
            this.gScore = gScore;
            this.fScore = fScore;
        }

        public ICoordinate getCoordinate() {
            return coordinate;
        }

        public int getFScore() {
            return fScore;
        }
    }