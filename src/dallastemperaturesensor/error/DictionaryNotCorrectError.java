package dallastemperaturesensor.error;

public class DictionaryNotCorrectError extends DallasTempSensorDS18Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5957583386887664626L;

	public DictionaryNotCorrectError() {
		// TODO Auto-generated constructor stub
	}

	public DictionaryNotCorrectError(String msg) {
		super("dictionary is not correct : " + msg);
		// TODO Auto-generated constructor stub
	}

}
