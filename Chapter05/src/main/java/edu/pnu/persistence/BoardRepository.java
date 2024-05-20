package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	List<Board> findByTitleContaining(String searchKeyword);
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword,Long num);
	List<Board> findByCntBetweenOrderBySeq(Long min,Long max);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title,String content);
	
	Page<Board> findByTitleContaining(String searchKeyword,Pageable paging);
	
	@Query("select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest11(String searchKeyword);
	
	@Query("select b.seq,b.title,b.writer,b.createDate from Board b "
			+"where b.title like %?1% order by b.seq desc")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword")String searchKeyword);
	
	@Query(value = "select seq, title, writer, create_date "
			+"from board where title like '%'||?1||'%' "
			+"order by seq desc",nativeQuery = true)
	List<Object[]> queryAnnotationTest3(String searchKeyword);
}
