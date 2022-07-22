package com.fastcampus.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastcampus.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
}
