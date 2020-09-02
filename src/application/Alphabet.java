package application;

public enum Alphabet {

	ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
	FRENCH("AÀÂÆBCÇDEÉÈÊËFGHIÎÏJKLMNOÔŒPQRSTUÙÛÜVWXYŸZ"),
	SPANISH("ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"),
	ITALIAN("ABCDEFGHILMNOPQRSTUVZ");
	//ALL("ABCDEFGHIJKLMNOPQRSTUVWXYZÀÂÆÇÉÈÊËÎÏÔŒÙÛÜŸÑ");
	
	private String letters;
	private String encode;
	
	private Alphabet(String letters) {
		this.letters = letters;
	}
	
	private Alphabet(String letters, String encode) {
		this.letters = letters;
		this.encode = encode;
	}
	
	public String getLanguage() { //returns 3 letter code of language
		return this.name();
	}
	public String getLetters() {
		return this.letters;
	}
	
	public String getCode() {
		return this.name().substring(0,3);
	}
	
	public String getEncode() {
		return this.encode;
	}
	
	/**
	 * @return String of all the known letters by the application
	 */
	
	public static String getAllLetters() {
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÂÆÇÉÈÊËÎÏÔŒÙÛÜŸÑ";
	}
}
