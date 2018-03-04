package day16.IObaseInputOutput;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
	public static void main(String ar[]){
		try{
			InetAddress local =InetAddress.getLocalHost();
			System.out.println("my ip:"+ local.getHostAddress());
			
			InetAddress[] iaArr=InetAddress.getAllByName("www.naver.com");
			for(InetAddress remote: iaArr){
				System.out.println("naver ip:"+ remote.getHostAddress());
			}
			
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}
}
