import java.util.Scanner;
import java.io.*; 
import java.util.*; 
class ReadFile{
    public static File fileObj;
    public static Scanner fileScanner;
    public ReadFile(String path) throws FileNotFoundException{
        fileObj = new File(path);
        fileScanner = new Scanner(fileObj);
    }
    public String readFileLine(){
        return (fileScanner.hasNextLine()) ? fileScanner.nextLine() : null;
    }
    public static void writeFileLine(String line){
    }
}
class Pass1 {
    public static int locationCounter;
    public static ArrayList<String[]> symTab;
    public static ArrayList<String[]> opTab;
    public static ArrayList<String[]> litTab;
    public static ArrayList<String[]> poolTab;

    public static void main(String[] args) throws FileNotFoundException
    {
        symTab = new ArrayList<>();
        opTab = new ArrayList<>();
        litTab = new ArrayList<>();
        poolTab = new ArrayList<>();

        ReadFile rf = new ReadFile("./TOC/sample.txt");
        Boolean flag = true;
        while(flag){
            String readLine = rf.readFileLine();
            if(readLine==null){
                flag=false;
            }
            else{
                classifyLine(readLine);
            }
        }
    }
    public static void classifyLine(String inputLine){
        ArrayList<String> temp;
        int tempSize;
        temp = filterStringArray(inputLine.split(" "));
        tempSize = temp.size();
        if(tempSize==3){
            if(temp.contains("DS")){
                System.out.println(temp.toString());
            }
        }else if(tempSize==2){

        }
        else if(tempSize==1){
            
        }
    }
    public static ArrayList<String> filterStringArray(String[] stringArray){
        ArrayList<String> tempArray = new ArrayList<>();
        for (String string : stringArray) {
            if(!string.equals("")){
                tempArray.add(string);
            }
        }
        return tempArray;
    }
}
  