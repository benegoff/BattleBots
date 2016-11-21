import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class TouchWrapper implements ITouchSensor {

    private lejos.nxt.TouchSensor sensor;

    /**
     * Constructs a new UltrasonicWrapper
     * @param p - The SensorPort into which the Ultrasonic sensor is plugged
     */
    public TouchWrapper(SensorPort p) {
        sensor = new TouchWrapper(p);
        // Activate the sensor
        p.activate();
        // Set the sensor mode
        sensor.continuous();
    }

    @Override
    public boolean isPressed() {
       return sensor.isPressed();
    }

    /**
     * Returns the distance read from the sensor.
     * @return The distance read from the sensor
     */
}
