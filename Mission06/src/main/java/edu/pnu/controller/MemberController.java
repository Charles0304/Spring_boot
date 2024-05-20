package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public List<Member> getMembers(){
		return memberService.getMembers();
	}
	
	@GetMapping("/member")
	public Member getMember(String username) {
		return memberService.getMember(username);
	}
	
	@PostMapping("/member")
	public Member insertMember(Member member) {
		return memberService.insertMember(member);
	}
	
	@PutMapping("/member")
	public Member updateMember(Member member) {
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member")
	public Member deleteMember(String username) {
		return memberService.deleteMember(username);
	}
}
