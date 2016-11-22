import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;


public class Robot implements ActionListener {
	
	private Action currentState; 
	private Drivetrain drivetrain;
	private RobotDetector detector;
	private RobotArm arm;

	public Robot() {
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
		arm = new RobotArm(Motor.C);
		SightSensor s = new SightSensor(new UltrasonicWrapper(SensorPort.S1));
		TouchSensor t = new TouchSensor(new TouchWrapper(SensorPort.S3));
		detector = new RobotDetector(s, t);
		detector.registerListener(this);
	}
	
	public void start() {
		currentState = Action.Wiggle;
		while(true) {
			if(currentState == Action.Wiggle) 
				wiggle();
			Thread.yield();
		}
	}

	public void wiggle() {
		halfRotate();
		while(isWiggling()) {
			forwardBackward();
			rotate();
			forwardBackward();
			fullRotate();
		}
	}
	
	private void halfRotate() {
		if(isWiggling()) {
			drivetrain.rotate();
			sleepForTime(.5f);
		}
	}

	private void rotate() {
		if(isWiggling()) {
			drivetrain.rotate();
			sleepForTime(1);
		}
	}
	
	private void fullRotate() {
		if(isWiggling()) {
			drivetrain.rotate();
			sleepForTime(5);
		}
	}
	
	private void forwardBackward() {
		if(isWiggling()) {
			drivetrain.moveForward();
			sleepForTime(1);
			drivetrain.moveBackward();
			sleepForTime(1);
		}
	}
	
	
	private void sleepForTime(float seconds) {
		try {
			Thread.sleep((long)(seconds * 1000.0f));
		} catch(InterruptedException e) {
			
		}
	}

	@Override
	public void onActionEvent(ActionEvent action) {
		switch(action.getAction()){
		case MovingForward:
			changeStateMovingForward();
			break;
		case Flipping:
			arm.close();
			break;
		}
	}
	
	private void changeStateMovingForward() {
		// We don't want to move forward if we are in the following states
		// Flipping
	}
	
	
	private boolean isWiggling() {
		return currentState == Action.Wiggle;
	}
}
