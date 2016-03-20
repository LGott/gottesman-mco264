package ticTacToe;

public class Player {

	//The player class is the parent class to the human and the computer.
	//It holds a symbol, and an instance of the board. 

	private char symbol;
	Board board = new Board();

	public Player(char symbol) {

		this.symbol = symbol;

	}

	public Board getBoard() {
		return board;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

}