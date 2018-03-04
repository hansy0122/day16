package day16.IObaseInputOutput;
import java.io.*;

public class SystemInExample2 {
	public static void main(String ar[])throws Exception{
		InputStream is=System.in;
		byte[] datas=new byte[100];
		
		System.out.print("name: ");
		int nameBytes=is.read(datas);
		String name=new String(datas,0,nameBytes-2);
		
		System.out.print("coment: ");
		int comentBytes=is.read(datas);
		String coment=new String(datas,0,comentBytes-2);
		
		System.out.println("name="+name);
		System.out.println("coment="+coment);
	}
}
