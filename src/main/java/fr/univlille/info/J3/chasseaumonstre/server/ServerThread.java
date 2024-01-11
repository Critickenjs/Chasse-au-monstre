package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.net.Socket;

import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;

/*
 * Thread du serveur permettant de traiter les requêtes des 2 clients continuellement durant la partie
 * 
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class ServerThread extends Thread {

    private Socket currClient, secondClient;

    /**
     * Constructeur du thread traitant les requêtes du client durant la partie
     * @param currSocket Socket du client courant
     * @param secondSocket Socket du client avec qui il communique
     * @throws IOException
     */
    public ServerThread(Socket currSocket, Socket secondSocket) throws IOException {
        this.currClient = currSocket;
        this.secondClient = secondSocket;
    }

    /**
     * Méthode éxecutée dans le thread
     * - Attend de reçevoir une réponse du client. Après que le joueur ait joué sur tel tour il envoie le nouveau modèle au serveur pour que le thread correspondant reçoive le modèle et le renvoie à l'autre client pour qu'il se synchronise au niveau du modèle.
     * - Si on reçoit un String à la place d'un modèle cela veut dire que le client envoie un message pour informer l'autre client qu'il a gagné
     */
    @Override
    public void run() {
		Object obj;
        while(true) {
            try {
                // Lire sur la socket (attendre une réponse)
                obj = UtilsServer.receive(currClient);
				// Renvoyer le modèle à l'autre client
				if(obj.getClass() == MonsterHunterModel.class)
                    UtilsServer.send(this.secondClient, (MonsterHunterModel)obj);
				else if(obj.getClass() == String.class)
					UtilsServer.send(this.secondClient, (String)obj);
            } catch(ClassNotFoundException | IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
