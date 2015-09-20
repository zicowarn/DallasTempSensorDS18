/**  
 *
 * @Title: SOLVE_TYPE.java
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
 */

package dallastemperaturesensor.api;


/**
 * @ClassName: SOLVE_TYPE
 * @Description: TODO
 * @author: mrwang
 * @date: 20.09.2015 18:48:27
 */

public class SOLVE_TYPE {

	/**
	 * 
	 * @Title:SOLVE_TYPE
	 * @Description:TODO
	 */
	public SOLVE_TYPE() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @Title: GET_TYPE_VALIE
	 * @Description: TODO
	 * @param Suffix
	 * @return
	 * @return: int
	 */
	public int GET_TYPE_VALIE(String Suffix){
		int _type_value;
		switch(Suffix){
			case "THERM_SENSOR_DS18S20": 
				_type_value = 0x10;
				break;
			case "THERM_SENSOR_DS18B20": 
				_type_value = 0x28;
				break;
			case "THERM_SENSOR_DS1822": 
				_type_value = 0x22;
				break;
			default:
				_type_value = 0;
				break;
				
		}
		return _type_value;			
	}

}
