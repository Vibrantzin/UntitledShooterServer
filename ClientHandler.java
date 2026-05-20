import java.net.*;
import java.io.*;

class ClientHandler extends Thread {
    private Socket sock;
    private Server server;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket s, Server srv) throws IOException {
        this.sock = s;
        this.server = srv;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
    }

    public void send(String data) {
        out.println(data);
    }

    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                server.broadcast(line, this);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + e);
        } finally {
            server.removeHandler(this);
            try { sock.close(); } catch (IOException ignored) {}
        }
    }
}