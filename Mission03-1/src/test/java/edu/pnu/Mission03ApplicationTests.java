package edu.pnu;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@SpringBootTest
class Mission03ApplicationTests {

	@Test
	void contextLoads() throws SQLException {
		MemberDao dao = new MemberDao();
		MemberVO m = dao.getMemberById(1);
		System.out.println(m);
	}

}
