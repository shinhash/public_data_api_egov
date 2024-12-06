package airplain.api.service.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import airplain.api.dao.AirPlainAPIDao;
import airplain.api.service.AirPlainAPIService;

@Service("airPlainAPIService")
public class AirPlainAPIServiceImpl implements AirPlainAPIService {
	
	@Resource(name="airPlainAPIDao")
	private AirPlainAPIDao airPlainAPIDao;
	
	@Override
	public List<Map<String, Object>> getAirPlainList() throws Exception {
		return airPlainAPIDao.getAirPlainList();
	}

	@Override
	public Map<String, String> saveAirPlainList(Map<String, Object> apiResult) throws Exception {
		InputStream is = getClass().getResourceAsStream("/prop/api/secValues.properties");
		Reader reader = new InputStreamReader(is);
		Properties properties = new Properties();
		properties.load(reader);
		String apiKey = properties.getProperty("api.key");
		
		Map<String, String> apiDataSaveRst = new HashMap<String, String>();
		Map<String, Object> apiMap = (Map<String, Object>) apiResult.get(apiKey);
		if(apiMap != null) {
			String apiResultCode = ((Map<String, Object>)apiMap.get("result")).get("code").toString();
			String apiResultMessage = ((Map<String, Object>)apiMap.get("result")).get("message").toString();
			List<Map<String, Object>> apiResultRow = (List<Map<String, Object>>) apiMap.get("row");
			
			if(apiResultCode.equals("INFO-000")) {
				try {
					for(Map<String, Object> map : apiResultRow) {
						int airPlainCtn = airPlainAPIDao.checkAirPlainInfo(map);
						if(airPlainCtn == 0) {
							airPlainAPIDao.saveAirPlainList(map);
						}
					}
					apiDataSaveRst.put("result", "SUCCESS");
				}catch(Exception e) {
					apiDataSaveRst.put("result", "ERROR");
				}
				apiDataSaveRst.put("code", apiResultCode);
				apiDataSaveRst.put("message", apiResultMessage);
			}else {
				apiDataSaveRst.put("result", "FAIL");
				apiDataSaveRst.put("code", apiResultCode);
				apiDataSaveRst.put("message", apiResultMessage);
			}
		}else {
			apiDataSaveRst.put("result", "FAIL");
			apiDataSaveRst.put("code", ((Map<String, Object>)apiResult.get("result")).get("code").toString());
			apiDataSaveRst.put("message", ((Map<String, Object>)apiResult.get("result")).get("message").toString());
		}
		return apiDataSaveRst;
	}

	@Override
	public String selectSchedulerInfoUseYnCheck(String schdulCd) throws Exception {
		return airPlainAPIDao.selectSchedulerInfoUseYnCheck(schdulCd);
	}
}
