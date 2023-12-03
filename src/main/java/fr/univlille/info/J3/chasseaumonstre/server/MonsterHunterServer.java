package fr.univlille.info.J3.chasseaumonstre.server;

import java.net.*;

import java.io.IOException;

public class MonsterHunterServer {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String CYAN = "\033[0;36m";

    private ServerSocket serverSocket;
    private Socket client1, client2;
    private String hunterName, monsterName;

    public MonsterHunterServer() throws IOException {
        this.serverSocket = new ServerSocket(8080);
        System.out.println("Écoute sur le port " + this.serverSocket.getLocalPort());
    }

    /**
     * Vérifie si le lobby est au complet
     * @return booléen
     */
    private boolean isLobbyFull() {
        return this.client1 != null && this.client2 != null;
    }

    /**
     * Affecte le socket du client au client 1 ou 2
     * @param socket Socket du client
     */
    private void set(Socket socket) {
        if(this.client1 == null)
            this.client1 = socket;
        else if(this.client2 == null)
            this.client2 = socket;
    }

    public void handleConnection() throws SocketException, IOException {
        Socket socket = null;
        while(true) {
            if(this.client1 == null)
                System.out.println(CYAN + "\nEn attente de joueurs..." + RESET);
            else if(this.client2 == null)
                System.out.println(CYAN + "\nEn attente du deuxième joueur..." + RESET);

            socket = this.serverSocket.accept();
            System.out.println(BLUE + "\nJoueur avec l'adresse " + socket.getRemoteSocketAddress() + " demande à rentrer dans le lobby..." + RESET);
            
            if(this.isLobbyFull()) {
                System.out.println(RED + "\nLe nombre de joueurs maximum est de 2" + RESET);
                socket.close();
            } else {
                this.set(socket);
                System.out.println(GREEN + "\nJoueur accepté dans le lobby..." + RESET);
                if(this.isLobbyFull()) {
                    System.out.println("\nAu complet ! Nous pouvons jouer");
                    // new ServerThread(this.client1, this.client2).start();
                    // new ServerThread(this.client2, this.client1).start();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            new MonsterHunterServer().handleConnection();
        } catch(SocketException e) {
            System.err.println("Erreur socket : " + e.getMessage());
        } catch(IOException e) {
            System.err.println("Erreur I/O : " + e.getMessage());
        }
    }
}
