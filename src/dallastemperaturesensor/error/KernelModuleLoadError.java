package dallastemperaturesensor.error;

public class KernelModuleLoadError extends DallasTempSensorDS18Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2800399687165019409L;

	public KernelModuleLoadError() {
		// TODO Auto-generated constructor stub
		super("Can not load w1 therm kernel modules");	
	}
}
