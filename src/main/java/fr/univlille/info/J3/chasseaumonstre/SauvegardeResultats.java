package fr.univlille.info.J3.chasseaumonstre;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class SauvegardeResultats 
{

    public static void sauvegarderDansFichier(ResultatsJoueurs resultats, String nomFichier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            sauvegarderScores(writer, "Monstres", resultats.getScoresMonstres());
            sauvegarderScores(writer, "Chasseurs", resultats.getScoresChasseurs());
            System.out.println("Données sauvegardées dans le fichier : " + nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sauvegarderScores(PrintWriter writer, String typeJoueur, Map<String, Integer> scores) {
        writer.println(typeJoueur + " :");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            writer.println(entry.getKey() + " : " + entry.getValue() + " points");
        }
        writer.println();  // Ajouter une ligne vide entre les types de joueurs
    }

}

