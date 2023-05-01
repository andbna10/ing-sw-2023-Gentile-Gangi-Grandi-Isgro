package Main;

import Client.NetworkHandler.LoginHandler;
import Networking.ClientManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class mainProva {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 59091 );){

            // this is the clientmanager which aim is to manage the connection client-server ( for the client )
            ClientManager client = new ClientManager(socket);

            // this is the login handler which will manage the login page of the new client connected
            LoginHandler loginhandler = new LoginHandler(client);

            System.out.println("I'm starting the heartbeat procedure - client");
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            ScheduledExecutorService heartbeatProcedure = Executors.newSingleThreadScheduledExecutor();
            heartbeatProcedure.scheduleAtFixedRate(() ->{
                //client.heartbeat();
                try {
                    synchronized (reader) {
                        String line = reader.readLine();
                        System.out.println(line + " is what I read");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }, 0, 5, TimeUnit.SECONDS);

            //client.start();


            // prova - cosi funziona
            /*InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("Client: start receiving");
            String line = reader.readLine();    // reads a line of text
            System.out.println(line);*/



        }
    }
}


