package day16.IObaseInputOutput;

import java.net.*;

public class UdpReceiveExample {
	public static void main(String ar[]) throws Exception{
		DatagramSocket datagramSocket =new DatagramSocket(5001);
		
		Thread thread =new Thread(){
			public void run(){
				System.out.println("Start receiving");
				try{
					while(true){
						DatagramPacket packet=new DatagramPacket(new byte[100],100);
						datagramSocket.receive(packet);
						
						String data=new String(packet.getData(),0,packet.getLength(),"UTF-8");
						System.out.println("[contents: "+ packet.getSocketAddress()+"]"+ data);
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("end receiving");
				}
			}
		};
		thread.start();
		Thread.sleep(10000);
		datagramSocket.close();
	}
}
