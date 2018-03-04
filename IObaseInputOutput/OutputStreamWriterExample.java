package day16.IObaseInputOutput;
import java.io.*;
	
public class OutputStreamWriterExample {
	public static void main(String ar[]) throws Exception{
		FileOutputStream fos=new FileOutputStream("C://Temp//test2.txt");
		Writer writer=new OutputStreamWriter(fos);
		String data="바이트 출력 스트림을 문자 출력 스트림으로 변환";
		writer.write(data);
		writer.flush();
		writer.close();
		System.out.println("file save");
	}
}
