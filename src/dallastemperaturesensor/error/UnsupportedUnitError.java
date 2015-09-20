package dallastemperaturesensor.error;

public class UnsupportedUnitError extends DallasTempSensorDS18Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3322800343628656427L;

	public UnsupportedUnitError() {
		// TODO Auto-generated constructor stub
	}

	public UnsupportedUnitError(String msg) {
		super("Only Degress C, F and Kelvin are currently supported");
		// TODO Auto-generated constructor stub
	}

}
