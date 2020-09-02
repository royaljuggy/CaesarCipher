package application;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class removes text of characters that are non-alpha. 
 * @author Jacob Meksavanh
 */

public class CleanText {

	/**
	 * Removes text of characters that are non-alpha 
	 * @param text the text to remove non-alpha characters from
	 * @param language the language which dictates what letters should be kept
	 * @return a String array with elements that are words
	 */
	public static String[] clean(String text, String language) {
		Alphabet alphabet = Alphabet.ENGLISH; //assume english
		text = text.toUpperCase();
		
		for (Alphabet element : Alphabet.values()) {
			if (element.getLanguage().equalsIgnoreCase(language)) {
				alphabet = element;
			}
		}
		
		System.out.println(language);
		text = text.trim();
		
		Pattern p= Pattern.compile("[^"+alphabet.getLetters()+" ]");
		Matcher match= p.matcher(text.toUpperCase());
		text =  match.replaceAll("");	
		
		String[] words = text.split(" ");
		
		for (int i = 0; i < words.length;i++) {
			words[i] = words[i].trim();
		}
		
		return words;
	}
	
	/**
	 * Removes text of characters that are non-alpha 
	 * @param text the text to remove non-alpha characters from
	 * @return a String array with elements that are words
	 */
	
	public static String[] clean(String text) {
		text = text.toUpperCase();
		
		text = text.trim();
		
		Pattern p= Pattern.compile("[^"+Alphabet.getAllLetters()+" ]");
		Matcher match= p.matcher(text.toUpperCase());
		text =  match.replaceAll("");	
		
		String[] words = text.split(" ");
		
		for (int i = 0; i < words.length;i++) {
			words[i] = words[i].trim();
		}
		
		return words;
	}
	
	/**
	 * Removes text of characters that are non-alpha 
	 * @param text the text to remove non-alpha characters from
	 * @param language the language which dictates what letters should be kept
	 * @return a String with non-alpha characters removed
	 */
	public static String cleanReturnStr(String text, Alphabet language) { 
		text = text.toUpperCase();	
		text = text.trim();
		
		Pattern p= Pattern.compile("[^"+language.getLetters()+" \n]"); //includes spaces and line breaks
		Matcher match= p.matcher(text.toUpperCase());
		text = match.replaceAll("");	
			
		return text;
	}
	
	/**
	 * Removes text of characters that are non-alpha 
	 * @param text the text to remove non-alpha characters from
	 * @param regex the regular expression to use
	 * @return a String with non-alpha characters removed
	 */
	public static String cleanReturnStr(String text, String regex) { 
		text = text.toUpperCase();	
		text = text.trim();
		
		Pattern p= Pattern.compile("[^"+regex+" \n]"); //includes spaces and line breaks
		Matcher match= p.matcher(text.toUpperCase());
		text = match.replaceAll("");	
			
		return text;
	}
	
	/**
	 * Cleans the name of a file of non-English characters
	 * @param text the file's name
	 * @return the cleaned text
	 */
	public static String fileNameCleaner(String text) {
		
		Pattern p= Pattern.compile("[^"+Alphabet.ENGLISH.getLetters()+"]");
		Matcher match= p.matcher(text);
		text =  match.replaceAll("");	
		text = text.trim();
		
		return text;
	}
}
