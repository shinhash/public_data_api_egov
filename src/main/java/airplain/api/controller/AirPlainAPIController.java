package airplain.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import airplain.api.service.AirPlainAPIService;

@RestController
public class AirPlainAPIController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AirPlainAPIController.class);
	
	@Resource(name="airPlainAPIService")
	private AirPlainAPIService airPlainAPIService;
	
	@RequestMapping(value="/index.do")
	public String index(Model model) throws Exception {
		return "api/index";
	}
	
	@RequestMapping(value="/saveAirPlainList.do", method={RequestMethod.POST})
	public String getAirPlainList(Model model) throws Exception {
		
		String apiUrl = "http://211.237.50.150:7080/openapi/sample/json/Grid_20170111000000000496_1/1/5";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> apiResp = restTemplate.getForEntity(apiUrl, String.class);
		
		JSONParser jsonPaser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonPaser.parse(apiResp.getBody().toString());
		LOGGER.debug("json result : " + jsonObject.get("Grid_20170111000000000496_1"));
		
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>(){};
		Map<String, Object> apiResult = objectMapper.readValue(jsonObject.get("Grid_20170111000000000496_1").toString(), typeReference);
		LOGGER.debug("apiResult : " + apiResult);
		
		String apiDataSaveResult = airPlainAPIService.saveAirPlainList(apiResult);
		model.addAttribute("result", apiDataSaveResult);
		LOGGER.debug("apiDataSaveResult : " + apiDataSaveResult);
		return "jsonView";
	}
	
	@RequestMapping(value="/getAirPlainList.do")
	public String saveAirPlainList(Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("result", airPlainAPIService.getAirPlainList(map));
		return "jsonView";
	}
}
