package maze;

import java.awt.Color;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class TiledFloor {
	private Tile[][] tiles;
	private int rows;
	private int columns;
	private Tile entrance;
	private Tile exit;

	private int nextStep[][];
	Stack<Tile> steps;
	Stack<Tile> possibleSteps;

	public TiledFloor(int r, int c) {
		rows = r;
		columns = c;
		int i, j;
		tiles = new Tile[rows][columns];
		//initialize the tiled floor
		for (i = 0; i < rows; i++) {
			for (j = 0; j < columns; j++) {
				tiles[i][j] = new Tile(i, j, Color.white);
				//now switch some tiles to red to create a maze
				//do this randomly , we don't know for sure whether our maze will be traversable
			}
		}

		Random random = new Random();
		int column;
		int row;
		int numTiles = rows * columns;
		for (i = 0; i < (numTiles / 3); i++) //for half of the tiles
		{
			column = random.nextInt(columns);
			row = random.nextInt(rows);
			tiles[row][column].setColor(Color.RED);
		}
		//set up entrance and exit of the maze
		//Ask the user to set up the entrance and the exit
		String value = JOptionPane.showInputDialog("enter row,column of entrance");
		StringTokenizer tokens = new StringTokenizer(value, ",");
		row = Integer.parseInt(tokens.nextToken());
		column = Integer.parseInt(tokens.nextToken());
		//make sure this row, column is inside the grid
		if ((row == 0) || (row == (rows - 1)) || (column == 0) || (column == (columns - 1))) { //set entrance
			entrance = getTile(row, column);
			entrance.setColor(Color.WHITE); //mouse doesn't like to walk on red tiles
			//now get the exit
			value = JOptionPane.showInputDialog("enter row,column of exit");
			StringTokenizer tok = new StringTokenizer(value, ",");
			row = Integer.parseInt(tok.nextToken());
			column = Integer.parseInt(tok.nextToken());
			//make sure exit is inside the grid
			if ((row == 0) || (row == (rows - 1)) || (column == 0) || (column == (columns - 1))) {
				//set exit
				exit = getTile(row, column);
				exit.setColor(Color.WHITE); //mouse doesn't like to walk on red tiles

			} else {
				JOptionPane.showMessageDialog(null, "incorrect exit specified, no such location");
			}

		} else {
			JOptionPane.showMessageDialog(null, "incorrect entrance specified, no such location");
		}

		//set up all the possible steps one can take across the floor
		//From each square there are exactly 8 ways to proceed
		//Each time , modify the row and column by a different combination
		nextStep = new int[8][2];
		nextStep[0][0] = 0;
		nextStep[0][1] = 1;
		nextStep[1][0] = 1;
		nextStep[1][1] = 0;
		nextStep[2][0] = 1;
		nextStep[2][1] = 1;
		nextStep[3][0] = 0;
		nextStep[3][1] = -1;
		nextStep[4][0] = -1;
		nextStep[4][1] = 1;
		nextStep[5][0] = -1;
		nextStep[5][1] = 0;
		nextStep[6][0] = 1;
		nextStep[6][1] = -1;
		nextStep[7][0] = -1;
		nextStep[7][1] = -1;

	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Tile getTile(int row, int col) {
		return (tiles[row][col]);
	}

	public Tile getEntrance() {
		return entrance;
	}

	public Tile getExit() {
		return exit;
	}

	public void reset() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tiles[i][j].reset();
			}
		}
	}

	public void nextMoves(Tile fromSquare) {
		int row, column;
		row = fromSquare.getRow();
		int tempRow, tempCol;
		column = fromSquare.getColumn();
		Color color;
		for (int i = 0; i < 8; i++) {
			//examine 8 possible moves from this square
			tempRow = row + nextStep[i][0];
			tempCol = column + nextStep[i][1];
			if ((tempRow >= 0) && (tempRow < rows)) {//check if column is in the grid
				if ((tempCol >= 0) && (tempCol < columns)) {
					if (!tiles[tempRow][tempCol].getVisited()) {
						//not just backing up to previous square
						//check if the Color is white
						color = tiles[tempRow][tempCol].getColor();
						if (color == Color.white) {
							//this is a possibility
							tiles[tempRow][tempCol].setVisited();
							possibleSteps.push(tiles[tempRow][tempCol]);
							//check if it is the exit, no need to search further
							if (tiles[tempRow][tempCol].equals(exit)) {
								return;
							}
							System.out.println("pushing a possible move " + tiles[tempRow][tempCol]);

						}
					}
				}
			}
		}

	}

	public boolean traverse() {
		//traverse the floor from entrance to exit
		//stepping only on red tiles
		steps = new Stack<Tile>();
		possibleSteps = new Stack<Tile>();
		//reset markings
		reset();
		//push the entrance onto the stack
		entrance.setVisited();
		steps.push(entrance);

		System.out.println("push " + entrance);
		nextMoves(entrance);
		//keep going as long as we have a possibility to move
		//and we haven't reached the end yet
		while (!possibleSteps.empty() && (!possibleSteps.peek().equals(exit))) {
			Tile x = possibleSteps.pop();

			x.setMarked(); //walked on that square
			steps.push(x); //walk to that square
			System.out.println("Walked to the square  " + x);
			//now figure out where you can walk from this square
			nextMoves(x);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if (possibleSteps.peek().equals(exit)) {
			steps.push(possibleSteps.pop());
			System.out.println("made it from entrance to exit");
			System.out.println(steps.toString());
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "sorry can't reach the exit");
		return false;
		}

	}

}
