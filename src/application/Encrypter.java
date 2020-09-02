package application;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * This class contains methods to encrypt a message
 * @author Jacob Meksavanh
 *
 */

public class Encrypter {

	private static Random rand = new Random();
	
	/**
	 * Encrypts a message
	 * @param file the text file to be read
	 * @param key the cipher shift of the alphabet
	 * @return the encrypted message
	 */
	
	public static String encrypt(File file, int key, Alphabet language) {
		String message = "";
		String encrypted = "";
		String alphabet = "";
		char ch;
		int index;
		
		try {
			message = CleanText.cleanReturnStr(ReadWriteFile.readFileAndReturn(file), language);
			alphabet = language.getLetters();
			
			for (int x = 0; x < message.length(); x++) {
				ch = message.charAt(x);
				if (ch == ' ' || ch == '\n') {
					encrypted += ch;
				} else {
					index = alphabet.indexOf(ch);
//					System.out.println("alphabet: " + alphabet);
//					System.out.print("index: " + index + " ");
//					
//					System.out.print("letter: " + message.charAt(x) + " ");		
					index += key;
//					System.out.println("index after: " + index);
					if (index >= alphabet.length()) { //pos key value
						index %= alphabet.length();
					} else if (index <= 0) { //neg key value 
						index = (alphabet.length() + (index%alphabet.length()));
					}
					encrypted += alphabet.charAt(index);
				}
							
			}
			
			return encrypted;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Main.alert("ERROR OCCURED IN ENCRYPTION");
			return "ERROR OCCURED";
		}
	
		
	}
	
	/**
	 * Encrypts a message
	 * @param message the text to encrypt
	 * @param key the cipher shift of the alphabet
	 * @param language the alphabet to use
	 * @return the encrypted message
	 */
	
	public static String encrypt(String message, int key, String language) {
		String encrypted = "";
		String alphabet = "";
		char ch;
		int index;
		
		for (Alphabet a : Alphabet.values()) {
			if (a.getLanguage().equals(language)) {
				alphabet = a.getLetters();
				message = CleanText.cleanReturnStr(message, a);
				break;
			}
		}

		//message = CleanText.cleanReturnStr(message, language);
		//System.out.println(message);
		for (int x = 0; x < message.length(); x++) {
			ch = message.charAt(x);
			if (ch == ' ' || ch == '\n') {
				encrypted += ch;
			} else {
				index = alphabet.indexOf(ch);
				index += key;
				if (index >= alphabet.length()) { //pos key value
					index %= alphabet.length();
				} else if (index < 0) { //neg key value 
					index = (alphabet.length() + (index%alphabet.length()));
				}
				encrypted += alphabet.charAt(index);
			}
						
		}
		
		return encrypted;
	
		
	}
	
	/**
	 * Encrypts a message
	 * @param message the text to encrypt
	 * @param key the cipher shift of the alphabet
	 * @param language the alphabet to use
	 * @return the encrypted message
	 */
	
//	public static String encrypt(String message, int key, String language) {
//		String encrypted = "";
//		String alphabet = "";
//		char ch;
//		int index;
//		
//		for (Alphabet a : Alphabet.values()) {
//			if (a.getLanguage().equals(language)) {
//				alphabet = a.getLetters();
//			}
//		}
//		
//		message = CleanText.cleanReturnStr(message, language);
//		
//		for (int x = 0; x < message.length(); x++) {
//			ch = message.charAt(x);
//			if (ch == ' ' || ch == '\n') {
//				encrypted += ch;
//			} else {
//				index = alphabet.indexOf(ch);
//				index += key;
//				if (index >= alphabet.length()) { //pos key value
//					index %= alphabet.length();
//				} else if (index <= 0) { //neg key value 
//					index = (alphabet.length() + (index%alphabet.length()));
//				}
//				encrypted += alphabet.charAt(index);
//			}
//						
//		}
//		
//		return encrypted;
//	
//		
//	}
	
	/**
	 * Encrypts a message with a randomly generated key
	 * @param message the message to be encrypted
	 * @return the encrypted message
	 */
	public static String randomEncrypt(String message) {
		message = message.toUpperCase();
		int key = rand.nextInt(27);
		
		String encrypted = "";
		
		for (int x=0; x < message.length(); x++) {
			char ch = message.charAt(x);
			ch += key;
			
			if (ch > 90) {
				int diff = (ch - 90) % 26; // at least 1 - mod gives diff from 1 - 26 (letters)
				ch = (char)(64 + diff);
			} //add upper bound
			
			encrypted += ch;
		}
		
		return encrypted;
		
	}
}
