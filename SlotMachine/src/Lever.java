import lejos.nxt.SensorPort;

public class Lever implements TouchListener	{
	
	private TouchDetector leverButton;
	
	/**
	 * Constructs a new Level object.
	 * @param p - The SensorPort to which the lever button is connected
	 */
	public Lever(SensorPort p) {
		// Create a TouchDetector for the lever button
		leverButton = new TouchDetector(new TouchSensorWrapper(p));
		// Register with the lever button
		leverButton.registerListener(this);
	}
	
	/**
	 * Listens for touch events from the lever button.
	 * @param e - The event that was raised
	 */
	@Override
	public void onTouchEvent(TouchEvent e) {
		// TODO: Handle the event 
	}
}
