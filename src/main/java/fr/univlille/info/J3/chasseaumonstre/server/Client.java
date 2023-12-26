package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        MonsterHunterModel model = new MonsterHunterModel();
        model.setWidth(21);
        model.setHeight(17);
        Socket socket = new Socket("127.0.0.1", 8080);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        // ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        while(true) {
            System.out.println("Envoi du modèle aux autres clients...");
            oos.writeObject(model);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // System.out.println("Réception du nouveau modèle : ");
            // System.out.println(ois.readObject());
        }
    }
}
