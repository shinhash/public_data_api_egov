package airplain.api.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import airplain.api.dao.AirPlainAPIDao;
//import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("airPlainAPIDao")
public class AirPlainAPIDaoImpl extends EgovAbstractMapper implements AirPlainAPIDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AirPlainAPIDaoImpl.class);
	/**
	 * EgovAbstractDAO ibatis version
	 * ibatis 사용시 xml 파일 형식은  new -> sqlMap
	 * result = (List<Map<String, Object>>) list("airPlainAPIDao.saveAirPlainList", null);
	 * EgovAbstractMapper mybatis version
	 * mybatis 사용시 xml 파일 생성 방법은   new -> mybatis xml
	 */
	
	@Override
	public List<Map<String, Object>> getAirPlainList(Map<String, Object> map) throws Exception {
		return selectList("airPlainAPIDao.getAirPlainList", map);
	}

	@Override
	public void saveAirPlainList(Map<String, Object> map) throws Exception {
		insert("airPlainAPIDao.saveAirPlainInfo", map);
	}
}
