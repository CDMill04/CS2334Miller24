
import java.lang.reflect.Array;
import java.util.Arrays;

public class Playlist {
	
	private int numSongs = 0;
	private Song [] songs = new Song [MIN_CAPACITY]; 
	private static final int MIN_CAPACITY = 3;
	
	//Constructors
	Playlist()
	{
		numSongs = 0;
	}

	Playlist(int capacity)
	{
		if (capacity > MIN_CAPACITY) {
			this.songs = new Song[capacity];
		}
		numSongs = 0;
	}

	public int getCapacity()
	{
		return songs.length;
	}
	
	public int getNumSongs() {
		return numSongs;
	}
	
	public Song getSong(int index) {
		if (index < 0) {
			return null;
		}
		
		if (index == songs.length) {
			return null;
		}
		return songs[index];
	}
	
	public Song [] getSongs() {
		return Arrays.copyOf(songs, numSongs);
	}
	
	public boolean addSong(Song song) {
		
		int numSpots = 0;
		for (int i = 0; i < songs.length; i++) {
			if (songs[i] == null) {
				++numSpots;
			}
		}
		
		if (numSpots == 0 || song == null) { //if this method is failing, could be this "or" statement
			return false;
		}
		
		/*for (int idx = 0; idx < songs.length; ++idx) {
			if (songs[idx] == null) {
				songs[idx] = song;
				return true;
			}
		}*/
		if (numSongs < songs.length) {
			songs[numSongs++] = song;
			return true;
		}
		
		return false;
	}
	
	public boolean addSong(int index, Song song) {
		int numSpots = 0;
		for (int i = 0; i < songs.length; i++) {
			if (songs[i] == null) {
				++numSpots;
			}
		}
		
		if (numSpots == 0 || song == null) { //if this method is failing, could be this "or" statement
			return false;
		}
		
		if (index < 0 || index >= (songs.length)) { //alternatively could be this code. May need "index > (songs.length + 1)"
			return false;
		}
		
		if(songs[index] != null) {
			return false;
		}
		
		for (int idx = songs.length - 1; idx <= songs.length && idx >= 0; idx--) {
			if (songs[idx] != null && idx != songs.length - 1 && idx >= index) { // Passing this statement means that the idx num has 
				// a song, is not at the top of the array, and is greater or equal to index.
				if (songs[idx + 1] == null) {
					songs[idx + 1] = songs[idx];
					songs[idx] = null;
				}
			}
		} 
		
		//if (songs[index] == null) {
			songs[index] = song;
			++numSongs;
			return true;
		//}
		
		
		//return false; //primary test for failure: switch this to true and see if changes the outcome.
	}
	
	public int addSongs(Playlist playlist) {
		int numSongsAdded = 0;
		if (playlist == null) {
			return numSongsAdded;
		}
		
		int idx = 0;
		
		for (int i = songs.length - 1; i <= songs.length && idx <= playlist.numSongs && i >= 0; --i) {
			if (songs[i] == null) {
				 songs[i] = playlist.songs[idx];
				 ++idx;
				 ++numSongsAdded;
			}
		}
		
		int numSpots = 0;
		for (int i = 0; i < songs.length; i++) {
			if (songs[i] == null) {
				++numSpots;
			}
		}
		
		numSongs = songs.length - numSpots;
		
		return numSongsAdded;
	}
	
	public Song removeSong() {
		
		if (songs == null) {
			return null;
		}
		int numSpots = 0;
		for (int idx = 0; idx < songs.length; idx++) {
			if (songs[idx] == null) {
				++numSpots;
			}
		}
		
		Song [] tempSongArray = Arrays.copyOf(songs, songs.length);
		for (int i = songs.length - 1; i <= songs.length && i >= 0; --i) {
			if (songs[i] != null) {
				songs[i] = null;
				
				numSongs = songs.length - numSpots -1;
				return tempSongArray[i];
			}
		}
		return null;
	}
	
	public Song removeSong(int index) {
		 
		if (index < 0) {
	    	return null;
		}  
		
		if (songs[index] == null) {
		        return null;
		}
		    
		Song removedSong = songs[index];

		    for (int i = index; i < songs.length - 1; i++) {
		        songs[i] = songs[i + 1];
		    }
		    	songs[songs.length - 1] = null;

		    	numSongs--;
		    
		return removedSong;

	}
	

}
