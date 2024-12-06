package airplain.api.scheduler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import airplain.api.service.AirPlainAPIService;

@Component
public class AirplainApiScheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AirplainApiScheduler.class);
	
	@Resource(name="airPlainAPIService")
	private AirPlainAPIService airPlainAPIService;
	
	@Scheduled(cron="0 */1 * * * *")
	public void airPlainScheduler() throws Exception {
		String schdulCd = "SJ001";
		
		String useYn = airPlainAPIService.selectSchedulerInfoUseYnCheck(schdulCd);
		LOGGER.debug("useYn : {}", useYn);
		if(useYn.equals("Y")) {
			LOGGER.debug("스케쥴러 테스트");
			Map<String, String> apiDataSaveRst = new HashMap<String, String>();
			try {
				InputStream is = getClass().getResourceAsStream("/prop/api/secValues.properties");
				Reader reader = new InputStreamReader(is);
				Properties properties = new Properties();
				properties.load(reader);
				String apiUrl = properties.getProperty("api.url");
				LOGGER.debug("apiUrl : " + apiUrl);
				
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> apiResp = restTemplate.getForEntity(apiUrl, String.class);
				LOGGER.debug("JSONObject result : " + apiResp.getBody());
				
				ObjectMapper objectMapper = new ObjectMapper();
				TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>(){};
				Map<String, Object> apiResult = objectMapper.readValue(apiResp.getBody().toString(), typeReference);
				LOGGER.debug("ObjectMapper result : " + (Map<String, Object>) apiResult);
				
				apiDataSaveRst = airPlainAPIService.saveAirPlainList(apiResult);
			}catch(Exception e) {
				apiDataSaveRst.put("result", e.toString());
				apiDataSaveRst.put("code", "");
				apiDataSaveRst.put("message", "");
			}
			LOGGER.debug("apiDataSaveRst : " + apiDataSaveRst);
		}else {
			LOGGER.debug("airPlainScheduler SCHEDULER IS NOT USED");
		}
	}
}
