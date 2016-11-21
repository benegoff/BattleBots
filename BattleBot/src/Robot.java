import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;


public class Robot implements ActionListener {
	
	private Action currentState; 
	private Drivetrain drivetrain;
	private RobotDetector detector;
	private RobotArm arm;

	public Robot() {
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
		SightSensor s = new SightSensor(new UltrasonicWrapper(SensorPort.S1));
		TouchSensor t = new TouchSensor(new TouchWrapper(SensorPort.S3));
		detector = new RobotDetector(s, t);
		detector.registerListener(this);
		arm = new RobotArm(Motor.C);
	}
	
	public void start() {
		currentState = Action.Wiggle;
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
			drivetrain.moveForward();
			break;
		case Flipping:
			arm.close();
			break;
		}
	}
	
	
	private boolean isWiggling() {
		return currentState == Action.Wiggle;
	}
}
