package Use_Serializable.Client;


import Use_Serializable.Server.Friend;

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

            String searchWord; // User searchword

            System.out.println("Server: " + objIn.readObject());

            while ((searchWord = keyBoardIn.nextLine()) != null) {
                out.println(searchWord);
                Friend friend = (Friend)objIn.readObject();
                if(friend.getName().equals("ERROR-404")) System.out.println("Återfanns ej i databasen: " + searchWord);
                else System.out.println(friend.getData());
            }

        }catch(ClassNotFoundException e){
            System.out.println("Kunde inte konvertera classen \"friend\"");
        }catch(NoSuchElementException e){
            System.out.println("Du kopplades ner:");
        }catch(IOException e){
            System.out.println("Gick ej att upprätta en uppkoppling mot servern:");
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client start = new Client();
    }
}
