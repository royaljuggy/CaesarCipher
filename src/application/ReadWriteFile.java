package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.nio.charset.StandardCharsets;

/**
 * This class reads and writes data into files
 * Example methods include:
 * Reading a string or object from a file
 * Writing the frequency of words, or serializing an object to a file
 * Clearing all of a file's data
 * @author Jacob Meksavanh
 *	
 */

public class ReadWriteFile {

	/**
	 * Reads a file's plain text
	 * @param file the file's path as a String
	 * @return A <code> string </code> representation of a file's data
	 * @throws IOException
	 */
	
	public static String readFileAndReturn(String file) throws IOException { 
		String line, text = "";
			System.out.println("Reading text from " + file);
			Path path = Paths.get(file);
            BufferedReader bufferedReader = Files.newBufferedReader(path);
 
            while ((line = bufferedReader.readLine()) != null) {
            	text += line;
            }
            
            return text;
            
	}
	
	/**
	 * Reads a file's plain text
	 * @param file the file object
	 * @return A <code> string </code> representation of a file's data
	 * @throws IOException
	 */
	public static String readFileAndReturn(File file) throws IOException { 
		String line, text = "";
			System.out.println("Reading text from " + file);
			FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
 
            while ((line = bufferedReader.readLine()) != null) {
            	text += line;
            }
            
            bufferedReader.close();
            return text;
            
	}
	
	/**
	 * Reads a file's plain text using a given language
	 * @param file the file's path as a String
	 * @param lang the language which permits the BufferedReader to read non-English characters
	 * @return a <code> string </code> representation of a file's data
	 * @throws IOException
	 */
	
	public static String readFileAndReturn(String file, String lang) throws IOException { 
			System.out.println("Reading text from " + file);
			String encode = "";
			for (Alphabet alph : Alphabet.values()) {
				if (alph.getLanguage().equalsIgnoreCase(lang)) {
					encode = alph.getEncode();
				}
			}
			
			Path path = Paths.get(file);
            BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName(encode));
 
            String line, text = "";
 
            while ((line = bufferedReader.readLine()) != null) {
            	text += line;
            }
            
            return text;
            
	}
	
	/**
	 * Writes the plain text of a string to a file
	 * @param file the file path
	 * @param text the text to write
	 * @throws IOException
	 */
	
	public static void writeToFile(File file, String text) throws IOException {
			file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(writer);
                 
            bWriter.write(text);          
            bWriter.close();
	}
	
	/**
	 * Writes the plain text representation of all words alongside frequency to a file.
	 * @param file the file's path as a String
	 * @param allWords ArrayList that holds Word objects to be written as plain text 
	 * alongside their frequency in the given file
	 * @throws IOException
	 */
	
	public static void writeFrequencyUsingArrayList(String file, ArrayList<Word> allWords) throws IOException {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(writer);
            
            for (Word word : allWords) {
            	
            	bWriter.write(word.toString());
                bWriter.newLine();
            }         
 
            System.out.println("File written at " + file);
            bWriter.close();
	}
	
	/**
	 * Writes the plain text representation of all words alongside frequency to a file. 
	 * Overridden.
	 * @param file the file's path as a String
	 * @param allWords ArrayList that holds Word objects to be written as plain text 
	 * alongside their frequency in the given file
	 * @throws IOException
	 */
	public static void writeFrequencyUsingArrayList(File file, ArrayList<Word> allWords) throws IOException {
			
			file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(writer);
            
            for (Word word : allWords) {
            	
            	bWriter.write(word.toString());
                bWriter.newLine();
            }         
 
            System.out.println("File written at " + file);
            bWriter.close();
	}
	
	
	/**
	 * Writes the plain text representation of a word alongside its frequency to a file.
	 * Frequency of a word must be greater than a percentage of all words (usually 1%) for it to be printed.
	 * @param file the file's path as a String
	 * @param allWords ArrayList that holds Word objects to be written as plain text 
	 * alongside their frequency in the given file
	 * @throws IOException
	 */
	
	public static void writeFrequencyListPercentage(String file, ArrayList<Word> allWords) throws IOException {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(writer);
            
            for (Word word : allWords) {
            	
            	if (word.getFreq() >= allWords.size() * 0.01) {
            		bWriter.write(word.toString());
                	bWriter.newLine();
            	} 
            }         
 
            System.out.println("File written at " + file);
            bWriter.close();

	}
	
	/**
	 * 
	 * Writes the plain text representation of a word alongside its frequency to a file (Overriden Method)
	 * Frequency of a word must be greater than a percentage of all words (usually 1%) for it to be printed.
	 * @param file the file
	 * @param allWords ArrayList that holds Word objects to be written as plain text 
	 * alongside their frequency in the given file
	 * @throws IOException
	 */
	
	public static void writeFrequencyListPercentage(File file, ArrayList<Word> allWords) throws IOException {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(writer);
            
            for (Word word : allWords) {
            	
            	if (word.getFreq() >= allWords.size() * 0.01) {
            		bWriter.write(word.toString());
                	bWriter.newLine();
            	} 
            }         
            
            System.out.println("File written at " + file);
            bWriter.close();
	}
	
	/**
	 * Prints the database
	 * @param allWords an arraylist of words
	 */
	public static String printText(ArrayList<Word> allWords) {
		String temp = "";
		
		for (Word word : allWords) {
        	temp += word.toString() + "\n";
        }
		
		return temp;
	}
	
	/**
	 * Clears a file of all data
	 * @param file the file's path as a String
	 */
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
	
	/**
	 * Serialization method for objects
	 * @param file the file's path as a String
	 * @param word the object (usually type Word) to be serialized
	 * @throws IOException
	 */
	public static void serializeData(String file, Object word) throws ClassCastException {
		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
			 objOut.writeObject(word);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Serialization method for objects
	 * @param file the file
	 * @param word the object (usually type Word) to be serialized
	 */
	public static void serializeData(File file, Object word) {
		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
			 objOut.writeObject(word);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deserialization method for objects
	 * @param file the file's path as a String
	 * @return the serialized Object
	 */
	public static Object deSerializeData(File file) {
		try(FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream objIn = new ObjectInputStream(fileIn)) 
		{
			Object word = objIn.readObject();
			return word;
		} catch (ClassNotFoundException e) {
			System.out.println("Object not found");
			e.printStackTrace();
			return null;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		}
	}
}
