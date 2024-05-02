import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;
public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		this.songs = new ArrayList<>();
	}
	
	public Playlist(String filename) throws IOException
	{
		this.songs = new ArrayList<>();
		addSongs(filename);
		//System.out.println(songs);
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		if (song == null || index < 0 || index > songs.size()) 
		{
			return false;
		}
		songs.add(index, song);
		return true;
	}
	
	public int addSongs(Playlist playlist) {
		if (playlist == null)
		{
			return 0;
		}
		
		int numAdded = 0;
		for (int i = 0; i < playlist.getNumSongs(); i++)
		{
			Song songToAdd = playlist.getSong(i);
			songs.add(songToAdd);
			++numAdded;
		}
		return numAdded;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		
		if (index < 0 || index > (songs.size() - 1))
		{
			return null;
		}
		
		Song removedSong = songs.get(index);
		songs.remove(index);
		return removedSong;
	}
	
	//New Methods
	
	public int addSongs(String filename) throws IOException
	{
		BufferedReader buffFile = new BufferedReader(new FileReader(filename));
		int count = 0;
		String newLine;
		while ((newLine = buffFile.readLine()) != null)
		{
			String[] songInfo = newLine.split("; ");
			
			String title = songInfo[0].trim();
            String artist = songInfo[1].trim();
            
            String[] timeParts = songInfo[2].trim().split(":");
            int[] time = new int[timeParts.length];
            for (int i = 0; i < timeParts.length; i++) {
                time[i] = Integer.parseInt(timeParts[i].trim());
            }
            
            if (time.length == 1) {
                int seconds = time[0];
                time = new int[]{seconds};
            }

            if (time.length == 2) {
                int minutes = time[0];
                int seconds = time[1];
                time = new int[]{seconds, minutes};
            }
            
            Song song = new Song(title, artist, time);
            songs.add(song);
            ++count;
		}
		
		buffFile.close();
		return count;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(System.lineSeparator(), "", "");
			for (Song song: songs)
			{
				int [] time = song.getTime();
				String title = song.getTitle();
				String artist = song.getArtist();
				
				int minutes1 = time.length > 1 ? time[0] : 0;
		        int seconds1 = time.length > 0 ? time[1] : time[0];
		        String timeString = String.format("%d:%02d", seconds1, minutes1);
		        //String timeString = minutesString + ":" +secondsString;
		        
				joiner.add(title + "; " + artist + "; " + timeString);
			}
			return joiner.toString();
	}
	
	public void saveSongs(String filename) throws IOException
	{
		Path filePath = Paths.get(filename);
		String playlist = this.toString();
		Files.write(filePath, playlist.getBytes());
	}
	
	public int[] getTotalTime()
	{
		int[] totalTime = new int[3];
	    for (Song song : songs) {
	        int[] songTime = song.getTime();

	        for (int i = 0; i < songTime.length; i++) {
	            totalTime[i] += songTime[i];
	        }

	        if (totalTime[0] == 0) {
	            int[] finalTime = new int[1];
	            finalTime[0] = 0;
	            return finalTime;
	        }

	        if (totalTime[2] == 0 && totalTime[1] == 0 && totalTime[0] < 60) {
	            int[] finalTime = new int[1];
	            finalTime[0] = totalTime[0];
	            return finalTime;
	        }

	        if (totalTime[2] == 0 && (totalTime[1] != 0 || totalTime[0] != 0)) {
	            if (totalTime[0] >= 60) {
	                totalTime[1] += totalTime[0] / 60;
	                totalTime[0] %= 60;
	                if (totalTime[1] >= 60) {
	                    totalTime[2] += totalTime[1] / 60;
	                    totalTime[1] %= 60;
	                    return totalTime;
	                }
	                int[] finalTime = new int[2];
	                finalTime[0] = totalTime[0];
	                finalTime[1] = totalTime[1];
	                return finalTime;
	            }
	        }

	        if (totalTime[2] != 0) {
	            if (totalTime[0] >= 60) {
	                totalTime[1] += totalTime[0] / 60;
	                totalTime[0] %= 60;
	            }
	            if (totalTime[1] >= 60) {
	                totalTime[2] += totalTime[1] / 60;
	                totalTime[1] %= 60;
	            }
	            return totalTime;
	        }
	    }
	    
	    return new int[]{0};
	}
		
}
