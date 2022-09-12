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
    public static ArrayList<Integer> affectedBlock;
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
            affectedBlock.clear();
            blockMemoryTemp.addAll(blockMemory);
            for(Map.Entry<String,Process> process : processList.entrySet()){
                index = 0;
                for(int blockValue : blockMemoryTemp){
                    if(process.getValue().processInfo[0]<=blockValue){
                        affectedBlock.add(index);
                        process.getValue().processInfo[1] = index;
                        process.getValue().processInfo[2] =  blockValue-process.getValue().processInfo[0];
                        blockMemoryTemp.set(index, process.getValue().processInfo[2]);
                        break;
                    }
                    index++;
                }
            }
        }
        void output(){
            /* 
                Calling Display Block Memory Function
            */
            System.out.println();
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
            /* Unallocated MemoryBlock*/
            System.out.print("Unallocated Blocks are : ");
            unallocatedMemoryBlocks(affectedBlock);
                        
        }
        void menu(){
            boolean menuLoop = true;
            int menuSwitch;
            while(menuLoop){
                System.out.println("----------- First Fit -----------");
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
            System.out.println("----------- Exiting First Fit -----------");
        }
    }
    static class NextFit extends FirstFit{
        NextFit(){
        }
        void calculateMethod(){
           int index=0;
           boolean flag = false;
           int count;
            ArrayList<Integer> blockMemoryTemp = new ArrayList<>();
            affectedBlock.clear();
            blockMemoryTemp.addAll(blockMemory);
            for(Map.Entry<String,Process> process : processList.entrySet()){
                count = 1;
                index = index % (blockMemoryTemp.size() -1);
                while(count!=blockMemoryTemp.size()){
                    if(process.getValue().processInfo[0]<=blockMemoryTemp.get(index)){
                        affectedBlock.add(index);
                        process.getValue().processInfo[1] = index;
                        process.getValue().processInfo[2] =  blockMemoryTemp.get(index)-process.getValue().processInfo[0];
                        blockMemoryTemp.set(index, process.getValue().processInfo[2]);
                        index ++;
                    }
                    count ++;
                    index ++;
                }
            }
            }
        void menu(){
            boolean menuLoop = true;
            int menuSwitch;
            while(menuLoop){
                System.out.println("----------- Next Fit -----------");
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
            System.out.println("----------- Exiting Next Fit -----------");
        }
    }
    static class WorstFit extends FirstFit{
        WorstFit(){
        }
        void calculateMethod(){
            ArrayList<Integer> blockMemoryTemp = new ArrayList<>();
            affectedBlock.clear();

            blockMemoryTemp.addAll(blockMemory);
            for(Map.Entry<String,Process> process : processList.entrySet()){
                    //Calculating Max Index Value
                    int maxIndex = maxBlockIndex(blockMemoryTemp, process.getValue().processInfo[0]);
                    if(process.getValue().processInfo[0]<=blockMemoryTemp.get(maxIndex)){
                        process.getValue().processInfo[1] = maxIndex;
                        process.getValue().processInfo[2] =  blockMemoryTemp.get(maxIndex)-process.getValue().processInfo[0];
                        affectedBlock.add(maxIndex);
                        blockMemoryTemp.set(maxIndex, process.getValue().processInfo[2]);
                    }
                }
            }
        void menu(){
            boolean menuLoop = true;
            int menuSwitch;
            while(menuLoop){
                System.out.println("----------- Worst Fit -----------");
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
            System.out.println("----------- Exiting Worst Fit -----------");
        }
        static int maxBlockIndex(ArrayList<Integer> blockMemory,int memorySize){
            int maxIndex=0;
            int index=0;
            for(int blockValue:blockMemory){
                if(blockValue>blockMemory.get(maxIndex)){
                    maxIndex=index;
                }
                index++;
            }
            return maxIndex;
        }
    }
    static class BestFit extends FirstFit{
        BestFit(){
        }
        void calculateMethod(){
            int minIndex;
            ArrayList<Integer> blockMemoryTemp = new ArrayList<>();
            affectedBlock.clear();
            blockMemoryTemp.addAll(blockMemory);
            for(Map.Entry<String,Process> process : processList.entrySet()){
                    //Calculating Max Index Value
                    minIndex = minBlockIndex(blockMemoryTemp, process.getValue().processInfo[0]);
                    if(minIndex!=-1){
                        process.getValue().processInfo[1] = minIndex;
                        process.getValue().processInfo[2] =  blockMemoryTemp.get(minIndex)-process.getValue().processInfo[0];
                        affectedBlock.add(minIndex);
                        blockMemoryTemp.set(minIndex, process.getValue().processInfo[2]);
                    }
                }
            }
        void menu(){
            boolean menuLoop = true;
            int menuSwitch;
            while(menuLoop){
                System.out.println("----------- Best Fit -----------");
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
            System.out.println("----------- Exiting Best Fit -----------");
        }
        static int minBlockIndex(ArrayList<Integer> blockMemoryTemp,int memorySize){
            int minIndex=-1;
            int index=0;
            for(int blockValue:blockMemoryTemp){
                if(blockValue>=memorySize){
                    if(minIndex==-1)
                        minIndex = index;
                    else if(blockMemoryTemp.get(minIndex) > blockValue)
                        minIndex = index;
                }
                index++;
            }
            return minIndex;
        }
    }
    /* Outputing Unallocated Memory*/
    static void unallocatedMemoryBlocks(ArrayList<Integer> allocatedMemoryBlock){
        boolean flag=true;
        for(int i=0;i<blockMemory.size();i++){
            if(!allocatedMemoryBlock.contains(i)){
                flag = false;
                System.out.print(i+" ");
            }
        }
        if(flag){
            System.out.print("No Memory Block Used");
        }
        System.out.println();
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
        NextFit nextFit = new NextFit();
        WorstFit worstFit = new WorstFit();
        BestFit bestFit = new BestFit();
        affectedBlock = new ArrayList<>();
        columnTitle = new String[]{"Process Name","Process Size","Memory Block","Memory Holes"};
        Boolean mainLoop = true;
        int innerSwitch;
        input = new Scanner(System.in);
        while(mainLoop){
            /* ------Menu------ */
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1.First");
            System.out.println("2.Next");
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
                    nextFit.menu();
                    break;
                case 3:
                    worstFit.menu();
                    break;
                case 4:
                    bestFit.menu();
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