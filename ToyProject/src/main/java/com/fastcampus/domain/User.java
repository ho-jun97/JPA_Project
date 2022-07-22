package com.fastcampus.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/*

CREATE TABLE USER (
	ID 		NUMBER(5) 	PRIMARY KEY,
	USERNAME 	VARCHAR2(30),
	PASSWORD 	VARCHAR2(100),
	EMAIL 	VARCHAR2(30)
);*/


@Data
@Table(name = "USERS")
@Entity
public class User {
	@Id	//식별자 변수 PK
	//1부터 자동으로 1씩 값이 증가하도록 설정한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 회원 일련번호
	
	@Column(nullable = false, length = 50, unique = true)
	private String username; // 아이디
	
	@Column(length = 50)
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 100)
	private String email; // 이메일
	
	private String role;
	
	@CreationTimestamp	//현재 시간 정보가 자동으로 설정된다.
	private Timestamp createDate;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	List<Reply> replyList = new ArrayList<>();
	
	public void addReply(Reply reply) {
		reply.setUser(this);
		this.replyList.add(reply);
	}
}

