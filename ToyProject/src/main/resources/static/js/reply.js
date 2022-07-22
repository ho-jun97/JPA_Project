function insertReply(postId) {
	alert("댓글 작성 요청");
	let reply = {
		content: $("#reply").val()
	}
	$.ajax({
		type: "POST",
		url: "/reply/insertReply/"+postId,
		data: JSON.stringify(reply), 
		contentType: "application/json; charset=utf-8"
	}).done(function(response) {
		alert(response);
		location = "/post/details/"+postId;
	});
}

function deleteReply(replyId, postId) {
	alert("댓글 삭제 요청됨");
	
	$.ajax({
		type: "DELETE",
		url: "/reply/deleteReply/" + replyId,
		contentType: "application/json; charset=utf-8"
	}).done(function(response) {
		alert(response);
		location = "/post/details/"+postId;
	});
}

