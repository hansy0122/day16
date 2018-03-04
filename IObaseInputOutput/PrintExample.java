package day16.IObaseInputOutput;
import java.io.*;
import java.util.*;

import javax.xml.crypto.Data;

public class PrintExample {
	public static void main(String ar[]){
			System.out.printf("price:%d$\n",123);
			System.out.printf("price:%6d$\n",123);
			System.out.printf("price:%-6d$\n",123);
			System.out.printf("price:%06d$\n",123);
			System.out.printf("Area of circle(radius %d):%10.2f\n",10,10*10*Math.PI);
			System.out.printf("%6d : %-100s : %100s \n",1,"hongil dong","programer");
			
			Date now =new Date();
			
			System.out.printf("now: %tY Year %tm Month %td Day\n",now,now,now);
			System.out.printf("now: %1$tY Year %1$tm Month %1$td Day\n",now);
			System.out.printf("now: %1$tH Hour %1$tM Minute %1$tS Second",now);
			
	}
}
