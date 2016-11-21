import lejos.nxt.MotorPort;


public class Robot {
	
	private Action currentState; 
	private Drivetrain drivetrain;

	public Robot() {
		drivetrain = new Drivetrain(MotorPort.A, MotorPort.B);
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
	
	private boolean isWiggling() {
		return currentState == Action.Wiggle;
	}
}
