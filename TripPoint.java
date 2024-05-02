import java.util.ArrayList;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TripPoint {

	private double lat;
	private double lon;
	private int time;
	private static ArrayList<TripPoint> trip = new ArrayList<TripPoint>();
	protected static String finalLine = null;
	
	TripPoint (int time, double lat, double lon)
	{
		this.lat = lat;
		this.lon = lon;
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public static ArrayList<TripPoint> getTrip() {
		return trip;
	}
	
	public static void readFile(String filename) throws IOException {
		
		trip = new ArrayList<>();
		BufferedReader buff = new BufferedReader(new FileReader(filename));
		String newLine;
		String timeSkip = "Time";
		
		while ((newLine = buff.readLine()) != null) {
			String[] info = newLine.split(",");
			if (info[0].equals(timeSkip)) {
				continue;
			}
			
			int newTime = Integer.parseInt(info[0]);
			double newLat = Double.parseDouble(info[1]);
			double newLon = Double.parseDouble(info[2]);
			TripPoint newTripPoint = new TripPoint(newTime, newLat, newLon);
			trip.add(newTripPoint);
			finalLine = newLine;
			
		}
		
		buff.close();
	}
	
	public static double totalTime() {
		/*TripPoint tempStore;
		double timeBank = 0;
		double tempTime = 0;
		
		for (int i = 0; i < trip.size(); i++) {
			tempStore = trip.get(i);
			tempTime = tempStore.getTime();
			tempTime = tempTime / 60;
			timeBank = timeBank + tempTime;
			
		} */
		//Was way overthinking it
		
		String[] forTotalTime = finalLine.split(",");
		double totTime = Integer.parseInt(forTotalTime[0]); //gives total time in minutes
		//totTime = Math.round((totTime / 60) * 100) / 100; 
		//Now this should hopefully convert to hours. Pray
		//Well, now Ive got the first value right. Now to stop it truncating the end. Update: Gonna try using String and converting back
		totTime = totTime / 60;
		String toConvert = String.format("%.2f", totTime);
		totTime = Double.parseDouble(toConvert);
		//Update: >:)
		
		return totTime;

	}
	
	public static double haversineDistance(TripPoint a, TripPoint b) {
		
		double eartRad = 6371;
		double distLat = Math.toRadians(b.getLat()) - Math.toRadians(a.getLat());
		double distLon = Math.toRadians(b.getLon())  - Math.toRadians(a.getLon());
		
		//Haversine formula SUCKS
		double sideA = Math.sin(distLat / 2) * Math.sin(distLat / 2) + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat())) * Math.sin(distLon / 2) * Math.sin(distLon / 2);
		double angC = 2 * Math.atan2(Math.sqrt(sideA), Math.sqrt(1 - sideA));
		double dist = eartRad * angC;
		//dist = Math.round(dist * 10.0);
		String toConvert = String.format("%.1f", dist);
		dist = Double.parseDouble(toConvert); //Math.round also sucks. It never works properly for me.
		return dist;
	}
	
	public static double avgSpeed(TripPoint a, TripPoint b) {
		double dist = haversineDistance(a, b);
		double timeBetween = b.getTime() - a.getTime();
		timeBetween = Math.abs(timeBetween);
		double avgVelocity = dist / timeBetween; //In minutes
		avgVelocity = avgVelocity * 60; //in hrs YES IT WORKS RAHHHHHHH
		String toConvert = String.format("%.2f", avgVelocity);
		avgVelocity = Double.parseDouble(toConvert);
		
		return avgVelocity;
	}
	
	public static double totalDistance() {
		String[] lastPoints = finalLine.split(",");
		int finalTime = Integer.parseInt(lastPoints[0]);
		TripPoint tempStore;
		TripPoint tempStore2;
		double totDist = 0;
		
		for (int i = 0; i < trip.size() - 1; i++) {
			tempStore = trip.get(i);
			
			if (tempStore.getTime() < finalTime) { //Why this part? I want to prevent an exception if it tries to reach past the final value.
				tempStore2 = trip.get(i + 1);
				double distBetween = haversineDistance(tempStore, tempStore2);
				totDist = distBetween + totDist;
				//totDist = totDist.add(BigDecimal.valueOf(distBetween));
			}
		}
		//totDist.setScale(3, RoundingMode.HALF_UP);
		//double finalDistance = totDist.doubleValue();
		return totDist; //Rounding errors like crazy. Gonna have to BigDecimal it. Update: didnt work. Going back.
		//Going to speculate not rounding errors. Might be a bounds issue.
	}
	
}
