package Use_Serializable.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Server {

    private Database d = new Database();
    private int port = 55555;

    public Server(){
        try(ServerSocket socketFromClient = new ServerSocket(port);
            Socket socket = socketFromClient.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream())) {

            objOut.writeObject("Ange fullständigt namn att söka på:");

            String searchWord;  // searchword from user

            while ((searchWord = in.readLine()) != null) {
                Friend friend = d.getFriend(searchWord);
                objOut.writeObject(friend);
            }

        }catch(NoSuchElementException e){
            System.out.println("Clienten kopplade ner sig:");
        }catch(IOException e){
            System.out.println("Gick ej att upprätta uppkoppling:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server start = new Server();
    }
}
