package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@BeforeEach//테스트전에 실행
	public void datePrepare() {
		for(int i=1;i<=200;i++) {
			Random rd = new Random();
			Board board = new Board();
			board.setTitle("테스트 제목"+i);
			//board.setWriter("테스터");
			board.setContent("테스트 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(100));
			boardRepo.save(board);
		}
	}
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "CONTENT";
		String searchKeyword = "테스트 내용10";
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.contains(searchKeyword));
		}else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boardList=boardRepo.findAll(builder,paging);
		System.out.println("검색결과");
		for(Board board:boardList)
			System.out.println("---->"+board);
	}
}
