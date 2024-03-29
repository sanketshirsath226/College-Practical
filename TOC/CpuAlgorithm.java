import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

class CpuAlgorithm {
    public static String[] columnTitle;
    public static Scanner input;
    public static ArrayList<Process> processList;
    public static ArrayList<String[]> ganttChart;
    /*
     * Process Class
     * getProcess() : get Process Information
     * displayProcess() : display Process Information
     */
    public static class Process {
        public String ProcessName;
        public int[] processInfo; // processInfo[ArrivalTime, BurstTime, WaitingTime, TurnAroundTime]

        Process() {
            ProcessName = "";
            processInfo = new int[4];
        }

        public void getProcess() {
            System.out.println("\nEnter Process Info");
            System.out.print(columnTitle[0] + ":");
            ProcessName = input.next();
            for (int i = 0; i < 2; i++) {
                System.out.print(columnTitle[i + 1] + ":");
                processInfo[i] = input.nextInt();
            }
        }
        
        public void displayProcess() {
            System.out.print("\n" + ProcessName + "                ");
            for (int i = 0; i < processInfo.length; i++) {
                System.out.print(processInfo[i] + "                    ");
            }
        }
    }
    /*Gantt Chart*/
    public static void ganttChartDisplayValue(String[] processInfo) {
        System.out.print("| "+processInfo[1] + "->" + processInfo[0] +"->" + processInfo[2] + " | ");
    }
    public static void calculateAvg(){
        int avgWaitTime=0;
        int avgTatTime=0;
        for (int i = 0; i < processList.size(); i++) {
           avgWaitTime += processList.get(i).processInfo[2];
           avgTatTime  += processList.get(i).processInfo[3];
        }
        System.out.println();
        System.out.println("Average:");
        System.out.println("WaitTime:"+avgWaitTime/processList.size());
        System.out.println("TurnAroundTime:"+avgTatTime/processList.size());
    }
    public static void addToGanttChart(String name,int waitTime,int TurnAroundTime){
        ganttChart.add(new String[]{name,Integer.toString(waitTime),Integer.toString(TurnAroundTime)});
    }
    public static void outputScreen(){
        System.out.println();
        System.out.println("Gantt Chart:");
        System.out.print("| Arrival Time  -> ProcessName ->  Exit Time |\n");
        for(int i=0;i<ganttChart.size();i++){
            ganttChartDisplayValue(ganttChart.get(i));
        }
        System.out.println();
        System.out.println();
        for (String title : columnTitle) {
            System.out.print(title + "        ");
        }
        for (int i = 0; i < processList.size(); i++) {
            processList.get(i).displayProcess();
        }
        System.out.println();
        calculateAvg();
    }
    public static class FCFS {
        /*
         * Calculate Wait Time,Turn Around Time
         */
        FCFS(){
            columnTitle[1] = "Arrival Time";
        }
        public void calculateMethod() {
            ganttChart.clear();
            int waitTime = 0;
            int TurnAroundTime = 0;
            for (int i = 0; i < processList.size(); i++) {
                waitTime = TurnAroundTime - processList.get(i).processInfo[0] ;
                waitTime = (waitTime < 0) ? 0 : waitTime; 
                TurnAroundTime = waitTime + processList.get(i).processInfo[1] ;
                processList.get(i).processInfo[2] = waitTime;
                processList.get(i).processInfo[3] = TurnAroundTime;
                addToGanttChart(processList.get(i).ProcessName,waitTime,TurnAroundTime);
            }
        }

        public void menu() {
            boolean fcfsflag = true;
            int fcfsswitchChoice;
            while (fcfsflag) {
                System.out.println();
                System.out.println("------ FCFS ------");
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.println("5.Exit");
                System.out.print("Enter Choice:");
                fcfsswitchChoice = input.nextInt();
                System.out.println();
                switch (fcfsswitchChoice) {
                    case 1:
                        processList.clear();
                        boolean innerLoop = true;
                        while (innerLoop) {
                            Process p = new Process();
                            processList.add(p);
                            p.getProcess();
                            System.out.println("Do you want to add more?:");
                            if (input.next().toLowerCase().charAt(0) == 'n') {
                                innerLoop = false;
                            }
                        }
                        break;
                    case 2:
                        outputScreen();
                    case 3:
                        calculateMethod();
                        break;
                    case 5:
                        fcfsflag = false;
                        break;
                    default:
                        System.out.println("Entered Invalid Option");
                }
            }
        }
    }

    public static class SJF extends FCFS {
        SJF(){
            columnTitle[1] = "Arrival Time";
        }
    public void calculateMethod() {
            ganttChart.clear();
            int waitTime = 0;
            int TurnAroundTime = 0;
             for (int i = 0; i < processList.size(); i++) {
                waitTime = TurnAroundTime - processList.get(i).processInfo[0] ;
                waitTime = (waitTime < 0) ? 0 : waitTime; 
                TurnAroundTime = waitTime + processList.get(i).processInfo[1] ;
                processList.get(i).processInfo[2] = waitTime;
                processList.get(i).processInfo[3] = TurnAroundTime;
                addToGanttChart(processList.get(i).ProcessName,waitTime,TurnAroundTime);
            }
        }
        public void menu() {
            boolean sjfFlag = true;
            int sjfSwitchChoice;
            while (sjfFlag) {
                System.out.println();
                System.out.println("------ SJF ------");
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.println("5.Exit");
                System.out.print("Enter Choice:");
                sjfSwitchChoice = input.nextInt();
                System.out.println();
                switch (sjfSwitchChoice) {
                    case 1:
                        processList.clear();
                        boolean innerLoop = true;
                        System.out.println("Note: For Non-Preemptive ,let Arrival Time be zero");
                        while (innerLoop) {
                            Process p = new Process();
                            processList.add(p);
                            p.getProcess();
                            System.out.println("Do you want to add more?:");
                            if (input.next().toLowerCase().charAt(0) == 'n') {
                                innerLoop = false;
                            }
                        }
                        break;
                    case 2:
                        outputScreen();
                    case 3:
                        sortArrayList(1);
                        calculateMethod();
                        break;
                    case 4:
                        
                        break;
                    case 5:
                    sjfFlag = false;
                        break;
                    default:
                        System.out.println("Entered Invalid Option");
                }
            }
        }
    }

    public static class Priority {
        Priority(){
            columnTitle[1] = "Prority";
        }
        
        public void calculateMethod() {
            ganttChart.clear();
            int waitTime = 0;
            int TurnAroundTime = 0;
            for (int i = 0; i < processList.size(); i++) {
                waitTime = TurnAroundTime;
                TurnAroundTime = TurnAroundTime+processList.get(i).processInfo[1];
                processList.get(i).processInfo[2] = waitTime;
                processList.get(i).processInfo[3] = TurnAroundTime;
                addToGanttChart(processList.get(i).ProcessName,waitTime,TurnAroundTime);
            }
        }
        public void menu() {
            boolean priorityFlag = true;
            int prioritySwitchChoice;
            while (priorityFlag) {
                System.out.println();
                System.out.println("------ Priority ------");
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.println("5.Exit");
                System.out.print("Enter Choice:");
                prioritySwitchChoice = input.nextInt();
                System.out.println();
                switch (prioritySwitchChoice) {
                    case 1:
                        processList.clear();
                        boolean innerLoop = true;
                        while (innerLoop) {
                            Process p = new Process();
                            processList.add(p);
                            p.getProcess();
                            System.out.println("Do you want to add more?:");
                            if (input.next().toLowerCase().charAt(0) == 'n') {
                                innerLoop = false;
                            }
                        }
                        break;
                    case 2:
                         outputScreen();
                    case 3:
                        sortArrayList(0);
                        calculateMethod();
                        break;
                    case 5:
                        priorityFlag = false;
                        break;
                    default:
                        System.out.println("Entered Invalid Option");
                }
            }
        }
    }
    public static class RoundsRobin {
        public int qutantumTime;
        RoundsRobin(){
            columnTitle[1] = "Arrival Time";
            qutantumTime =0;
        }
        public void calculateMethod() {
            Boolean innerFlag=true;
            ganttChart.clear();
            int time=0;
            int[] remainingBurstTime = new int[processList.size()];
            for(int i=0;i<processList.size();i++){
                remainingBurstTime[i] = processList.get(i).processInfo[1];
            }
            while(innerFlag){
                innerFlag = false;
                for(int i=0;i<processList.size();i++){
                    if(remainingBurstTime[i]>0){
                        innerFlag = true;
                        if(remainingBurstTime[i]>qutantumTime){
                            addToGanttChart(processList.get(i).ProcessName,time,time+qutantumTime);
                            time+=qutantumTime;
                            remainingBurstTime[i] -= qutantumTime;
                        }else{
                            addToGanttChart(processList.get(i).ProcessName,time,time+remainingBurstTime[i]);
                            time = time + remainingBurstTime[i];
                            //Wait Time Calculation
                            processList.get(i).processInfo[2] = (time-processList.get(i).processInfo[1]);
                            remainingBurstTime[i] = 0;
                            //Turn Around Time Calculation
                            processList.get(i).processInfo[3] = (processList.get(i).processInfo[2]+processList.get(i).processInfo[1]);
                        }
                    }
                }
            }
        }
        public void menu() {
            boolean roundsRobinflag = true;
            int roundsRobinswitchChoice;
            while (roundsRobinflag) {
                System.out.println();
                System.out.println("------ Round Robin------");
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.println("5.Exit");
                System.out.print("Enter Choice:");
                roundsRobinswitchChoice = input.nextInt();
                System.out.println();
                switch (roundsRobinswitchChoice) {
                    case 1:
                        processList.clear();
                        boolean innerLoop = true;
                        System.out.println("Note: Let Arrival Time be zero");
                        while (innerLoop) {
                            Process p = new Process();
                            processList.add(p);
                            p.getProcess();
                            System.out.println("Do you want to add more?:");
                            if (input.next().toLowerCase().charAt(0) == 'n') {
                                innerLoop = false;
                            }
                        }
                        System.out.println("Enter Quantum Time:");
                        qutantumTime = input.nextInt();
                        break;
                    case 2:
                        outputScreen();
                    case 3:
                        calculateMethod();
                        break;
                    case 5:
                    roundsRobinflag = false;
                        break;
                    default:
                        System.out.println("Entered Invalid Option");
                }
            }
        }
    }
    /*Common Function to Sort ArrayList*/
    public static void sortArrayList(int index) {
        Process temp;
        for (int i = 0; i < processList.size(); i++) {
            for (int j = i; j < processList.size(); j++) {
                if (processList.get(i).processInfo[index] > processList.get(j).processInfo[index]) {
                    temp = processList.get(i);
                    processList.set(i, processList.get(j));
                    processList.set(j, temp);
                }
            }
        }
    }
    public static void main(String[] args) {
        processList = new ArrayList<>();
        FCFS fcfs;
        SJF sjf;
        Priority priority;
        RoundsRobin roundRobin;
        columnTitle = new String[] { "Process No", "", "Burst Time", "Waiting Time", "Turn Around Time" };
        input = new Scanner(System.in);
        int choice ;
        Boolean flag=true;
        ganttChart =  new ArrayList<String[]>();
        while(flag){
            System.out.println("Menu:");
            System.out.println("1.FCFS");
            System.out.println("2.SJF");
            System.out.println("3.Priority");
            System.out.println("4.Round Robin");
            System.out.println("6.Exit");
            System.out.println("Note:Calculate Option is used to calculate the waiting time and Turn Around Time.\nThus, After Input Option Select Calculate Option to Get Proper Output");
            System.out.print("Enter Choice:");
            choice = input.nextInt();
            switch(choice){
                case 1:
                fcfs = new FCFS();
                fcfs.menu();
                System.out.println("Exiting FCFS....");
                break;
                case 2:
                sjf = new SJF();
                sjf.menu();
                System.out.println("Exiting SJF....");
                break;
                case 3:
                priority = new Priority();
                priority.menu();
                System.out.println("Exiting Priority....");
                break;
                case 4:
                roundRobin = new RoundsRobin();
                roundRobin.menu();
                System.out.println("Exiting Round Robin....");
                break;
                case 6:
                flag = false;
                System.out.println("Exiting ....");
                break;
                default:
                System.out.println("Enter Valid Option");
            }
        }
    }
        
}