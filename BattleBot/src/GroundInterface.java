import lejos.nxt.MotorPort;

public class GroundInterface implements LineListener {
	
	private ActionListener listener;
	private Drivetrain drivetrain;
	private Action state;
	/**
	 * Constructs a new GroundInterface 
	 * @param lineDetection - The LineDetector with which to register
	 * @param canDetection - The CanDetector with which to register
	 */
	public GroundInterface(LineDetector lineDetection, ActionListener listener) {
		// Create the list of listeners
		this.listener = listener;
		// Create the drive train
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
		// Register with the line detector
		lineDetection.registerListener(this);
	}
	
	public void unregisterListener() {
		listener = null;
	}
	
	/**
	 * Calls to the drive train to start moving forward.
	 */
	public void moveForward() {
		// Tell the drive train to start moving forward
		drivetrain.moveForward();
		state = Action.MovingForward;
	}
	
	/**
	 * Calls to the drive train to start moving backward.
	 */
	public void moveBackward() {
		// Tell the drive train to start moving backward
		drivetrain.moveBackward();
		state = Action.MovingBackward;
	}
	
	/**
	 * Calls to the drive train to start rotating.
	 */
	public void rotate() {
		// Tell the drive train to start rotating
		drivetrain.rotate();
		state = Action.Rotating;
	}
	
	public void stop() {
		drivetrain.stopMovement();
	}

	/**
	 * Implementation of the onLightSensorEvent from the LightSensorListener interface.
	 * @param e - The LightSensorEvent that was raised
	 */
	@Override
	public void onLineEvent(LineEvent e) {
		// TODO: Event handling
	}
	
	/**
	 * Method designed to get the system to leave the bordered area as quickly as possible
	 */
	public void leaveArea() {
		// we did not include this in the general functions created
		state = Action.Finished;
		moveForward();
	}

}
