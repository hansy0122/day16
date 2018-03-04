package day16.IObaseInputOutput;
	import java.io.*;
	
public class FileReaderExample {
	
	public static void main(String ar[]) throws Exception{
		FileReader fr=new FileReader("C:\\java\\eclipse\\workspace\\java-study\\src\\day16\\IObaseInputOutput\\FileReaderExample.java");
		int readCharNo;
		char[] cbuf =new char[100];
		while((readCharNo=fr.read(cbuf)) != -1){
			String data=new String(cbuf,0,readCharNo);
			System.out.print(data);
		}
		fr.close();	
	}
}
