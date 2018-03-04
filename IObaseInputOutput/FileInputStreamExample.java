package day16.IObaseInputOutput;

import java.io.FileInputStream;

public class FileInputStreamExample {
	public static void main(String ar[]){
		try{
			FileInputStream fis=new FileInputStream("C:\\java\\eclipse\\"
		+ "workspace\\java-study\\src\\day16\\IObaseInputOutput\\FileInputStreamExample.java");
			int data;
			while((data=fis.read()) != -1){
				System.out.write(data);
			}
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
