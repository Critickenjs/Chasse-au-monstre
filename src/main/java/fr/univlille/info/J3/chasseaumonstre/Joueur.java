package fr.univlille.info.J3.chasseaumonstre;

public class Joueur {
    private String nomUtilisateur;
    private String motDePasse;

    public Joueur(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
}
