import java.util.ArrayList;
import java.util.List;

public class RobotDetector implements SightSensorListener, TouchListener{

	public static final int MIN_DISTANCE = 3;
	public static final int MAX_DISTANCE = 25;
	
	private SightSensor sightSensor;
	private TouchSensor touchSensor;
	private List<ActionListener> actionListeners;
	private boolean isPressed;
	private boolean foundRobot;
	
	public RobotDetector(SightSensor sightSensor, TouchSensor touchSensor){
		actionListeners = new ArrayList<ActionListener>();
		isPressed = false;
		foundRobot = false;
		this.sightSensor = sightSensor;
		this.sightSensor.registerListener(this);
		this.touchSensor = touchSensor;
		this.touchSensor.registerListener(this);
		
	}

	public void registerListener(ActionListener al){
		actionListeners.add(al);
	}
	
	public void unregisterListener(ActionListener al){
		actionListeners.remove(al);
	}
	
	/**
	 * Called when a touch event is sent up from the touch detector.
	 * Because the touch detector only sends changes in the state of
	 * touch sensor, this method is only called when the sensor is 
	 * first pressed or first released.
	 * @param e - The event that was raised.
	 */
	@Override
	public void onTouch(TouchEvent e) {		
		if(isPressed != e.isTouching()) {
			Action sendingAction;
			// Update isPressed
			isPressed = e.isTouching();
			sendingAction = isPressed ? Action.Flipping : Action.Releasing;

			for(ActionListener al : actionListeners){
				al.onActionEvent(new ActionEvent(sendingAction));
			}
		}
	}

	@Override
	public void onSightEvent(SightSensorEvent e) {
		boolean inRange = e.getDistance() > MIN_DISTANCE && e.getDistance() < MAX_DISTANCE;
		if(inRange != foundRobot) {
			// Update the value of foundRobot
			foundRobot = inRange;
			// A change occurred, send the event
			Action sendingAction = inRange ? Action.MovingForward : Action.MovingBackward;
			// Send the event
			for(ActionListener al : actionListeners){
				al.onActionEvent(new ActionEvent(sendingAction));
			}
		}
	}
	
}
