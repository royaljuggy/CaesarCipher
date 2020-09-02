package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Writer {

//	private FileWriter writer;
//	private BufferedWriter bufferedWriter;
//	private static int fileNum = 0000;
	
	public static void writeBW(String file, String message) {
		
		try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.newLine();
            bufferedWriter.write(message);
 
            System.out.println("File written at " + file);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
//	public static void writeFrequency(String file, HashMap<String, Integer> wordFrequency) {
//		
//		try {
//            FileWriter writer = new FileWriter(file, false);
//            BufferedWriter bWriter = new BufferedWriter(writer);
//            
//            for (HashMap.Entry<String, Integer> word: wordFrequency.entrySet()) {
//            	bWriter.write(word.getKey() + ": " + word.getValue());
//            	bWriter.newLine();
//            }
// 
//            System.out.println("File written at " + file);
//            bWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//	}
	
	public static void writeFrequencyMostUsed(String file, HashMap<String, Integer> wordFrequency, int minFrequency) {
		
		try {
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(writer);
            int numOmitted = 0;
            
            for (HashMap.Entry<String, Integer> word: wordFrequency.entrySet()) {
            	if (word.getValue() >= minFrequency) {
            		bWriter.write(word.getKey() + ": " + word.getValue());
                	bWriter.newLine();
            	} else {
            		numOmitted++;           		
            	}            	
            }
            
            bWriter.write("Number of omitted words (frequency less than " + minFrequency + "): " + numOmitted);
 
            System.out.println("File written at " + file);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void writeFrequencyUsingArrayList(String file, ArrayList<Word> allWords, int minFreq) {
		try {
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(writer);
            
            for (Word word : allWords) {
            	
            	if (word.getFreq() >= minFreq) {
            		bWriter.write(word.toString());
                	bWriter.newLine();
//            		bWriter.write(word.getWord() + ": " + word.getFreq());
//                	bWriter.newLine();
            	} else {
            		break;
            	}	
            }         
 
            System.out.println("File written at " + file);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void clearFile(String file) {
		FileWriter writer;
		try {
			writer = new FileWriter(file, false);
			 BufferedWriter bWriter = new BufferedWriter(writer);
			 bWriter.write("");
			 bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
}
