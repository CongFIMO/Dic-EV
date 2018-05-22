package Dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DictEV {
	static HashMap<String, String> MapEV;
	static ArrayList<String> word;

	public static void EVon() {
		MapEV = new HashMap<String, String>();
		word = new ArrayList<String>();
		File f = new File("C:\\Users\\asus\\workspace\\DictDemo\\dataEV.txt");
		String s = "";
		try {
			Scanner ev = new Scanner(f, "UTF-8");
			try {
				while (ev.hasNextLine()) {
					String[] str = ev.nextLine().split("=");
					if (str[0] != "" && str[1] != "") {
						word.add(str[0]);
						MapEV.put(str[0].trim(), str[1]);
						s = MapEV.get(str[0].trim());
					} else
						System.out.println("Error !!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Collections.sort(word);
			}
			ev.close();
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}

	}
	public static void addEV() {
	    try {
	        String wordadd ;
	        String meaning ;
	        Scanner inp = new Scanner(System.in);
	        System.out.println("Nhap tu can them");
	        wordadd = inp.nextLine();
	        System.out.println("Nhap nghia:");
	        meaning = inp.nextLine();
	        File file = new File("dataEV.txt");
	        if (!file.exists()) {
	           file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file.getName(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(wordadd+"="+""+"#"+""+"#"+meaning+"#null"+"\n");
	        
	        bw.close();
	        inp.close();
	        System.out.println("Done"); 
	     	}  
	    	catch (Exception e) {
	        e.printStackTrace();
	    }
	 }

}
