package airplain.api.service;

import java.util.List;
import java.util.Map;

public interface AdminSchedulerService {
	public List<Map<String, Object>> selectSchedulerList() throws Exception;
	
	public Map<String, Object> saveSchedulerInfo(Map<String, Object> jsonMap) throws Exception;
}
