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
public class ManyToOneTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	@Transactional
	void dataSelect() {
		Optional<Order> findOrder = orderRepository.findById(1);
		
		if(findOrder.isPresent()) {
			Order order = findOrder.get();
			System.out.println(order.getId() + "번 주문 상세 정보");
			System.out.println("주문 상태 : " + order.getStatus());
			System.out.println("주문자 이름 : " + order.getMember().getName());
			System.out.println("주문자 도시 : " + order.getMember().getCity());
		}
	}
	
	
//	@Test
	void dataInsert() {
		
		//Member 등록
		Member member1 = new Member();
		member1.setName("둘리");
		member1.setCity("서울");
		memberRepository.save(member1);
		
		Member member2 = new Member();
		member2.setName("또치");
		member2.setCity("대전");
		memberRepository.save(member2);
		
		for(int i=1; i<=5; i++) {
			Order order = new Order();
			order.setStatus("배송완료");
			// 실제로는 ORDERS 테이블의 MEMBER_ID 컬럼(FK)에 회원의 아이디가 설정된다.
			order.setMember(member1);
			
			orderRepository.save(order);
		}
	}
}
