package ticTacToe;

public class Board {

	static char[][] board = new char[3][3];

	public Board() {

	}

	//Create a board to be displayed 
	public void displayBoard() {

		System.out.println("-------------");

		for (int i = 0; i < 3; i++) {

			System.out.print("| ");

			for (int j = 0; j < 3; j++) {

				System.out.print(board[i][j] + " | ");
			}

			System.out.println();

			System.out.println("-------------");
		}
	}

	//Fill the given position with the given symbol of the player

	public void setPosition(int[] position, char symbol) throws PositionFullException {

		//Fill only if the position is not full. If the position is filled already, throw an exception

		if (!(isFull(board, position))) {

			board[position[0]][position[1]] = symbol;

		}

		else {
			throw new PositionFullException();
		}
	}

	public boolean isFull(char[][] theBoard, int[] position) {

		if ((board[position[0]][position[1]] == 'X') || (board[position[0]][position[1]] == 'O')) {
			return true;

		} else {
			return false;
		}
	}

	//Getter for the board
	public char[][] getBoard() {
		return board;
	}

	//Check if the board is full
	public boolean isBoardFull(){

		boolean check = false;
		for (int row = 0; row < 2; row++) {
			for (int column = 0; column < 2; column++) {
				if( (board[row][column]=='X') || (board[row][column]=='O')) {
					check = true;
				}
			}
		}

		check = false;
		return check;
	}

	//Check if there is a winner in the rows
	public int checkRows() {

		for (int row = 0; row <= 2; row++) {

			if ((board[row][0] == 'X') && (board[row][1] == 'X') && (board[row][2] == 'X')) {
				return 1;
			}
			else if ((board[row][0] == 'O') && (board[row][1] == 'O') && (board[row][2] == 'O')) {
				return 2;
			}
		}

		return 0;

	}

	//check if there is a winner in the columns 
	public int checkColumns() {
		for (int column = 0; column <= 2; column++) {

			if ((board[0][column] == 'X') && (board[1][column] == 'X') && (board[2][column] == 'X')) {
				return 1;
			}
			else if ((board[0][column] == 'O') && (board[1][column] == 'O') && (board[2][column] == 'O')) {
				return 2;
			}
		}

		return 0;

	}

	//Check if there is a winner in the diagonals 
	public int checkDiagonals() {

		for (int row = 0; row <= 2; row++) {
			for (int column = 0; column < 2; column++) {

				if ((board[0][0] == 'X') && (board[1][1] == 'X') && (board[2][2] == 'X')) {
					return 1;
				} else if ((board[0][0] == 'O') && (board[1][1] == 'O') && (board[2][2] == 'O')) {
					return 2;
				}
				else if ((board[0][2] == 'X') && (board[1][1]== 'X') &&  (board[2][0]== 'X') ){
					return 1;
				}
				else if((board[0][2] == 'O') && (board[1][1]== 'O') &&  (board[2][0]== 'O'))	{
					return 2;
				}
			}

		}
		return 0;
	}

	public void clearBoard(){

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				board[row][column] = ' ';
			}
		}
	}
}