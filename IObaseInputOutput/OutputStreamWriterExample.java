package day16.IObaseInputOutput;
import java.io.*;
	
public class OutputStreamWriterExample {
	public static void main(String ar[]) throws Exception{
		FileOutputStream fos=new FileOutputStream("C://Temp//test2.txt");
		Writer writer=new OutputStreamWriter(fos);
		String data="����Ʈ ��� ��Ʈ���� ���� ��� ��Ʈ������ ��ȯ";
		writer.write(data);
		writer.flush();
		writer.close();
		System.out.println("file save");
	}
}
