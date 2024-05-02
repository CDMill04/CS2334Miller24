import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Convert {
	//private final static String TRI_DELIMITER = "=";
	private final static String PERIOD = ".";
	private final static String NEGATIVE_SIGN = "-";

	public static void convertFile(String filename) throws IOException
	{
		//Code here. A few lines to generate a new file from the .gpx.
		boolean didWork = readFile(filename);
		if(didWork == false)
		{
			System.out.println("Something broke");
		}
	}
	
	//read in a file: Check
	//read in the specific lats and longs: Check
	//increment time by 5 each time, starting at 0: Check
	//export the file as a csv: Check
	//It stops at line 636. Why? Update: I forgot to close the Buffered things
	
	public static boolean readFile(String filename) throws IOException //Method stub will probably need to change
	{
		int timeCount = 0;
		try {
		BufferedReader bf = new BufferedReader(new FileReader(filename));
		String newLine;
		BufferedWriter wf = new BufferedWriter(new FileWriter("triplog.csv"));
		wf.write("Time,Latitude,Longitude");
		wf.newLine();
		while ((newLine = bf.readLine()) != null)
		{
			String latStart = "lat=";
            String lonStart = "lon=";
            String lineMarker = "<trkpt";
            if (newLine.contains(lineMarker)) {
            String latCoords = getLatCoords(newLine, latStart, lonStart);
            String longCoords = getLonCoords(newLine, latStart, lonStart);

			latCoords = onlyInts(latCoords); 
			longCoords = onlyInts(longCoords); 
			
			String toExport = timeCount + "," + latCoords + "," + longCoords;
			wf.write(toExport);
			wf.newLine();
			timeCount = timeCount + 5; 
            }
			}
		bf.close();
		wf.close();
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//Method to get the values for Latitude
	public static String getLatCoords(String newLine, String latStart, String lonStart)
	{
		String[] split1 = newLine.split(latStart);
		String[] latAndLon =split1[1].split(lonStart);
		return latAndLon[0];
	}
	
	//Method to get values for Longitude
	public static String getLonCoords(String newLine, String latStart, String lonStart)
	{
		String[] split1 = newLine.split(lonStart);
		return split1[1];
	}
	
	//Method to ensure that only the numbers or chars we want are included in the coordinates
	public static String onlyInts(String coords)
	{
		StringBuilder bob = new StringBuilder();
		String[] values = new String[12];
		values[0] = String.valueOf(0);
		values[1] = String.valueOf(1);
		values[2] = String.valueOf(2);
		values[3] = String.valueOf(3);
		values[4] = String.valueOf(4);
		values[5] = String.valueOf(5);
		values[6] = String.valueOf(6);
		values[7] = String.valueOf(7);
		values[8] = String.valueOf(8);
		values[9] = String.valueOf(9);
		values[10] = PERIOD;
		values[11] = NEGATIVE_SIGN;
		
		for (int i = 0; i < coords.length(); i++)
		{
			char charAtCoords = coords.charAt(i);
			for (String value: values)
			{
				if(String.valueOf(charAtCoords).equals(value))
				{
					bob.append(charAtCoords);
				}
				
			}
		}
		String fixedCoords = bob.toString();
		return fixedCoords;
	}
}
