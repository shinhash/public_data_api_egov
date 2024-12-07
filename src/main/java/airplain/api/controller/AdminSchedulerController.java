package airplain.api.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import airplain.api.service.AdminSchedulerService;

@CrossOrigin
@RestController
public class AdminSchedulerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminSchedulerController.class);
	
	@Resource(name="adminSchedulerService")
	private AdminSchedulerService adminSchedulerService;
	
	@RequestMapping(value="/scheduleManage.do", method={RequestMethod.POST})
	public ModelAndView scheduleManage(ModelAndView modelAndView) throws Exception {
		List<Map<String, Object>> schedulerList = adminSchedulerService.selectSchedulerList();
		LOGGER.debug("schedulerList : {}", schedulerList);
		
		modelAndView.addObject("schedulerList", schedulerList);
		modelAndView.setViewName("api/schdulMg");
		return modelAndView;
	}
	
	@RequestMapping(value="/saveScheduleInfo.do", method=RequestMethod.POST)
	public Map<String, Object> saveScheduleInfo(@RequestBody Map<String, Object> receiveJson) throws Exception {
		LOGGER.debug("receiveJson : {}", receiveJson);
		Map<String, Object> saveRstMap = adminSchedulerService.saveSchedulerInfo(receiveJson);
		LOGGER.debug("saveRstMap : {}", saveRstMap);
		return saveRstMap;
	}
	
	
	@RequestMapping(value="/testRequestBodyToString.do", method=RequestMethod.POST)
	public Map<String, Object> testRequestBodyToString(@RequestBody String receiveJson) throws Exception {
		LOGGER.debug("receiveJson : {}", receiveJson);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(receiveJson);
		
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>(){};
		Map<String, Object> jsonMap = objectMapper.readValue(jsonObject.toString(), typeReference);
		LOGGER.debug("jsonMap : {}", jsonMap);
		return jsonMap;
	}
}
