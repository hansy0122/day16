package day16.IObaseInputOutput;
import java.io.*;

public class BufferedReaderExample {
	public static void main(String ar[]) throws Exception{
		InputStream is=System.in;
		Reader reader=new InputStreamReader(is);
		BufferedReader br=new BufferedReader(reader);
		
		System.out.print("Input:");
		String lineString=br.readLine();
		System.out.println("Output:"+lineString);
	}
}
