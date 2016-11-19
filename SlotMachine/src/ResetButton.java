import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;

public class ResetButton implements TouchListener {

	private TouchDetector detector;
	private boolean isResetButtonPushed;
	
	private List<ResetButtonListener> listeners;
	
	/**
	 * Constructs a new ResetButton with the given information.
	 * @param p - The sensor port to which the rest button is connected
	 */
	public ResetButton(SensorPort p) {
		// Set the value of isResetButtonPushed
		isResetButtonPushed = false;
		// Create the list of listeners
		listeners = new ArrayList<ResetButtonListener>();
		// Create the touch detector for the reset button
		detector = new TouchDetector(new TouchSensorWrapper(p));
		// Register with the touch detector
		detector.registerListener(this);
	}
	
	/**
	 * Registers a ResetButtonListener to the list of listeners.
	 * @param listener - The listener to register
	 */
	public void registerListener(ResetButtonListener listener) {
		// Add the listener to the list of listeners
		listeners.add(listener);
	}
	
	/**
	 * Unregisters a ResetButtonListener from the list of listeners.
	 * @param listener - The listener to unregister
	 */
	public void unregiseterListener(ResetButtonListener listener) {
		// Remove the listener from the list of listeners
		listeners.remove(listener);
	}
	
	/**
	 * Implementation of the {@link TouchListener#onTouchEvent(TouchEvent)} method.
	 * @param e - The event that was raised
	 */
	@Override
	public void onTouchEvent(TouchEvent e) {
		// If the event is different from the stored data
		if(e.getIsPressed() != isResetButtonPushed) {
			// Set the value of the current state
			isResetButtonPushed = e.getIsPressed();
			// Send the event to the listeners
			notifyListeners(isResetButtonPushed);
		}
	}
	
	/**
	 * Notifies the listeners in the list of registered listeners.
	 * @param state - The state of the reset button to send
	 */
	private void notifyListeners(boolean state) {
		// For each listener in the list of registered listeners...
		for(ResetButtonListener l : listeners) {
			// Call the listening method
			l.onResetButtonEvent(state);
		}
	}
}
