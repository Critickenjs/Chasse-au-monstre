package fr.univlille.info.J3.chasseaumonstre;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class SauvegardeResultats {

    public static void sauvegarderDansFichier(ResultatsJoueurs resultats, String nomFichier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            sauvegarderScores(writer, "Monstres", resultats.getScoresMonstres());
            sauvegarderScores(writer, "Chasseurs", resultats.getScoresChasseurs());
            System.out.println("Données sauvegardées dans le fichier : " + nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sauvegarderScores(PrintWriter writer, String categorie, Map<Joueur, Integer> scores) {
        writer.println(categorie);
        for (Map.Entry<Joueur, Integer> entry : scores.entrySet()) {
            Joueur joueur = entry.getKey();
            writer.println(joueur.getNomUtilisateur() + "," + joueur.getMotDePasse() + "," + entry.getValue());
        }
        writer.println(); // Ajoutez une ligne vide pour séparer les catégories
    }
}
