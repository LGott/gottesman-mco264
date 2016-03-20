package ticTacToe;

public class ComputerPlayer extends Player {

	//The computers position is randomly generated

	public ComputerPlayer(char symbol) {

		super(symbol);
	}

	public void position(int[] position) throws PositionFullException {

		board.setPosition(position, getSymbol());

	}

}
