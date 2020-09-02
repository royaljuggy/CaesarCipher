package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// https://www.developer.com/java/data/how-to-create-java-api-documentation.html
/**
 * Class used for testing of main task
 * @author Jacob Meksavanh
 *
 */
public class TestTask1 {


	public static void main(String[] args) {	
		try {
		//variables and objects		
		String[] wordArray;		
		WordList engList = new WordList("ENGLISH");
		WordList freList = new WordList("FRENCH");		
		
		//test code
		String text = "";
		
		//START ENGLISH
		String[] engFiles = {
				"Texts\\ENG_01.txt",
				"Texts\\ENG_02",
				"Texts\\ENG_03"
				};
		
//		for (int x=0; x < engFiles.length; x++) {
//			text = ReadWriteFile.readFileAndReturn(engFiles[x]);
//			wordArray = CleanText.clean(text, "ENGLISH");
//			engList.updateTree(wordArray); //updates words and their frequencies
//			ReadWriteFile.writeToFile("db\\English", engList.getWordsTreeMap());
//		}
		
		for (int x=0; x < engFiles.length; x++) {
			text = ReadWriteFile.readFileAndReturn(engFiles[x]);
			wordArray = CleanText.clean(text, "ENGLISH");
			engList.updateHash(wordArray); //updates words and their frequencies
		}
		
		engList.hashToList();
		//engList.hashSort(); 
		ReadWriteFile.writeFrequencyListPercentage("db\\English", engList.getWordsList());
		//END ENGLISH
		
		//START FRENCH
		String[] freFiles = {"Texts\\FRE_01",
				"Texts\\FRE_02",
				"Texts\\FRE_03"};
		
		for (int x=0; x < freFiles.length; x++) {
			text = ReadWriteFile.readFileAndReturn(freFiles[x]);
			wordArray = CleanText.clean(text, "FRENCH");
			freList.updateHash(wordArray);
		}
		
		freList.hashSort();
		ReadWriteFile.writeFrequencyListPercentage("db\\French", freList.getWordsList());
		//END FRENCH
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Word> hashToList(ArrayList<Word> wordList, HashMap<String, Integer> allWords) {
		for (HashMap.Entry<String, Integer> word : allWords.entrySet()) {
			wordList.add(new Word(word.getKey(), word.getValue()));
		}
		
		return wordList;
	}

}
