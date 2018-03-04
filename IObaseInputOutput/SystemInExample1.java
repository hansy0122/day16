package day16.IObaseInputOutput;

import java.io.*;

public class SystemInExample1 {
	public static void main(String ar[]) throws Exception {
		System.out.println("-----menu-----");
		System.out.println("1.output money");
		System.out.println("2.intput money");
		System.out.println("3.look money");
		System.out.println("4.end service");
		System.out.print("Plz choose the menu");

		InputStream is = System.in;
		char inputchar = (char) is.read();
		switch (inputchar) {
		case '1':
			System.out.println("choosing output money");
			break;
		case '2':
			System.out.println("choosing intput money");
			break;
		case '3':
			System.out.println("choosing look money");
			break;
		case '4':
			System.out.println("choosing end service");
			break;
		}

	}
}
