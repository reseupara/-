$(document).ready(function() {
	
	var form = $("#loginForm");
	
	// 로그인 버튼 클릭 시 
	$("#btn_login").on("click", function(){
		
		var mbme_id = $("#mbme_id");
		var mbme_pw = $("#mbme_pw");

		if(mbme_id.val()==null || mbme_id.val()==""){
			alert("Please enter your ID.");
			mbme_id.focus();
			
		} else if(mbme_pw.val()==null || mbme_pw.val()==""){
			alert("Please enter your Password.");
			mbme_pw.focus();

		} else {
			form.submit();
		}
	});
	
});

