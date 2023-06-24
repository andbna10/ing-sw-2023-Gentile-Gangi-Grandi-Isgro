package Networking;

import Messages.Message;
import Messages.PingMessage;
import Messages.fromClientToServer.PostUserReconMessage;
import Messages.fromServerToClient.ReconnectedMessage;
import Messages.fromServerToClient.SendDisconMessage;
import ServerSide.Controller.LobbyManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Server {
    private int port;
    private ServerSocket serversocket;
    private LobbyManager lobbymanager;

    private List<ListNode> socketList;

    private Boolean discon = false;

    private ListNode disconRef;

    private ListNode node;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private int counter = 0;

    /**
     * Overview: constructor of the server class aimed to construct the socket for communication server-client
     */
    public Server(int port){
        this.lobbymanager = new LobbyManager();
        this.port = port;
    }

    /**
     * Overview: method aimed to create a serversocket object on the specific port. it enters a loop to continuously listen for incoming clients connections.
     */
    public void start() throws IOException{
        socketList = new ArrayList<>();

        Thread.currentThread().setName("Server");


        //task to send ping messages, servermanager handles his client's ping feedback and sets flag ListNode.Ok
        /*
        new Thread(() -> {
            while(true) {

                //System.out.println("pinging "+ socketList.size());

                try {

                    Thread.sleep(6000);

                    // closing clients which did not answer the ping
                    Iterator<ListNode> iterator = socketList.iterator();
                    while (iterator.hasNext()) {
                        ListNode client = iterator.next();
                        if (!client.getOk()) {
                            iterator.remove();
                            client.close();
                        } else
                            client.resetOk();
                    }

                    // sending ping message to clients still connected
                    for (ListNode client : socketList) client.send();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
        */

        try{
            this.serversocket = new ServerSocket(port);
            serversocket.setSoTimeout(200000);
            System.out.println("The server is running on port "+this.port);

            // "while" loop for accepting client connections
            while(true){
                System.out.println("The server started listening");

                Socket clientsocket = serversocket.accept();

                counter++;

                out = new ObjectOutputStream(clientsocket.getOutputStream());
                in = new ObjectInputStream(clientsocket.getInputStream());

                SendDisconMessage tmp1 = new SendDisconMessage(discon);

                try {
                    this.out.writeObject(tmp1);
                    this.out.flush();
                } catch (IOException e) { throw new RuntimeException(e); }

                if(discon) {

                    try {
                        PostUserReconMessage tmp2 = (PostUserReconMessage) in.readObject();
                        if (tmp2.getUser().equals(disconRef.getManager().getUsername())) {

                            this.out.writeObject(new ReconnectedMessage(
                                    lobbymanager.getLobby(lobbymanager.getIdByUser(tmp2.getUser())).getModel().getUsernames()
                            ));
                            this.out.flush();

                            disconRef.setWriter(out);
                            disconRef.setSocket(clientsocket);

                            disconRef.getManager().resetSocket(clientsocket, out, in);

                            System.out.println("reconnecting player");

                            disconRef.getManager().sendLastMsg();

                            this.discon = false;
                            this.disconRef = null;
                            continue;
                        } else {

                            try {
                                this.out.writeObject(new SendDisconMessage(false));
                                this.out.flush();
                            } catch (IOException e) { throw new RuntimeException(e); }

                        }

                    } catch (Exception e) { e.printStackTrace(); }

                }

                System.out.println("A Client has just connected");

                node = new ListNode(clientsocket, null, out);

                // initialization of the manager whose aim is to manage the new client connection with the server ( for the server )
                ServerManager manager = new ServerManager(clientsocket, lobbymanager, node, this, in);

                node.setManager(manager);

                socketList.add(node);

                // here we start the thread aka manager with which client and server exchange messages
                manager.start();



            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Overview: discon setter
     */
    public void setDiscon(Boolean arg) {this.discon = arg;}

    /**
     * Overview: discon setter
     */
    public boolean getDiscon() {return this.discon;}

    /**
     * Overview: disconRef setter
     */
    public void setDisconRef(ListNode arg) {this.disconRef = arg;}

    /**
     * Overview: disconRef setter
     */
    public ListNode getDisconRef() {return this.disconRef;}


    /**
     * Overview: method aimed to close the server socket
     */
    public void stop() throws IOException{
        try{
            if(serversocket != null){
                serversocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Overview: counter getter
     */
    public int getCounter() {
        return this.counter;
    }
}
