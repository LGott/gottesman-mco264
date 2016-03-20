package maze;


import java.awt.Color;

import javax.swing.JFrame;

public class VisualMaze extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FloorPanel maze;

	//  private Canvas canvas;

	public VisualMaze() {
		setTitle("Maze");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		maze = new FloorPanel(10, 10);
		maze.setBackground(Color.white);
		add(maze);//add to content pane
		setVisible(true);

		repaint();
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean traverseMaze() {
		boolean result = maze.traverseMaze();
		repaint();
		return result;

	}

	public static void main(String[] args) {
		VisualMaze myMaze = new VisualMaze();
		myMaze.traverseMaze();

	}

}
