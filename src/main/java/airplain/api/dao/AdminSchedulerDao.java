package airplain.api.dao;

import java.util.List;
import java.util.Map;

public interface AdminSchedulerDao {

	public List<Map<String, Object>> selectSchedulerList() throws Exception;
	
	public int saveSchedulerInfo(Map<String, Object> jsonMap) throws Exception;

	public int saveSchedulerAllInfo(Map<String, Object> jsonMap) throws Exception;
}
