package edu.pnu;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class Mission03ApplicationTests {
	
	@Autowired
	private MemberDao memberdao;
	

	@Test
	public void test() throws SQLException {
		List<MemberVO> list = memberdao.getMembers();
		for(MemberVO m : list)
			System.out.println(m);
	}
//	@Test
//	void contextLoads() throws SQLException {
//		MemberDao dao = new MemberDao();
//		MemberVO m = dao.getMemberById(1);
//		System.out.println(m);
//	}

}
