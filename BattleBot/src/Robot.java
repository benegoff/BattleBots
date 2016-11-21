import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;


public class Robot implements ActionListener {
	
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
		testFrontMotor();
		wiggle();
	}
	
	public void testFrontMotor() {
		System.out.println(Motor.C.getPosition());
		Motor.C.rotate(60);
		System.out.println(Motor.C.getPosition());
		Button.waitForAnyPress();
		Motor.C.rotate(-60);
		Button.waitForAnyPress();
	}
	
	public void wiggle() {
		try {
			drivetrain.rotate();
			Thread.sleep(500);
			drivetrain.moveForward();
			Thread.sleep(500);;
			drivetrain.moveBackward();
			Thread.sleep(500);
			drivetrain.rotateRight();
			Thread.sleep(1000);
			drivetrain.moveForward();
			Thread.sleep(500);;
			drivetrain.moveBackward();
			Thread.sleep(500);
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
	
}
