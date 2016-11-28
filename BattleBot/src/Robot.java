import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class Robot implements ActionListener, LineListener {
	
	private Action currentState; 
	private LineDetector lineDetector;
	private Drivetrain drivetrain;
	private RobotDetector detector;
	private RobotArm arm;

	public Robot() {
		lineDetector = new LineDetector(new LightSensor(new LejosLightWrapper(SensorPort.S2)));
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
		lineDetector.registerListener(this);
		arm = new RobotArm(Motor.C);
		SightSensor s = new SightSensor(new UltrasonicWrapper(SensorPort.S1));
		TouchSensor t = new TouchSensor(new TouchWrapper(SensorPort.S3));
		detector = new RobotDetector(s, t);
		detector.registerListener(this);
	}
	
	public void start() {
		changeStateWiggle();
		while(true) {
			Thread.yield();
		}
	}
	
	/**
	 * Called when an action event is received from the robot detector.
	 * @param action - The event that was raised
	 */
	@Override
	public void onActionEvent(ActionEvent action) {
		switch(action.getAction()){
		case MovingForward:
			changeStateMovingForward();
			break;
		case Flipping:
			changeStateFlipping();
			break;
		case Releasing: 
			changeStateReleasing();
			break;
		default:
			changeStateWiggle();
			break;
		}
	}
	
	/**
	 * Called when a line event is sent from the line detector. 
	 * Because the line detector only sends events when a line is
	 * first found and when a line is no longer found, this event
	 * is called when the state of a line being found has occurred.
	 * @param e - The event that was raised
	 */
	@Override
	public void onLineEvent(LineEvent e) {
		/* 
		 * Cases to handle - Found line: 
		 * 1) Moving forward -> Backup
		 * 2) Backward -> Move forward
		 * 3) Rotating Left -> Rotate right
		 * 4) Rotating right -> Rotate left
		 * 5) Flipping -> Releasing
		 * Special cases: 
		 * 1) Getting stuck between Rotate right and rotate left, 
		 * 		Need to rotate for longer - Half rotate then rotate?
		 */
		if(e.isOnLine()) {
			switch(currentState) {
			case RotateLeft:
				changeStateRotateRight();
				break;
			case RotateRight:
				changeStateRotateLeft();
				break;
			case MovingForward:
				changeStateBackup();
				break;
			case Clearing:
				changeStateReleasing();
				break;
			default:
				changeStateRotateLeft();
				break;
			}
		}
	}
	
	/* ===== STATE CHANGE METHODS ===== */
	
	private void changeStateBackup() {
		if(currentState != Action.Backup) {
			System.out.println("Backup");
			currentState = Action.Backup;
			drivetrain.moveBackward();
			Delay.msDelay(1500);
		}
		// Return to the idle state
		changeStateWiggle();
	}
	
	private void changeStateMovingForward() {
		// We don't want to move forward if we are in the following states
		if(currentState != Action.Clearing) {
			System.out.println("Forward");
			currentState = Action.MovingForward;
			drivetrain.moveForward();
		}
	}
	
	private void changeStateRotateRight() {
		System.out.println("RotateRight");
		currentState = Action.RotateRight;
		drivetrain.rotateRight();
	}
	
	private void changeStateRotateLeft() {
		System.out.println("RotateLeft");
		currentState = Action.RotateLeft;
		drivetrain.rotateLeft();
	}
	
	private void changeStateFlipping() {
		System.out.println("Flipping");
		currentState = Action.Flipping;
		// Close the arm
		arm.close();
		// Change the state to clearing
		changeStateClearing();
	}
	
	private void changeStateClearing() {
		System.out.println("Clearing");
		currentState = Action.Clearing;
		// Set the power to 100
		drivetrain.setCurrentPower(100);
		// Move forward
		drivetrain.moveForward();
	}
	
	private void changeStateReleasing() {
		System.out.println("Releasing");
		currentState = Action.Releasing;
		// Open the robot arm
		arm.open();
		// Set the power back to normal
		drivetrain.setCurrentPower(Drivetrain.POWER);
		// Change the state to backup
		changeStateBackup();
	}
	
	private void changeStateWiggle() {
		System.out.println("Wiggle");
		currentState = Action.Wiggle;
		// Perform half rotate
		halfRotate();
		while(isWiggling()) {
			wiggleLeft();
			wiggleRight();
		}
	}
	
	private void halfRotate() {
		if(isWiggling()) {
			drivetrain.rotateRight();
			wiggleWaiter(.75f);
		}
	}
	
	private void wiggleLeft() {
		if(isWiggling()) {
			drivetrain.rotateLeft();
			wiggleWaiter(1.25f);
		}
	}
	
	private void wiggleRight() {
		drivetrain.rotateRight();
		wiggleWaiter(1.25f);
	}
	
	/**
	 * A specialized wait that keeps a program in scope unless the state
	 * of the machine changes.
	 * @param seconds - The time to wait, in seconds
	 */
	private void wiggleWaiter(float seconds) {
		long endTime = System.currentTimeMillis() + (long)(seconds * 1000);
		while(System.currentTimeMillis() < endTime && isWiggling()) {
			Thread.yield();
		}
	}
	
	private boolean isWiggling() {
		return currentState == Action.Wiggle;
	}
}
