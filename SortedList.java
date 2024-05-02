import java.util.StringJoiner;
import java.util.Arrays;

public class SortedList extends IntegerList{
	
	SortedList()
	{
		super();
	}
	
	SortedList(int capacity)
	{
		super(capacity);
	}
	
	public void add(int integer)
	{
		
		int index = 0;
	    
	    while (index < size() && super.get(index) < integer) {
	        index++;
	    }
	    
	    
	    for (int i = size() - 1; i > index;i--) {
	        super.insert(i + 1, super.get(i));
	        
	    }
	    
	    super.insert(index, integer);
		
		joiner = new StringJoiner(", ", "[", "]");
	    for (int i = 0; i < size(); i++) {
	        joiner.add(String.valueOf(super.get(i)));
	    }
	}
	
	public void insert(int index, int integer)
	{
		throw new UnsupportedOperationException();
	}
}
