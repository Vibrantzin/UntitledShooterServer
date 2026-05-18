import java.net.*;
import java.io.*;
import java.util.*;

public class Server{

    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;
    public int port;

    ArrayList<Socket> clients = new ArrayList<Socket>();
    ArrayList<DataInputStream> inputs = new ArrayList<DataInputStream>();

    public Server(int p) {
        port = p;
        try{
            ss = new ServerSocket(port);


        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public ServerSocket getSS(){
       return ss;
    }
    public void addClient(Socket sock, DataInputStream Shoe){
      clients.add(sock);
      inputs.add(Shoe);

    }
}
