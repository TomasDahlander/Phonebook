package Standard.Client;

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
            BufferedReader in = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()))) {

            String searchWord; // User searchword
            String messageFromServer; // Message from Standard.Client.Client.Server

            System.out.println("Standard.Client.Client.Server: " + in.readLine());

            while ((searchWord = keyBoardIn.nextLine()) != null) {
                out.println(searchWord);
                messageFromServer = in.readLine();
                System.out.println(messageFromServer);
            }

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
