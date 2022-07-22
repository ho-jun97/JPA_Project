package com.fastcampus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.domain.Reply;
import com.fastcampus.domain.User;
import com.fastcampus.security.jpa.UserDetailsImpl;
import com.fastcampus.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/reply/insertReply/{postId}")
	public @ResponseBody String insertReply(@PathVariable int postId, @RequestBody Reply reply
			,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userDetailsImpl.getUser();
		replyService.insertReply(postId, user.getId(), reply);
		return "댓글 작성 완료 되었습니다.";
	}
	
	@DeleteMapping("/reply/deleteReply/{id}")
	public @ResponseBody String deleteReply(@PathVariable int id) {
		replyService.deleteReply(id);
		return "댓글 삭제되셨습니다.";
	}
	
}
