package day16.IObaseInputOutput;
import java.io.*;

public class ObjectInputOutputStreamExample {
	public static void main(String ar[]) throws Exception{
		FileOutputStream fos=new FileOutputStream("C:\\Temp\\Object.dat");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		String n="kkk";
		oos.writeObject(new Integer(10));
		oos.writeObject(new Double(3.144));
		oos.writeObject(new int[]{1,2,3,4});
		oos.writeObject(new String("hong gil dong"));
		oos.writeObject(n);
		
		oos.flush();oos.close();fos.close();
		
		n="AAA";
		
		FileInputStream fis=new FileInputStream("C:\\Temp\\Object.dat");
		ObjectInputStream ois=new ObjectInputStream(fis);
		
		Integer o1=(Integer) ois.readObject();
		Double o2=(Double) ois.readObject();
		int[] o3=(int[]) ois.readObject();
		String o4=(String) ois.readObject();
		String o5=(String) ois.readObject();
		
		ois.close();fis.close();
		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3[0]+" "+o3[1]+" "+o3[2]+" "+o3[3]+" ");
		System.out.println(o4);
		//주소값을 보내는지 객체의 data 전부를 보내는지 알수있음
		System.out.println("o5="+o5+" VS"+" n="+n);
	}
}
