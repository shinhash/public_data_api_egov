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
	/**
	 * EgovAbstractDAO ibatis version
	 * ibatis 사용시 xml 파일 형식은  new -> sqlMap
	 * result = (List<Map<String, Object>>) list("airPlainAPIDao.saveAirPlainList", null);
	 * EgovAbstractMapper mybatis version
	 * mybatis 사용시 xml 파일 생성 방법은   new -> mybatis xml
	 */
	
	@Override
	public int checkAirPlainInfo(Map<String, Object> map) throws Exception {
		return selectOne("airPlainAPIDao.checkAirPlainInfo", map);
	}
	
	@Override
	public List<Map<String, Object>> getAirPlainList() throws Exception {
		return selectList("airPlainAPIDao.getAirPlainList");
	}

	@Override
	public void saveAirPlainList(Map<String, Object> map) throws Exception {
		insert("airPlainAPIDao.saveAirPlainInfo", map);
	}

	@Override
	public String selectSchedulerInfoUseYnCheck(String schdulCd) throws Exception {
		return selectOne("airPlainAPIDao.selectSchedulerInfoUseYnCheck", schdulCd);
	}
}
