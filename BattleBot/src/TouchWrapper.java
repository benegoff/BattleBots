import lejos.nxt.SensorPort;

public class TouchWrapper implements ITouchSensor {

    private lejos.nxt.TouchSensor sensor;

    /**
     * Constructs a new TouchWrappers
     * @param p - The SensorPort into which the Touch sesnor sensor is plugged
     */
    public TouchWrapper(SensorPort p) {
        sensor = new lejos.nxt.TouchSensor(p);
        // Activate the sensor
        p.activate();
    }

    /**
     * Returns the value read in from the sensor.
     * @return The value read from the hardware sensor
     */
    @Override
    public boolean isPressed() {
       return sensor.isPressed();
    }
}
