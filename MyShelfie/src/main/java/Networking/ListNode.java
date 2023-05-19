package Networking;

import Messages.PingMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ListNode {

    private Socket socket;

    private ServerManager manager;

    private ObjectOutputStream writer;

    private boolean Ok;

    public ListNode(Socket arg1, ServerManager arg2, ObjectOutputStream arg3) {
        this.socket = arg1;
        this.manager = arg2;
        this.writer = arg3;
        this.Ok = true;}

    /**
     * Overview: writer getter
     */
    public ObjectOutputStream getWriter() {
        return writer;
    }
    /**
     * Overview: Ok getter
     */
    public boolean getOk() {
        return this.Ok;
    }

    /**
     * Overview: Ok setter
     */
    public void setOk() {
        this.Ok = true;
    }

    /**
     * Overview: manager setter
     */
    public void setManager(ServerManager arg) {
        this.manager = arg;
    }

    /**
     * Overview: manager getter
     */
    public ServerManager getManager() {
        return this.manager;
    }

    /**
     * Overview: resets Ok
     */
    public void resetOk() {
        this.Ok = false;
    }

    /**
     * Overview: sends ping messages
     * @throws IOException
     */
    public void send() throws IOException {
        writer.writeObject(new PingMessage("ping", "server"));
    }

    /**
     * Overview: closes streams then closes socket
     */
    public void close() throws IOException {

        // delete references
        manager.getLobbyview().getObs().getModel().removePlayer(manager);
        this.socket.close();
    }

    /**
     * Overview: socket setter
     */
    public void setSocket(Socket arg) {this.socket = arg;};

    /**
     * Overview: writer setter
     */
    public void setWriter(ObjectOutputStream arg) {this.writer = arg;}
}
