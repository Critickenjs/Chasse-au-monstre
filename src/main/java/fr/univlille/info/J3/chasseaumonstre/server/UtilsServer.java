package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;

public class UtilsServer {

    public static MonsterHunterModel receive(Socket socket) throws ClassNotFoundException, IOException {
        ObjectInputStream oos = new ObjectInputStream(socket.getInputStream());
        return (MonsterHunterModel)oos.readObject();
    }

    public static void send(Socket socket, MonsterHunterModel model) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(model);
    }
}
