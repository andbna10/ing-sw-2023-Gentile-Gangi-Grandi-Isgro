package Client.NetworkHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket clientsocket;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * ClientHandler cosntructor
     */
    public ClientHandler(Socket clientsocket) throws IOException {
        this.clientsocket = clientsocket;
        this.reader = new BufferedReader(new InputStreamReader(this.clientsocket.getInputStream()));
        this.writer = new PrintWriter(this.clientsocket.getOutputStream(), true);
    }

    @Override
    /**
     * Overview: run del thread
     */
    public void run(){
        //
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
