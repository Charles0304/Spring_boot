package edu.pnu.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	//@Service나 @Repository로 메모리(IoC 컨테이너)에 올라간 객체를 자동으로 연결
	//생성자로 따로 설정할 필요가 없음 
	@Autowired
	private MemberService ms;
	
	public MemberController() {
		System.out.println("MemberController 생성");
		
		//DI 코드1방식
		//ms = new MemberService(new MemberDao());
		
		//DI 코드2방식
		//ms = new MemberService();
		//ms.setMemberDao(new MemberDao());
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() throws SQLException{
		return ms.getMembers();
	}
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) throws SQLException {
		return ms.getMemberById(id);
	}
	@PostMapping("/member")
	public MemberVO insertMember(MemberVO m)  {
		try {
			return ms.insertMember(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping("/member")
	public int updateMember(MemberVO m) throws SQLException {
		return ms.updateMember(m);
	}
	
	@DeleteMapping("/member")
	public int deleteMember(Integer id) throws SQLException {
		return ms.deleteMember(id);
	}
}
