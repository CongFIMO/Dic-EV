package Dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DictVE {
	 static HashMap<String, String> MapVE;
	 static ArrayList<String> word;

	public static void VEon() {
		MapVE = new HashMap<String, String>();
		word = new ArrayList<String>();
		File fev = new File("C:\\Users\\asus\\workspace\\DictDemo\\dataVE.txt");
		String s = "";
		try {
			Scanner ve = new Scanner( fev,"UTF-8");
			try {
				while (ve.hasNextLine()) {
					String[] str = ve.nextLine().split("=");
					if (str[0] != "" && str[1] != "") {
						word.add(str[0]);
						MapVE.put(str[0].trim(), str[1]);
						s = MapVE.get(str[0].trim());
					} else {
						System.out.println("Error!!");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				Collections.sort(word);
			}
			ve.close();
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
	}
	
	public static void addVE() {
	    try {
	        String wordadd ;
	        String meaning ;
	        Scanner inp = new Scanner(System.in);
	        System.out.println("Nhap tu can them");
	        wordadd = inp.nextLine();
	        System.out.println("Nhap nghia:");
	        meaning = inp.nextLine();
	        File file = new File("dataVE.txt");
	        if (!file.exists()) {
	           file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file.getName(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(wordadd+"="+meaning+"#null");
	        
	        bw.close();
	        inp.close();
	        System.out.println("Done"); 
	     	}  
	    	catch (Exception e) {
	        e.printStackTrace();
	    }
	 }
}
