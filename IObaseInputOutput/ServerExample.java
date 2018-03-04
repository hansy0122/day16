package day16.IObaseInputOutput;

import java.net.*;
import java.io.*;
public class ServerExample {
	public static void main(String ar[]){
		ServerSocket serverSocket =null;
		
		try{
			serverSocket=new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));
			
			while(true){
				System.out.println("[연결 기다림]");
				Socket socket =serverSocket.accept();
				InetSocketAddress isa= (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함]"+ isa.getHostName());
				
				
				byte[] bytes=null;
				String message=null;
				
				InputStream is=socket.getInputStream();
				bytes=new byte[100];
				int readByteCount =is.read(bytes);
				message =new String(bytes,0,readByteCount,"UTF-8");
				System.out.println("data input Success -> "+message);
				
				OutputStream os= socket.getOutputStream();
				message="Hello Client";
				bytes=message.getBytes("UTF-8");
				os.write(bytes);
				System.out.println("data output Success  ");
				os.close();
				is.close();
				socket.close();
				
				
			}
		}catch(Exception e){e.printStackTrace();}
		
		if(!serverSocket.isClosed()){
			try{
				serverSocket.close();
			}catch(Exception e){e.printStackTrace();}
		}
	}
}
