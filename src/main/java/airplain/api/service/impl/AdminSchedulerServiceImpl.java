package airplain.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import airplain.api.dao.AdminSchedulerDao;
import airplain.api.service.AdminSchedulerService;

@Service("adminSchedulerService")
public class AdminSchedulerServiceImpl implements AdminSchedulerService {
	
	@Resource(name="adminSchedulerDao")
	private AdminSchedulerDao adminSchedulerDao;

	@Override
	public List<Map<String, Object>> selectSchedulerList() throws Exception {
		return adminSchedulerDao.selectSchedulerList();
	}

	@Override
	public Map<String, Object> saveSchedulerInfo(Map<String, Object> jsonMap) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String saveResultCode = "FAIL";
		String saveResultMessage = "저장 실패";
		try {
			int saveRstCnt = 0;
			if(jsonMap.get("schdulCd").equals("ALL")) {
				saveRstCnt = adminSchedulerDao.saveSchedulerAllInfo(jsonMap);
			}else {
				saveRstCnt = adminSchedulerDao.saveSchedulerInfo(jsonMap);
			}
			
			if(saveRstCnt > 0) {
				saveResultCode = "SUCCESS";
				saveResultMessage = "저장 성공";
			}
		}catch(Exception e) {
			saveResultCode = "ERROR";
			saveResultMessage = "저장 오류 : [" + e.toString() + "]";
		}
		returnMap.put("saveResultCode", saveResultCode);
		returnMap.put("saveResultMessage", saveResultMessage);
		return returnMap;
	}

}
