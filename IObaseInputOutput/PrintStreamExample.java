package day16.IObaseInputOutput;
import java.io.*;
public class PrintStreamExample {
	public static void main(String ar[]) throws Exception{
		FileOutputStream fos=new FileOutputStream("C:\\Temp\\print.txt",true);
		PrintStream ps=new PrintStream(fos);
		
		ps.println("[print ���� stream]");
		ps.println("��ġ �����Ͱ� ����ϴ� ��ó��");
		ps.print("�����͸�");
		ps.print("����մϴ�.");
		ps.flush();ps.close();fos.close();
	}
}
