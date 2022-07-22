package com.fastcampus;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.domain.Member;
import com.fastcampus.domain.Order;
import com.fastcampus.persistance.MemberRepository;
import com.fastcampus.persistance.OrderRepository;

@SpringBootTest
public class CascadeTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Test
	void dataDelete() {
		memberRepository.deleteById(1);
	}
	
}
