/**  
 *
 * @Title: DallasTempSensorDS18.java
 * @Prject: DallasTempSensorDS18
 * @Package: test
 * @Description: TODO
 * @author: mrwang  
 * @date: 20.09.2015 18:40:06
 * @version: V1.1  
 * @license:  MIT License

 * Copyright (c) 2015 Zhichao Wang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 */

package dallastemperaturesensor.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dallastemperaturesensor.error.DictionaryNotCorrectError;
import dallastemperaturesensor.error.KernelModuleLoadError;
import dallastemperaturesensor.error.NoSensorFoundError;
import dallastemperaturesensor.error.SensorNotReadyError;
import dallastemperaturesensor.error.UnsupportedUnitError;



/**
 * @ClassName: DallasTempSensorDS18
 * @Description: TODO
 * @author: mrwang
 * @date: 20.09.2015 18:49:55
 */
public class DallasTempSensorDS18 {

	public static int THERM_SENSOR_DS18S20 = 0x10;
	public static int THERM_SENSOR_DS1822 = 0x22;
	public static int THERM_SENSOR_DS18B20 = 0x28;
	public static int DEGREES_C = 0x01;
	public static int DEGREES_F = 0x02;
	public static int KELVIN = 0x03;
	public String BASE_DIRECTORY = "/sys/bus/w1/devices";
	public String SLAVE_FILE = "w1_slave";
	public int RETRY_ATTEMPS = 10;
	public float RETRY_DELAY_SECONDS = (float) (1.0 / RETRY_ATTEMPS);
	public RESOLVE_TYPE_STR mRESOLVE_TYPE_STR = new RESOLVE_TYPE_STR();
	public SOLVE_TYPE mSOLVE_TYPE = new SOLVE_TYPE();
	public TYPE_NAMES mTYPE_NAMES = new TYPE_NAMES();
	protected int _type;
	protected String _id;
	protected Path _sensorpath;
	
	/**
	 * 
	 * @Title:DallasTempSensorDS18
	 * @Description:TODO
	 * @param sensor_type
	 * @param sensor_id
	 * @throws NoSensorFoundError
	 * @throws NumberFormatException
	 * @throws DictionaryNotCorrectError
	 * @throws UnsupportedUnitError
	 * @throws IOException 
	 * @throws KernelModuleLoadError 
	 */
	public DallasTempSensorDS18(int sensor_type, String sensor_id) throws NoSensorFoundError, NumberFormatException, DictionaryNotCorrectError, UnsupportedUnitError, KernelModuleLoadError, IOException {
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
	
	/**
	 * @Title: load_kernel_modules
	 * @Description: TODO
	 * @return: void
	 * @throws IOException 
	 * @throws KernelModuleLoadError 
	 */
	private void load_kernel_modules() throws IOException, KernelModuleLoadError {
		// TODO Auto-generated method stub
		File f = new File(BASE_DIRECTORY);
		if (!f.isDirectory()){
			// Get runtime
	        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
	        // Start a new process: UNIX command ls
	        @SuppressWarnings("unused")
			java.lang.Process p = rt.exec("modprobe w1-gpio");
	        @SuppressWarnings("unused")
			java.lang.Process p1 = rt.exec("modprobe w1-therm");
		}
		else{}
		for (int i=0; i<=RETRY_ATTEMPS; i++){
			if(f.isDirectory()){
				break;
			}
			else{
				try {
				    Thread.sleep((long) RETRY_DELAY_SECONDS);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		}
		if (!f.isDirectory()) throw new KernelModuleLoadError();
	}
	
	/**
	 * 
	 * @Title: get_temperature
	 * @Description: TODO
	 * @param unit
	 * @return
	 * @throws UnsupportedUnitError
	 * @throws NoSensorFoundError
	 * @return: float
	 */
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
	
	/**
	 * @Title: get_temperatures
	 * @Description: TODO
	 * @return
	 * @throws NoSensorFoundError
	 */
	public List<Float> get_temperatures() throws NoSensorFoundError{
		List<Integer> units = Arrays.asList(DEGREES_C, DEGREES_F, KELVIN);
		List<Float> rts = new ArrayList<Float>();
		//System.out.println(units.contains(unit));
		int target = raw_sensor_value();
		for (int unit : units){
			
			if (unit == DEGREES_C){
				rts.add(UNIT_FACTORS_C(target));
			}
			else if (unit == DEGREES_F){
				rts.add(UNIT_FACTORS_F(target));
			}
			else{
				rts.add(UNIT_FACTORS_K(target));
			}
		}
		return rts;
	}
	
	/**
	 * @Title: get_temperatures
	 * @Description: TODO
	 * @return
	 * @throws UnsupportedUnitError
	 * @throws NoSensorFoundError
	 */
	public List<Float> get_temperatures(int[] in_units) throws UnsupportedUnitError, NoSensorFoundError{
		List<Integer> units = Arrays.asList(DEGREES_C, DEGREES_F, KELVIN);
		List<Float> rts = new ArrayList<Float>();
		//System.out.println(units.contains(unit));
		int target = raw_sensor_value();
		for (int unit : in_units){
			if (units.contains(unit)){
				if (unit == DEGREES_C){
					rts.add(UNIT_FACTORS_C(target));
				}
				else if (unit == DEGREES_F){
					rts.add(UNIT_FACTORS_F(target));
				}
				else{
					rts.add(UNIT_FACTORS_K(target));
				}
			}
			else{
				throw new UnsupportedUnitError();
			}
		}
		return rts;
	}
	
	/**
	 * 
	 * @Title: raw_sensor_value
	 * @Description: TODO
	 * @return
	 * @throws NoSensorFoundError
	 * @return: int
	 */
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

	/**
	 * 
	 * @Title: get_available_sensors
	 * @Description: TODO
	 * @param mTypes
	 * @return
	 * @throws NumberFormatException
	 * @throws DictionaryNotCorrectError
	 * @return: List<List<String>>
	 */
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
	
	/**
	 * 
	 * @Title: listdir
	 * @Description: TODO
	 * @param dictionary
	 * @return
	 * @throws DictionaryNotCorrectError
	 * @return: ArrayList<String>
	 */
	private ArrayList<String> listdir(String dictionary) throws DictionaryNotCorrectError{
		File f = new File(dictionary);
		try{
			ArrayList<String> files = new ArrayList<String>(Arrays.asList(f.list()));
			return files;
		}catch (Exception e){
			throw new DictionaryNotCorrectError(dictionary);
		}
	}
	
	/**
	 * 
	 * @Title: is_sensor
	 * @Description: TODO
	 * @param suffix
	 * @param mTypes
	 * @return
	 * @return: boolean
	 */
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
	
	/**
	 * 
	 * @Title: slave_prefix
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	private String slave_prefix(){
		return String.format("%s-", Integer.toHexString(_type));
	}
	
	/**
	 * 
	 * @Title: exists
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	private boolean exists(){
		File f = new File(_sensorpath.toString());
		return f.exists();
	}
	
	/**
	 * 
	 * @Title: UNIT_FACTORS_C
	 * @Description: TODO
	 * @param aims
	 * @return
	 * @return: float
	 */
	private float UNIT_FACTORS_C(int aims){
		return (float) (aims*0.001);
	}
	
	/**
	 * 
	 * @Title: UNIT_FACTORS_F
	 * @Description: TODO
	 * @param aims
	 * @return
	 * @return: float
	 */
	private float UNIT_FACTORS_F(int aims){
		return (float) (aims*0.001*1.8 +32.0);
	}
	
	/**
	 * 
	 * @Title: UNIT_FACTORS_K
	 * @Description: TODO
	 * @param aims
	 * @return
	 * @return: float
	 */
	private float UNIT_FACTORS_K(int aims){
		return (float) (aims*0.001 + 273.15);
	}
	
	/**
	 * 
	 * @Title: id
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String id(){
		return _id;
	}
	
	/**
	 * 
	 * @Title: type
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	public int type(){
		return _type;
	}
	
	/**
	 * 
	 * @Title: type_name
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String type_name(){
		return mRESOLVE_TYPE_STR.GET_TYPE(_type);
	}
	
	/**
	 * 
	 * @Title: sensorpath
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String sensorpath(){
		return _sensorpath.toString();
	}
}
