import java.io.Serializable;

public class PCB implements Serializable {

    String program_name = null;
    int pid = 0;
    String state = null;
    int priority = 0;
    int interruptible = 0;
    int estRunTime = 0;
    int estRemainTime = 0;

    PCB(){}

    void returnProcessToClient(PCB pcb){
        if(pcb.estRemainTime >= 201){
            pcb.estRemainTime= pcb.estRemainTime - 200;
        }
        System.out.println( "CPU: Exec "+ pcb.program_name + " "+ pcb.pid + " "+pcb.state+ " "+pcb.interruptible+ " "+pcb.estRunTime+ " "+pcb.estRemainTime );
    }

    PCB(String PCBString){

        String[] PCBValues = PCBString.split(",");

        program_name = PCBValues[0];
        pid = Integer.parseInt(PCBValues[1]);
        state = PCBValues[2];
        priority = Integer.parseInt(PCBValues[3]);
        interruptible = Integer.parseInt(PCBValues[4]);
        estRunTime = Integer.parseInt(PCBValues[5]);
        estRemainTime =  Integer.parseInt(PCBValues[6]);
    }
}
