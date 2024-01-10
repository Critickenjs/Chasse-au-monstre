package fr.univlille.info.J3.chasseaumonstre.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UtilsServer {

    public static Object receive(Socket socket) throws ClassNotFoundException, IOException {
        ObjectInputStream oos = new ObjectInputStream(socket.getInputStream());
        return oos.readObject();
    }
   
    public static void send(Socket socket, Object data) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(data);
    }
}
