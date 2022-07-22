package com.fastcampus.persistance;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fastcampus.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	List<Member> findByName(String name);
	List<Member> findByNameContaining(String name);
	List<Member> findByNameContainingOrCityContaining(String name, String city);
	List<Member> findByNameContainingAndCityContaining(String name, String city);
	List<Member> findByNameContainingOrderByIdDesc(String name);
	Page<Member> findByNameContainingOrderByIdDesc(String name, Pageable pageable);

//	@Query(value = "select member_id, city, name from member where city like '%'||:keyword||'%' order by member_id desc",)
//	List<Member> getMemberList(@Param("keyword") String keyword);
}
