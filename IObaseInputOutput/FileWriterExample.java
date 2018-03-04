package day16.IObaseInputOutput;
	import java.io.*;
public class FileWriterExample {
	public static void main(String ar[]) throws Exception{
		File file=new File("C:\\Temp\\nfile.txt");
		
		FileWriter fw=new FileWriter(file,true);
		fw.write("FileWriter can write"+"\r\n");
		fw.write("String imediately"+"\r\n");
		
		fw.flush();
		fw.close();
		System.out.println("file save");
	}
}
