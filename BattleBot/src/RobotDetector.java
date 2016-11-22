import java.util.ArrayList;
import java.util.List;

public class RobotDetector implements SightSensorListener, TouchListener{

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
	
	@Override
	public void onTouch(TouchEvent e) {
		if(e.isTouching()) {
			for(ActionListener al : actionListeners){
				al.onActionEvent(new ActionEvent(Action.Flipping));
			}
		}
	}

	@Override
	public void onSightEvent(SightSensorEvent e) {
		for(ActionListener al : actionListeners){
			al.onActionEvent(new ActionEvent(Action.MovingForward));
		}
	}
	
}
