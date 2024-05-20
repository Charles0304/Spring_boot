package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	public List<Board> getBoard(String username){
		Member m = memberRepo.findById(username).get();
		
		return boardRepo.findByMember(m);
		
	}
	
	public Board insertBoard(Board board,Member member) {
		board.setMember(member);
		boardRepo.save(board);
		return board;
	}
}
