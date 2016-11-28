import lejos.nxt.NXTRegulatedMotor;

public class RobotArm {

	private NXTRegulatedMotor motor;
	private boolean isClosed;
	private final int startingDegrees;
	
	public RobotArm(NXTRegulatedMotor m){
		isClosed = false;
		startingDegrees = m.getPosition();
		motor = m;
		motor.setSpeed(720);
	}
	
	public void close(){
		if(!isClosed()) {
			motor.rotateTo(startingDegrees + 90);;
			isClosed = true;
		}
	}
	
	public void open(){
		if(isClosed()) {
			motor.rotateTo(startingDegrees);
			isClosed = false;
		}
	}
	
	public boolean isClosed(){
		return isClosed;
	}
	
}
