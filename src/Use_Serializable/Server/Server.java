package Use_Serializable.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Server {

    private Database d = new Database();
    Intro intro = new Intro();
    private int port = 55555;

    public Server(){
        try(ServerSocket socketFromClient = new ServerSocket(port);
            Socket socket = socketFromClient.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream())){

            objOut.writeObject(intro);

            String searchWord;  // searchword from user
            Response response;



            while ((searchWord = in.readLine()) != null) {
                Object obj = d.getFriend(searchWord);

                if(obj instanceof Friend){
                    response = new Response();
                    response.setFriend((Friend)obj);
                    response.setContainsFriend(true);
                    objOut.writeObject(response);
                }
                else {
                    response = new Response();
                    response.setContainsFriend(false);
                    objOut.writeObject(response);
                }
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
