package dallastemperaturesensor.error;

public class NoSensorFoundError extends DallasTempSensorDS18Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8971200608101637466L;

	public NoSensorFoundError(String sensor_type, String sensor_id) {
		// TODO Auto-generated constructor stub
		super(String.format("No %s temperature sensor with id %s", sensor_type, sensor_id));
		
	}
}
