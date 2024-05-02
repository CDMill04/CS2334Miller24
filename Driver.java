import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Driver extends JFrame{
	
	// Declare class data
	private JMapViewer mView;
	private JComboBox<Integer> timePicker;
	private JCheckBox stopBox;
	//private JFrame frame;
	private Image raccoonBoi;
	private IconMarker trashPandaFinder;
	private JButton goButton;
	private JPanel newPanel;
	private static final long serialVersionUID = 8675309;
	private static ArrayList<TripPoint> newTrip = new ArrayList<TripPoint>();
	int numPointsPlotted = 0;

	public Driver() throws FileNotFoundException, IOException {
    
    	// Set up frame, include your name in the title
    	/*frame = new JFrame("Project Five - Caleb Miller");
    	frame.setSize(getPreferredSize());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); */
       
        // Play Button
        goButton = new JButton("Play");
    	
        // CheckBox to enable/disable stops
        stopBox = new JCheckBox("Include Stops");
    	
        // ComboBox to pick animation time
        timePicker = new JComboBox<>(new Integer[]{15, 30, 60, 90});
        
     // Set up mapViewer
        newPanel = new JPanel();
        mView = new JMapViewer();
        mView.setTileSource(new OsmTileSource.TransportMap());
        getContentPane().add(mView);
        mView.setVisible(true);
        Coordinate unitedCoords = new Coordinate(37.0902, -95.7129);
        mView.setDisplayPosition(unitedCoords, 5);
        //frame.add(mView, BorderLayout.CENTER);
    	
        // Add all to top panel
        //newPanel.add(frame);
        newPanel.add(new JLabel("Project Five - Caleb Miller"));
        newPanel.add(timePicker);
        newPanel.add(goButton);
        newPanel.add(stopBox);
        getContentPane().add(newPanel, BorderLayout.NORTH);
        getContentPane().setSize(800, 600); 
        
        // Add listeners for GUI components
        
        goButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		try {
					goRaccoon();
					System.out.println(numPointsPlotted);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });

        // Set the map center and zoom level
        setLocationRelativeTo(null);
        
        
    }
	
	private void goRaccoon() throws FileNotFoundException, IOException, InterruptedException{
		TripPoint.readFile("triplog.csv");
		int stops = TripPoint.h2StopDetection();
    	boolean stopBoxOn = stopBox.isSelected();
    		
    	int length = (int) timePicker.getSelectedItem();
    	mView.removeAllMapMarkers();
    	mView.removeAllMapPolygons();
    	
    	/*if (stopBoxOn == true) {
    		stops = TripPoint.h2StopDetection();
    	}
    	else {
    		TripPoint.getTrip();
    	} */
    	
    	raccoonBoi = Toolkit.getDefaultToolkit().getImage("raccoon.png");
        newTrip = TripPoint.getMovingTrip();
        List<Coordinate> lineCoords = new ArrayList<>();
    	
    	int stopInterval = (int) (length/newTrip.size());
    	
    	for(int i = 0; i < newTrip.size(); i++) {
    
    		Coordinate newCoords = getCoords(i);
    		lineCoords.add(newCoords);
    		
    		if (stopBoxOn) {
    		trashPandaFinder = new IconMarker(newCoords, raccoonBoi);
    		}
    		
    		if (i > 0) {
    			Coordinate oldCoords = lineCoords.get(i - 1);
    			Coordinate[] lineCoordArray = {oldCoords, newCoords};
    			//MapPolygonImpl newLine = new MapPolygonImpl(lineCoordArray);
    			mView.addMapPolygon(new MapPolygonImpl(lineCoordArray));
    		}
    		mView.repaint();
    		numPointsPlotted++;
    		Thread.sleep(stopInterval * 1000);
    		
    	}
		
	}
	
	public Coordinate getCoords(int stopPoint) {
		Coordinate newCoords = new Coordinate(newTrip.get(stopPoint).getLat(), newTrip.get(stopPoint).getLon());
		return newCoords;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
    	
    	new Driver().setVisible(true);
    	
		
	}
			
}
	
	
  