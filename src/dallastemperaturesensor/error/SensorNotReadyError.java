package dallastemperaturesensor.error;

public class SensorNotReadyError extends DallasTempSensorDS18Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932327619780135326L;

	public SensorNotReadyError() {
		// TODO Auto-generated constructor stub
	}

	public SensorNotReadyError(String msg) {
		super("Sensor is not yet ready to read temperature");
		// TODO Auto-generated constructor stub
	}

}
