package test;


import dallastemperaturesensor.api.DallasTempSensorDS18;
import dallastemperaturesensor.api.RESOLVE_TYPE_STR;
import dallastemperaturesensor.error.DictionaryNotCorrectError;
import dallastemperaturesensor.error.KernelModuleLoadError;
import dallastemperaturesensor.error.NoSensorFoundError;
import dallastemperaturesensor.error.UnsupportedUnitError;

public class ExceptionTest {
	
	public int THERM_SENSOR_DS18S20 = 0x10;
	public int THERM_SENSOR_DS1822 = 0x22;
	public int THERM_SENSOR_DS18B20 = 0x28;
	public String BASE_DIRECTORY = "./sys/bus/w1/devices";
	public RESOLVE_TYPE_STR mRESOLVE_TYPE_STR = new RESOLVE_TYPE_STR();
	/**
	 * @param a
	 */
	public static void execute(String a) throws KernelModuleLoadError {
		// TODO Auto-generated method stub
		System.out.println("execute....");
		if ("true".equals(a)){
			throw new KernelModuleLoadError();
		}
	}
	public static void main(String[] args) throws KernelModuleLoadError, NoSensorFoundError, NumberFormatException, DictionaryNotCorrectError, UnsupportedUnitError {
		// TODO Auto-generated method stub
		DallasTempSensorDS18 mSensor = new DallasTempSensorDS18(0x28, null);
		System.out.println(mSensor.get_temperature(DallasTempSensorDS18.DEGREES_C));
	}
}
