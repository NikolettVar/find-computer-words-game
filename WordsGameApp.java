/*
*WordsGameApp.java
*N Varadi
*09 01 2021
*/

import javax.swing.*; //package allows the use of JOptionPane

public class WordsGameApp{
	public static void main(String args[]){

		//Variables, object

		String playAgain = ""; //variable to set up do/while loop
		String dozenLetters = ""; //variable to hold 12 randomly picked chars from alphabet String
	 	String player1Guess = ""; //input from player 1
		String player2Guess = ""; //input from player 2
		int player1PointsCurrentRound = 0; //variable to store player 1's points for current valid input
		int player2PointsCurrentRound = 0; //variable to store player 2's points for current valid input
		int player1PointsTotal = 0; //variable to store player 1's total points
		int player2PointsTotal = 0; //variable to store player 2's total points
		String message; //validation message
		String message1; //validation message
		String message2; //validation message
		String message3; //validation message
		String message4; //validation message

		JOptionPane.showMessageDialog(null, "Dear players, please create a computer-related word using the 12 letters provided. Please use these 12 letters only, no use of other letters is allowed.");
		JOptionPane.showMessageDialog(null, "Please ensure you use a letter as many times as it occurs.");
		JOptionPane.showMessageDialog(null, "In case the same word is entered by both players, no points will be acquired. If your word matches a word in our secret database, you will receive double the amount of points as the number of vowels in the word.");

		do{ //start of do/while loop to make game turn-based

			WordsGame myWordsGame = new WordsGame(); //create an object instance of instantiable class

			//call setter and getter method to generate 12 random letters (computer input)
			myWordsGame.setDozenLetters();
			dozenLetters = myWordsGame.getDozenLetters();

			//Ask players for input, call setter methods and send out player inputs to instan class for processing
			JOptionPane.showMessageDialog(null, "Player 1, please use these 12 letters to create a word: " +dozenLetters);
			player1Guess = JOptionPane.showInputDialog(null, "Player 1, please enter the word you created ");
			myWordsGame.setPlayer1Guess(player1Guess);


			JOptionPane.showMessageDialog(null, "Player 2, please use these 12 letters to create a word: " +dozenLetters);
			player2Guess = JOptionPane.showInputDialog(null, "Player 2, please enter the word you created ");
			myWordsGame.setPlayer2Guess(player2Guess);

			//call compute() method to carry out validations and calculate reward points
			myWordsGame.compute();

			//call getter methods to retrieve feedback messages
			message = myWordsGame.getMessage();
			JOptionPane.showMessageDialog(null, message);

			message1 = myWordsGame.getMessage1();
			JOptionPane.showMessageDialog(null, message1);

			message2 = myWordsGame.getMessage2();
			JOptionPane.showMessageDialog(null, message2);

			message3 = myWordsGame.getMessage3();
			JOptionPane.showMessageDialog(null, message3);

			message4 = myWordsGame.getMessage4();
			JOptionPane.showMessageDialog(null, message4);

			//call getter methods to retrieve points for current round for both players
			player1PointsCurrentRound = myWordsGame.getPlayer1PointsCurrentRound();
			JOptionPane.showMessageDialog(null, "Player 1, you earned " + player1PointsCurrentRound + " points in this round");

			player2PointsCurrentRound = myWordsGame.getPlayer2PointsCurrentRound();
			JOptionPane.showMessageDialog(null, "Player 2, you earned " + player2PointsCurrentRound + " points in this round");

			//work out total points received by each player
			player1PointsTotal = player1PointsTotal + player1PointsCurrentRound;
			JOptionPane.showMessageDialog(null, "Player 1, your total points so far: " + player1PointsTotal);

			player2PointsTotal = player2PointsTotal + player2PointsCurrentRound;
			JOptionPane.showMessageDialog(null, "Player 2, your total points so far: " + player2PointsTotal);

			//ask players if they wanted to run another round
			playAgain = JOptionPane.showInputDialog(null, "If you want to play again, please type 'yes'");

		}

		//check termination condition for do/while loop
		while(playAgain.equalsIgnoreCase("Yes"));

			//Output winner and his/her total points after last round
			if(player1PointsTotal > player2PointsTotal){
				JOptionPane.showMessageDialog(null, "The winner is Player 1, the number of your points is: " + player1PointsTotal);
			}
			else if(player1PointsTotal == player2PointsTotal){
				JOptionPane.showMessageDialog(null, "It's a draw, both players acquired the same amount of points: " + player1PointsTotal + " , " + player2PointsTotal);
			}
			else{
				JOptionPane.showMessageDialog(null, "The winner is Player 2, the number of your points is: " + player2PointsTotal);
			}
	}
}