package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParsing {
	/**
	 * removes all characters that are not in the regex plus " "
	 * returns an array of strings representing all the words
	 * without any leading or trailing white spaces
	 * @param text to be cleaned
	 * @param regex a String with characters that we need to keep in the text
	 * @return the cleaned words in upper case as array
	 */
	public static String[] remove(String text, String regex) {
		Pattern p= Pattern.compile("[^"+regex+" ]");
		Matcher match= p.matcher(text.toUpperCase());
		text =  match.replaceAll("");		
		String[] words = text.split(" ");
		for (int i = 0; i < words.length;i++) {
			words[i] = words[i].trim();
		}
		return words;
	}
}
