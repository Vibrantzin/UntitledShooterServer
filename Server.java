import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    private ServerSocket ss = null;
    public int port;

    ArrayList<ClientHandler> handlers = new ArrayList<ClientHandler>();

    public Server(int p) {
        port = p;
        try {
            ss = new ServerSocket(port);
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public ServerSocket getSS() {
        return ss;
    }

    public synchronized void addHandler(ClientHandler h) {
        handlers.add(h);
    }

    public synchronized void removeHandler(ClientHandler h) {
        handlers.remove(h);
    }

    public synchronized void broadcast(String data, ClientHandler sender) {
        for (ClientHandler h : handlers)
            if (h != sender) h.send(data);
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        AcceptLoop loop = new AcceptLoop(server);
        loop.start();
    }
}