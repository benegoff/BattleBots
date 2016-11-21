import lejos.nxt.NXTRegulatedMotor;

public class RobotArm {

	private NXTRegulatedMotor motor;
	private boolean isClosed;
	public RobotArm(NXTRegulatedMotor m){
		isClosed = false;
		motor = m;
	}
	
	public void close(){
		motor.rotate(60);
		isClosed = true;
	}
	
	public void open(){
		motor.rotate(-60);
		isClosed = false;
	}
	
	public boolean isClosed(){
		return isClosed;
	}
	
}
