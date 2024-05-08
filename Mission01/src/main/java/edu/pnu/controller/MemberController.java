package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
	HashMap<Integer,MemberVO> memberMap = new HashMap<>();
	
	public MemberController() {
		for(int i=1;i<=10;i++) {
			memberMap.put(i, MemberVO.builder().id(i).pass("1234").name("member"+i).regidate(new Date()).build());
		}
	}
	@GetMapping("/members")
	public Collection<MemberVO> getMembers(){
		
		return memberMap.values();
	}
	
	@GetMapping("/member")
	public MemberVO getMember(Integer id) {
		return memberMap.get(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		if(getMember(memberVO.getId())!=null)
			return null;
		memberVO.setRegidate(new Date());
		memberMap.put(memberVO.getId(), memberVO);
		return memberVO;
	}
	@PutMapping("/member")
	public int updateMember(MemberVO memberVO) {
		MemberVO m = memberMap.get(memberVO.getId());
		if(m==null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
}
