import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {
	public static void main(String[] args) {
		//Affichage TA = new Affichage("AAA");
		//Affichage TB = new Affichage("BB");

		Affichage2 TA = new Affichage2("AAAAAAAAAAAAA");
		Affichage2 TB = new Affichage2("BBBBBBBBBBBBBBB");
		Affichage2 TC = new Affichage2("CCCCCCCCCCCCCCCCCCCCCCCCC");

		TA.start();
		TB.start();
		TC.start();
	}
}
