package dallastemperaturesensor.api;

public class RESOLVE_TYPE_STR {

	public RESOLVE_TYPE_STR() {
		// TODO Auto-generated constructor stub
	}
	
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
