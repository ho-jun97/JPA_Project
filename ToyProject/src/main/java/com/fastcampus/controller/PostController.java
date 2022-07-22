package com.fastcampus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.domain.Post;
import com.fastcampus.domain.Reply;
import com.fastcampus.domain.User;
import com.fastcampus.security.jpa.UserDetailsImpl;
import com.fastcampus.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping({"", "/"})
	public String getPostList(Model model, 
			@PageableDefault(size = 3, sort = "id",direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList",postService.getPostList(pageable));
		return "welcome";
	}
	
	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}
	
	@PostMapping("/post/insertPost")
	@ResponseBody
	public String insertPost(@RequestBody Post post, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		//Post 객체를 등록하기 위해서는 반드시 User 객체를 Post에 설정해야한다.
		//그래야 Post가 Post 테이블에 등록될 때 FK컬럼에 회원의 PK를 등록한다.
		User user = userDetailsImpl.getUser();
		postService.insertPost(user.getId(),post);
		return "새로운 1:1 문의를 등록했습니다.";
	}
	
	@GetMapping("/post/details/{id}")
	public String detailsPost(@PathVariable int id, Model model
			,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl ) {
		Post post = postService.findById(id);
		List<Reply> replyList = post.getReplyList();
		if(replyList != null) {
			model.addAttribute("replyList",replyList);
		}
		model.addAttribute("post", post);
		model.addAttribute("user", userDetailsImpl.getUser());
		return "post/detailsPost";
	}
	
	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		System.out.println("==> 수정 폼()");
		Post post = postService.findById(id);
		model.addAttribute("post", post);
		return "post/updatePost";
	}
	@PutMapping("/post/updatePost/{id}")
	public @ResponseBody String update(@PathVariable int id, @RequestBody Post post) {
		System.out.println("==> 수정 기능 진행중()");
		postService.updatePost(id, post);
		return "1:1 문의를 수정했습니다.";
	}
	@DeleteMapping("/post/deletePost/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		System.out.println("==> 삭제 기능 진행중()");
		postService.deletePost(id);
		return "1:1 문의를 삭제했습니다.";
	}
}
