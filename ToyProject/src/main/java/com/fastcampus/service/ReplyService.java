package com.fastcampus.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.domain.Post;
import com.fastcampus.domain.Reply;
import com.fastcampus.domain.User;
import com.fastcampus.persistence.PostRepository;
import com.fastcampus.persistence.ReplyRepository;
import com.fastcampus.persistence.UserRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void insertReply(int postId, int userId, Reply reply) {
		Post post = postRepository.findById(postId).get();
		User user = userRepository.findById(userId).get();
		
		user.addReply(reply);
		post.addReply(reply);
		
		replyRepository.save(reply);
	}
	
	@Transactional
	public void deleteReply(int id) {
		Reply reply = replyRepository.findById(id).get();
		
		replyRepository.delete(reply);
	}
}
