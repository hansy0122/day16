package day16.IObaseInputOutput;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ClientExample {
	public static void main(String ar[]){
		Socket socket=null;
		try{
			socket =new Socket();
			System.out.println("[연결 요청]");
			socket.connect(new InetSocketAddress("localhost",5001));
			System.out.println("[연결 성공]");
			
			byte[] bytes=null;
			String message=null;
			
			OutputStream os=socket.getOutputStream();
			message ="Hello server";
			bytes=message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("[data output success");
			
			InputStream is= socket.getInputStream();
			bytes=new byte[100];
			int readByteCount=is.read(bytes);
			message =new String(bytes,0,readByteCount,"UTF-8");
			System.out.println("[data input success ->"+message);
			
			os.close();
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(!socket.isClosed()){
			try{
				socket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
