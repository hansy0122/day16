package day16.IObaseInputOutput;
import java.io.*;

public class BufferedOutputStreamExample {
	public static void main(String ar[]) throws Exception {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		
		int data=-1;
		long start=0;
		long end=0;
		
		fis=new FileInputStream("C:\\Temp\\Kahn_Bentley_GTS_1024_x_768.jpg");
		bis=new BufferedInputStream(fis);
		fos=new FileOutputStream("C:\\Temp\\DreamCar.jpg");
		start=System.currentTimeMillis();
		while((data=bis.read()) !=-1){
			fos.write(data);
		}
		fos.flush();
		end=System.currentTimeMillis();
		fos.close(); bis.close(); fis.close();
		System.out.println("FileOutputStream Time:"+(end-start)+"ms");
		
		fis=new FileInputStream("C:\\Temp\\Kahn_Bentley_GTS_1024_x_768.jpg");
		bis=new BufferedInputStream(fis);
		fos=new FileOutputStream("C:\\Temp\\DreamCar1.jpg");
		bos= new BufferedOutputStream(fos);
		
		start=System.currentTimeMillis();
		while((data=bis.read()) !=-1){
			bos.write(data);
		}
		bos.flush();
		end=System.currentTimeMillis();
		bos.close();fos.close(); bis.close(); fis.close();
		System.out.println("BUfferedOutputStream Time:"+(end-start)+"ms");
		
	}
}
