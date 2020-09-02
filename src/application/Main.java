package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * JavaFX Main Class
 * @author Jacob Meksavanh
 *
 */
public class Main extends Application {
	
	
	private static TabPane tabPane;
	private static Tab tabDB, tabExplore, tabDetect, tabEncrypt, tabDecrypt;
	private static Stage primaryStage;
	private static ComboBox<String> comboBoxDB, comboBoxDetect;
	
	//Database nodes/objects/variables
	private static Button btnFileDir, btnDBDir, btnUpdateDB, btnTest, btnSong, btnReset;
	private static Pane rootDB, rootDetect, rootExplore, rootEncrypt, rootDecrypt;
	private static FileChooser fcDirectory, fcDatabase, fcExplorer;
	private static DirectoryChooser dcDirectory, dcDatabase;
	private static Path pathDB;
	private static File fileDB;
	private static String fileName;
	private static TextField tfFileName; 
	private static WordList tempWl;
	
	//private static ArrayList<File> fileList;
	private static HashSet<File> fileList;
	private static TextArea output;
	
	//explorer nodes/objects
	private static Button btnSortAlpha, btnSortFreq, btnGetDB, btnSearchWord;
	private static File fileExplore;
	private static WordList wlExplore;
	private static TextArea outputExp;
	private static TextInputDialog tid;
	
	//Detector nodes/objects/variables
	private static Button btnDBChooser, btnDetect, btnText;
	private static HashMap<String, Integer> langCounter;
	private static FileChooser fcDetectFile;
	private static String[] detectText;
	private static TextArea detectOutput;
	
	//encrypt nodes/obj/var
	private static File fileText;
	private static FileChooser fcEncrypt;
	private static Button btnEncrypt, btnExecuteEncrypt, btnDCEncrypt;
	private static TextField tfShift, tfEncryptFile;
	private static int shiftValue;
	private static TextArea taEncrypt;
	private static String encryptedText;
	private static ComboBox<String> cbEncrypt;
	private static DirectoryChooser dcEncryptedFile;
	private static File fileEncrypted;
	
	//decrypt nodes/obj/var
	private static Button btnChooseText, btnChooseDB, btnDecrypt;
	private static TextArea taDecrypted;
	private static FileChooser fcDecrypted;
	private static DirectoryChooser dcDB;
	private static File fileD, fileDC;
	private static HashMap<String, Integer> langCounter_DE;
	private static HashSet<WordList> allDB_DE;
	private static ComboBox<String> cbDecrypt;
	
	//objects
	private static HashMap<String, WordList> allLists;
	private static DirectoryChooser dcDBDetect;
	private static File fileDBDetect;
	private static HashSet<WordList> allDB;
	private static Alert alert;
	
	private static AudioClip song;
	
	public void start(Stage tempStage) {
		try {
			primaryStage = tempStage;
			initialize();
			Scene scene = new Scene(tabPane,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
			primaryStage.setScene(scene);
			primaryStage.show();
			//song.play(0.03);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Initialize GUI
	public static void initialize() {
			
		allLists = new HashMap<>(); //creates a wordlist for each language
		for (Alphabet a : Alphabet.values()) {
			allLists.put(a.getLanguage(), new WordList(a.getLanguage()));
		}
		
		tabPane = new TabPane();	
		alert = new Alert(AlertType.INFORMATION);
		//song = new AudioClip(Paths.get("resources\\no_title.mp3").toUri().toString());
		alert.setTitle("Cipher");
		alert.setHeaderText("Information");	
		
		//initialize the each tab
		initDB();		
		initExplore();
		initDetector();
		initEncrypt();
		initDecrypt();	
		
		tabPane.getTabs().addAll(tabDB, tabExplore, tabDetect, tabEncrypt, tabDecrypt);
	}
	
	//Button btnFileDir, btnDBDir, btnUpdateDB, btnSortAlpha, btnSortFreq;
	//Initialize database GUI
	//https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
	public static void initDB() {
		tabDB = new Tab("Database");
		tabDB.setClosable(false);
		rootDB = new Pane();
		output = new TextArea();
		fileList = new HashSet();		
		
		comboBoxDB = new ComboBox<String>();	
        
		fcDirectory = new FileChooser();
		fcDirectory.setTitle("Select Training Files");
		fcDatabase = new FileChooser();
		fcDatabase.setTitle("Select Database Folder");
		
		dcDatabase = new DirectoryChooser();
		dcDatabase.setTitle("Select Database Folder");
		
		btnFileDir = new Button("Select training files");
		btnFileDir.setLayoutX(20);
		btnFileDir.setLayoutY(20);		
		
		//select training files button
		//on click opens file explorer, and allows users to input multiple files to be read (only txt files are read)
		//users may add files in separate occurences if needed
		btnFileDir.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { //duplicates are allowed for now
	                	List<File> temp = fcDirectory.showOpenMultipleDialog(primaryStage);   
	                	try {
	                	for (File file : temp) {
	                		if (file.getName().endsWith(".txt")) {
	                			fileList.add(file);
	                		}
	                		
	                	}
	                	
	                    alert("Text(s) successfully chosen.");
	                	} catch (NullPointerException npe) {
	                		alert("No text chosen. Try again.");
	                	}
	                }
	            });
		
		
		btnDBDir = new Button("Select database directory");
		btnDBDir.setLayoutX(20);
		btnDBDir.setLayoutY(55);
		
		//on pressing database chooser button, allows user to select the directory to save the database to
		btnDBDir.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(final ActionEvent e) {
				try {
					fileDB = dcDatabase.showDialog(primaryStage); //gets directory path
					//fileDB = fcDatabase.showOpenDialog(primaryStage);
					alert("Database directory successfully chosen.");
				} catch (NullPointerException npe) {
					npe.printStackTrace();
				}
			
			}
		});
		
		tfFileName = new TextField("Enter db file name");
		tfFileName.setLayoutX(20);
		tfFileName.setLayoutY(90);
		
		comboBoxDB.setValue("LANG. TO READ");
        comboBoxDB.setLayoutX(20);
		comboBoxDB.setLayoutY(125);
		
		btnUpdateDB = new Button("Update database");
		btnUpdateDB.setLayoutX(20);
		btnUpdateDB.setLayoutY(160);
		
		//creates database 
		btnUpdateDB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent e) {
				
				try {				
					
				if (fileList != null && !fileList.isEmpty()) { //checks if files to be read were chosen
					String tempLang = comboBoxDB.getValue().toString(); //gets language to write database in
					if (tempLang.equals("LANG. TO READ")) {
						tempLang = "ENGLISH";
						
					}
					
					tempWl = new WordList(tempLang);
					
		            for (File file : fileList) { 
		            	//reads each file chosen by user
		            	//reads file to plain text, cleans file of unwanted characters, 
		            	//then updates a hashmap of words(String) and their frequencies(int)
		            	
		            	//old code
		            	//allLists.get(tempLang).updateHash(CleanText.clean(ReadWriteFile.readFileAndReturn(file), tempLang));
		            	tempWl.updateHash(CleanText.clean(ReadWriteFile.readFileAndReturn(file), tempLang));
		            }
				
					if (!tfFileName.getText().equals(null)) { //gets name of the database's file
						fileName = tfFileName.getText();
					} else {
						fileName = "no_name_file";
					}
					
					fileName =  fileName.replaceAll(" ", "");//removes whitespaces in fileName
					
					//creates a new file (possible override)
					fileDB = new File(fileDB.getAbsoluteFile().toPath() + "\\" + fileName); 
					
					// old code that im keeping incase I mess something up
//					allLists.get(tempLang).hashToList(); //converts hashmap to arraylist	
//					ReadWriteFile.serializeData(fileDB, allLists.get(tempLang)); //writes database as a wordlist to fileName
//					output.setText(ReadWriteFile.printText(allLists.get(tempLang).getWordsList())); //prints wordlist to textarea
					
					tempWl.hashToList(); //converts hashmap to arraylist	
					ReadWriteFile.serializeData(fileDB, tempWl); //writes database as a wordlist to fileName
					output.setText(ReadWriteFile.printText(tempWl.getWordsList())); //prints wordlist to textarea
					
					btnUpdateDB.setDisable(true); //disables button so everything must be reset to be used again
					if (!output.getText().equals("")) {
						alert("Database successfully created!");
					} 
				} else {
					alert("No text files chosen");
				}
				
				} catch (NullPointerException npe) {
					alert("No list exists for the given language");
				} catch (IOException io) {
					alert("Input/output exception occurred");
				}
			}
		});
		
		btnSong = new Button("Unrelated button");
		btnSong.setLayoutX(20);
		btnSong.setLayoutY(400);
		
		btnSong.setOnAction(new EventHandler<ActionEvent>() { //it's unrelated I swear
			
			@Override
			public void handle(final ActionEvent e) {
				
			//song.play(0.05);
				
			}
		});
		
		//textarea shows database in plain text, can be removed later since explorer was added and has this function
        output.setLayoutX(200);
        output.setLayoutY(20);
        output.setMaxHeight(350);
        output.setMaxWidth(180);
        output.setEditable(false);

        //creates combobox which selects the language to write the DB to
        for (Alphabet a: Alphabet.values()) {
        	comboBoxDB.getItems().add(a.getLanguage());
		}
		
        //gets file name if enter is hit
        tfFileName.setOnKeyPressed(ke -> {

            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
            	fileName = tfFileName.getText();
            }

        });
        
        //clears previous text in box upon focus of textfield
        tfFileName.setOnMouseClicked((MouseEvent me) -> {
        	
        	if (tfFileName.focusedProperty().get()) {
        		tfFileName.setText("");
        	}
        });
		
        btnReset = new Button("Reset");
        btnReset.setLayoutX(20);
        btnReset.setLayoutY(265);
        
        //resets database builder to be used again
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent e) {
				fileList = new HashSet<>();
				allLists = new HashMap<>();
				btnUpdateDB.setDisable(false);
				for (Alphabet a : Alphabet.values()) {
					allLists.put(a.getLanguage(), new WordList(a.getLanguage()));
				}
				output.setText("");
			}
		});

        
		rootDB.getChildren().addAll(btnFileDir, btnDBDir, btnUpdateDB, comboBoxDB, output, tfFileName, btnSong, btnReset);
		tabDB.setContent(rootDB);
	}
	
	public static void initExplore() {
		
		tabExplore = new Tab("Explore database");
		tabExplore.setClosable(false);
	
		rootExplore = new Pane();
		
		outputExp = new TextArea();
		outputExp.setLayoutX(200);
        outputExp.setLayoutY(20);
        outputExp.setMaxHeight(350);
        outputExp.setMaxWidth(180);
        outputExp.setEditable(false);
        fcExplorer = new FileChooser();
        fcExplorer.setTitle("Select Database");
		btnGetDB = new Button("Choose database");
		btnGetDB.setLayoutX(20);
		btnGetDB.setLayoutY(20);
		
		//btn to get the database to explore
		btnGetDB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent e) { 
				try {
					fileExplore = fcExplorer.showOpenDialog(primaryStage); //get DB            	           
	            	wlExplore = (WordList)ReadWriteFile.deSerializeData(fileExplore); //reads database as wordlist
					outputExp.setText(ReadWriteFile.printText(wlExplore.getWordsList())); //print database as plaintext
					alert("Database successfully chosen.");
				} catch (ClassCastException ce) {
					alert("Invalid file was chosen (not a database!)");
				} catch (Exception ee) {
					alert("Error, try a valid file.");
				}
            }
		});
		
		btnSortAlpha = new Button("Sort alphabetically"); //disabled for now, implementation incomplete
		btnSortAlpha.setLayoutX(20);
		btnSortAlpha.setLayoutY(55);
		btnSortAlpha.setDisable(true);
		
		btnSortFreq = new Button("Sort by frequency"); 
		btnSortFreq.setLayoutX(20);
		btnSortFreq.setLayoutY(90);
		
		//sorts database by frequency
		btnSortFreq.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent e) {
				
				try {					
					wlExplore.sort(); //sorts DB by frequency
					outputExp.setText(ReadWriteFile.printText(wlExplore.getWordsList())); //prints DB as plaintext
				} catch (NullPointerException npe) {
					alert("No list exists for the given language");
				} catch (ClassCastException ce) {
					alert("Invalid file was chosen (not a database!)");
				}
				
			}
		});
		
		btnSearchWord = new Button("Search for a word");
		btnSearchWord.setLayoutX(20);
		btnSearchWord.setLayoutY(125);
		
		
		btnSearchWord.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent e) {
							
				try {
					tid = new TextInputDialog();
					tid.setTitle("Cipher Project");
					tid.setHeaderText("Word search");
					tid.showAndWait();
					String word = tid.getResult().toUpperCase();
					alert(word + ": " + wlExplore.getWordsHashMap().get(word));
					
				} catch (NullPointerException npe) {
					alert("This word is not in the database.");
				}
				
			}
		});
		rootExplore.getChildren().addAll(btnGetDB, btnSortAlpha, btnSortFreq, outputExp, btnSearchWord);
		tabExplore.setContent(rootExplore);
	}
	
	public static void initDetector() {
		tabDetect = new Tab("Detect Language");
		tabDetect.setClosable(false);
		rootDetect = new Pane();	
		btnDBChooser = new Button("Select Database");
		btnDetect = new Button("Detect Language");
		btnText = new Button("Choose text");
		
		btnDBChooser.setDisable(true);
		
		dcDBDetect = new DirectoryChooser();
		fcDetectFile = new FileChooser();
		allDB = new HashSet<>();
		detectOutput = new TextArea();
		
		btnDetect.setDisable(true);
//		comboBoxDetect = new ComboBox<String>();
//		comboBoxDetect.setValue("Read from:");
//		comboBoxDetect.getItems().add("TEXT");
//		comboBoxDetect.getItems().add("FILE");
//		comboBoxDetect.setLayoutX(20);
//		comboBoxDetect.setLayoutY(20);
//		
//		input = new TextField("Enter text/file path here");
//		input.setLayoutX(20);
//		input.setLayoutY(55);
		
		//creates the counter that will score each language against the text
		langCounter = new HashMap<>();
		for (Alphabet a: Alphabet.values()) {
			langCounter.put(a.getLanguage(), 0);
		}
		
		btnText.setLayoutX(20);
		btnText.setLayoutY(20);
		
		btnText.setOnAction( new EventHandler<ActionEvent>() { //gets text to detect language of
	        @Override
	        public void handle(final ActionEvent e) { //duplicates are allowed for now
	                	
	        	try {
	        		File temp = fcDetectFile.showOpenDialog(primaryStage); //gets file
	        		if (!temp.getName().toString().endsWith("txt")) { //if file is not txt, disables other buttons
	        			btnDBChooser.setDisable(true);
	        			alert("Text to be read must be a text file!");
	        		} else {
	        			//reads text file, cleans it of unwanted characters, converts it to string array of words
	        			detectText = CleanText.clean(ReadWriteFile.readFileAndReturn(temp)); 
	        			btnDBChooser.setDisable(false); //enables DB chooser button
	        		}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					alert("Input/output exception occurred!");
				}   
	 
	        }
	    });
		
		btnDBChooser.setLayoutX(20);
		btnDBChooser.setLayoutY(55);
		
		btnDBChooser.setOnAction(new EventHandler<ActionEvent>() { 
			//selects directory of databases to use as a reference for each language
			
			@Override
			public void handle(final ActionEvent e) {
				
				try {				
					fileDBDetect = dcDBDetect.showDialog(primaryStage);	
					btnDetect.setDisable(false);
					
				} catch (NullPointerException npe) {
					npe.printStackTrace();
				}
			}
		});

		
		btnDetect.setLayoutX(20);
		btnDetect.setLayoutY(90);
		
		detectOutput.setLayoutX(20);
		detectOutput.setLayoutY(125);
		detectOutput.setEditable(false);
		detectOutput.setMaxHeight(50);
		detectOutput.setMaxWidth(300);
		
		btnDetect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent e) {
				
				try {				
					// check everyword in the given text file against every database
					//+1 if the word is in the database, else do nothing
					for (File file : fileDBDetect.listFiles()) { //reads every file in directory
						//adds every wordlist of each language to a set, assumes only one DB per language
						//duplicate DB will inflate numbers
						allDB.add((WordList)ReadWriteFile.deSerializeData(file)); 
					}
				
					detectOutput.setText("Detecting language... this may take a while");
					for (WordList w : allDB) { //gets one language's wordlist database
						int count = 0;
						HashMap<String, Integer> temp = w.getWordsHashMap(); //gets hashmap of words (frequency is irrelevant)
						for (String str: detectText) { //checks if a word in the read text is in the database, if yes +1
							if (temp.containsKey(str)) {
								count++;
							}
						}
						
						langCounter.put(w.getLanguage(), count); //adds count (weight) to language 
					}
					
					String lang = "";
					int max = 0;
					
					for (HashMap.Entry<String, Integer> entry : langCounter.entrySet()) {
						System.out.println(entry.getKey() + " hits: " + entry.getValue());
						if (entry.getValue() > max) { //finds highest weight of all languages
							max = entry.getValue();
							lang = entry.getKey();
						}
					}
					
					detectOutput.setText("The text is in " + lang); //prints highest weight (likely the language)
					btnDetect.setDisable(true);
					btnDBChooser.setDisable(true);
				} catch (NullPointerException npe) {
					detectOutput.setText("NullPointerException occurred! Message Programmer!");
					npe.printStackTrace();
				} catch (ClassCastException ce) { //thrown if list is sorted by frequency before testing
					detectOutput.setText("ClassCastException occurred! Message Programmer!");
					ce.printStackTrace();
				}
			}
		});
		
		
		rootDetect.getChildren().addAll(btnText, btnDetect, btnDBChooser, detectOutput);
		tabDetect.setContent(rootDetect);
	}
	
	public static void initEncrypt() {
		tabEncrypt = new Tab("Encrypt");
		tabEncrypt.setClosable(false);
		rootEncrypt = new Pane();
		fcEncrypt = new FileChooser();
		dcEncryptedFile = new DirectoryChooser();
		
		btnEncrypt = new Button("Select Text");
		btnEncrypt.setLayoutX(20);
		btnEncrypt.setLayoutY(20);
		
		tfShift = new TextField("Enter caesar shift");
		tfShift.setLayoutX(20);
		tfShift.setLayoutY(55);
		
		taEncrypt = new TextArea();
		taEncrypt.setMaxWidth(180);
		taEncrypt.setLayoutX(200);
		taEncrypt.setLayoutY(20);
		taEncrypt.setEditable(false);
	
		btnDCEncrypt = new Button("Select save directory");
		tfEncryptFile = new TextField("Enter save file name");		
		
		cbEncrypt = new ComboBox<String>();	
        for (Alphabet a: Alphabet.values()) {
        	cbEncrypt.getItems().add(a.getLanguage());
		}
        
        cbEncrypt.setValue("LANG. TO ENCRYPT IN");
        cbEncrypt.setLayoutX(20);
        cbEncrypt.setLayoutY(90);
        
		btnEncrypt.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	fileText = fcEncrypt.showOpenDialog(primaryStage);                 	
	                	try {
	                		if (!fileText.getName().toString().endsWith("txt")) {
		                		alert("Invalid file type (.txt ONLY)");
		                		fileText = null;
		                	} else {
		                		alert("Text successfully chosen.");
		                		btnExecuteEncrypt.setDisable(false);
		                	}
	                	} catch (NullPointerException npe) {
	                		alert("No text was chosen.");
	                	}
	                	           	                  
	                }
	            });
		
		
		 	btnDCEncrypt.setLayoutX(20);
		 	btnDCEncrypt.setLayoutY(125);
		 	btnDCEncrypt.setOnAction(
		            new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(final ActionEvent e) { 
		                	try {
		                		fileEncrypted = dcEncryptedFile.showDialog(primaryStage);
		                		fileEncrypted.equals(null); //throws nullpointer if nothing was selected
		                		alert("Directory successfully chosen");
			                	
		                	} catch (NullPointerException npe ){
		                		alert("Nothing was selected.");
		                	}
		                	
		                }
		            });	
	        
	        
		tfEncryptFile.setLayoutX(20);
		tfEncryptFile.setLayoutY(160);
		
		tfEncryptFile.setOnMouseClicked((MouseEvent me) -> {
        	
        	if (tfEncryptFile.focusedProperty().get()) {
        		tfEncryptFile.setText("");
        	}
        });

		btnExecuteEncrypt = new Button("Encrypt Text");
		btnExecuteEncrypt.setDisable(true);
		btnExecuteEncrypt.setLayoutX(20);
		btnExecuteEncrypt.setLayoutY(195);
		
		btnExecuteEncrypt.setOnAction( //encrypts text
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	shiftValue = Integer.valueOf(tfShift.getText());
	                	
	                	Alphabet alpha = Alphabet.ENGLISH; //default language
	                	
	                	for (Alphabet a : Alphabet.values()) {
	                		if (a.toString().equals(cbEncrypt.getValue().toString())) {
	                			alpha = a;
	                			break;
	                		}
	                	}
	                	
	                	taEncrypt.setText("Encrypting text... this may take a long time!");
	                	String temp = Encrypter.encrypt(fileText, shiftValue, alpha);   
	                	taEncrypt.setText(temp);
	                	try {
	                		if(!tfEncryptFile.getText().equals(null)) {
	                			fileEncrypted = new File(fileEncrypted.getAbsoluteFile().toPath() + "\\" + tfEncryptFile.getText());
	                		} else {
	                			fileEncrypted = new File(fileEncrypted.getAbsoluteFile().toPath() + "\\" + "no_name");
	                		}
	                		 
							ReadWriteFile.writeToFile(fileEncrypted, temp);
							alert("Encrypted text successfully written to file");
							btnExecuteEncrypt.setDisable(true);
						} catch (IOException e1) {
							alert("IOException occurred. Try again and double-check directory chosen.");
						}
	                }
	            });		
        
        //clears previous text in box upon focus of textfield
        tfShift.setOnMouseClicked((MouseEvent me) -> {
        	
        	if (tfShift.focusedProperty().get()) {
        		tfShift.setText("");
        	}
        });
        
        
		rootEncrypt.getChildren().addAll(btnEncrypt, tfShift, taEncrypt, btnExecuteEncrypt, cbEncrypt, 
				btnDCEncrypt, tfEncryptFile);
		tabEncrypt.setContent(rootEncrypt);
	}
	
	public static void initDecrypt() {
		tabDecrypt = new Tab("Decrypt");
		tabDecrypt.setClosable(false);
		
		fcDecrypted = new FileChooser();
		dcDB = new DirectoryChooser();
		allDB_DE = new HashSet<>();
		
		langCounter_DE = new HashMap<>();
		for (Alphabet a: Alphabet.values()) {
			langCounter_DE.put(a.getLanguage(), 0);
		}
		
		btnChooseText = new Button("Select encrypted text");
		btnChooseText.setLayoutX(20);
		btnChooseText.setLayoutY(20);
		
		btnChooseText.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	fileD = fcEncrypt.showOpenDialog(primaryStage);                 	
//	                	try {
//	                		if (!fileD.getName().toString().endsWith("txt")) {
//		                		alert("Invalid file type (.txt ONLY)");
//		                		fileD = null;
//		                	} else {
//		                		alert("Text successfully chosen.");
//		                	}
//	                	} catch (NullPointerException npe) {
//	                		alert("No text was chosen.");
//	                	}
	                	           	                  
	                }
	            });
		
		btnChooseDB = new Button("Select database");
		btnChooseDB.setLayoutX(20);
		btnChooseDB.setLayoutY(55);
		
		btnChooseDB.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	try {
	                		fileDC = dcDB.showDialog(primaryStage);
	                		fileDC.equals(null); //throws nullpointer if nothing was selected
	                		
	                		alert("Directory successfully chosen");
			                btnDecrypt.setDisable(false);
		                	
	                	} catch (NullPointerException npe ){
	                		alert("Nothing was selected.");
	                	}
	                	
	                }
	            });	
		
		cbDecrypt = new ComboBox<String>();	
        for (Alphabet a: Alphabet.values()) {
        	cbDecrypt.getItems().add(a.getLanguage());
		}
        
        cbDecrypt.setValue("DECRYPT LANG.");
        cbDecrypt.setLayoutX(20);
        cbDecrypt.setLayoutY(90);
        
		btnDecrypt = new Button("Decrypt");
		btnDecrypt.setDisable(true);
		btnDecrypt.setLayoutX(20);
		btnDecrypt.setLayoutY(125);
		
		//read the encrypted text
		//take a sample size of text (25%) and check for words in here
		//select database of words (like detect language)
		//put sample text into hashmap with words and frequencies
		//check database against sample hashmap
		//-> to do this, take each sample word, shift it by X spaces then check with database 
		//(if exists, +freq of word to DB counter)
		//if there is X or more hits then we have found the shift key (if over 70% of words are hits then good)
		//now decrypt message using Encrypter.encrypt but with a negative of the shift key (to go backwards)
		
		btnDecrypt.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	try {
							String temp = 
									CleanText.cleanReturnStr(ReadWriteFile.readFileAndReturn(fileD), Alphabet.getAllLetters());
//							System.out.println("Temp: " + temp);
							//String subStr = temp.substring(0, (int)(temp.length() * 0.4)); //gets sample size of text (for speed)
							//System.out.println("subStr: " + subStr);
							String[] tempArr = temp.split(" ");

							int totalCount = tempArr.length;
							
							HashMap<String, Integer> encryptedWords = new HashMap<>();
							for (String str : tempArr) { //creates hashmap of sample words to decrypt and check
								if (encryptedWords.containsKey(str)) {
									encryptedWords.put(str, encryptedWords.get(str) + 1);
								} else {
									encryptedWords.put(str, 1);
								}
							}
							
							for (File file : fileDC.listFiles()) { //reads every file in directory
								//adds every wordlist of each language to a set, assumes only one DB per language
								allDB_DE.add((WordList)ReadWriteFile.deSerializeData(file)); 
							}
							
							String lang = "";
							if (cbDecrypt.getValue().equals("DECRYPT LANG.")) {
								lang = "ENGLISH";
							} else {
								lang = cbDecrypt.getValue();
							}
							
							WordList wl = new WordList(); 
							for (WordList w : allDB_DE) { //language to try and decrypt into, gets wordlist of that language from DB
								if (w.getLanguage().equals(lang)) {
									wl = w;
									break;
								}
							}
							
							int alphabetSize = 26;
							for (Alphabet a : Alphabet.values()) {
								if (a.getLanguage().equals(wl.getLanguage())) {
									alphabetSize = a.getLetters().length(); //alphabet size is the max number of shifts to check
									break;
								}
							}
							
							boolean shiftFound = false;
							//checking against database (WE DO KNOW THE LANGUAGE)
							for (int shift = 0; shift < alphabetSize; shift++) { //start with no shift in case text is not encrypted		
								int count = 0;
								for (HashMap.Entry<String, Integer> entry : encryptedWords.entrySet()) {
														
									//take a word, shift it with a key, then check it with each database, adding scores		
									String shifted = Encrypter.encrypt(entry.getKey(), shift, wl.getLanguage());
//									System.out.println("Entry key: " + entry.getKey());
//									System.out.println("Shifted: " + shifted + ", key: " + shift);
//									System.out.println(wl.getWordsHashMap().get(shifted));
									if (wl.getWordsHashMap().containsKey(shifted)) { 
										count += entry.getValue(); 
									}
									
								}
								
								System.out.println("shift: " + shift);
								if (count > (totalCount * 0.75)) { //if 75% of words are present then good
									//'encrypt' with negative to shift leftward 
									taDecrypted.setText(Encrypter.encrypt(temp, (alphabetSize - shift) * -1, wl.getLanguage()));
									alert("The shift value is " + (alphabetSize-shift));
									shiftFound = true;
									break;
								} 
							}
							
							if (!shiftFound) {
								taDecrypted.setText("Message could not be decrypted");
							}
							
//							for (HashMap.Entry<String, Integer> entry : encryptedWords.entrySet()) {
//								
//								
//								for (int shift = 0; shift < 26; shift++) { //start with no shift incase text is unencrypted								
//									//take a sample word, shift it with a key, then check it with each database, adding scores
//									String shifted = Encrypter.encrypt(entry.getKey(), shift, wl.getLanguage());
//									if (wl.getWordsHashMap().containsKey(shifted)) {
//										langCounter_DE.put(wl.getLanguage(), langCounter_DE.get(wl.getLanguage()) + 1);
//									}
//								}
//								
//							}
							
//							//checking against database (WE DONT KNOW WHAT LANGUAGE IT IS)
//							for (HashMap.Entry<String, Integer> entry : encryptedWords.entrySet()) {
//								for (int shift = 0; shift < 26; shift++) { //start with no shift incase text is unencrypted
//									
//									for (WordList wl : allDB_DE) {
//										//take a sample word, shift it with a key, then check it with each database, adding scores
//										String shifted = Encrypter.encrypt(entry.getKey(), shift, Alphabet.ENGLISH);
//										if (wl.getWordsHashMap().containsKey(shifted)) {
//											langCounter_DE.put(wl.getLanguage(), langCounter_DE.get(wl.getLanguage()) + 1);
//										}
//									}
//								}
//								
//							}
							
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	                	
	                }
	            });	
		
		taDecrypted = new TextArea();
		taDecrypted.setMaxWidth(180);
		taDecrypted.setLayoutX(200);
		taDecrypted.setLayoutY(20);
		taDecrypted.setEditable(false);
		
		rootDecrypt = new Pane();
		rootDecrypt.getChildren().addAll(btnDecrypt, btnChooseText, btnChooseDB, taDecrypted, cbDecrypt);
		tabDecrypt.setContent(rootDecrypt);
	}
	public static void alert(String text) {
		alert.setHeaderText(text);	
		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
