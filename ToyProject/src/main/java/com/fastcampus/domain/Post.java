package com.fastcampus.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Post {
	@Id	//식별자 변수 PK
	//1부터 자동으로 1씩 값이 증가하도록 설정한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 회원 일련번호

	@Column(nullable = false, length = 100)
	private String title; // 아이디
	
	@Lob	//아주 긴 GB 크기의 문자데이터를 저장할 수 있는 설정
	@Column(nullable = false)
	private String content; // 이메일
	
	@CreationTimestamp	//현재 시간 정보가 자동으로 설정된다.
	private Timestamp createDate;
	
	//포스트: 회원 = N:1 관계다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")	//FK
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	List<Reply> replyList = new ArrayList<>();
	
	public void addReply(Reply reply) {
		reply.setPost(this);
		this.replyList.add(reply);
	}
}

