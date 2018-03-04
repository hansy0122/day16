package day16.IObaseInputOutput;
import java.io.*;
public class DataInputOutputStreamExample {
	public static void main(String ar[])throws Exception{
		FileOutputStream fos=new FileOutputStream("C:\\Temp\\primitive.dat");
		DataOutputStream dos=new DataOutputStream(fos);
		
		dos.writeUTF("Honggi Dong");
		dos.writeDouble(99.99);
		dos.writeInt(1);
		
		dos.writeUTF("Jeajun kim");
		dos.writeDouble(11.99);
		dos.writeInt(2);
		
		dos.flush();dos.close();fos.close();
		
		FileInputStream fis=new FileInputStream("C:\\Temp\\primitive.dat");
		DataInputStream dis=new DataInputStream(fis);
		
		for(int i=0;i<2;i++){
			String name =dis.readUTF();
			double score =dis.readDouble();
			int order=dis.readInt();
			System.out.println(name+":"+score+":"+order);
		}
		dis.close();fis.close();
	}
}
