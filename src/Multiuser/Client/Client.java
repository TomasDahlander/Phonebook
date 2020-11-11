package Multiuser.Client;

import Multiuser.Server.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private int port = 55555;
    private InetAddress iAdr;
    private Scanner keyBoardIn = new Scanner(System.in);

    public Client() {
        iAdr = InetAddress.getLoopbackAddress();

        try(Socket socketToServer = new Socket(iAdr,port);
            ObjectOutputStream objOut = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream objIn = new ObjectInputStream(socketToServer.getInputStream())){

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
                    System.out.println("Standard.Client.Client.Server:\n" + response.getFriend().getData());
                }
                else System.out.println("Standard.Client.Client.Server: Återfanns ej i databasen: " + searchWord);

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

    public static void main(String[] args) {
        Client start = new Client();
    }
}
