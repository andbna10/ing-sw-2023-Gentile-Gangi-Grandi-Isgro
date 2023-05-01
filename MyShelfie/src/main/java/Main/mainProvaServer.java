package Main;

import Networking.Server;

import java.io.IOException;

public class mainProvaServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server(59091);
        server.start();
    }
}
