package fr.univlille.info.J3.chasseaumonstre.server;

import java.net.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import java.io.IOException;

public class MonsterHunterServer {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String CYAN = "\033[0;36m";

    private ServerSocket serverSocket;
    private Map<String, Socket> clients;

    public MonsterHunterServer() throws IOException {
        this.serverSocket = new ServerSocket(8080);
        this.clients = new HashMap<>();
        System.out.println("Écoute sur le port " + this.serverSocket.getLocalPort());
    }

    /**
     * Retourne le nombre de clients connectés
     * @return
     */
    private int getNbClientsConnected() {
        return this.clients.size();
    }
    

    /**
     * Vérifie si le lobby est au complet
     * @return booléen
     */
    private boolean isLobbyFull() {
        return this.getNbClientsConnected() == 2;
    }

    /**
     * Affecte le socket du client au client 1 ou 2
     * @param socket Socket du client
     */
    private String set(Socket socket) {
        Random r = new Random();
        String[] roles = {"Monster", "Hunter"};
        String role = roles[r.nextInt(2)];
        if(!this.clients.keySet().contains(role))
            this.clients.put(role, socket);
        else {
            role = role.equals("Monster") ? "Hunter" : "Monster";
            this.clients.put(role, socket);
        }
        return role;
    }

    public void handleConnection() throws SocketException, IOException {
        Socket socket = null;
        while(true) {

            if(this.getNbClientsConnected() == 0)
                System.out.println(CYAN + "\nEn attente de joueurs..." + RESET);
            else if(this.getNbClientsConnected() == 1)
                System.out.println(CYAN + "\nEn attente du deuxième joueur..." + RESET);

            socket = this.serverSocket.accept();

            System.out.println(GREEN + "\nJoueur avec l'adresse " + socket.getRemoteSocketAddress() + " tente de rentrer dans le lobby..." + RESET);

            if(!this.isLobbyFull()) {
                System.out.println("\nLe joueur devient " + this.set(socket) + " !");

                if(this.isLobbyFull()) {
                    System.out.println("\nLa partie commence !");

                    // Thread du Monstre
                    new ServerThread(this.clients.get("Monster"), this.clients.get("Hunter")).start();

                    // Thread du Chasseur
                    new ServerThread(this.clients.get("Hunter"), this.clients.get("Monster")).start();
                }
            } else {
                System.out.println("\nEn pleine partie, plus personne peut rejoindre !");
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
