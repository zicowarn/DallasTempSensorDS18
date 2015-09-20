package dallastemperaturesensor.api;

public class SOLVE_TYPE {

	public SOLVE_TYPE() {
		// TODO Auto-generated constructor stub
	}
	
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
