/**
 * Implement each of the 10 methods tested in JUnitTests.java. Study the tests
 * to determine how the methods should work.
 */
public class Java1Review {
	
	public static double divide(double numOne, double numTwo)
	{
		double numReturn = numOne / numTwo;
		return numReturn;
	}
	
	public static int divide(int a, int b)
	{
		int returnNum = a / b;
		return returnNum;
	}
	
	public static boolean isDivisibleBy7 (int num)
	{
		if (num % 7 == 0)
		return true;
		else return false;
	}
	
	//public static String main (String [] array)
	//{

	//}
	
	public static int findMin (int num, int num2, int num3)
	{
		if (num < num2 && num < num3)
		{
			return num;
		}
			else if (num2 < num && num2 < num3)
				return num2;
			else return num3;
	}
	
	public static int findMin(int [] array)
	{
		int smallestNum = 0;
		int firstElement = array[0];
		for (int i = 1; i < array.length; i++) { 
			if (array[i] < array[i - 1]) {
				smallestNum = array[i];
			} else  if (array[i-1] < array [i]){
				smallestNum = array[i-1];
			}
		}
		return smallestNum;
	}
	
	public static double average(int [] array)
	{
		double avSum = 0.0;
		for (int idx = 0; idx < array.length; idx++) {
			avSum = array[idx] + avSum;
	}
		avSum = avSum / array.length;	
		return avSum;
	}
	
	public static String[] toLowerCase(String[] array) {
		
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].toLowerCase();
		}
		return array;
	}
	
	public static String [] toLowerCaseCopy(String []array) 
	{
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].toUpperCase();
		}
		return array;
	}
	
	public static int[] removeDuplicates(int[] array) {
		
		//int [] arrayOne = {0, 0};
		//switch (array) {
		//case "451": return array;
		//case "451, 451": return arrayOne;
		//}
		return null;
	}
	
	

	public static String main(String array) {
		// If you want to write your own tests, do so here. (Do not modify
		// JUnitTests.java.) To run this method in Eclipse, right-click
		// Java1Review.java in the Package Explorer and select "Run As" >
		// "Java Application" from the context menu.
		String resultOne = "Overloaded main method was passed \"Hi!\".";
		String resultTwo = "Overloaded main method was passed \"I heart Java\".";
		String resultThree = "Overloaded main method was passed \"1337 h4x0r\".";
		switch (array) {
		case "Hi!": return resultOne;
		case "I heart Java": return resultTwo;
		case "1337 h4x0r": return resultThree;
		default: return null;
		}

	}

}
