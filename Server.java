import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private DatagramSocket socket;
    public int port;
    private Set<InetSocketAddress> clients = new HashSet<InetSocketAddress>();

    public Server(int p) throws SocketException {
        port = p;
        socket = new DatagramSocket(port);
    }

    public void run() throws IOException {
        byte[] buf = new byte[2048];
        System.out.println("UDP server listening on " + port);
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            InetSocketAddress sender = new InetSocketAddress(packet.getAddress(), packet.getPort());
            
            System.out.println("Got packet from " + sender + " (" + packet.getLength() + " bytes, " + clients.size() + " known clients)");
            
            synchronized (clients) { clients.add(sender); }

            byte[] data = new byte[packet.getLength()];
            System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());

            synchronized (clients) {
                for (InetSocketAddress c : clients) {
                    if (!c.equals(sender)) {
                        DatagramPacket out = new DatagramPacket(data, data.length, c.getAddress(), c.getPort());
                        socket.send(out);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Server(5000).run();
    }
}