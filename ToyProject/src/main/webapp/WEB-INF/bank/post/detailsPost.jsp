<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container-fluid mt-3" style="width: 50%">

	<div class="card">
		<div class="card-body">
			<h4 class="card-title">${post.title }</h4>
			<br>
			<p class="card-text">${post.content }</p>
			<br>
			<p>포스트 번호 : ${post.id }</p>
			<p>작성자 : ${post.user.username }</p>
		</div>
		<hr>
		<div>
			<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
			<c:if test="${post.user.id == user.id }">
				<a href="/post/updatePost/${post.id }" class="btn btn-warning">수정하기</a>
				<button id="btn-delete" class="btn btn-danger" onclick="deletePost(${post.id})">삭제하기</button>
			</c:if>
		</div>
		<div>
			<c:if test="${!empty replyList }">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>내용</th>
							<th>작성자</th>
						</tr>
					</thead>
					<c:forEach var="reply" items="${replyList }">
						<tbody>
							<tr>
								<td width="400">${reply.content}</td>
								<td width="50">${ reply.user.username}</td>
								<c:if test="${reply.user.id == user.id}">
								<td width="50">
									<button class="btn btn-secondary" onclick="deleteReply(${reply.id},${post.id })">삭제</button>
								</td>
								</c:if>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<form>
			<div class="mb-3">
				<textarea class="form-control" rows="1" id="reply"></textarea>
			</div>
			<div class="text-end">
				<button id="btn-reply-insert" class="btn btn-secondary" onclick="insertReply(${post.id})">댓글등록</button>
			</div>
		</form>
		<hr>
	</div>
</div>
<script src="/js/post.js"></script>
<script src="/js/reply.js"></script>
<%@ include file="../layout/footer.jsp"%>
