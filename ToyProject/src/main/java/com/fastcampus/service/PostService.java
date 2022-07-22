package com.fastcampus.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fastcampus.domain.Post;
import com.fastcampus.domain.User;
import com.fastcampus.persistence.PostRepository;
import com.fastcampus.persistence.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void insertPost(int userId, Post post) {
		User user = userRepository.findById(userId).get();
		post.setUser(user);
		postRepository.save(post);
	}
	
	@Transactional
	public Page<Post> getPostList(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	@Transactional
	public void updatePost(int id, Post post) {
		Post before = postRepository.findById(id).get();
		before.setTitle(post.getTitle());
		before.setContent(post.getContent());
	}
	
	public Post findById(int id) {
		return postRepository.findById(id).get();
	}
	
	@Transactional
	public void deletePost(int id) {
		Post post = postRepository.findById(id).get();
		
		postRepository.delete(post);
	}
	
	
}
