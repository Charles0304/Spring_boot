package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {
	@Autowired private BoardRepository boardRepo;
	
	@BeforeEach//테스트전에 실행
	public void datePrepare() {
		for(int i=1;i<=200;i++) {
			Board board = new Board();
			board.setTitle("테스터 제목"+i);
			board.setWriter("테스터");
			board.setContent("테스터 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}
	
	@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스터 제목10");
		System.out.println();
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
	
	@Test
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		System.out.println("검색결과");
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
	
	@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17", "17");
		System.out.println("검색결과");
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
}
