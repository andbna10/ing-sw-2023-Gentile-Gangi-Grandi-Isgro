package Main;

import ClientSide.NetworkHandler.LoginHandler;
import Networking.ClientManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
public class mainProva2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 59091 );

        // this is the clientmanager which aim is to manage the connection client-server ( for the client )
        ClientManager client = new ClientManager(socket);

        // this is the login handler which will manage the login page of the new client connected
        LoginHandler loginhandler = new LoginHandler(client);


        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        System.out.println("I'm starting the heartbeat procedure - client");
        ScheduledExecutorService heartbeatProcedure = Executors.newSingleThreadScheduledExecutor();
        heartbeatProcedure.scheduleAtFixedRate(() ->{
            try {
                Boolean ok = client.heartbeat();
                if(!ok){
                    heartbeatProcedure.shutdown();
                }
            } catch (IOException e) {
                System.exit(1);
            }

        }, 0, 5, TimeUnit.SECONDS);

        System.out.println("dopo scheduler");

        //client.start();

    }
}

*/


