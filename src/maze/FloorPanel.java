package maze;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FloorPanel extends JPanel {
	private TiledFloor aFloor;
	private int rows;
	private int columns;
	private Graphics g;

	public FloorPanel(int rows, int columns) {
		aFloor = new TiledFloor(rows, columns);
		this.rows = rows;
		this.columns = columns;
	}

	public boolean traverseMaze() {
		boolean result = aFloor.traverse();
		return result;
	}

	@Override
	public void paintComponent(Graphics g) {
		Tile aTile;
	// automatically called when repaint
	super.paintComponent(g);
	//slow down the process
	try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	//display floor on the screen
	for (int i = 0; i < rows; i++) {
		System.out.println();
		System.out.println("-------------------------------------------");
		for (int j = 0; j < columns; j++) {
			{
					aTile = aFloor.getTile(i, j);
					if (aTile.getColor() == Color.red) {
						System.out.print("|--R--|");
						g.setColor(Color.red);
					} else {
						System.out.print("|--W--|");
						g.setColor(Color.white);
					}
					//draw the tile

					g.fillRect(j * 50, i * 50, 50, 50);
					if (aTile.getMarked()) {
						g.setColor(Color.CYAN);
						g.fillOval(j * 50, i * 50, 40, 40);
					}
					//place a green marker at the entrance
					if (aTile.equals(aFloor.getEntrance())) {
						g.setColor(Color.GREEN);
						g.fillOval(j * 50, i * 50, 30, 30);

					} else if (aTile.equals(aFloor.getExit())) {
						g.setColor(Color.magenta);
						g.fillOval(j * 50, i * 50, 30, 30);
					}
			}

			}
		}

	}

}
