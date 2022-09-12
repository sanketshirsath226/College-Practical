import java.util.Scanner;

public class PageReplacement {
    public static Scanner input;
    public static String[] frames;
    public static int frameSize;
    public static int pageHits;
    public static int pageFaults;
    public static int[] recentlyOrNotRecentlyUsed;
    public static String[] references;
    static void input(){
            // Flush the Previous Input
            input.nextLine();
            System.out.print("Enter Reference String:");
            references = input.nextLine().split(",");
            System.out.print("Enter No of Frames:");
            frameSize = input.nextInt();
            frames = new String[frameSize];
            recentlyOrNotRecentlyUsed = new int[frameSize];
    }
    static void output(String reference){
        System.out.print(reference+" ");
        for(String frame: frames){
            System.out.print("|"+frame);
        }
        System.out.println("|");
    }
    static class Fifo{
        public int frontptr;
        Fifo(){ 
        }
        int checkEmptySpace(){
            int spaceIndex = -1;
            int count = 0;
            for(String frame: frames){
                if(frame == null){
                    spaceIndex = count;
                    break;
                }
                count++;
            }
            return spaceIndex;
        }

        boolean PageHit(String referenceFrame){
            boolean flag=false;
            for(String frame:frames){
                if(frame!=null && frame.equals(referenceFrame)){
                    flag=true;
                    break;
                }
            }
            return flag;
        }

        int FirstInserted(){
            frontptr++;
            frontptr = frontptr % frameSize;
            return frontptr;
        }
        void calculate(){
            frontptr = -1;
            pageFaults = 0;
            pageHits = 0;
            int SpaceIndex;
            int replaceIndex;
            resetFrames();
            for(String reference:references){
                SpaceIndex = checkEmptySpace();
                if(PageHit(reference)){
                    pageHits++;
                }else if(SpaceIndex!=-1){
                    frames[SpaceIndex] = reference;
                    pageFaults++;
                }else {
                    replaceIndex = FirstInserted();
                    frames[replaceIndex] = reference;
                    pageFaults++;
                }
                output(reference);
            }
            display();
        }
        void display(){
            System.out.println("Page Reference String Length:"+references.length);
            System.out.println("Page Hits :"+pageHits);
            System.out.println("Page Faults :"+pageFaults);
            System.out.println();
        }
    }
    static int recentlyUsedOrNotUsed(boolean flag,int index){
        int count=0;
        if(flag){
            for(String frame : frames){
                for(int i=index-1;i>=0;i--){
                    if(frame.equals(references[i])){
                        recentlyOrNotRecentlyUsed[count] = i;
                        break;  
                      }
                      recentlyOrNotRecentlyUsed[count] = -1;
                }
                count ++ ;
            }
            count = 0;
            int recentIndex = count;
            for(int recent:recentlyOrNotRecentlyUsed){
                if(recent == -1){
                    recentIndex = count;
                    break;
                }
                if(recentlyOrNotRecentlyUsed[recentIndex]>recent){
                    recentIndex = count;
                }
                count++;
            }
            return recentIndex;
        }else{
            for(String frame : frames){
                for(int i=index+1;i<references.length;i++){
                    if(frame.equals(references[i])){
                      recentlyOrNotRecentlyUsed[count] = i;
                      break;  
                    }
                    recentlyOrNotRecentlyUsed[count] = -1;
                }
                count ++;
            }
            count = 0;
            int OptimalIndex = count;
            for(int recent:recentlyOrNotRecentlyUsed){
                if(recent == -1){
                    OptimalIndex = count;
                    break;
                }
                if(recentlyOrNotRecentlyUsed[OptimalIndex]<recent){
                    OptimalIndex = count;
                }
                count++;
            }
            return OptimalIndex;
        }

    }
    static class LRU extends Fifo{
        LRU(){
            super();
        }
        void calculate(){
            frontptr = -1;
            pageFaults = 0;
            pageHits = 0;
            int SpaceIndex;
            int replaceIndex;
            int count=0;
            resetFrames();
            for(String reference:references){
                SpaceIndex = checkEmptySpace();
                if(PageHit(reference)){
                    pageHits++;
                }else if(SpaceIndex!=-1){
                    frames[SpaceIndex] = reference;
                    pageFaults++;
                }else {
                    replaceIndex = recentlyUsedOrNotUsed(true,count);
                    frames[replaceIndex] = reference;
                    pageFaults++;
                }
                output(reference);
                count++;
            }
            display();
        }
    }
    static class Optimal extends Fifo{
        Optimal(){
            super();
        }
        void calculate(){
            frontptr = -1;
            int SpaceIndex;
            pageFaults = 0;
            pageHits = 0;
            int replaceIndex;
            int count=0;
            resetFrames();
            for(String reference:references){
                SpaceIndex = checkEmptySpace();
                if(PageHit(reference)){
                    pageHits++;
                }else if(SpaceIndex!=-1){
                    frames[SpaceIndex] = reference;
                    pageFaults++;
                }else {
                    replaceIndex = recentlyUsedOrNotUsed(false,count);
                    frames[replaceIndex] = reference;
                    pageFaults++;
                }
                output(reference);
                count++;
            }
            display();
        }
    }
    static void resetFrames(){
        for(int i=0;i<frameSize;i++){
            frames[i] = null;
        }
    }
    static boolean checkInputCondition(){
        try{
            String rough = references[0];
        }catch(NullPointerException n){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        input = new Scanner(System.in);
        Boolean mainLoop = true;
        int innerSwitch;
        Fifo fifo = new Fifo();
        LRU lru = new LRU();
        Optimal optimal = new Optimal();
        input = new Scanner(System.in);
        while(mainLoop){
            /* ------Menu------ */
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1.Input");
            System.out.println("2.FIFO");
            System.out.println("3.LRU");
            System.out.println("4.Optimal");
            System.out.println("5.Exit");
            System.out.print("Enter Choice:");
            /* ------------- */
            innerSwitch = input.nextInt();
            switch(innerSwitch){
                case 1:
                    input();
                    break;
                case 2:
                    if(!checkInputCondition()){
                        System.out.println("Please Provide Input");
                        break;
                    }
                    fifo.calculate();
                    break;
                case 3:
                    if(!checkInputCondition()){
                        System.out.println("Please Provide Input");
                        break;
                    }
                    lru.calculate();
                    break;
                case 4:
                    if(!checkInputCondition()){
                        System.out.println("Please Provide Input");
                        break;
                    }
                    optimal.calculate();
                    break;
                case 5:
                    mainLoop = false;
                    break;
                default:
                    System.out.println("Enter a Valid Choice");
                    break;
            }
        }
        System.out.println("Exiting......"); 
        input.close();
    }
}
