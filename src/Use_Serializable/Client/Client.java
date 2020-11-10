package Use_Serializable.Client;


import Use_Serializable.Server.Friend;
import Use_Serializable.Server.Intro;
import Use_Serializable.Server.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private int port = 55555;
    private InetAddress iAdr;
    private Scanner keyBoardIn = new Scanner(System.in);

    public Client() throws UnknownHostException {
        iAdr = InetAddress.getLocalHost();

        try(Socket socketToServer = new Socket(iAdr,port);
            PrintWriter out = new PrintWriter(socketToServer.getOutputStream(),true);
            ObjectInputStream objIn = new ObjectInputStream(socketToServer.getInputStream())) {

            Intro intro = (Intro)objIn.readObject();
            System.out.println(intro.getMessage());

            String searchWord; // User searchword
            System.out.println("Ange fullständigt namn att söka på:");

            while ((searchWord = keyBoardIn.nextLine()) != null) {
                out.println(searchWord);
                Response response = (Response)objIn.readObject();

                if(response.getContainsFriend()){
                    System.out.println(response.getFriend().getData());
                }
                else System.out.println("Återfanns ej i databasen: " + searchWord);

//                if(obj instanceof Friend){
//                    Friend friend = (Friend)obj;
//                    System.out.println(friend.getData());
//                }
//                else System.out.println("Återfanns ej i databasen: " + searchWord);
            }

        }catch(ClassNotFoundException e){
            System.out.println("Kunde inte konvertera classen \"friend\"");
        }catch(NoSuchElementException e){
            System.out.println("Du kopplades ner:");
        }catch(IOException e){
            System.out.println("Gick ej att upprätta en uppkoppling mot servern:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client start = new Client();
    }
}
