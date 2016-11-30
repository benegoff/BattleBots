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
	
	private LightSensor lightSensor;
	private SightSensor sightSensor;
	private TouchSensor touchSensor;

	public Robot() {
		lightSensor = new LightSensor(new LejosLightWrapper(SensorPort.S2));
		lineDetector = new LineDetector(lightSensor);
		lineDetector.registerListener(this);
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
		arm = new RobotArm(Motor.C);
		sightSensor = new SightSensor(new UltrasonicWrapper(SensorPort.S1));
		touchSensor = new TouchSensor(new TouchWrapper(SensorPort.S3));
		detector = new RobotDetector(sightSensor, touchSensor);
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
//		case Releasing: 
//			changeStateReleasing();
//			break;
		case MovingBackward:
			changeStateMovingBackward();
			break;
		default:
			changeStateRotateLeft();
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
		if(currentState != null) {
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
				case Wiggle:
					changeStateBackup();
					break;
				case MovingBackward:
					changeStateRingReturn();
					break;
				case Clearing:
					changeStateReleasing();
					break;
				default:
					changeStateRotateLeft();
					break;
				}
			}
			else {
//				switch(currentState) {
//				case RotateLeft:
//				case RotateRight:
//					changeStateRingReturn();
//					break;
//				default:
//					break;
//				}
			}
		}
	}
	
	/* ===== STATE CHANGE METHODS ===== */
	
	private void changeStateRingReturn() {
		if(currentState != Action.RingReturn) {
			System.out.println("RingReturn");
			currentState = Action.RingReturn;
			drivetrain.moveForward();
			Delay.msDelay(500);
		}
		// Return to the idle state
		changeStateRotateLeft();
	}
	
	private void changeStateBackup() {
		if(currentState != Action.Backup) {
			System.out.println("Backup");
			currentState = Action.Backup;
			drivetrain.moveBackward();
			Delay.msDelay(1500);
		}
		// Return to the idle state
		changeStateRotateLeft();
	}
	
	private void changeStateMovingForward() {
		// We don't want to move forward if we are in the following states
		if(currentState != Action.Clearing) {
			System.out.println("Forward");
			currentState = Action.MovingForward;
			drivetrain.moveForward();
		}
	}
	
	private void changeStateMovingBackward() {
		if(currentState != Action.Clearing) {
			System.out.println("Backward");
			currentState = Action.MovingBackward;
			drivetrain.moveBackward();
		}
	}
	
	private void changeStateRotateRight() {
		if(currentState != Action.Clearing) {
			System.out.println("RotateRight");
			currentState = Action.RotateRight;
			drivetrain.rotateRight();
		}
	}
	
	private void changeStatePivotRight() {
		if(currentState == Action.RotateRight || currentState == Action.RotateLeft) {
			System.out.println("PivotRight");
			currentState = Action.PivotRight;
			drivetrain.pivotRight();
		}
	}
	
	private void changeStateRotateLeft() {
		if(currentState != Action.Clearing) {
			drivetrain.stopMovement();
			System.out.println("RotateLeft");
			currentState = Action.RotateLeft;
			drivetrain.rotateLeft();
		}
	}
	
	private void changeStatePivotLeft() {
		if(currentState == Action.RotateRight || currentState == Action.RotateLeft) {
			drivetrain.stopMovement();
			System.out.println("PivotLeft");
			currentState = Action.PivotLeft;
			drivetrain.pivotLeft();
		}
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
		drivetrain.stopMovement();
		currentState = Action.Releasing;
		// Open the robot arm
		arm.open();
		// Set the power back to normal
		drivetrain.setCurrentPower(Drivetrain.POWER);
		// backup 
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
			Delay.msDelay((long).75f * 1000);
		}
	}
	
	private void wiggleLeft() {
		if(isWiggling()) {
			drivetrain.rotateLeft();
			Delay.msDelay((long)1.25f * 1000);
		}
	}
	
	private void wiggleRight() {
		if(isWiggling()) {
			drivetrain.rotateRight();
			Delay.msDelay((long)1.5f * 1000);
		}
	}
	
	private boolean isWiggling() {
		return currentState == Action.Wiggle;
	}
}
