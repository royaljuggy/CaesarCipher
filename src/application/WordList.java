package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * This class holds all of the words for a given language
 * @author Jacob Meksavanh
 *
 */
public class WordList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Word> wordsList;
	private HashMap<String, Integer> wordsHashMap;
	private String language;
	private int count;
	
	
	/**
	 * Main constructor 
	 * @param language the wordlist's language
	 * @see #setLanguage(String)
	 * @see #setWordsHashMap(HashMap)
	 * @see #setCount(int)
	 */
	public WordList(String language) {
		this.setLanguage(language);
		this.setWordsHashMap(new HashMap<>());
		this.setCount(count);
		this.setWordsList(new ArrayList<>());
	}	
	
	/**
	 * Constructor 
	 * @see #setLanguage(String)
	 * @see #setWordsHashMap(HashMap)
	 * @see #setCount(int)
	 */
	public WordList() {
		this.setLanguage("n/a");
		this.setWordsHashMap(new HashMap<>());
		this.setCount(count);
		this.setWordsList(new ArrayList<>());
	}	
	
	/**
	 * Converts the wordlist's HashMap into an ArrayList, sorts it, and assigns it to wordsList
	 */
	public void hashToList() { //converts hashmap to arraylist
		ArrayList<Word> temp = new ArrayList<>();
		for (HashMap.Entry<String, Integer> word : getWordsHashMap().entrySet()) {
			temp.add(new Word(word.getKey(), word.getValue()));
		}
			
		setWordsList(temp);
	}
	
	/**
	 * Sorts the ArrayList of words by frequency
	 */
	public void sort() {
		Collections.sort(wordsList);
	}
	
	/**
	 * Calls {@link #hashToList()} to sort the words by frequency
	 */
	public void hashSort() { //converts the hashmap into an arraylist, hashToList then sorts the arraylist
		hashToList();
		sort();
	}
	
	/**
	 * Updates the frequency of words
	 * @param text an Array of words to be added to the HashMap
	 */
	public void updateHash(String[] text) { //updates hashmap (remember to choose if the wordlist will use maps/lists)
		for (int x = 0; x < text.length; x++) {
			String word = text[x];
			if (!wordsHashMap.containsKey(word)) {				
				wordsHashMap.put(word, 1);
			} else {
				wordsHashMap.put(word, wordsHashMap.get(word) + 1);
			}
		}
	}
	
	/**
	 * 
	 * @return WordList's count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @param count the WordList's new count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 
	 * @return the WordList's set language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 
	 * @param language the WordList's new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 
	 * @return the WordList's HashMap
	 */
	public HashMap<String, Integer> getWordsHashMap() {
		return wordsHashMap;
	}

	/**
	 * 
	 * @param wordsHashMap the WordList's new HashMap
	 */
	public void setWordsHashMap(HashMap<String, Integer> wordsHashMap) {
		this.wordsHashMap = wordsHashMap;
	}

	/**
	 * 
	 * @return the WordList's ArrayList
	 */
	public ArrayList<Word> getWordsList() {
		return wordsList;
	}

	/**
	 * 
	 * @param wordsList the WordList's new ArrayList
	 */
	public void setWordsList(ArrayList<Word> wordsList) {
		this.wordsList = wordsList;
	}

	
}
