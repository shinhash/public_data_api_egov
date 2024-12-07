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
		Map<String, String> apiDataSaveRst = new HashMap<String, String>();
		
		Map<String, Object> headerMap = ((Map<String, Object>) apiResult.get("header"));
		Map<String, Object> bodyMap = ((Map<String, Object>) apiResult.get("body"));
		
		System.out.println("headerMap : " + headerMap);
		System.out.println("bodyMap : " + bodyMap);
		
		String apiResultCode = headerMap.get("resultCode").toString();
		String apiResultMsg = headerMap.get("resultMsg").toString();
	
		List<Map<String, Object>> apiResultItemList = (List<Map<String, Object>>) bodyMap.get("items");
		
		if(apiResultCode.equals("00")) {
			try {
				for(Map<String, Object> apiResultItemInfo : apiResultItemList) {
					int airPlainCtn = airPlainAPIDao.checkAirPlainInfo(apiResultItemInfo);
					if(airPlainCtn == 0) {
						airPlainAPIDao.saveAirPlainList(apiResultItemInfo);
					}
				}
				apiDataSaveRst.put("result", "SUCCESS");
			}catch(Exception e) {
				apiDataSaveRst.put("result", "ERROR");
			}
			apiDataSaveRst.put("code", apiResultCode);
			apiDataSaveRst.put("message", apiResultMsg);
		}else {
			apiDataSaveRst.put("result", "FAIL");
			apiDataSaveRst.put("code", apiResultCode);
			apiDataSaveRst.put("message", apiResultMsg);
		}
		return apiDataSaveRst;
	}

	@Override
	public String selectSchedulerInfoUseYnCheck(String schdulCd) throws Exception {
		return airPlainAPIDao.selectSchedulerInfoUseYnCheck(schdulCd);
	}
}
