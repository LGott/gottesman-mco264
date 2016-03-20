package ticTacToe;

public class PositionFullException extends Exception {
	public PositionFullException() {
		super("Position Is Taken Already!");
	}
}
