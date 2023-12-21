package fr.univlille.info.J3.chasseaumonstre;

import java.util.HashMap;
import java.util.Map;

public class ResultatsJoueurs {
    private Map<String, Integer> scoresMonstres;
    private Map<String, Integer> scoresChasseurs;

    public ResultatsJoueurs() {
        scoresMonstres = new HashMap<>();
        scoresChasseurs = new HashMap<>();
    }

    // Enregistre le score d'un joueur monstre
    public void enregistrerScoreMonstre(String nomJoueur, int score) {
        scoresMonstres.put(nomJoueur, score);
    }

    // Enregistre le score d'un joueur chasseur
    public void enregistrerScoreChasseur(String nomJoueur, int score) {
        scoresChasseurs.put(nomJoueur, score);
    }

    // Obtient le score d'un joueur monstre
    public int obtenirScoreMonstre(String nomJoueur) {
        return scoresMonstres.getOrDefault(nomJoueur, 0);
    }

    // Obtient le score d'un joueur chasseur
    public int obtenirScoreChasseur(String nomJoueur) {
        return scoresChasseurs.getOrDefault(nomJoueur, 0);
    }

    
}
