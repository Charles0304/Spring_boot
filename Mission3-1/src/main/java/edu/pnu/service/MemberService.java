package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	public MemberService() {
		System.out.println("MemberService 기본생성자");
	}
//	public MemberService(MemberDao dao) {
//		System.out.println("MemberService 생성");
//		this.dao = dao;
//	}
//	public void setMemberDao(MemberDao dao) {
//		this.dao = dao;
//	}
	public List<MemberVO> getMembers() throws SQLException{
		return dao.getMembers();
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
