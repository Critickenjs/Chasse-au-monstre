package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerThread extends Thread {
    
    private Socket currClient, secondClient;
    private Map<Socket, Object[]> streamClients;

    public ServerThread(Socket currSocket, Socket secondSocket) throws IOException {
        this.currClient = currSocket;
        this.secondClient = secondSocket;
        this.streamClients = new HashMap<>();
        this.streamClients.put(this.currClient, new Object[]{new ObjectInputStream(this.currClient.getInputStream()), new ObjectOutputStream(this.currClient.getOutputStream())});
        this.streamClients.put(this.secondClient, new Object[]{new ObjectInputStream(this.secondClient.getInputStream()), new ObjectOutputStream(this.secondClient.getOutputStream())});
    }

    public Object receiveModel() {
        try {
            return ((ObjectInputStream)this.streamClients.get(this.currClient)[0]).readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("La tenative de déserialisation de la classe 'MonsterHunterModel' n'a pas été effectué avec succès (La classe est introuvable): " + e.getMessage());
        } catch(IOException e) {
            System.err.println("Erreur I/O : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("[" + this.currClient.getRemoteSocketAddress() + "] -> model received : " + this.receiveModel());
        }
        // for(Socket s : this.clients) {
        //     try {
        //         new ObjectOutputStream(s.getOutputStream()).writeObject(model);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
    }
}
