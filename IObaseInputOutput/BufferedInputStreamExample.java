package day16.IObaseInputOutput;
import java.io.*;
public class BufferedInputStreamExample {
	public static void main(String ar[]) throws Exception{
		long start=0;
		long end=0;
		
		FileInputStream fis1=new FileInputStream("C:\\Temp\\Kahn_Bentley_GTS_1024_x_768.jpg");
		start=System.currentTimeMillis();
		while(fis1.read()!=-1){}
		end=System.currentTimeMillis();
		
		System.out.println("FileInputStream Time:"+(end-start)+"ms");
		fis1.close();
		
		FileInputStream fis2=new FileInputStream("C:\\Temp\\Kahn_Bentley_GTS_1024_x_768.jpg");
		BufferedInputStream bis=new BufferedInputStream(fis2);
		
		start=System.currentTimeMillis();
		while(bis.read()!=-1){}
		end=System.currentTimeMillis();
		
		System.out.println("BuffedInputStream Time:"+(end-start)+"ms");
		bis.close();
	}
}
