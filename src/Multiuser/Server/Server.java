package Multiuser.Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Server {
    Protocol procotol = new Protocol();
    private int port = 55555;

    public Server(){
        try(ServerSocket socketFromClient = new ServerSocket(port);
            Socket socket = socketFromClient.accept();
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream())) {

            String searchWord;  // searchword from client/user
            Response response;  // data sent to client/user

            response = procotol.getResponse(null);
            objOut.writeObject(response);

            while ((searchWord = (String) objIn.readObject()) != null) {
                response = procotol.getResponse(searchWord);
                objOut.writeObject(response);
            }



        }catch(ClassNotFoundException e){
            System.out.println("Kunde inte läsa från användaren.");
            e.printStackTrace();
        }catch(NoSuchElementException | EOFException e){
            System.out.println("Clienten kopplade ner sig:");
        }catch(IOException e){
            System.out.println("Gick ej att upprätta uppkoppling:");
        }
    }

    public static void main(String[] args) {
        Use_Serializable.Server.Server start = new Use_Serializable.Server.Server();
    }
}
