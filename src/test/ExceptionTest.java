/**  
 *
 * @Title: ExceptionTest.java
 * @Prject: DallasTempSensorDS18
 * @Package: test
 * @Description: TODO
 * @author: mrwang  
 * @date: 20.09.2015 18:40:06
 * @version: V1.0  
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
 *
 */

package test;

import dallastemperaturesensor.api.DallasTempSensorDS18;
import dallastemperaturesensor.api.RESOLVE_TYPE_STR;
import dallastemperaturesensor.error.DictionaryNotCorrectError;
import dallastemperaturesensor.error.KernelModuleLoadError;
import dallastemperaturesensor.error.NoSensorFoundError;
import dallastemperaturesensor.error.UnsupportedUnitError;


/**
 * @ClassName: ExceptionTest
 * @Description: TODO
 * @author: mrwang
 * @date: 20.09.2015 18:47:33
 */

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
