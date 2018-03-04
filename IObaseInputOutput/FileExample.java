package day16.IObaseInputOutput;

import java.io.File;
import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FileExample {
	public static void main(String ar[]) throws Exception{
		File dir=new File("C://Temp//Dir");
		File file1=new File("C://Temp//file1.txt");
		File file2=new File("C://Temp//file2.txt");
		File file3=new File(new URI("file:///C://Temp//file3.txt"));
		
		if(!dir.exists()){dir.mkdirs();}
		if(!file1.exists()){file1.createNewFile();}
		if(!file2.exists()){file2.createNewFile();}
		if(!file3.exists()){file3.createNewFile();}
		
		File temp =new File("C://Temp");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		File[] contents = temp.listFiles();
		System.out.println("Day\t time\t\t\t type\t lenght\t name");
		System.out.println("----------------------------------------------");
		for(File file:contents){
			System.out.print(sdf.format(new Date(file.lastModified())));
			if(file.isDirectory()){
				System.out.print("\t\t<DIR>\t\t"+file.getName());
			}else{
				System.out.println("\t\t\t"+file.length()+"\t"+file.getName());
			}
			System.out.println();
		}
	}
}
