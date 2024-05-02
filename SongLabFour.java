import java.util.Arrays;
public class Song {
	
	private String title;
	private String artist;
	private int [] time;
	private int [] timeImmutable;
	
	//Constructor
	
	Song(String title, String artist, int [] time1)
	{
		this.title = title;
		this.artist = artist;
		this.time= time1;
		timeImmutable = Arrays.copyOf(time, time.length);
		
	}

	//getters
	public String getTitle()
	{
		return title;
	}

	public String getArtist() {
		return artist;
	}
	
	public int [] getTime()
	{
		time = Arrays.copyOf(timeImmutable, timeImmutable.length);
		return time;
	}
}
