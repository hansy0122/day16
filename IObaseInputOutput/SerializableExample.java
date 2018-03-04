package day16.IObaseInputOutput;

import java.io.*;

public class SerializableExample {
 public static void main(String ar[]) throws Exception{
	 FileOutputStream fos=new FileOutputStream("C:\\Temp\\Object.dat");
	 ObjectOutputStream oos=new ObjectOutputStream(fos);
	 ClassA classA=new ClassA();
	 classA.field1=1;
	 classA.field2.field1=2;
	 classA.field3=3;
	 classA.field4=4;
	 oos.writeObject(classA);
	 oos.flush();oos.close();fos.close();
		System.out.println(classA.field1 + " " + classA.field2.field1 + " " + classA.field3 + " " + classA.field4);
		
	
 }
}





class ClassA implements Serializable{
	int field1;
	ClassB field2=new ClassB();
	//Except Serializable
	static int field3;
	transient int field4;
}

class ClassB implements Serializable{
	int field1;
}
