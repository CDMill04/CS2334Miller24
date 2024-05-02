import java.util.StringJoiner;
public class UniqueList extends IntegerList{
	
	UniqueList() 
	{
		super();
	}
	
	UniqueList(int capacity) 
	{
		super(capacity);
	}
	
	public void add(int integer) 
	{
		int index = size();
		for (int i = 0; i < size(); i++) 
		{
			if (super.get(i) == integer)
			{
				String message = "The integer " + integer + " is already in the list.";
				throw new IllegalArgumentException(message);
			}
			
		}
		super.insert(index, integer); 
		joiner.add(String.valueOf(integer));
	}
	public void insert(int index, int integer)
	{
		for (int i = 0; i < size(); i++) 
		{
			if (super.get(i) == integer)
			{
				String message = "The integer " + integer + " is already in the list.";
				throw new IllegalArgumentException(message);
			}
		}
		super.insert(index, integer);
		//joiner.add(String.valueOf(integer));
		joiner = new StringJoiner(", ", "[", "]");
	    for (int i = 0; i < size(); i++) {
	        joiner.add(String.valueOf(super.get(i)));
	    }
	}
}
