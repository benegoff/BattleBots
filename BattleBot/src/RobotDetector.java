import java.util.ArrayList;
import java.util.List;

public class RobotDetector implements SightSensorListener, TouchListener{

	public static final int MIN_DISTANCE = 3;
	public static final int MAX_DISTANCE = 25;
	
	private SightSensor sightSensor;
	private TouchSensor touchSensor;
	private List<ActionListener> actionListeners;
	
	public RobotDetector(SightSensor sightSensor, TouchSensor touchSensor){
		actionListeners = new ArrayList<ActionListener>();
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
		Action sendingAction;
		
		if(e.isTouching()) {
			sendingAction = Action.Flipping;
		} else {
			sendingAction = Action.Releasing;
		}
		
		for(ActionListener al : actionListeners){
			al.onActionEvent(new ActionEvent(sendingAction));
		}
	}

	@Override
	public void onSightEvent(SightSensorEvent e) {
		if(e.getDistance() > MIN_DISTANCE && e.getDistance() < MAX_DISTANCE)
			for(ActionListener al : actionListeners){
				al.onActionEvent(new ActionEvent(Action.MovingForward));
			}
	}
	
}
