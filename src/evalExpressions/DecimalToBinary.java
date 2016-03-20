package evalExpressions;

import java.util.Scanner;
import java.util.Stack;
public class DecimalToBinary {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String startOver;

		Stack<Integer> stack = new Stack<Integer>();

		do {
			System.out.println("Enter decimal number");
			int num = input.nextInt();

			//Calculate the remainder of the number divided by 2 and 
			//push the remainder value onto the stack

			while (num != 0) {
				int d = num % 2;
				stack.push(d);
				num /= 2;
			}

			System.out.print("\nEquivalent Binary Number = ");
			while (!(stack.isEmpty())) {
				System.out.print(stack.pop()); //Pop all the numbers from the stack and display them
			}
			System.out.println();

			//Allow the user to enter another number
			System.out.println("\nConvert another number? Y/N");
			startOver = input.next();

		} while (startOver.equalsIgnoreCase("Y"));

		System.out.println("Good Bye");
	}

}

