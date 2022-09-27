import java.util.Scanner;
import java.util.Arrays;
class CRC{
		public static Scanner input;
		public  static int[] divisor;
		public  static int divisorLength;
		public  static int[] remainder;
		public  static  int[] divident;
		public  static int dividentLength;
		public static class TransmittedData{
			
			public  boolean senderFlag;
			TransmittedData(boolean flag){
				senderFlag = flag;
			}
			public void getData(){
				if(senderFlag){
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
				initremainder();
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
		   		}
			}else
			{
				int length = remainder.length;
				int templen = dividentLength-1;
				while(length>0){
					divident[templen] = remainder[length-1];
					templen--;
					length--; 
				}
				initremainder();
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
					int index= divisorLength;
					int[] tempremainder = new int[index];
					CompareData(divident);
					while(index<=dividentLength-1){
						swapremainder(divident[index]);
						tempremainder = copyremainder();
						CompareData(tempremainder);
						index++;
						Arrays.toString(remainder);
					}
			}
			public int[] copyremainder(){
				int[] copyremainder = new int[divisorLength];
				for(int i=0;i<divisorLength;i++){
					copyremainder[i] = remainder[i];
				}
				return copyremainder;
			}
			public void swapremainder(int appendData){
				for(int i=0;i<divisorLength-1;i++){
					remainder[i] =remainder[i+1];
				}
				remainder[divisorLength-1] = appendData;
			}
			public void initremainder(){
				for(int i=0;i<divisorLength;i++){
					remainder[i] = -1;
				}
			}
			public void addErrorCode(){
				System.out.println("Enter Transmitted Code");
				for(int i=0;i<dividentLength;i++){
					divident[i] = input.nextInt();
				}
			}
			public boolean checkError(){
				for(int i=0;i<divisorLength;i++){
					if(remainder[i]!=0){
						return false;
					}
				}
				return true;
			}
			public void CompareData(int[] divident){
			int count=0;
			int i=0;
			if(remainder[0]==0){
				while(count<=(divisorLength-1)){
					if(divident[i] == 0){
						remainder[count] = 0;
					}
					else{
						remainder[count] = 1;
					}
					count++;
					i++;
					}
			}
			else{
				while(count<=(divisorLength-1)){
					if(divident[i] == divisor[count]){
						remainder[count] = 0;
					}
					else{
						remainder[count] = 1;
					}
					count++;
					i++;
					}
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
		receiver.getData();
		System.out.println("Do you want to manipulate Data:");
		char choice = input.next().toLowerCase().charAt(0);
		if(choice=='y'){
			receiver.addErrorCode();
		}
		receiver.calculateData();
		receiver.displayData();
		if(!receiver.checkError()){
			System.out.println("\nError Found");
		}else{
			System.out.println("\nNo Error Found");
		}
		input.close();
	}
}
