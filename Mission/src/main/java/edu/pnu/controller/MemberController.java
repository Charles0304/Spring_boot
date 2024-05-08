package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	List<MemberVO> memberList = new ArrayList<MemberVO>();
	public MemberController() {
		for(int i=1;i<=10;i++) {
			memberList.add(MemberVO.builder().id(i).name("Member"+i)
					.pass("1234").regidate(new Date()).build());

		}
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMemberList() {
		return memberList;
	}
	
//	@GetMapping("/members")
//	public ResponseEntity<?> getAllMember(){
//		return ResponseEntity.ok(memberList);
//	}
	
	@GetMapping("/member")
	public MemberVO getMember(Integer id) {
		MemberVO member = null;
		for(MemberVO m:memberList) {
			if(m.getId()==id)
				member = m;
		}
		return member;
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		if(getMember(memberVO.getId())!=null)
			return null;
		memberVO.setRegidate(new Date());
		memberList.add(memberVO);
		
		return memberVO;
	}
	
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMember(memberVO.getId());
		if(m==null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			memberList.remove(getMember(id));
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
		if(getMember(memberVO.getId())!=null)
			return null;
		memberVO.setRegidate(new Date());
		memberList.add(memberVO);
		return memberVO;
	}
}
