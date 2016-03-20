package ticTacToe;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String name1 = null;
		String name2 = null;
		String startOver = null;
		Board newBoard = new Board(); //Create a new board for the game

		Scanner input = new Scanner(System.in);

		System.out.println(
				"Game Rules: For each turn, enter the grid number value to place your symbol in.\n3 in a row wins!\n");

		//There are different choices for the players in the game. It can be human vs. human, or human vs. computer. 
		//The game executes different statements depending on the players. 

		do {
			newBoard.clearBoard(); //If the game is running multiple times, clear the board before each game

			System.out.println(
					"Enter choice for Player 1:\n (In a Human vs Computer game, Human must be chosen as Player 1) ");
			menu();
			int player1 = input.nextInt();

			System.out.println("Enter choice for Player 2" + ": ");
			menu();
			int player2 = input.nextInt();

			input.nextLine();

			//Throw an exception if the user chooses computer vs computer 
			if ((player1 == 2) && (player2 == 2)) {

				System.out.println("Error. Cannot play computer vs computer.");
				throw new InvalidDataException();

			}

			if (player1 == 2) {
				System.out.println("Error. In a human vs Computer game, Human must be chosen as player 1.");
				throw new InvalidDataException();

			}

			if (player1 == 1) {
				System.out.println("Enter name for Human player:");
				name1 = input.nextLine();
			}

			if (player2 == 1) {
				System.out.println("Enter name for Human player:");
				name2 = input.nextLine();
			}

			//Create a new game and pass in the players and name
			TicTacToe newGame = new TicTacToe(player1, player2, name1, name2);

			newBoard.displayBoard(); //display the board 

			int turn = 1;
			int player = 1; //To keep track of whos turn it is 
			int row = 0; //For the position values
			int column = 0;


			do {
				try {

					//In order to determine who's turn it is, calculate the mod of the turn to determine if its even. 
					//If it is even, then it player 2's turn, because player 1 always goes first. If its not even, 
					//then it is player 1's turn. 

					if ((player2 == 2) || (player1 == 2)) { //Only execute these statements if one of the players is the computer

						if ((turn % 2) == 0) {
							player = 2;
							System.out.println("Turn " + turn + "\nComputer placed it's symbol");
						}

						else {
							player = 1;

							System.out.println("Turn " + turn + "\nEnter the grid value to place your symbol:");
							row = input.nextInt();
							column = input.nextInt();
						}
					}

					else if ((player1 == 1) || (player2 == 1)) {

						System.out.println("Turn " + turn + "\nEnter the grid value to place your symbol:");
						row = input.nextInt();
						column = input.nextInt();

						if ((turn % 2) == 0) {
							player = 2;
						}

						else {
							player = 1;
						}
					}

					//Call the nextTurn method to play the game. Pass in the the row, column values and the player.
					//Return the player value into the variable so that the next turn can later be determined. 

					player = newGame.nextTurn(row, column, player);

					newBoard.displayBoard(); //Display the board with the symbols in it

					turn++; //Increment the turn

					//Check for winners after each turn 
					int checkRows = newBoard.checkRows();
					int checkColumns = newBoard.checkColumns();
					int checkDiagonals = newBoard.checkDiagonals();


					if ((checkRows > 0) || (checkColumns > 0) || (checkDiagonals > 0)) {

						if ((checkRows == 1) || (checkColumns == 1) || (checkDiagonals == 1)) {

							System.out.println("Player X has won the game! Great Job " + newGame.getName() + "!!");
							break;
						} else if ((checkRows == 2) || (checkColumns == 2) || (checkDiagonals == 2)) {
							System.out.println("Player O has won the game! Great Job " + newGame.getName() + "!!");
							break;
						}
					}
					if (turn > 9) {
						System.out.println("Tie Game! Game Over.");
						break;
					}

				} catch (PositionFullException e) {
					System.out.println("Position is taken already. Please try again:");
				} catch (InvalidDataException e) {
					System.out.println("Invalid grid values entered! Only values from 0-2 are allowed.Please try again. ");
				}
			} while (!(newBoard.isBoardFull()));
			System.out.println("\nDo you want to play again? Y/N");
			startOver = input.next();
		} while (startOver.equalsIgnoreCase("y"));

		System.out.println("Goodbye!");
		input.close();
	}

	public static void menu() {

		System.out.println("1. Human\n" + "2. Computer");

	}

}

