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
            ObjectOutputStream objOut = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream objIn = new ObjectInputStream(socketToServer.getInputStream())) {

            Object searchWord; // User searchword
            Response response; // Response from Server

            response = (Response)objIn.readObject();
            System.out.println("Server: " + response.getIntro().getMessage());

            System.out.println("Client: Ange fullständigt namn att söka på:");
            System.out.print("Client: ");

            while ((searchWord = keyBoardIn.nextLine()) != null) {
                objOut.writeObject(searchWord);
                response = (Response)objIn.readObject();

                System.out.println("**********************************");

                if(response.getContainsFriend()){
                    System.out.println("Server:\n" + response.getFriend().getData());
                }
                else System.out.println("Server: Återfanns ej i databasen: " + searchWord);

                System.out.println("**********************************");

                System.out.print("Client: ");
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
