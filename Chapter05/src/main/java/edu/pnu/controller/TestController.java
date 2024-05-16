package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	
	@GetMapping("/boards")
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	@PostMapping("/board")
	public Board insertBoard(Board board) {
		board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	@PutMapping("/board")
	public Board updateBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		if(board.getContent()!=null)
			b.setContent(board.getContent());
		if(board.getTitle()!=null)
			b.setTitle(board.getTitle());
		if(board.getWriter()!=null)
			b.setWriter(board.getWriter());
		if(board.getCnt()!=null)
			b.setCnt(board.getCnt());
		return boardRepo.save(b);
	}
	
	@DeleteMapping("/board/{seq}")
	public void deleteBoard(@PathVariable Long seq) {
		boardRepo.deleteById(seq);
	}
}
