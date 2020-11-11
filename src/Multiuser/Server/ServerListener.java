package Multiuser.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private int port = 55555;

    public ServerListener() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while(true) {
            try (Socket socketToClient = serverSocket.accept()){
                ClientHandler clientHandler = new ClientHandler(socketToClient);
                clientHandler.start();

            } catch (IOException e) {
                System.out.println("Kunde inte upprätta uppkoppling till clienten med port nr: " + port);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerListener start = new ServerListener();
    }
}
