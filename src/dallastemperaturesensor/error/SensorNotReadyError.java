/**  
 *
 * @Title: SensorNotReadyError.java
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

package dallastemperaturesensor.error;



/**
 * @ClassName: SensorNotReadyError
 * @Description: TODO
 * @author: mrwang
 * @date: 20.09.2015 18:45:44
 */

public class SensorNotReadyError extends DallasTempSensorDS18Error {

	private static final long serialVersionUID = -7932327619780135326L;

	/**
	 * 
	 * @Title:SensorNotReadyError
	 * @Description:TODO
	 */
	public SensorNotReadyError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @Title:SensorNotReadyError
	 * @Description:TODO
	 * @param msg
	 */
	public SensorNotReadyError(String msg) {
		super("Sensor is not yet ready to read temperature");
		// TODO Auto-generated constructor stub
	}

}
