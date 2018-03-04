package day16.IObaseInputOutput;

import java.util.Scanner;

public class ScannerExample {
	public static void main(String ar[]){
		Scanner scanner =new Scanner(System.in);
		
		System.out.print("input String:");
		String inputString =scanner.nextLine();
		System.out.println(inputString);
		System.out.println();
		

		System.out.print("input Integer:");
		int inputInt =scanner.nextInt();
		System.out.println(inputInt);
		System.out.println();
		

		System.out.print("input Double:");
		double inputDouble =scanner.nextDouble();
		System.out.println(inputDouble);
		
		}
}
