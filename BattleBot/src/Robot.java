import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;


public class Robot {
	
	private Drivetrain drivetrain;

	public Robot() {
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
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
}
