package dallastemperaturesensor.error;

public class DallasTempSensorDS18Error extends Exception {
	/**
	 * @param args
	 * Exception Base class for DS18B20 sensor errors
	 */
	private static final long serialVersionUID = -2725560978436353726L;
	public DallasTempSensorDS18Error(){
		super();
	}
	public DallasTempSensorDS18Error(String msg){
		super(msg);
	}

}
