package day16.IObaseInputOutput;

import java.io.*;

public class NonSerializableParentExample {
	public static void main(String ar[])throws Exception{
		FileOutputStream fos=new FileOutputStream("C:\\Temp\\Object.dat");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		Child child=new Child();
		child.field1="HONG";
		child.field2="KANG";
		oos.writeObject(child);
		oos.flush();oos.close();fos.close();
		
		FileInputStream fis=new FileInputStream("C:\\Temp\\Object.dat");
		ObjectInputStream ois =new ObjectInputStream(fis);
		Child v =(Child)ois.readObject();
		System.out.println(v.field1);
		System.out.println(v.field2);
		ois.close();fis.close();
	}
}

class Parent{
	public String field1;
}

class Child extends Parent implements Serializable{
	public String field2;

	private void writeObject(ObjectOutputStream out)throws IOException{
		out.writeUTF(field1);
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
		field1= in.readUTF();
		in.defaultReadObject();
	}
}