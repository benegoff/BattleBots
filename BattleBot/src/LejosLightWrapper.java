import lejos.nxt.SensorPort;

public class LejosLightWrapper implements ILightSensor {

	private lejos.nxt.LightSensor sensor;
	
	public LejosLightWrapper(SensorPort port){
		sensor = new lejos.nxt.LightSensor(port);
	}
			
	@Override
	public int getLightReading() {
		return sensor.getLightValue();
	}

}
