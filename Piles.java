import java.util.Arrays;
public class Piles {

	private int[] sizes;
	private int[] sizesImmutable;
	
	Piles(int... initSizes) 
	{
		if (initSizes == null)
		{
			throw new IllegalArgumentException();
		}
		if (initSizes.length == 0) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < initSizes.length; i++)
		{
			if (initSizes[i] <= 0) {
				throw new IllegalArgumentException();
			}
		}
		sizes = new int[initSizes.length];
		for (int idx = 0; idx < initSizes.length; idx++)
		{
			sizes[idx] = initSizes[idx];
		}
		sizesImmutable = Arrays.copyOf(initSizes, initSizes.length);	
	}
	
	public int[] getSizes()
	{
		sizes = Arrays.copyOf(sizesImmutable, sizesImmutable.length);
		return sizes;
	}
	
	public void removeObjects(int[] move) throws IllegalMoveException
	{	
		if(move == null)
		{
			throw new IllegalMoveException("null move");
		}
		
		if(move.length != 2)
		{
			throw new IllegalMoveException("Invalid length: " + move.length);
		}
		else if(move[0] > sizes.length - 1)
		{
			throw new IllegalMoveException("Index out of bounds: " + move[0]);
		}
		else if(move[0] < 0)
		{
			throw new IllegalMoveException("Index out of bounds: " + move[0]);
		}
		else if(sizes[move[0]] == 0)
		{
			throw new IllegalMoveException("Pile " + move[0] + " is empty.");
		}
		else if(move[1] <= 0)
		{
			throw new IllegalMoveException("Nonpositive object number: " + move[1]);
		}
		else if(move[1] > sizes[move[0]])
		{
			throw new IllegalMoveException("Object number greater than pile size: " + move[1] + " > " + sizes[move[0]]);
		}
			
			int index = move[0];
			int amount = move[1];
		sizes[index] = sizes[index] - amount;
		sizesImmutable = Arrays.copyOf(sizes, sizes.length);
	}
	
	public boolean isEmpty()
	{
		int numEmpty = 0;
		for(int i = 0; i < sizes.length; i++)
		{
			if (sizes[i] == 0)
			{
				numEmpty++;
			}
		}
		
		if (numEmpty == sizes.length)
		{
			return true;
		}
		return false;
	}
}
