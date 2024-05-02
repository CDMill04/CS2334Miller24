import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	
	private final static String INFO_DELIMITER = "; ";
	private final static String TIME_DELIMITER = ":";
	private final static int IDX_TITLE = 0;
	private final static int IDX_ARTIST = 1;
	private final static int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info)
	{
		String[] songAspects = info.split(INFO_DELIMITER);
		this.title = songAspects[IDX_TITLE];
		this.artist = songAspects[IDX_ARTIST];
		String timeIDX = songAspects[IDX_TIME];
		
		//String[] timeParts = songAspects[IDX_TIME].split(TIME_DELIMITER);
		String[] timeParts = timeIDX.split(TIME_DELIMITER);
        this.time = new int[timeParts.length];

        if (timeParts.length == 1)
        {
        	this.time[0] = Integer.parseInt(timeParts[0]);
        }
        
        else if (timeParts.length == 2)
        {
        	this.time[0] = Integer.parseInt(timeParts[1]);
        	this.time[1] = Integer.parseInt(timeParts[0]);
        }
        
        else if(timeParts.length == 3)
        {
        	this.time[0] = Integer.parseInt(timeParts[2]);
        	this.time[1] = Integer.parseInt(timeParts[1]);
        	this.time[2] = Integer.parseInt(timeParts[0]);
        }
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}

	@Override
	public String toString() {
		
		int seconds = time[0];
        int minutes = time.length > 1 ? time[1] : 0;
        int hours = time.length > 2 ? time[2] : 0;
        String minutesString = String.format("%02d", minutes);
        String secondsString = String.format("%02d", seconds);
        //String hoursString = String.format("%02d", hours);
        
        if (time.length == 1)
        {
        	return title + INFO_DELIMITER + artist + INFO_DELIMITER + seconds;
        }
        
        if (time.length == 2) 
        {
        	return title + INFO_DELIMITER + artist + INFO_DELIMITER + minutes + TIME_DELIMITER + secondsString;
        }
        
        return title + INFO_DELIMITER + artist + INFO_DELIMITER + hours + TIME_DELIMITER + minutesString + TIME_DELIMITER + secondsString;
    } 

}
