import lejos.nxt.NXTRegulatedMotor;

public class RobotArm {

	private NXTRegulatedMotor motor;
	public RobotArm(NXTRegulatedMotor m){
		motor = m;
	}
	
	public void close(){
		motor.rotate(60);
	}
	
	public void open(){
		motor.rotate(-60);
	}
	
}
