package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	private MemberService ms;
	public MemberController() {
		ms = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO>getMembers(){
		return ms.getMembers();
	}
	
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		return ms.getMemberById(id);
	}
	
	@PostMapping("/member")
	public MemberVO insertMember(MemberVO m) {
		return ms.insertMember(m);
	}
	
	@PutMapping("/member")
	public int updateMember(MemberVO m) {
		return ms.updateMember(m);
	}
	
	@DeleteMapping("/member")
	public int deleteMember(Integer id) {
		return ms.deleteMember(id);
	}
}
