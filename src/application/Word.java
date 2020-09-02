package application;

import java.util.Comparator;

/**
 * Word object which contains a String of the object's word and an Integer of its frequency. 
 * Also contains the serialVersionUID 
 * @author Jacob Meksavanh
 *
 */
public class Word implements Comparable<Word>, java.io.Serializable {

	private int frequency;
	private String word;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Main constructor
	 * @param word the word of the object
	 */
	public Word(String word) {
		this.word = word;
		this.frequency = 1;
	}
	
	/**
	 * Constructor which allows for a set starting frequency
	 * @param word the word of the object
	 * @param freq the frequency of the word
	 */
	public Word(String word, int freq) {
		this.word = word;
		this.frequency = freq;
	}

	/**
	 * compareTo method that compares the frequency of words
	 */
	@Override
	public int compareTo(Word otherWord) {	
		if (this.frequency < otherWord.getFreq()) 
			return 1;
		else if (this.frequency > otherWord.getFreq()) 
			return -1;
		else 
			return 0;
	}
	
	
	/**
	 * Gets the object's word
	 * @return the object's word
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * Gets the frequency of the word
	 * @return the word's frequency
	 */
	public int getFreq() {
		return this.frequency;
	}
	
	/**
	 * Increase the frequency of a word by one
	 */
	public void addFreq() {
		this.frequency++;
	}
	
	/**
	 * Converts to a string format
	 * @return a string representing the word object
	 */
	public String toString() {
		return this.word + ": " + this.frequency;
	}
}
