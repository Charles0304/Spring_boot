package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public List<Member> getMembers(){
		return memberRepo.findAll();
	}
	
	public Member getMember(String username) {
		return memberRepo.findById(username).get();
	}
	
	public Member insertMember(Member member) {
		memberRepo.save(member);
		return member;
	}
	
	public Member updateMember(Member member) {
		Member m = memberRepo.findById(member.getUsername()).get();
		if(member.getPass()!=null)
			m.setPass(member.getPass());
		if(member.getRole()!=null)
			m.setRole(member.getRole());
		memberRepo.save(m);
		return m;
	}
	
	public Member deleteMember(String username) {
		Member m = memberRepo.findById(username).get();
		memberRepo.deleteById(username);
		return m;
	}
}
