package day16.IObaseInputOutput;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class FileServerExample {
	public static void main(String ar[]) throws Exception{
		ServerSocket serverSocket=new ServerSocket();
		serverSocket.bind(new InetSocketAddress("localhost",5001));
		
		System.out.println("start server");
		
		while(true){
			try{
				Socket socket=serverSocket.accept();
				InputStream is=socket.getInputStream();
				
				byte[] bytes=new byte[100];
				int re=-1;
				
				re=is.read(bytes);
				
				if(re==-1){
					throw new IOException();
				}
				
				String filename=new String(bytes,0,re);
				filename=filename.trim();
				
				System.out.println("receiving file"+filename);
				FileOutputStream fos=new FileOutputStream("C:\\Temp\\copy.png");
				while((re=is.read(bytes))!=-1){
					fos.write(bytes, 0, re);
				}
				System.out.println("receiving file end"+filename);
				fos.close();
				is.close();
				socket.close();
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
		serverSocket.close();
		System.out.println("server close");
		
		
	}
}
