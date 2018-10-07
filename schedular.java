import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class schedular {

    static void displayProcess(PCB pcb1){
        if(pcb1.estRemainTime <= 199){
            System.out.println("Scheduler: Process " + pcb1.pid + " "+pcb1.program_name +" "+"Completed.");
        }
    }

    public static void main(String[] args){

        //create the queue
        LinkedList<PCB> queue = new LinkedList<>();

        //load up the queue
        try (BufferedReader br = new BufferedReader(new FileReader("processes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.offer(new PCB(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{

            while(queue.peek() != null){
                Socket sock = new Socket("127.0.0.1", 6022);

                ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());

                oos.writeObject(queue.poll());

                ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

                PCB pcbCaughtFromServer = (PCB) ois.readObject();

                sock.close();

                displayProcess(pcbCaughtFromServer);

                if(pcbCaughtFromServer.estRemainTime >= 201){
                    queue.offer(pcbCaughtFromServer);
                }
            }
        }
        catch (IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
