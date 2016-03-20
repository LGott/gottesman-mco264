package ticTacToe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	Board newBoard;
	Player player1;
	Player player2;
	Player currentPlayer;
	String name1;
	String name2;
	int[] position;  //An int array for the users choice of position on the board 
	Random randomPosition; //For the computer to generate a random position on the board

	Scanner input = new Scanner(System.in);

	public TicTacToe(int player1, int player2, String name1, String name2) {

		newBoard = new Board();
		this.player1 = createPlayer(player1);
		this.player2 = createPlayer(player2);
		this.name1 = name1;
		this.name2 = name2;

		this.position = new int[2];
		randomPosition = new Random();
	}

	public Player createPlayer(int player){

		//If user chooses human, return an instance of the HumanPlayer class, if user chooses computer, 
		//return an instance of the ComputerPlayer class.

		//If the game is human vs human, then we must give one human the symbol X, and the other human the symbol O. 
		//In order to do that, check if player1 was instantiated as a human. If it was, then return
		//the 2nd human with the O symbol. 

		if (player1 instanceof HumanPlayer) {
			if (player == 1) {
				return new HumanPlayer(name2, 'O');
			}
		}

		if(player == 1){
			return new HumanPlayer(name1, 'X');
		}

		else if (player == 2) {

			return new ComputerPlayer('O');
		}

		else {
			return null;
		}
	}

	public int nextTurn(int row, int column, int player) throws PositionFullException, InvalidDataException {

		//If the player parameter is 1, then make currentPlayer into player1. Otherwise, make currentPlayer player2.
		if (player == 1) {
			currentPlayer = player1;

		} else if (player == 2) {
			currentPlayer = player2;
		}

		//If player is human, fill the position array with the row/column parameters, and call the position method in
		//the player class to set the symbol, while passing the position array as an argument. 

		if (currentPlayer instanceof HumanPlayer) {

			position[0] = row;
			position[1] = column;

			if ((position[0] > 2) || (position[1] > 2)) {
				throw new InvalidDataException();
			}

			((HumanPlayer) currentPlayer).position(position); //Set the position on the board
		}

		//If the player is the computer, generate a random position. If the position is full, generate a different one.
		else if (currentPlayer instanceof ComputerPlayer) {
			do{
				position[0] = randomPosition.nextInt(3); //The position can only use a number from 0-2.
				position[1] = randomPosition.nextInt(3);
			}
			while(newBoard.isFull(newBoard.getBoard(), position));  //Call the isFull method to check if position is full

			((ComputerPlayer) currentPlayer).position(position); //Set the position on the board 

		}
		return player; //Returns the current player so that the correct player can be determined for the next turn 
	}

	public boolean isBoardFull() {

		if (newBoard.isBoardFull()) {
			return true;
		} else {
			return false;
		}

	}

	//To be able to display the names at the end of the game 
	public String getName() {
		if (currentPlayer instanceof HumanPlayer) {
			if(currentPlayer.getSymbol() == 'X'){
				return name1;
			}
			else{
				return name2;
			}
		}
		if (currentPlayer instanceof ComputerPlayer) {
			return "Computer"; //If the computer won, return "computer" to be displayed
		}
		return null;
	}
}