package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.LogDTO;
import edu.pnu.domain.MemberVO;
@Service
public class MemberService {
	@Autowired
	MemberDao dao;
	@Autowired
	LogDao ldao;

	
	public List<MemberVO> getMembers(){
		LogDTO log = new LogDTO();
		log.setMethod("getMembers");
		log.setSqlstring("select * from member");
		List<MemberVO> list = null;
		try {
			list = dao.getMembers();
			log.setSuccess(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.setSuccess(false);
			e.printStackTrace();
		}
		ldao.insertLog(log);
		return list;
	}
	
	public MemberVO getMemberById(Integer id) throws SQLException {
		Map<String, Object> map = dao.getMemberById(id);
		LogDTO log = new LogDTO();
		log.setMethod((String)map.get("method"));
		log.setSqlstring((String)map.get("sql"));
		log.setSuccess((boolean)map.get("isSuccess"));
		ldao.insertLog(log);
		return (MemberVO)map.get("member");
	}
	
	public MemberVO insertMember(MemberVO m) throws SQLException {
		Map<String, Object> map = dao.InsertMember(m);
		LogDTO log = new LogDTO();
		log.setMethod((String)map.get("method"));
		log.setSqlstring((String)map.get("sql"));
		log.setSuccess((boolean)map.get("isSuccess"));
		ldao.insertLog(log);
		return (MemberVO) map.get("member");
	}
	
	public int updateMember(MemberVO m) throws SQLException {
		Map<String, Object> map = dao.UpdateMember(m);
		LogDTO log = new LogDTO();
		log.setMethod((String)map.get("method"));
		log.setSqlstring((String)map.get("sql"));
		log.setSuccess((boolean)map.get("isSuccess"));
		ldao.insertLog(log);
		return (int) map.get("result");
	}
	
	public int deleteMember(Integer id) throws SQLException {
		Map<String, Object> map = dao.DeleteMember(id);
		LogDTO log = new LogDTO();
		log.setMethod((String)map.get("method"));
		log.setSqlstring((String)map.get("sql"));
		log.setSuccess((boolean)map.get("isSuccess"));
		ldao.insertLog(log);
		return (int) map.get("result");
	}
}
