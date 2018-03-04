package day16.IObaseInputOutput;
import java.io.*;
public class PrintStreamExample {
	public static void main(String ar[]) throws Exception{
		FileOutputStream fos=new FileOutputStream("C:\\Temp\\print.txt",true);
		PrintStream ps=new PrintStream(fos);
		
		ps.println("[print 보조 stream]");
		ps.println("마치 프린터가 출력하는 것처럼");
		ps.print("데이터를");
		ps.print("출력합니다.");
		ps.flush();ps.close();fos.close();
	}
}
