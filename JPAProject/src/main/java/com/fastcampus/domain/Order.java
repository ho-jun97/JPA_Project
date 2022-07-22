package com.fastcampus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ORDERS")
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private int id;
	
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY) // 주문(Many) : 회원(One)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
}
