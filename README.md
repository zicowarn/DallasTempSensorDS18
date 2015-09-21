# DallasTempSensorDS18
===============================================================================

This java project provide a api and represents a temperature sensor of type w1-thermosensor 

### File structure of this project:
	
> Package: dallastemperaturesensor.api
>> - DallasTempSensorDS18.java
>> - RESOLVE_TYPE_STR.java
>> - TYPE_NAMES.java
>> - SOLVE_TYPE.java

> Package: dallastemperaturesensor.error
>> - DallasTempSensorDS18Error.java
>> - DictionaryNotCorrectError.java
>> - KernelModuleLoadError.java
>> - NoSensorFoundError.java
>> - SensorNotReadyError.java
>> - UnsupportedUnitError.java

> Package: 
>> - ExceptionTest.java

> Dictionary:
>> - /sys/bus/w1/devices/w1_bus_master1/
>> - /sys/bus/w1/devices/28-0000006c4fdb1/
>> - /sys/bus/w1/devices/28-0000006c6ff43/
>> - /sys/bus/w1/device/w1_bus_master1/
>> - /sys/bus/w1/devic/

> Java Doc:
>> - javadoc

> Java Archive File (not runnable):
>> - DallasTempSensorDS18.jar
 
### Dependent library:

there is no specific dependent manner.

- java.io.BufferedReader;
- java.io.File;
- java.io.FileReader;
- java.nio.file.Path;
- java.nio.file.Paths;
- java.util.ArrayList;
- java.util.Arrays;
- java.util.List;

-----------------------------------------------------------------


## Usage:
------------------------------------------------------------------

example:
```java
DallasTempSensorDS18 mSensor = new DallasTempSensorDS18(DallasTempSensorDS18.THERM_SENSOR_DS18B20, null);
System.out.println(mSensor.get_temperature(DallasTempSensorDS18.DEGREES_C));
```


### Version
V1.0

### Installation

```sh
$ git clone [git-repo-url] DallasTempSensorDS18
```

### Todos

 - Write Tests
 - Add Code Comments
 - function: get_temperatures()
 - function: load_kernel_modules()

License
----
The MIT License (MIT)

Copyright (c) 2015 Zhichao Wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

**Free Software, Hell Yeah!**
