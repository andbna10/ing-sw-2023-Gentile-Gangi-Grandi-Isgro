package Networking;

import Messages.PingMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ListNode {

    private Socket socket;

    private ObjectOutputStream writer;

    private boolean Ok;

    public ListNode(Socket arg1, ObjectOutputStream arg2) {
        this.socket = arg1;
        this.writer = arg2;
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
        //this.socket.getInputStream().close();
        //this.socket.getOutputStream().close();
        this.socket.close();
    }

}
