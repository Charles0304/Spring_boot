package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.LogDTO;
import edu.pnu.domain.MemberVO;

public class MemberService {
	MemberDao dao;
	LogDao ldao;
	public MemberService() {
		dao = new MemberDao();
		ldao = new LogDao();
	}
	
	public List<MemberVO> getMembers() throws SQLException{
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
		return dao.getMemberById(id);
	}
	
	public MemberVO insertMember(MemberVO m) throws SQLException {
		return dao.InsertMember(m);
	}
	
	public int updateMember(MemberVO m) throws SQLException {
		return dao.UpdateMember(m);
	}
	
	public int deleteMember(Integer id) throws SQLException {
		return dao.DeleteMember(id);
	}
}
