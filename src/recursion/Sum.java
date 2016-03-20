package recursion;

public class Sum {

	public int sum(int[] numbers) {

		int sum = 0;
		//assume the array is filled to capacity
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}

	public int recurSum(int[] numbers) {

		return recurSum(numbers, 0);

	}

	public int recurSum(int[] numbers, int curr) {

		if (numbers.length == 0) {
			return 0;
		}

		if (curr == (numbers.length - 1)) {
			return (numbers[curr]);

		} else {
			return numbers[curr] + recurSum(numbers, curr + 1);
		}

	}

	private int recurSum(int[] numbers, int index, int sum){
		if (index == numbers.length){
			return sum;
		}
		sum+= numbers[index];
		return recurSum(numbers, index+1, sum);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		Sum sum = new Sum();

	}
}
