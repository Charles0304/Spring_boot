package edu.pnu;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {
	private final MemberRepository memberRepo;
	private final BoardRepository boardRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for(int i=1;i<=5;i++) {
			Member m = Member.builder().pass("pass"+i)
					.username("member"+i)
					.role("USER").build();
			memberRepo.save(m);
			for(int j=1;j<=5;j++) {
				Board b = Board.builder().member(m)
						.cnt(0L)
						.title("title"+i+"-"+j)
						.content("content"+i+"-"+j)
						.createDate(new Date())
						.build();
				boardRepo.save(b);
			}
		}
	}

}
