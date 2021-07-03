/*
*WordsGame.java
*N Varadi
*09 01 2021
*/

import java.lang.Math; //package needed for Math.random() method

public class WordsGame{

	//Data members

	private final String COMPUTERWORDS[] = {"algorithm", "application", "backup", "bit", "buffer", "bandwidth", "broadband", "bug", "binary", "browser", "bus", "cache", "command", "computer", "cookie", "compiler",
	"cyberspace", "compress", "configure", "database", "digital", "data", "debug", "desktop", "disk", "domain", "decompress", "development", "download", "dynamic",	"email", "encryption", "firewall",
	"flowchart", "file", "folder", "graphics", "hyperlink", "host", "hardware", "icon", "inbox", "internet", "kernel", "keyword", "keyboard", "laptop", "login", "logic", "malware", "motherboard",
	"mouse", "mainframe", "memory", "monitor",	"multimedia", "network", "node", "offline", "online", "path", "process", "protocol", "password", "phishing", "platform", "program", "portal", "privacy",
	"programmer", "queue",	"resolution", "root", "restore", "router", "reboot", "runtime", "screen", "security", "shell", "snapshot", "spam", "screenshot", "server", "script", "software", "spreadsheet",
	"storage", "syntax", "table", "template", "thread",	"terminal", "username", "virtual", "virus", "web", "website", "window", "wireless"
	};


	private final  String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private char randomChar; // stores a single random char from alphabet String
	private StringBuffer strBuff;
	private String dozenLetters; //stores 12 randomly picked chars from alphabet String
	private String player1Guess; //input from player 1
	private String player2Guess; //input from player 2
	private boolean valid1; //player 1 input validation, step 2
	private boolean valid2; //player 2 input validation, step 2
	private boolean contains1; //player 1 input validation, step 3
	private boolean contains2; //player 1 input validation, step 3
	private boolean sameOccurrences1; //player 1 input validation, step 4
	private boolean sameOccurrences2; //player 1 input validation, step 4
	private int vowelCount1; //holds number of vowels in player 1 input
	private int vowelCount2; //holds number of vowels in player 2 input
	private int player1PointsCurrentRound; //stores player 1's points for current valid input
	private int player2PointsCurrentRound; //stores player 2's points for current valid input
	private String message; //stores message for validation
	private String message1; //stores message for validation
	private String message2; //stores message for validation
	private String message3; //stores message for validation
	private String message4; //stores message for validation



	//Constructor, initial values to data members
	public WordsGame(){

		randomChar = ' ';
		strBuff = new StringBuffer();
		dozenLetters = "";
		player1Guess = "";
		player2Guess = "";
		valid1 = true;
		valid2 = true;
		contains1 = false;
		contains2 = false;
		sameOccurrences1 = false;
		sameOccurrences2 = false;
		vowelCount1 = 0;
		vowelCount2 = 0;
		player1PointsCurrentRound = 0;
		player2PointsCurrentRound = 0;
		message = "";
		message1 = "";
		message2 = "";
		message3 = "";
		message4 = "";
	}

	//Setter methods, inputs: 1 input from computer, 2 user inputs

	public void setDozenLetters(){
		for(int i = 0; i < 12; i++){
			int index = (int)(Math.random()*ALPHABET.length()); //pick a random index in alphabet String
			randomChar = ALPHABET.charAt(index); //grab the char at the randomly picked index number in alphabet String
			strBuff.append(randomChar); //use StringBuffer to build a new String out of the 12 randomly picked chars
			strBuff.append(" ");//insert space between char on screen for better readability
		}

		dozenLetters = strBuff.toString(); //convert StringBuffer back to a String
	}

	public void setPlayer1Guess(String player1Guess){ //setter method for player 1 input
		this.player1Guess = player1Guess;
	}

	public void setPlayer2Guess(String player2Guess){ //setter method for player 2 input
		this.player2Guess = player2Guess;
	}

	//Processing method: input validations, reward points

	public void compute(){

		//validation step 1: both players entered the same word
		if(player1Guess.equalsIgnoreCase(player2Guess)){
			message = "Both players entered the same word. Neither of you acquire points this time.";
			return; //end process here
		}
		else{
			message = "Each player entered different words.";
		}

		//validation step 2: check whether players used letters outside the 12 random letters provided
		//validate player 1 input
		for(int i = 0; i < player1Guess.length(); i++){
			if(dozenLetters.indexOf(player1Guess.charAt(i), 0)==-1){ //if any char in player 1 input is not found in 12 letters provided
				valid1 = false; //user 1 input invalid
				break;
			}
		}

		//feedback to player 1 on input validity
		if(valid1){
			message1 = "Player 1, your input correctly uses the 12 letters provided: " + player1Guess;
		}
		else{
			message1 = "Player 1, your input is invalid, please use the 12 letters provided";
		}


		//validate player 2 input
		for(int i = 0; i < player2Guess.length(); i++){
			if(dozenLetters.indexOf(player2Guess.charAt(i), 0)==-1){ //if any char in player 2 input is not found in 12 letters provided
				valid2 = false; //user 2 input invalid
				break;
			}
		}

		//feedback to player 2 on input validity
		if(valid2){
			message2 = "Player 2, your input correctly uses the 12 letters provided: "+ player2Guess;
		}
		else{
			message2 = "Player 2, your input is invalid, please use the 12 letters provided";
		}

		//validation step 3: checking if players' inputs could be found in valid words array


		 //iterate 100 words array to find player 1 input
		 for(int i=0; i < COMPUTERWORDS.length; i++){
			 if(COMPUTERWORDS[i].equalsIgnoreCase(player1Guess)){ //if player 1 input found in 100 words array
				contains1 = true; //match found
				break;
			 }
		 }

		 //iterate 100 words array to find player 2 input
		 for(int i=0; i < COMPUTERWORDS.length; i++){ //if player 2 input found in 100 words array
		 	if(COMPUTERWORDS[i].equalsIgnoreCase(player2Guess)){
		 		contains2 = true; //match found
		 		break;
		 	}
		}

		//validation step 4: checking players use letters as many times as occurrences the letter has
		//validation step 4 attempted, not fully functional

		 char[] abc = new char[ALPHABET.length()]; //turn ALPHABET string into an array
		 	for(int i = 0; i < ALPHABET.length(); i++){
				abc[i] = ALPHABET.charAt(i);
			}

		 int[] frequency1 = new int[26]; //array holds 26 numbers, representing the count of each letters in player 1 input
		 int[] frequency2 = new int[26]; //array holds 26 numbers, representing the count of each letters in player 2 input
		 int[] frequencyDozen = new int[26]; //array hold 26 numbers, representing the count of each letters among 12 random letters

			//iterate through both player 1 input and abc array to work out the count of each letters
			char char1 = ' ';
			for (int i = 0; i < player1Guess.length(); i++) {
				char1 = player1Guess.charAt(i);
				for (int j = 0; j < abc.length; j++) {
					  if (char1 == abc[j]) {
						  frequency1[j]++;
					  }
				}
			}

			//iterate through both player 2 input and abc array to work out the count of each letters
			char char2 = ' ';
			for (int i = 0; i < player2Guess.length(); i++) {
				char2 = player2Guess.charAt(i);
				for (int j = 0; j < abc.length; j++) {
						if (char2 == abc[j]) {
							frequency2[j]++;
						}
				}
			}

			//iterate through both 12 random letters string and abc array to work out the count of each letters
			char character = ' ';
			for (int i = 0; i < dozenLetters.length(); i++) {
				character = dozenLetters.charAt(i);
				for (int j = 0; j < abc.length; j++) {
						if (character == abc[j]) {
							frequencyDozen[j]++;
						}
				}
			}

			//iterate through both arrays holding letter frequencies and compare values
			for(int i = 0; i < frequency1.length; i++){
				for(int j = 0; j <frequencyDozen.length; j++){
					if(frequency1[i] == frequencyDozen[i]){
						sameOccurrences1 = true;
					}
				}
			}

			for(int i = 0; i < frequency2.length; i++){
				for(int j = 0; j < frequencyDozen.length; j++){
					if(frequency2[i] == frequencyDozen[i]){
						sameOccurrences2 = true;
					}
				}
			}



		//feedback and working out reward points for player 1 valid input
		 if(contains1 && valid1){
		 	message3 = "Player 1, your word is in the array of valid words and you will acquire points for this word: " + player1Guess;
			 for(int i = 0; i < player1Guess.length(); i++){
				if(player1Guess.charAt(i) == 'a' || player1Guess.charAt(i) == 'e' || player1Guess.charAt(i) == 'i' || player1Guess.charAt(i) == 'o' || player1Guess.charAt(i) == 'u'){
					vowelCount1 = vowelCount1 +1;
					player1PointsCurrentRound = vowelCount1 *2;
				}
			}
		 }
		 else{
		 	message3 = "Player 1, your word is NOT in the array of valid words: " + player1Guess;
 		}

		//feedback and working out reward points for player 2 valid input
		if(contains2 && valid2){
			message4 = "Player 2, your word is in the array of valid words and you will acquire points for this word: " + player2Guess;
			for(int i = 0; i < player2Guess.length(); i++){
				if(player2Guess.charAt(i) == 'a' ||player2Guess.charAt(i) == 'e' || player2Guess.charAt(i) == 'i' || player2Guess.charAt(i) == 'o' || player2Guess.charAt(i) == 'u'){
					vowelCount2 = vowelCount2 +1;
					player2PointsCurrentRound = vowelCount2 *2;
				}
			}
		}
		else{
			message4 = "Player 2, your word is NOT in the array of  valid words: " + player2Guess;
 		}

	}// end of compute() method

	//Getter methods: outputs
	public String getDozenLetters(){ //returns 12 random letters
		return dozenLetters;
	}

	public int getPlayer1PointsCurrentRound(){ //returns player 1's points for current round
		return player1PointsCurrentRound;
	}

	public int getPlayer2PointsCurrentRound(){ //returns player 2's points for current round
		return player2PointsCurrentRound;
	}

	public String getMessage(){ //feedback message to players
		return message;
	}

	public String getMessage1(){ //feedback message to player 1
		return message1;
	}

	public String getMessage2(){ //feedback message to player 2
		return message2;
	}

	public String getMessage3(){ //feedback message to player 1
		return message3;
	}

	public String getMessage4(){ //feedback message to player 2
		return message4;
	}
}