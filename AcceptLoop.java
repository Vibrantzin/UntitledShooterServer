import java.net.*;
import java.io.*;

class AcceptLoop extends Thread {
    private Server server;

    public AcceptLoop(Server s) {
        this.server = s;
    }

    public void run() {
        try {
            System.out.println("Waiting for a client ...");
            while (true) {
                Socket c = server.getSS().accept();
                System.out.println("Client accepted");
                DataInputStream in = new DataInputStream(
                    new BufferedInputStream(c.getInputStream()));
                server.addClient(c, in);
            }
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}