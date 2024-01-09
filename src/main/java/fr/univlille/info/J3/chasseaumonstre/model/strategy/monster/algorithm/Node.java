package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

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
public class Node implements ICoordinate {
        private final ICoordinate coordinate;
        private int gScore; 
        private int fScore; 

        public Node(ICoordinate coordinate, int gScore, int fScore) {
            this.coordinate = coordinate;
            this.gScore = gScore;
            this.fScore = fScore;
        }

        public ICoordinate getCoordinate() {
            return coordinate;
        }

        public int getGScore() {
            return gScore;
        }

        public int getFScore() {
            return fScore;
        }

        public void setGScore(int gScore) {
            this.gScore = gScore;
        }

        public void setFScore(int fScore) {
            this.fScore = fScore;
        }

        public int getRow() {
            return coordinate.getRow();
        }

        public int getCol() {
            return coordinate.getCol();
        }

        @Override
        public String toString() {
            return coordinate.toString();
        }
    }