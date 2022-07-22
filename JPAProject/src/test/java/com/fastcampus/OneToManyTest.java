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
public class OneToManyTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Test
	@Transactional
	void dataSelect() {
		Optional<Member> findMember = memberRepository.findById(1);
		
		if(findMember.isPresent()) {
			Member member = findMember.get();
			System.out.println(member.getId() + "번 회원 상세 정보");
			System.out.println("회원 이름 : " + member.getName());
			System.out.println("회원 도시 : " + member.getCity());
		
//			System.out.println("[주문 목록]");
//			for(Order order : member.getOrders()) {
//				System.out.println("---> "+order.getId() + ", " + order.getStatus());
//			}
		}
	}
	
}
