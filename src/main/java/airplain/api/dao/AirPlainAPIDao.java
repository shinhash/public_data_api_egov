package airplain.api.dao;

import java.util.List;
import java.util.Map;

public interface AirPlainAPIDao {

	public List<Map<String, Object>> getAirPlainList(Map<String, Object> map) throws Exception;
	
	public void saveAirPlainList(Map<String, Object> map) throws Exception;
}
