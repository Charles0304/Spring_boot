package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boards")
	public List<Board> getBoards(){
		return boardService.getBoards();
	}
	
	@GetMapping("/board")
	public List<Board> getBoardsById(String username){
		return boardService.getBoard(username);
	}
	
	//@PostMapping("/board")
}
