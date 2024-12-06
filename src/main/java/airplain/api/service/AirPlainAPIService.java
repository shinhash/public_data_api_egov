package airplain.api.service;

import java.util.List;
import java.util.Map;

public interface AirPlainAPIService {
	
	public List<Map<String, Object>> getAirPlainList() throws Exception;
	
	public Map<String, String> saveAirPlainList(Map<String, Object> apiResult) throws Exception;

	public String selectSchedulerInfoUseYnCheck(String schdulCd) throws Exception;
}
