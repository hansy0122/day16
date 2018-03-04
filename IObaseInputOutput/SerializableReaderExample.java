package day16.IObaseInputOutput;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SerializableReaderExample {
	public static void main(String ar[]) throws Exception{
	FileInputStream fis=new FileInputStream("C:\\Temp\\Object.dat");
	ObjectInputStream ois=new ObjectInputStream(fis);
	ClassA v=(ClassA) ois.readObject();
	System.out.println("field1="+v.field1);
	System.out.println("field2.field1="+v.field2.field1);
	System.out.println("field3="+v.field3);
	System.out.println("field4="+v.field4);
	}
}
