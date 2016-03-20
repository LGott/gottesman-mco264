package maze;

import java.awt.Color;

public class Tile {
	private int row;
	private int column;
	private Color color;
	private boolean visited;
	private boolean marked;

	public Tile(int row, int column, Color color) {
		this.row = row;
		this.column = column;
		this.color = color;
		visited = false;
	}

	public Tile(int row, int column) {
		this(row, column, null);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean equals(Tile t) {
		if ((row == t.getRow()) && (column == t.getColumn())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String tile = " Tile[ " + row + "][" + column + "] " + color;
		return tile;
	}

	public void setVisited() {
		visited = true;
	}

	public void reset() {
		visited = false;
		marked = false;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setMarked() {
		marked = true;
	}

	public boolean getMarked() {
		return marked;
	}
}