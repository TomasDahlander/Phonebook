package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {

    private Database d = new Database();
    private int port = 55555;

    public Server(){
        try(ServerSocket socketFromClient = new ServerSocket(port);
            Socket socket = socketFromClient.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner in = new Scanner(new InputStreamReader(socket.getInputStream()))) {

            out.println("Ange fullständigt namn att söka på:");

            String searchWord;  // searchword from user
            String dataToSend; // data to send back

            while ((searchWord = in.nextLine()) != null) {
                dataToSend = d.getFriendData(searchWord);

                if (dataToSend == null) out.println("Namnet återfanns ej i databasen: " + searchWord.trim());
                else out.println(dataToSend);
            }

        }catch(NoSuchElementException e){
            System.out.println("Clienten kopplade ner sig:");
        }catch(IOException e){
            System.out.println("Gick ej att upprätta uppkoppling:");
        }
    }

    public static void main(String[] args) {
        Server start = new Server();
    }
}
