import java.util.ArrayList;
import java.util.Scanner;

class CpuAlgorithm {
    public static String[] columnTitle;
    public static Scanner input;
    public static ArrayList<Process> processList;

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

        public void ganttChartDisplayLayout() {
            System.out.print("|   " + ProcessName + "  | ");
        }

        public void ganttChartDisplayValue() {
            System.out.print(processInfo[2] + "       " + processInfo[3] + " ");
        }
    }

    public static class FCFS {
        /*
         * Calculate Wait Time,Turn Around Time
         */
        FCFS(){
            columnTitle[1] = "Arrival Time";
        }
        public void calculateMethod() {
            int waitTime = 0;
            int TurnAroundTime = 0;
            for (int i = 0; i < processList.size(); i++) {
                waitTime = TurnAroundTime - processList.get(i).processInfo[0];
                TurnAroundTime = waitTime + processList.get(i).processInfo[1];
                processList.get(i).processInfo[2] = waitTime;
                processList.get(i).processInfo[3] = TurnAroundTime;
            }
        }

        public void menu() {
            boolean flag = true;
            char switchChoice;
            while (flag) {
                System.out.println();
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.print("Enter Choice:");
                switchChoice = input.next().charAt(0);
                System.out.println();
                switch (switchChoice) {
                    case '1':
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
                    case '2':
                        System.out.println();
                        for (String title : columnTitle) {
                            System.out.print(title + "        ");
                        }
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).displayProcess();
                        }
                        break;
                    case '3':
                        calculateMethod();
                        break;
                    case '4':
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayLayout();
                        }
                        System.out.println();
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayValue();
                        }
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

        public void menu() {
            boolean flag = true;
            char switchChoice;
            while (flag) {
                System.out.println();
                ;
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.print("Enter Choice:");
                switchChoice = input.next().charAt(0);
                System.out.println();
                switch (switchChoice) {
                    case '1':
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
                    case '2':
                        System.out.println();
                        for (String title : columnTitle) {
                            System.out.print(title + "        ");
                        }
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).displayProcess();
                        }
                        break;
                    case '3':
                        sortArrayList(1);
                        calculateMethod();
                        break;
                    case '4':
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayLayout();
                        }
                        System.out.println();
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayValue();
                        }
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
            int waitTime = 0;
            int TurnAroundTime = 0;
            for (int i = 0; i < processList.size(); i++) {
                waitTime = TurnAroundTime;
                TurnAroundTime = waitTime+processList.get(i).processInfo[1];
                processList.get(i).processInfo[2] = waitTime;
                processList.get(i).processInfo[3] = TurnAroundTime;
            }
        }
        public void menu() {
            boolean flag = true;
            char switchChoice;
            while (flag) {
                System.out.println();
                ;
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.print("Enter Choice:");
                switchChoice = input.next().charAt(0);
                System.out.println();
                switch (switchChoice) {
                    case '1':
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
                    case '2':
                        System.out.println();
                        for (String title : columnTitle) {
                            System.out.print(title + "        ");
                        }
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).displayProcess();
                        }
                        break;
                    case '3':
                        sortArrayList(0);
                        calculateMethod();
                        break;
                    case '4':
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayLayout();
                        }
                        System.out.println();
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayValue();
                        }
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
            qutantumTime =0;
        }
        public void calculateMethod() {
            int waitTime = 0;
            int TurnAroundTime = 0;
            for (int i = 0; i < processList.size(); i++) {
                waitTime = TurnAroundTime;
                TurnAroundTime = waitTime+processList.get(i).processInfo[1];
                processList.get(i).processInfo[2] = waitTime;
                processList.get(i).processInfo[3] = TurnAroundTime;
            }
        }
        public void menu() {
            boolean flag = true;
            char switchChoice;
            while (flag) {
                System.out.println();
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Output");
                System.out.println("3.Calculate");
                System.out.print("Enter Choice:");
                switchChoice = input.next().charAt(0);
                System.out.println();
                switch (switchChoice) {
                    case '1':
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
                            System.out.println("Enter Quantum Time:");
                            qutantumTime = input.nextInt();
                        }
                        break;
                    case '2':
                        System.out.println();
                        for (String title : columnTitle) {
                            System.out.print(title + "        ");
                        }
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).displayProcess();
                        }
                        break;
                    case '3':
                        sortArrayList(0);
                        calculateMethod();
                        break;
                    case '4':
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayLayout();
                        }
                        System.out.println();
                        for (int i = 0; i < processList.size(); i++) {
                            processList.get(i).ganttChartDisplayValue();
                        }
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
        columnTitle = new String[] { "Process No", "", "Burst Time", "Waiting Time", "Turn Around Time" };
        input = new Scanner(System.in);
        Priority priority = new Priority();
        priority.menu();
    }
}