import lejos.nxt.Motor;


public class Robot {

	public Robot() {
		
	}
	
	public void start() {
		
	}
	
	public void testFrontMotor() {
		System.out.println(Motor.C.getPosition());
		Motor.C.rotate(30);
		System.out.println(Motor.C.getPosition());
		Motor.C.rotate(-30);
		System.out.println(Motor.C.getPosition());
		Motor.C.rotate(-30);
		System.out.println(Motor.C.getPosition());
		Motor.C.rotate(30);
		System.out.println(Motor.C.getPosition());
	}
}
