package Multiuser.Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class ClientHandler extends Thread{

    Protocol procotol = new Protocol();
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try(ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
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
            System.out.println("Kunde ej framkalla skickat objekt.");
            e.printStackTrace();
        }catch(NoSuchElementException | EOFException e){
            System.out.println("Clienten kopplade ner sig:");
        }catch(IOException e){
            System.out.println("Kunde ej upprätta uppkoppling med clienten.");
            e.printStackTrace();
        }
    }

}
