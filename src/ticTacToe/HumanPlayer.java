package ticTacToe;

public class HumanPlayer extends Player {

	String name;  //For the human players name 

	public HumanPlayer(String name, char symbol){
		super(symbol);

		this.name = name;
	}

	//Calls the setPosition in the Board class to set the position that the player 

	public void position(int[] position) throws PositionFullException {

		board.setPosition(position, super.getSymbol());

	}

	public String getName() {
		return name;
	}
}
