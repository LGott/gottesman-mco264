package blobStack;

import java.util.Random;
import java.util.Stack;

public class CountBlob
{
	protected int rows;         // number of grid rows
	protected int cols;         // number of grid columns

	protected boolean [][] grid;     // the grid containing blobs
	boolean [][] visited;            // used by blobCount

	//Create 2 int stacks so that the row and col values can be saved on the stacks 
	//and later pushed off to be checked again 
	private Stack<Integer> stack;
	private Stack<Integer> stack2;

	public CountBlob(int rows, int cols, int percentage)

	{
		this.rows = rows;
		this.cols = cols;
		grid = new boolean [rows][cols];
		stack = new Stack<Integer>();
		stack2 = new Stack<Integer>();

		//generate random numbers
		int randInt;
		Random rand = new Random();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
			{
				randInt = rand.nextInt(100);
				if (randInt < percentage) {
					grid[i][j] = true;
				} else {
					grid[i][j] = false;
				}
			}
		}
	}

	@Override
	public String toString()
	{
		String gridString = "";
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (grid[i][j]) {
					gridString = gridString + "X";
				} else {
					gridString = gridString + "-";
				}
			}
			gridString = gridString + "\n";   // end of row
		}  
		return gridString;
	}

	public int blobCount()
	// returns the number of blobs in this grid
	{
		int count = 0;
		visited = new boolean [rows][cols];  // true if location visited

		//Initialize the visited array
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				visited[i][j] = false;
			}
		}

		// count blobs
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] && !visited[i][j])
				{
					count++;
					markBlob(i, j);

					//While the stacks are not empty, pop each int off, and pass it to the markBlob method again so that it 
					//can check the surrounding spaces

					while ((!stack.isEmpty()) && (!stack2.isEmpty())) {
						int topRow = stack.pop();
						int topCol = stack2.pop();

						markBlob(topRow, topCol);
					}
				}
			}
		}

		return count;
	}

	private void markBlob(int row, int col)

	{
		visited[row][col] = true;

		// check above
		if ((row - 1) >= 0)
		{
			if (grid[row - 1][col])
			{
				if (!visited[row - 1][col])
				{

					visited[row - 1][col] = true; //Mark as true
					stack.push(row - 1); //push the row onto the stack
					stack2.push(col); //push the column onto the stack 
				}
			}
		}

		// check below
		if ((row + 1) < rows)
		{
			if (grid[row + 1][col])
			{
				if (!visited[row + 1][col])
				{
					visited[row + 1][col] = true;
					stack.push(row + 1);
					stack2.push(col);
				}
			}
		}

		// check left
		if ((col - 1) >= 0)
		{
			if (grid[row][col - 1])
			{
				if (!visited[row][col - 1])
				{
					visited[row][col - 1] = true;
					stack.push(row);
					stack2.push(col - 1);
				}
			}
		}

		// check right
		if ((col + 1) < cols)
		{
			if (grid[row][col + 1])
			{
				if (!visited[row][col + 1])
				{
					visited[row][col + 1] = true;
					stack.push(row);
					stack2.push(col + 1);
				}
			}
		}
	}
}

