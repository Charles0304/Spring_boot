package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {
	@Autowired private BoardRepository boardRepo;
	
	@BeforeEach//테스트전에 실행
	public void datePrepare() {
		for(int i=1;i<=200;i++) {
			Random rd = new Random();
			Board board = new Board();
			board.setTitle("테스트 제목"+i);
			board.setWriter("테스터");
			board.setContent("테스트 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(100));
			boardRepo.save(board);
		}
	}
	
	//@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스터 제목10");
		System.out.println();
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
	
	//@Test
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		System.out.println("검색결과");
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
	
	//@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17", "17");
		System.out.println("검색결과");
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
	
	//@Test
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("17");
		System.out.println("검색결과");
		for(Board board : boardList) {
			System.out.println("---->"+board.toString());
		}
	}
	
	//@Test
	public void testQueryMethod() {
		List<Board> boardList;
		//boardList=boardRepo.findByTitleContaining("1");
		//boardList= boardRepo.findByTitleContainingAndCntGreaterThan("12",50L);
		//boardList=boardRepo.findByCntBetweenOrderBySeq(40L, 50L);
		//boardList=boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10","20");
		
		Pageable paging =PageRequest.of(0, 5, Sort.Direction.ASC,"seq");
		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목",paging);
		//boardList = boardRepo.findByTitleContaining("제목", paging);
		System.out.println("결과1");
		for(Board board : pageInfo) {
			System.out.println("--->"+board.toString());
		}
		System.out.println("page size : "+pageInfo.getSize());
		System.out.println("total pages : "+pageInfo.getTotalPages());
		System.out.println("total count : "+pageInfo.getTotalElements());
		System.out.println("next : "+pageInfo.getContent());
		
	}
	
	@Test
	public void testQueryAnnotationTest1() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("테스트 제목10");
		System.out.println("검색 결과");
		for(Object[] row:boardList) {
			System.out.println("---->"+Arrays.toString(row));
		}
	}
}
