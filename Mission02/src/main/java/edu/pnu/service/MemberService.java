package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import edu.pnu.domain.MemberVO;

public class MemberService {
	private List<MemberVO> memberList = new ArrayList<>();
	
	public MemberService() {
		for(int i=1;i<=10;i++) {
			memberList.add(MemberVO.builder().id(i).pass("1234").name("member"+i).regidate(new Date()).build());
		}
	}
	

	public List<MemberVO> getMembers(){
		return memberList;
	}
	

	public MemberVO getMemberById(Integer id) {
		for(MemberVO m:memberList) {
			if(m.getId()==id)
				return m;
		}
		return null;
	}
	

	public MemberVO insertMember(MemberVO m) {
		if(getMemberById(m.getId())!=null)
			return null;
		m.setRegidate(new Date());
		memberList.add(m);
		return m;
	}

	public int updateMember(MemberVO m) {
		MemberVO member = getMemberById(m.getId());
		if(member==null)
			return 0;
		if(m.getPass()!=null)
			member.setPass(m.getPass());
		if(m.getName()!=null)
			member.setName(m.getName());
		
		return 1;
	}

	public int deleteMember(Integer id) {
		try {
			memberList.remove(getMemberById(id));
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
}
