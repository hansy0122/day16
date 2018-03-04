package day16.IObaseInputOutput;
import java.io.*;
public class FileOutputStreamExample  {
	public static void main(String ar[])throws Exception{
		String originalFile="C:\\Users\\HanSeungYeob\\Pictures\\untitled.png";
		String targetFile="C:\\Users\\HanSeungYeob\\Pictures\\stanford1.png";
		
		FileInputStream fis=new FileInputStream(originalFile);
		FileOutputStream fos=new FileOutputStream(targetFile);
		
		int readByteNo;
		byte[] readBytes=new byte[100];
		while((readByteNo=fis.read(readBytes))!=-1){
			fos.write(readBytes,0,readByteNo);
		}
		fos.flush();
		fos.close();
		fis.close();
		
		System.out.println("coplete copying");
	}
}
