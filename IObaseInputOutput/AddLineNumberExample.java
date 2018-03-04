package day16.IObaseInputOutput;


import java.io.*;

public class AddLineNumberExample {
	public static void main(String ar[]) throws Exception{
		String filePath="C:\\java\\eclipse\\workspace\\java-study\\src\\day07\\Pizza.java";
		
		File file=new File(filePath);
		FileReader fr=new FileReader(file);
		int re;
		int i=1;
		System.out.print(i+": ");
		while((re=fr.read()) != -1){
			System.out.print((char)re);
			if(re==10){
				System.out.print(i+1+": ");
				i++;
			}
		}
	}
}
