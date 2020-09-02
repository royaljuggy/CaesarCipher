package application;

import java.util.ArrayList;
import java.util.HashMap;

public class TestReader {

	private static ArrayList arr = new ArrayList();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		for (Alphabet lang: Alphabet.values()) {
//			System.out.println(lang.getLanguage() + ": " + lang.getLetters());
//		}
		
		Reader reader = new Reader();
		
		//String message = reader.readFileAndReturn("D:\\Documents\\Documents\\MurzakuZipFiles\\ExampleEncryption.txt");
			
		CleanText cleaner = new CleanText();
		
		String testMsg = "HELLO! WORLD! HELLO, HELLO!! MY NAME I~S JACOB~~!";
		String[] wordArray;
		System.out.println("Test message: " + testMsg);
		wordArray = cleaner.clean(testMsg, "FRENCH");
		System.out.println("After cleaning: " + testMsg);
		HashMap<String, Integer> wordFrequency = new HashMap<>();
		//System.out.println("Frequency of words : " + reader.wordFrequency(testMsg, wordFrequency));
		
		System.out.println(reader.readFileAndReturn
				("D:\\Documents\\Documents\\Eclipse Workspace\\Cipher_JavaFX\\TrainingTexts\\ENG_CommonWords"));
		Encrypter encrypter = new Encrypter();
		System.out.println(encrypter.encrypt("HELLO", 3));
		
		Writer write = new Writer();
		write.writeBW("D:\\Documents\\Documents\\Eclipse Workspace\\Cipher_JavaFX\\TrainingTexts\\ENG_CommonWords", "Hi");

		
//		int key=0;
//		key = (int)encrypted.charAt(0);
//		
//		for (int x=0; x < encrypted.length(); x++) {
//			char ch = encrypted.charAt(x);
//		}
//		
//		if (key==0) 
//			System.out.println("The message was not encrypted");
//		else if (key>=0)
//			System.out.println("The key is " + key + " shifts to the right");
//		else 
//			System.out.println("The key is " + key + " shifts to the left");
	}

}
