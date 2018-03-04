package day16.IObaseInputOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileClientExample {
	public static void main(String ar[]) throws Exception{
		Socket socket =new Socket("localhost",5001);
		OutputStream os=socket.getOutputStream();
		
		String filepath="C:\\Temp\\house.png";
		File file=new File(filepath);
		
		String fileName=file.getName();
		byte[] bytes=new byte[100];
		bytes=fileName.getBytes();
		os.write(bytes);
		os.flush();
		
		
		System.out.println("Start file sending"+ fileName);
		FileInputStream fis=new FileInputStream(file);
		int re;
		while((re=fis.read(bytes))!= -1){
			os.write(bytes, 0, re);
		}
		System.out.println("end file sending"+ fileName);
		os.flush();
		fis.close();
		os.close();
		socket.close();
	}
}
