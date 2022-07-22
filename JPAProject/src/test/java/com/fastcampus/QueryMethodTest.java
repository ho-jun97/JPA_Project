package com.fastcampus;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fastcampus.domain.Member;
import com.fastcampus.domain.Order;
import com.fastcampus.persistance.MemberRepository;
import com.fastcampus.persistance.OrderRepository;

@SpringBootTest
public class QueryMethodTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
//	@BeforeEach
	public void dataInsert() {
		for(int i=1; i<=200 ; i++) {
			Member member =  new Member();
			member.setName("테스터 " + i);
			member.setCity("서울 " + i);
			memberRepository.save(member);
		}
	}
	
	@Test
	void queryMethodTest() {
		int page = 0;
		int size = 5;
		Pageable pageable = PageRequest.of(page, size);
		Page<Member> pageInfo = memberRepository.findByNameContainingOrderByIdDesc("스터 10", pageable);
		System.out.println("[검색된 회원 목록]");
		for(Member member : pageInfo.getContent()) {
			System.out.println("--> " + member.toString());
		}
		System.out.println("검색된 데이터의 수 : " + pageInfo.getTotalElements());
		System.out.println("전체 페이지 수 : " + pageInfo.getTotalPages());
		System.out.println("한 ㅔ이지에 출력되는 데이터의 수 : " +  pageInfo.getSize());	
		System.out.println("현재 페이지가 첫번째 페이지인가? : "+ pageInfo.isFirst());
		System.out.println("현재 페이지가 마지막 페이지인가? : "+ pageInfo.isLast());
	}
	
}
