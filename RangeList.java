import java.util.StringJoiner;

public class RangeList extends IntegerList{
	
	RangeList()
	{
		super();
	}
	
	RangeList(int lowerBound, int upperBound)
	{
		super(upperBound - lowerBound + 1);
		
		if (lowerBound > upperBound) 
		{
			String message = "The upper bound must be greater than or equal to the lower bound.";
			throw new IllegalArgumentException(message);
		}
		
		
		for (int i = 0; i <= upperBound - lowerBound; i++)
		{
			
			super.insert(i, lowerBound + i);
		}
		
		joiner = new StringJoiner(", ", "[", "]");
	    for (int i = 0; i < size(); i++) {
	        joiner.add(String.valueOf(super.get(i)));
	    }
	}
	
	public void add(int lowerBound, int upperBound)
	{
		if (lowerBound > upperBound) 
		{
			String message = "The upper bound must be greater than or equal to the lower bound.";
			throw new IllegalArgumentException(message);
		}
		
		 int first = Integer.MAX_VALUE;
		    int last = Integer.MIN_VALUE;

		    for (int i = 0; i < super.size(); i++) {
		        int value = super.get(i);
		        if (value < first) {
		            first = value;
		        }
		        if (value > last) {
		            last = value;
		        }
		    }
		    
		    for (int i = first; i <= last; i++) {
		        while (super.contains(i)) {
		            super.remove(i);
		        }
		    }

		    for (int i = lowerBound; i <= upperBound; i++) {
		        super.insert(super.size(), i);
		    }
		}
			/*for (int i = lowerBound; i <= upperBound; i++)
			{
				if (super.get(i) != lowerBound +1) {
				super.insert(i, lowerBound + i);
				}
			}	*/		
		
	}
	
	public void remove(int lowerBound, int upperBound)
	{
		if (lowerBound > upperBound) 
		{
			String message1 = "The upper bound must be greater than or equal to the lower bound.";
			throw new IllegalArgumentException(message1);
		}
		
		if (size() == 0) {
			String message2 = "Cannot remove range from an empty list.";
			throw new UnsupportedOperationException(message2);
		}
		
		if (lowerBound < super.get(0) || upperBound > super.get(size() - 1)) 
		{
	        throw new IllegalArgumentException("Lower and/or upper bounds are out of the current list range.");
	    }
		
		int lowerIdx = -1;
	    int upperIdx = -1;

	    for (int i = 0; i < size(); i++) {
	        if (super.get(i) == lowerBound) {
	            lowerIdx = i;
	        }
	        if (super.get(i) == upperBound) {
	            upperIdx = i;
	        }
	    }

	    if (lowerIdx != 0 || upperIdx != size() - 1) {
	        throw new IllegalArgumentException("Cannot remove non-terminal ranges.");
	    }
		
	    for (int i = lowerIdx; i <= upperIdx; i++) 
	    {
	        super.remove(lowerIdx);
	    }
	}
	
	public void insert(int index, int integer)
	{
		throw new UnsupportedOperationException();
	}
	
	public void add(int integer)
	{
		throw new UnsupportedOperationException();
	}
	
	
}
