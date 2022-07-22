// loginObject 객체 선언 
let loginObject = {

	// init() 함수 선언 
	init: function() {
		let _this = this;
		
		// "#btn-save" 버튼에 "click" 이벤트가 발생하면 insertUser() 함수를 호출한다. 
		$("#btn-login").on("click", () => {
			_this.login();
		});
	},
	
	login: function() {
		alert("로그인 요청됨");
		
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
		}		

		// Ajax를 이용한 비동기 호출
		$.ajax({
			type: "POST", // 요청 방식
			url: "/auth/login", // 요청 path
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8"

		}).done(function(response) {
			// 메인 페이지로 이동한다.
			alert(response);
			location = "/";
		});

	},
}
 
// loginObject 객체의 init() 함수 호출. 
loginObject.init();
