import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class cpuEmulator implements Serializable {


    public static void main(String[] args)  {

        try{
            ServerSocket sock = new ServerSocket(6022);

            while(true){
                //be always open to receiving
                Socket client = sock.accept();

                PrintWriter pout = new PrintWriter(client.getOutputStream(),true);

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

                PCB pcbCaught = (PCB) ois.readObject();

                PCB pcb1 = new PCB();

                pcb1.returnProcessToClient(pcbCaught);

                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                oos.writeObject(pcbCaught);

                oos.flush();
                client.close();
            }
                }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
