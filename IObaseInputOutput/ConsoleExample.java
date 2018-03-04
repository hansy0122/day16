package day16.IObaseInputOutput;

import java.io.Console;

public class ConsoleExample {
	public static void main(String ar[]){
		Console console=System.console();
		
		System.out.print("Id: ");
		String id=console.readLine();
		System.out.print("Password: ");
		char[] charPass=console.readPassword();
		String strPassword=new String(charPass); //char[] array->String
		
		System.out.println("-----------------------");
		System.out.println(id);
		System.out.println(strPassword);
	}
}
