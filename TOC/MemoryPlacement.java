import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class MemoryPlacement {
    public static HashMap<String,Process> processList;
    public static Scanner input;
    public static Process p;
    public static ArrayList<Integer> blockMemory;
    public static String[] columnTitle;
    static class Process{
        int[] processInfo; 
        Process(){
            processInfo = new int[3]; // processSize, processBlock, processMemoryHoles 
            processInfo[0] = -1;
            processInfo[1] = -1;
            processInfo[2] = -1;
        }
        void displayProcess(){
            for(int processValue : processInfo){
                System.out.print("                  "+processValue);
            }
        }
    }
    static class FirstFit{
        FirstFit(){
        }
        void input(){
            boolean innerSwitchLoop=true;
            String processName;
            int blockSize;
            int processSize;
            System.out.println();
            if(!(processList.isEmpty())){
                System.out.print("Do you want clear previous Input(Y/N)?:");
                if(input.next().toLowerCase().charAt(0) == 'y'){
                    processList.clear();
                }
            }
            while(innerSwitchLoop){
                System.out.print("Do you want add Process(Y/N)?:");
                if(input.next().toLowerCase().charAt(0) == 'n'){
                    innerSwitchLoop = false;
                }else{
                    System.out.print("Enter Process Name:");
                    processName = input.next().toLowerCase();
                    /* Checking Process Name Duplication */
                    if(processList.get(processName)!=null){
                        System.out.println("Error : Process name already exists...");
                        System.out.print("Do you want to change value of process(Y/N):");
                        if(input.next().toLowerCase().charAt(0)=='y'){
                            System.out.print("Enter Process Size:");
                            processSize = input.nextInt();
                            processList.get(processName).processInfo[0] = processSize;
                        }else{}
                    }else{
                        p = new Process();
                        System.out.print("Enter Process Size:");
                        processSize = input.nextInt();
                        p.processInfo[0] = processSize;
                        processList.put(processName,p);
                    }
                }
                System.out.println();
            }
            innerSwitchLoop = true;
            if(!blockMemory.isEmpty()){
                System.out.print("Do you want clear Memory Block(Y/N)?:");
                if(input.next().toLowerCase().charAt(0) == 'y'){
                    blockMemory.clear();
                }
            }
            /*Block Memory Input*/
            while(innerSwitchLoop){
                System.out.print("Do you want add Memory Block(Y/N)?:");
                if(input.next().toLowerCase().charAt(0) == 'n'){
                    innerSwitchLoop = false;
                }else{
                    System.out.print("Enter Block Memory Size:");                                
                    blockSize = input.nextInt();
                    blockMemory.add(blockSize);
                }
            }
        }
        void calculateMethod(){
            int index;
            ArrayList<Integer> blockMemoryTemp = new ArrayList<>();
            blockMemoryTemp.addAll(blockMemory);
            for(Map.Entry<String,Process> process : processList.entrySet()){
                index = 0;
                for(int blockValue : blockMemoryTemp){
                    if(process.getValue().processInfo[0]<=blockValue){
                        
                        process.getValue().processInfo[1] = index;
                        process.get
                    }
                    index++;
                }
            }
        }
        void output(){
            /* 
                Calling Display Block Memory Function
            */
            display();
            /*
                Displaying Columns
            */
            for(String title: columnTitle){
                System.out.print(title+"      ");  
            }
            System.out.println();
            /* Displaying Process Information*/
            for(Map.Entry<String,Process> processValue  : processList.entrySet()){
                System.out.print(processValue.getKey()); //getKey : get key from processValue
                /* 
                    Calling Process Display Function
                */
                processValue.getValue().displayProcess(); 
                System.out.println();
            }
                        
        }
        void menu(){
            boolean menuLoop = true;
            int menuSwitch;
            while(menuLoop){
                System.out.println();
                System.out.println("Menu:");
                System.out.println("1.Input");
                System.out.println("2.Calculate");
                System.out.println("3.Output");
                System.out.println("5.Exit");
                System.out.print("Enter Choice:");
                menuSwitch = input.nextInt();
                switch(menuSwitch){
                    case 1:
                        input();
                        break;
                    case 2:
                        calculateMethod();
                        break;
                    case 3:
                        output();
                        break;
                    case 4:
                        break;
                    case 5:
                        menuLoop = false;
                        break;
                    default:
                        break;
                    }
            }
            System.out.println("Exiting First Fit.....");
        }
    }
    static void display(){
        System.out.print("Block of Memory: ");
        if(blockMemory.isEmpty()){
            System.out.print("No Block of Memory Allocated");
        }else{
            for(int blockValue:blockMemory){
                System.out.print("| "+blockValue+" ");
            }
            System.out.print("|");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        processList = new HashMap<>();
        blockMemory = new ArrayList<Integer>();
        FirstFit firstfit = new FirstFit();
        columnTitle = new String[]{"Process Name","Process Size","Memory Block","Memory Holes"};
        Boolean mainLoop = true;
        int innerSwitch;
        input = new Scanner(System.in);
        while(mainLoop){
            /* ------Menu------ */
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1.First");
            System.out.println("2.New");
            System.out.println("3.Worst");
            System.out.println("4.Best");
            System.out.println("5.Exit");
            System.out.print("Enter Choice:");
            /* ------------- */
            innerSwitch = input.nextInt();
            switch(innerSwitch){
                case 1:
                    firstfit.menu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    mainLoop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Exiting......");
    input.close();
    }
}