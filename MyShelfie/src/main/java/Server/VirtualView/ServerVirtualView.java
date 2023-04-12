package Server.VirtualView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerVirtualView {
    private Socket clientsocket;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * Overview: ServerVirtualView constructor
     */
    public ServerVirtualView(Socket clientsocket) throws IOException {
        this.clientsocket = clientsocket;
        this.reader = new BufferedReader(new InputStreamReader(this.clientsocket.getInputStream()));
        this.writer = new PrintWriter(this.clientsocket.getOutputStream(), true);
    }

    // metodi per inviare/ricevere messaggi

    /**
     * Overview: method aimed to close resources
     */
    public void close() throws IOException{
        reader.close();
        writer.close();
        clientsocket.close();
    }

    /**
     * Overview: hearthbeat method
     */
    public void hearthbeat() throws IOException {
        try{
            writer.println("ping");
            clientsocket.setSoTimeout(10000);
            String response = reader.readLine();
            if(response == null){
                clientsocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}
