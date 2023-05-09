package Main;

public class CLITest {

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            Thread.sleep(500);
            System.out.print("---\r");
            Thread.sleep(250);
            System.out.print("----------\r");
            Thread.sleep(250);
            System.out.print("-------------------\r");
            Thread.sleep(2500);
            System.out.print("-------------------------------\r");
        }

    }
}
