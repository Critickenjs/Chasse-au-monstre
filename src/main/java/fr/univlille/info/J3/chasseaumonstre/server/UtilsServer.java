package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * Classe utile pour le serveur
 * 
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class UtilsServer {

    /**
     * Reçoit un objet sérialisé du socket et le retourner
     * @param socket
     * @return L'objet sérialisé
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Object receive(Socket socket) throws ClassNotFoundException, IOException {
        ObjectInputStream oos = new ObjectInputStream(socket.getInputStream());
        return oos.readObject();
    }
   
    /**
     * Envoie un objet vers la socket
     * @param socket
     * @param data
     * @throws IOException
     */
    public static void send(Socket socket, Object data) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(data);
    }
}
