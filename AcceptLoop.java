import java.net.*;
import java.io.*;
import java.util.*;
class AcceptLoop extends Thread{
  private ServerSocket ss = null;
  private DataInputStream in = null;

  public void run(Server s){
    try{

      System.out.println("Waiting for a client ...");
      while(true){
        ss = s.getSS();
        Socket c = ss.accept();
        System.out.println("Client accepted");

        in = new DataInputStream(
            new BufferedInputStream(c.getInputStream()));
        s.addClient(c, in);
      }
    }
    catch(IOException i){
            System.out.println(i);
    }

  }
}
