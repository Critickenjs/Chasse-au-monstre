package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.net.Socket;

import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;

public class ServerThread extends Thread {

    private Socket currClient, secondClient;

    public ServerThread(Socket currSocket, Socket secondSocket) throws IOException {
        this.currClient = currSocket;
        this.secondClient = secondSocket;
    }

    @Override
    public void run() {
        MonsterHunterModel model = null;
        while(true) {
            try {
                // Lire sur la socket (attendre une réponse)
                model = (MonsterHunterModel)UtilsServer.receive(currClient);
                // Renvoyer le modèle à l'autre client
                UtilsServer.send(this.secondClient, model);
            } catch(ClassNotFoundException | IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
