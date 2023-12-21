package fr.univlille.info.J3.chasseaumonstre;

import java.util.HashMap;
import java.util.Map;

public class ResultatsJoueurs 
{
    private Map<Joueur, Integer> scoresMonstres;
    private Map<Joueur, Integer> scoresChasseurs;

    public ResultatsJoueurs() {
        scoresMonstres = new HashMap<>();
        scoresChasseurs = new HashMap<>();
    }

    // Enregistre le score d'un joueur monstre
    public void enregistrerScoreMonstre(Joueur joueur, int score) {
        scoresMonstres.put(joueur, score);
    }

    // Enregistre le score d'un joueur chasseur
    public void enregistrerScoreChasseur(Joueur joueur, int score) {
        scoresChasseurs.put(joueur, score);
    }

    // Obtient le score d'un joueur monstre
    public int obtenirScoreMonstre(Joueur joueur) {
        return scoresMonstres.getOrDefault(joueur, 0);
    }

    // Obtient le score d'un joueur chasseur
    public int obtenirScoreChasseur(Joueur joueur) {
        return scoresChasseurs.getOrDefault(joueur, 0);
    }

    public Map<Joueur, Integer> getScoresChasseurs() {
        return scoresChasseurs;
    }

    public Map<Joueur, Integer> getScoresMonstres() {
        return scoresMonstres;
    }
}
