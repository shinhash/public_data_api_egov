package airplain.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import airplain.api.dao.AirPlainAPIDao;
import airplain.api.service.AirPlainAPIService;

@Service("airPlainAPIService")
public class AirPlainAPIServiceImpl implements AirPlainAPIService {
	
	@Resource(name="airPlainAPIDao")
	private AirPlainAPIDao airPlainAPIDao;
	
	@Override
	public List<Map<String, Object>> getAirPlainList(Map<String, Object> map) throws Exception {
		return airPlainAPIDao.getAirPlainList(map);
	}

	@Override
	public String saveAirPlainList(Map<String, Object> apiResult) throws Exception {
		
		String apiResultCode = ((Map<String, Object>)apiResult.get("result")).get("code").toString();
		List<Map<String, Object>> apiResultRow = (List<Map<String, Object>>) apiResult.get("row");
		
		String apiDataSaveResult = "";
		
		if(apiResultCode.equals("INFO-000")) {
			try {
				for(Map<String, Object> map : apiResultRow) {
					airPlainAPIDao.saveAirPlainList(map);
				}
				apiDataSaveResult = "SUCCESS";
			}catch(Exception e) {
				apiDataSaveResult = "ERROR : " + e;
			}
		}else {
			apiDataSaveResult = "FAIL";
		}
		return apiDataSaveResult;
	}
}
