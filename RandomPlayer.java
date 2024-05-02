import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class RandomPlayer extends Player{
	
	private Random generator;

	RandomPlayer(String name)
	{
		super(name);
		generator = new Random();
	}
	
	public int[] getMove(int [] pileSizes)
	{
		int[] move = new int[2];
		int randomIndex;
		ArrayList<Integer> notEmpty = new ArrayList<>();
	    for (int i = 0; i < pileSizes.length; i++) {
	        if (pileSizes[i] > 0) {
	            notEmpty.add(i);
	        }
	    }
	    if (notEmpty.isEmpty()) //Using isEmpty from Piles class; be careful
	    {
	    	return move;
	    }
	    
		randomIndex = notEmpty.get(generator.nextInt(notEmpty.size()));
		move[0] = randomIndex;
		if (pileSizes[move[0]] == 1)
		{
			move[1] = 1;
		}
		else {
		//Need to code this differently. Have it take the index, then select a number from the index that is not 0.
		int getNum = pileSizes[move[0]];
		
		int randomNum = generator.nextInt(getNum) + 1;
		/*if (randomNum == 0)
		{
			randomNum = randomNum + 1;
		} */
		move[1] = randomNum;
		}
		return move;
	}
}
