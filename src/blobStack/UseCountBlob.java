package blobStack;

import java.util.Scanner;

public class UseCountBlob
{
	public static void main(String[] args)
	{
		Scanner conIn = new Scanner(System.in);

		final int GRIDR = 10;   // number of grid rows
		final int GRIDC = 20; // number of grid columns

		// Get percentage probability of blob characters
		int percentage;       
		System.out.print("Enter a percentage (0 to 100): ");
		if (conIn.hasNextInt()) {
			percentage = conIn.nextInt();
		} else
		{
			System.out.println("Error: you must enter an integer.");
			System.out.println("Terminating program.");
			return;
		}
		System.out.println();

		// create grid
		CountBlob grid = new CountBlob(GRIDR, GRIDC, percentage);

		// display grid and blob count
		System.out.println(grid);
		System.out.println("\nBlob Count: " + grid.blobCount() + ".\n");
	}
}
