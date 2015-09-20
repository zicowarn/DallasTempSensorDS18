/**  
 *
 * @Title: RESOLVE_TYPE_STR.java
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
 * @ClassName: RESOLVE_TYPE_STR
 * @Description: TODO
 * @author: mrwang
 * @date: 20.09.2015 18:49:25
 */
public class RESOLVE_TYPE_STR {

	/**
	 * 
	 * @Title:RESOLVE_TYPE_STR
	 * @Description:TODO
	 */
	public RESOLVE_TYPE_STR() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @Title: GET_TYPE
	 * @Description: TODO
	 * @param Suffix
	 * @return
	 * @return: String
	 */
	public String GET_TYPE(int Suffix){
		String _type = "";
		switch(Suffix){
			case 16: 
				_type = "THERM_SENSOR_DS18S20";
				break;
			case 34: 
				_type = "THERM_SENSOR_DS1822";
				break;
			case 40: 
				_type = "THERM_SENSOR_DS18B20";
				break;
			default:
				_type = null;
				break;
				
		}
		return _type;			
	}
	
	/**
	 * 
	 * @Title: GET_TYPE_WITH_HEX_LSB
	 * @Description: TODO
	 * @param Suffix
	 * @return
	 * @return: String
	 */
	public String GET_TYPE_WITH_HEX_LSB(int Suffix){
		String _type = "";
		switch(Suffix){
			case 10: 
				_type = "THERM_SENSOR_DS18S20";
				break;
			case 22: 
				_type = "THERM_SENSOR_DS1822";
				break;
			case 28: 
				_type = "THERM_SENSOR_DS18B20";
				break;
			default:
				_type = null;
				break;
				
		}
		return _type;			
	}
}
