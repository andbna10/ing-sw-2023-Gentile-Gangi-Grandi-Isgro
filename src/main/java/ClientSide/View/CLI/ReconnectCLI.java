package ClientSide.View.CLI;

import ClientSide.NetworkHandler.ReconnectHandler;

import java.util.Scanner;

public class ReconnectCLI {

    private ReconnectHandler handler;

    /**
     * Overview: constructor
     */
    public ReconnectCLI(ReconnectHandler arg) {
        this.handler = arg;
    }

    /**
     * Overview: launches player reconnection procedure
     * @author Mirko Gentile
     */
    public void procedure() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("what's your username?\n");

        String input = scanner.nextLine();

        handler.sendpostuserreconmessage(input);
    }

}
