package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberInitialize implements ApplicationRunner {

	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		memberRepo.save(Member.builder().username("finance").password(encoder.encode("abcd"))
				.role(Role.ROLE_FINANCE).enabled(true).build());
		memberRepo.save(Member.builder().username("develop").password(encoder.encode("abcd"))
				.role(Role.ROLE_DEVELOP).enabled(true).build());
		memberRepo.save(Member.builder().username("market").password(encoder.encode("abcd"))
				.role(Role.ROLE_MARKET).enabled(true).build());
		memberRepo.save(Member.builder().username("admin").password(encoder.encode("abcd"))
				.role(Role.ROLE_ADMIN).enabled(true).build());

	}

}
