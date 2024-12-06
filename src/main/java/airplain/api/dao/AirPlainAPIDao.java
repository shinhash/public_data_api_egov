package airplain.api.dao;

import java.util.List;
import java.util.Map;

public interface AirPlainAPIDao {

	public int checkAirPlainInfo(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> getAirPlainList() throws Exception;
	
	public void saveAirPlainList(Map<String, Object> map) throws Exception;

	public String selectSchedulerInfoUseYnCheck(String schdulCd) throws Exception;

}
