import java.util.Scanner;
import java.util.Arrays;
class CRC{
		public static Scanner input;
		public static class TransmittedData{
			public  int[] divisor;
			public  int divisorLength;
			public  int[] remainder;
			public  int[] divident;
			public  int dividentLength;
			public  boolean senderFlag;
			TransmittedData(boolean flag){
				senderFlag = flag;
			}
			public void getData(){
				int count=0;
				System.out.println();
				System.out.print("Enter Divisor:");
				String divisorStr = input.next();
				divisorLength = divisorStr.length();
				divisor = new int[divisorLength];
				for(char ch:divisorStr.toCharArray()){
		        	if(ch == '0'){
		        		divisor[count] = 0;
		        	}else if(ch == '1'){
		        		divisor[count] = 1;
		        	}
		        	else{
		        		divisorLength--;
		        		continue;
		        	}
		        	count++;
		   		}
				remainder = new int[divisorLength];
				
				System.out.print("Enter Divident:");
				String dividentStr = input.next();
				dividentLength = dividentStr.length() + (divisorLength-1);
				divident = new int[dividentLength];
				
				count = 0;
				for(char ch:dividentStr.toCharArray()){
		        	if(ch == '0'){
		        		divident[count] = 0;
		        	}else if(ch == '1'){
		        		divident[count] = 1;
		        	}
		        	else{
		        		dividentLength--;
		        		continue;
		        	}
		        	count++;
		   		}
		   		
		   		if(senderFlag){
			   		for(int i=count;i<(count+divisorLength)-1;i++){
			   			divident[i] = 0;
			   		}	
		   		}else{
		   			for(int i=count;i<(count+divisorLength)-1;i++){
			   			divident[i] = remainder[count%dividentLength];
			   		}	
		   		}
			}
			
			public void displayData(){
				System.out.println();
				System.out.print("Divisor:");
				System.out.print(Arrays.toString(divisor)+"\n");
				System.out.print("Divident:");
				System.out.print(Arrays.toString(divident)+"\n");
				System.out.print("Remainder:");
				System.out.print(Arrays.toString(remainder)+"\n");
			}
			public void calculateData(){
				int index =0;
					if(index==0)
						CompareData(index,divident);
					else	
						CompareData(index,remainder);
					swapremainder();
					if(index<=(dividentLength-1)){
						remainder[divisorLength-1] = divident[(divisorLength-1)+index];
					index++;
					}
				
			}
			public void swapremainder(){
				int temp;
				for(int i=0;i<divisorLength-1;i++){
					remainder[i] = remainder[i+1];
				}
			}
			public void CompareData(int start,int[] divident){
			int count=0;
			int i = start;
			while(count!=(divisorLength-1)){
				if(divident[start] == divisor[count]){
					remainder[count] = 0;
				}
				else{
					remainder[count] = 1;
				}
				count++;
				start++;
				}
			}
	}
	public static void main(String[] args){
		TransmittedData sender = new TransmittedData(true);
		TransmittedData receiver = new TransmittedData(false);
		input = new Scanner(System.in);
		sender.getData();
		sender.calculateData();
		sender.displayData();
		input.close();
	}
}
