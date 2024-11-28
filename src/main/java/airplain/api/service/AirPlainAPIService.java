package airplain.api.service;

import java.util.List;
import java.util.Map;

public interface AirPlainAPIService {

	public List<Map<String, Object>> getAirPlainList(Map<String, Object> map) throws Exception;
	
	public String saveAirPlainList(Map<String, Object> apiResult) throws Exception;
}
