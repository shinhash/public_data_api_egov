package airplain.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import airplain.api.dao.AdminSchedulerDao;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("adminSchedulerDao")
public class AdminSchedulerDaoImpl extends EgovAbstractMapper implements AdminSchedulerDao {

	@Override
	public List<Map<String, Object>> selectSchedulerList() throws Exception {
		return selectList("adminSchedulerDao.selectSchedulerList");
	}

	@Override
	public int saveSchedulerInfo(Map<String, Object> jsonMap) throws Exception {
		return update("adminSchedulerDao.saveSchedulerInfo", jsonMap);
	}

	@Override
	public int saveSchedulerAllInfo(Map<String, Object> jsonMap) throws Exception {
		return update("adminSchedulerDao.saveSchedulerAllInfo", jsonMap);
	}

}
