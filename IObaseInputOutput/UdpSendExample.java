package day16.IObaseInputOutput;

import java.net.*;

public class UdpSendExample {
	public static void main(String ar[]) throws Exception{
		DatagramSocket datagramsocket=new DatagramSocket();
		
		System.out.println("[start sending]");
		
		for(int i=0;i<2;i++){
			String data="message"+i;
			byte[] byteArr=data.getBytes("UTF-8");
			DatagramPacket packet =new DatagramPacket(byteArr,byteArr.length,new InetSocketAddress("localhost",5001));
			
			datagramsocket.send(packet);
			System.out.println("Send Byte Count:" +byteArr.length);
		}
		System.out.println("[complete sending]");
		
		datagramsocket.close();
	}
}
