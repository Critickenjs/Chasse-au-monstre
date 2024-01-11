package fr.univlille.info.J3.chasseaumonstre.server;

import java.net.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.io.IOException;

import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;

/**
 * Serveur du jeu Chasse Au Monstre pour le mode multijoueur
 * 
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class MonsterHunterServer {
    // Attributs utilisés pour la coloration de la console avec les couleurs ANSI
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";
    private static final String CYAN = "\033[0;36m";

    private ServerSocket serverSocket;
    private Map<String, Socket> clients;
    private boolean run;

    /**
     * Constructeur du serveur du jeu Chasse Au Monstre
     * - On initialise une hashmap de clients pour stocker le rôle qu'ils auront
     * aléatoirement et leur socket reçu côté serveur
     * - On écoute continuellement sur le port 8080 sur la socket du serveur
     * 
     * @throws IOException
     */
    public MonsterHunterServer() throws IOException {
        this.serverSocket = new ServerSocket(8080);
        this.clients = new HashMap<>();
        this.run = true;
        System.out.println("Écoute sur le port " + this.serverSocket.getLocalPort());
    }

    /**
     * Retourne le nombre de clients connectés
     */
    private int getNbClientsConnected() {
        return this.clients.size();
    }

    /**
     * Vérifie si le lobby est au complet
     */
    private boolean isLobbyFull() {
        return this.getNbClientsConnected() == 2;
    }

    /**
     * Affecte et retourne aléatoirement les rôles respectifs de chaque client
     * 
     * @param socket Socket du client
     */
    private String set(Socket socket) {
        Random r = new Random();
        String[] roles = { "Monster", "Hunter" };
        String role = roles[r.nextInt(2)];
        if (!this.clients.keySet().contains(role))
            this.clients.put(role, socket);
        else {
            role = role.equals("Monster") ? "Hunter" : "Monster";
            this.clients.put(role, socket);
        }
        return role;
    }

    /**
     * Traite les requêtes provenant des sockets clients et commence la partie dès
     * que 2 clients ont rejoint le lobby
     * en créeant 2 threads pour que les 2 sockets puissent communiquer avec le
     * client continuellement
     * 
     * @throws IOException
     */
    public void handleConnection() throws SocketException, IOException {
        Socket socket = null;
        while (this.run) {

            if (this.getNbClientsConnected() == 0)
                System.out.println(CYAN + "\nEn attente de joueurs..." + RESET);
            else if (this.getNbClientsConnected() == 1)
                System.out.println(CYAN + "\nEn attente du deuxième joueur..." + RESET);

            socket = this.serverSocket.accept();

            System.out.println(GREEN + "\nJoueur avec l'adresse " + socket.getRemoteSocketAddress()
                    + " tente de rentrer dans le lobby..." + RESET);

            if (!this.isLobbyFull()) {
                System.out.println("\nLe joueur devient " + this.set(socket) + " !");

                if (this.isLobbyFull()) {
                    System.out.println("\nLa partie commence !");

                    // Prévenir les 2 clients que la partie commence
                    UtilsServer.send(this.clients.get("Monster"), "Monster");
                    UtilsServer.send(this.clients.get("Hunter"), "Hunter");

                    // Initialiser le même modèle pour les 2 clients
                    MonsterHunterModel model = new MonsterHunterModel();
                    model.initialize();
                    UtilsServer.send(this.clients.get("Monster"), model);
                    UtilsServer.send(this.clients.get("Hunter"), model);

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
        } catch (SocketException e) {
            System.err.println("Erreur socket : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur I/O : " + e.getMessage());
        }
    }
}
