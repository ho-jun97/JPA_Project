package com.fastcampus.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
