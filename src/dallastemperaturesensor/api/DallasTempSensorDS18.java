package dallastemperaturesensor.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dallastemperaturesensor.error.DictionaryNotCorrectError;
import dallastemperaturesensor.error.NoSensorFoundError;
import dallastemperaturesensor.error.SensorNotReadyError;
import dallastemperaturesensor.error.UnsupportedUnitError;

public class DallasTempSensorDS18 {

	public static int THERM_SENSOR_DS18S20 = 0x10;
	public static int THERM_SENSOR_DS1822 = 0x22;
	public static int THERM_SENSOR_DS18B20 = 0x28;
	public static int DEGREES_C = 0x01;
	public static int DEGREES_F = 0x02;
	public static int KELVIN = 0x03;
	public String BASE_DIRECTORY = "./sys/bus/w1/devices";
	public String SLAVE_FILE = "w1_slave";
	public int RETRY_ATTEMPS = 10;
	public float RETRY_DELAY_SECONDS = (float) (1.0 / RETRY_ATTEMPS);
	public RESOLVE_TYPE_STR mRESOLVE_TYPE_STR = new RESOLVE_TYPE_STR();
	public SOLVE_TYPE mSOLVE_TYPE = new SOLVE_TYPE();
	public TYPE_NAMES mTYPE_NAMES = new TYPE_NAMES();
	protected int _type;
	protected String _id;
	protected Path _sensorpath;
	
	public DallasTempSensorDS18(int sensor_type, String sensor_id) throws NoSensorFoundError, NumberFormatException, DictionaryNotCorrectError, UnsupportedUnitError {
		// TODO Auto-generated constructor stub
		
		//TODO try to load kernel modules
		load_kernel_modules();
		_type = sensor_type;
		_id = sensor_id;
		if (mRESOLVE_TYPE_STR.GET_TYPE(sensor_type) == null && sensor_id == null){ // take first found sensor
			List<List<String>> sensors = new ArrayList<List<String>>();
			for (int i=0; i<=RETRY_ATTEMPS; i++){
				sensors = get_available_sensors(null);
				if (sensors != null && sensors.get(0).size() != 0){
					_type = mSOLVE_TYPE.GET_TYPE_VALIE(sensors.get(0).get(0));
					_id = sensors.get(1).get(0);
					break;
				}
				try {
				    Thread.sleep((long) RETRY_DELAY_SECONDS);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
			if (_type == 0 && _id == null) throw new NoSensorFoundError("","");
		}
		else if (sensor_id == null){
			List<List<String>> sensors = new ArrayList<List<String>>();
			sensors = get_available_sensors(new String[]{mRESOLVE_TYPE_STR.GET_TYPE(sensor_type)});
			if (sensors != null && sensors.get(0).size() != 0){
				_id = sensors.get(1).get(0);
			}
			if ( _id == null) throw new NoSensorFoundError(mRESOLVE_TYPE_STR.GET_TYPE(_type),"");
		}
		//System.out.println(_type);
		//System.out.println(mRESOLVE_TYPE_STR.GET_TYPE(_type));
		//System.out.println(_id);
		//System.out.println(slave_prefix());
		_sensorpath = Paths.get(BASE_DIRECTORY, slave_prefix()+_id, SLAVE_FILE);
		if (!exists()) throw new NoSensorFoundError(mRESOLVE_TYPE_STR.GET_TYPE(_type), _id);
	}
	
	private void load_kernel_modules() {
		// TODO Auto-generated method stub
		
	}
	
	public float get_temperature(int unit) throws UnsupportedUnitError, NoSensorFoundError{
		List<Integer> units = Arrays.asList(DEGREES_C, DEGREES_F, KELVIN);
		//System.out.println(units.contains(unit));
		if (units.contains(unit)){
			int target = raw_sensor_value();
			if (unit == DEGREES_C){
				return UNIT_FACTORS_C(target);
			}
			else if (unit == DEGREES_F){
				return UNIT_FACTORS_F(target);
			}
			else{
				return UNIT_FACTORS_K(target);
			}
		}
		else{
			throw new UnsupportedUnitError();
		}
	}
	
	private int raw_sensor_value() throws NoSensorFoundError{
		List<String> records = new ArrayList<String>();
		try{
			
			BufferedReader reader = new BufferedReader(new FileReader(_sensorpath.toString()));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    	//System.out.println(line);
		    	records.add(line);
		    }
		    //System.out.println(records);
		    reader.close();
		    String aims;
		    int Linelen = records.get(0).length();
		    aims = records.get(0).trim().substring(Linelen-3, Linelen);
		    //System.out.println(aims);
		    if (!aims.equals("YES")) throw new SensorNotReadyError();
		    String target;
		    target = records.get(1).split("=")[1];
		    //System.out.println(target);
		    return Integer.parseInt(target);
		}catch (Exception e){
			throw new NoSensorFoundError(mRESOLVE_TYPE_STR.GET_TYPE(_type), _id);
		}
	}

	@SuppressWarnings("unused")
	private List<List<String>> get_available_sensors(String[] mTypes) throws NumberFormatException, DictionaryNotCorrectError{
		if (mTypes == null){
			List<List<String>> mListOfAvaiable = new ArrayList<List<String>>();
			List<String> mListOfAvaiable_type = new ArrayList<String>(1);
			List<String> mListOfAvaiable_id = new ArrayList<String>(2);
			for (String item : listdir(BASE_DIRECTORY)){
				if (is_sensor(item, null)){
					mListOfAvaiable_id.add(item.substring(3));
					mListOfAvaiable_type.add(mRESOLVE_TYPE_STR.GET_TYPE_WITH_HEX_LSB(Integer.parseInt(item.substring(0,2))));
				}
			}
			mListOfAvaiable.add(mListOfAvaiable_type);
			mListOfAvaiable.add(mListOfAvaiable_id);
			if (mListOfAvaiable != null){
				return mListOfAvaiable;
			}
			else{
				return null;
			}
		}
		else{
			List<List<String>> mListOfAvaiable = new ArrayList<List<String>>();
			List<String> mListOfAvaiable_type = new ArrayList<String>(1);
			List<String> mListOfAvaiable_id = new ArrayList<String>(2);
			for (String item : listdir(BASE_DIRECTORY)){
				if (is_sensor(item, mTypes[0])){
					mListOfAvaiable_id.add(item.substring(3));
					//System.out.println(item.substring(0,2));
					//System.out.println(Integer.parseInt(item.substring(0,2)));
					mListOfAvaiable_type.add(mRESOLVE_TYPE_STR.GET_TYPE_WITH_HEX_LSB(Integer.parseInt(item.substring(0,2))));
				}
			}
			mListOfAvaiable.add(mListOfAvaiable_type);
			mListOfAvaiable.add(mListOfAvaiable_id);
			if (mListOfAvaiable != null){
				return mListOfAvaiable;
			}
			else{
				return null;
			}
		}
	}
	
	private ArrayList<String> listdir(String dictionary) throws DictionaryNotCorrectError{
		File f = new File(dictionary);
		try{
			ArrayList<String> files = new ArrayList<String>(Arrays.asList(f.list()));
			return files;
		}catch (Exception e){
			throw new DictionaryNotCorrectError(dictionary);
		}
	}
	
	private boolean is_sensor(String suffix, String mTypes){
		if (suffix == null && mTypes == null) {
			return false;
		}
		else if (mTypes == null){
			if (suffix.startsWith(Integer.toHexString(THERM_SENSOR_DS18S20))) return true;
			else if (suffix.startsWith(Integer.toHexString(THERM_SENSOR_DS1822))) return true;
			else if (suffix.startsWith(Integer.toHexString(THERM_SENSOR_DS18B20))) return true;
			else return false;
		}
		else {
			if (suffix.startsWith(Integer.toHexString((mSOLVE_TYPE.GET_TYPE_VALIE(mTypes))))) return true;
			else return false;
		}
	}
	
	private String slave_prefix(){
		return String.format("%s-", Integer.toHexString(_type));
	}
	
	private boolean exists(){
		File f = new File(_sensorpath.toString());
		return f.exists();
	}
	
	private float UNIT_FACTORS_C(int aims){
		return (float) (aims*0.001);
	}
	
	private float UNIT_FACTORS_F(int aims){
		return (float) (aims*0.001*1.8 +32.0);
	}
	
	private float UNIT_FACTORS_K(int aims){
		return (float) (aims*0.001 + 273.15);
	}
	
	public String id(){
		return _id;
	}
	
	public int type(){
		return _type;
	}
	
	public String type_name(){
		return mRESOLVE_TYPE_STR.GET_TYPE(_type);
	}
	
	public String sensorpath(){
		return _sensorpath.toString();
	}
}
