package airplain.api.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import airplain.api.service.AirPlainAPIService;

@RestController
public class AirPlainAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AirPlainAPIController.class);
	
	@Resource(name="airPlainAPIService")
	private AirPlainAPIService airPlainAPIService;
	
	@RequestMapping(value="/index.do")
	public ModelAndView index(ModelAndView modelAndView) throws Exception {
		modelAndView.setViewName("api/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/saveAirPlainList.do", method={RequestMethod.POST})
	public Map<String, String> saveAirPlainList() throws Exception {
		InputStream is = getClass().getResourceAsStream("/prop/api/secValues.properties");
		Reader reader = new InputStreamReader(is);
		Properties properties = new Properties();
		properties.load(reader);
		String apiUrl = properties.getProperty("api.airplain.url") + "?" + properties.getProperty("api.airplain.key") + "&" + properties.getProperty("api.airplain.returnType");
		LOGGER.debug("apiUrl : " + apiUrl);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> apiResp = restTemplate.getForEntity(apiUrl, String.class);
		LOGGER.debug("JSONObject result : " + apiResp.getBody());
		
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>(){};
		Map<String, Object> apiResult = objectMapper.readValue(apiResp.getBody().toString(), typeReference);
		LOGGER.debug("ObjectMapper result : " + (Map<String, Object>) apiResult);
		
		Map<String, String> apiDataSaveRst = airPlainAPIService.saveAirPlainList((Map<String, Object>)apiResult.get("response"));
		LOGGER.debug("apiDataSaveRst : " + apiDataSaveRst);
		return apiDataSaveRst;
	}
	
	@RequestMapping(value="/getAirPlainList.do")
	public List<Map<String, Object>> getAirPlainList() throws Exception {
		List<Map<String, Object>> listMap = airPlainAPIService.getAirPlainList();
		return listMap;
	}
}
