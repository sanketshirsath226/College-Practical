import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Rough {
    public static HashMap<String,int[]> ganttChart;
    public static void main(String[] args) {
        ganttChart = new HashMap<String,int[]>();
        ganttChart.put("Hello",new int[]{0,1});
    }
}
