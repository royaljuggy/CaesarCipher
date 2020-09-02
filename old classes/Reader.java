package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Reader {
	
	public void readBR(String file) { //preferred way (most efficient)
		try {
			FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close(); //careful, this will close the reader for good
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static String readFileAndReturn(String file) { 
		try {
			FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line, text = "";
 
            while ((line = bufferedReader.readLine()) != null) {
            	text += line;
            }
            
            return text;
            
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
	}
	
//	public String[] splitText(String file) { //currently can only read one line
//		try {
//			reader = new FileReader(file);
//            bufferedReader = new BufferedReader(reader);
//            String line, text = "";
// 
//            while ((line = bufferedReader.readLine()) != null) {
//            	text += line;
//            }
//            
//            return text.split(" ");
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ("I/O ERROR OCCURRED").split(""); //ok, .split is a temporary fix 
//        }
//	}
	
	
	public static void wordFrequency(String[] text, HashMap<String, Integer> wordFrequency) {
		//HashMap<String, Integer> wordFrequency = new HashMap<>();
		
		for (int x = 0; x < text.length; x++) {
			String word = text[x];
			if (!wordFrequency.containsKey(word)) {
				wordFrequency.put(word, 1);
			} else {
				wordFrequency.put(word, wordFrequency.get(word) + 1);
			}
		}
		
		//return wordFrequency;
	}
	
//	public HashMap<String, Integer> wordFrequency(String message) {
//		HashMap<String, Integer> wordFrequency = new HashMap<>();
//		String[] allWords = message.split(" ");
//		
//		for (int x = 0; x < allWords.length; x++) {
//			String word = allWords[x];
//			if (!wordFrequency.containsKey(word)) {
//				wordFrequency.put(word, 1);
//			} else {
//				wordFrequency.put(word, wordFrequency.get(word) + 1);
//			}
//		}
//		
//		return wordFrequency;
//	}
}
