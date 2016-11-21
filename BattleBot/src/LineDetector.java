
import java.util.ArrayList;
import java.util.List;


public class LineDetector implements LightSensorListener {

    private List<LineListener> listeners;

    private boolean onLine;

    /**
     * Constructs a new LineDetector.
     * @param sensor the {@link LightSensor} to which the LineDetector
     * will register for events
     */
    public LineDetector(LightSensor sensor) {
        // Register with the light sensor
        sensor.registerListener((EventListener)this);
        // Create the list of listeners
        listeners = new ArrayList<LineListener>();
        // Set the initial value of onLine
        onLine = false;
    }

    /**
     * Adds the given listener to the list of listeners.
     * @param listener - The listener to register
     */
    public void registerListener(LineListener listener) {
        // Add the listener to the list
        listeners.add(listener);
    }

    /**
     * Removes the given listener from the list of listeners.
     * @param listener - The listener to unregister
     */
    public void unregisterListener(LineListener listener) {
        // Remove the listener from the registered listeners list
        listeners.remove(listener);
    }

    /**
     * Implementation of the onLightSensorEvent method from LightSesnorListener.
     * @param e - The LightSensorEvent that was raised
     */
    @Override
    public void onLightSensorEvent(LightSensorEvent e) {
        final int LINE_THRESHOLD = 40; //47
        // Determine if the event data is above a line threshold
        boolean foundLine = e.getLightValue() > LINE_THRESHOLD;
        // If the event data is different the last event,
        if(foundLine) {
            // Update the value of onLine
            onLine = foundLine;
            // Notify listeners with a new LineEvent
            notifyListeners(new LineEvent(foundLine));
        }
    }

    /**
     * Calls the event listening method on each of the event listeners.
     * @param e - The LightSensorEvent that was raised
     */
    private void notifyListeners(LineEvent e) {
        // For each listener in the list of registered listeners...
        for(LineListener l : listeners) {
            // call the event listening method
            l.onLineEvent(e);
        }
    }
}