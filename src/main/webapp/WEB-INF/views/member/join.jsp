<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <meta name="theme-color" content="#563d7c">
    <title>포트폴리오 참고용 Bootstrap v4.6</title>
    
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    
    <%@include file="/WEB-INF/views/common/config.jsp" %>

<script src="/js/member/join.js"></script>
</head>
<body>
    
<!-- nav.jsp -->
<%@include file="/WEB-INF/views/common/nav.jsp" %>

<main role="main" class="container">

  <div class="wrapper">
    <%-- Main content --%>
		<section class="content container-fluid">
			<div class="container" style="width: 70%; min-width: 900px; background-color: white; font-size: 14px;" >
				<form id="joinForm" action="/member/join" method="post">
					<div class="container" style="width: 800px; padding: 10% 5%;">
						<h2 class="form-signin-heading">회원가입</h2>
						<br><br>
						<div class="form-group" style="width:100%;">
							<label for="inputId">* 아이디</label> <br /> <input type="text"
								class="form-control" id="mbme_id" name="mbme_id"
								placeholder="아이디를 입력해 주세요"
								style="max-width:540px; width:calc(100% - 100px); margin-right: 5px; display: inline-block;">
							<button id="btn_checkId" class="btn btn-default" type="button">중복 확인</button>
							<p id="id_error" style="color: red;"></p>
						</div>
						<div class="form-group">
							<label for="inputPassword">* 비밀번호</label>
							<input type="password" class="form-control" id="mbme_pw" name="mbme_pw"
							placeholder="비밀번호를 입력해주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<label for="inputPasswordCheck">* 비밀번호 확인</label> <input
								type="password" class="form-control" id="mbme_pw_check"
								placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요" style="max-width: 630px;" >
						</div>
						<div class="form-group">
							<label for="inputName">* 이름</label> <input type="text"
								class="form-control" id="mbme_name" name="mbme_name"
								placeholder="이름을 입력해 주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<label for="inputNickName">* 닉네임</label> <input type="text"
								class="form-control" id="mbme_nick" name="mbme_nick"
								placeholder="사용할 닉네임을 입력해 주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<label for="InputEmail">* 이메일 주소</label><br /> <input type="email"
								class="form-control" id="mbme_email" name="mbme_email"
								placeholder="이메일 주소를 입력해주세요"
								style="max-width: 526px; width:calc(100% - 115px); margin-right: 5px; display: inline-block;">
							<%--<button id="btn_sendAuthCode" class="btn btn-default" type="button" >이메일 인증</button> --%>
							<p id="authcode_status" style="color: red;"></p>
						</div>
						<!-- 이메일 인증 요청을 하고 , 성공적으로 진행이 되면, 아래 div태그가 보여진다. -->
						<div id="email_authcode" class="form-group" style="display: none;">
							<label for="inputAuthCode">* 이메일 인증코드</label><br /> 
							<input type="text"
								class="form-control" id="mem_authcode" 
								placeholder="이메일 인증코드를 입력해 주세요" 
								style="max-width: 570px; width:calc(100% - 70px); margin-right: 5px; display: inline-block;" />
							<button id="btn_checkAuthCode" class="btn btn-default" type="button" >확인</button>
						</div>
						<div class="form-group">
							<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
								class="form-control" id="mbme_phone" name="mbme_phone"
								placeholder="휴대폰 번호를 입력해 주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<label for="inputAddr">* 주소</label> <br />
							
							<input type="text" id="sample2_postcode" name="mbme_zipcode" class="form-control" 
								style="max-width: 510px; width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
							<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" class="btn btn-default" value="우편번호 찾기"><br>
							<input type="text" id="sample2_address" name="mbme_add" class="form-control" 
								placeholder="주소" style="max-width: 630px; margin:3px 0px;" readonly>
							<input type="text" id="sample2_detailAddress" name="mbme_add2" class="form-control" 
								placeholder="상세주소" style="max-width: 630px;">
							<input type="hidden" id="sample2_extraAddress" class="form-control" 
								placeholder="참고항목">
							
							<!-- 
							<input type="text" id="sample2_postcode" name="" class="form-control"  placeholder="우편번호" readonly>
							<input type="button" onclick="sample2_execDaumPostcode()" name="" class="form-control" value="우편번호 찾기"><br>
							<input type="text" id="sample2_address" name="" class="form-control" placeholder="주소" readonly><br>
							<input type="text" id="sample2_detailAddress" name="" class="form-control" placeholder="상세주소">
							<input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
							 -->
							 
						</div>
						<div class="form-group">
							<label>* 수신 동의</label><br /> 
							이벤트 등 프로모션 메일 알림 수신에 동의합니다.
							<label><input type="radio" name="mbme_eventre" value="Y" style="margin-left: 20px;" checked="checked"> 예</label>
	     						<label><input type="radio" name="mbme_eventre" value="N" style="margin-left: 20px;"> 아니오</label>
						</div>
					</div>
					<div class="form-group text-center">
						<button type="button" id="btn_submit" class="btn btn-primary">
							회원가입 <i class="fa fa-check spaceLeft"></i>
						</button>
						<button type="button" id="btn_cancle" class="btn btn-warning">
							가입취소 <i class="fa fa-times spaceLeft"></i>
						</button>
					</div>
					<br><br><br><br>
				</form>
			</div>
			
			<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
			<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
			</div>
			
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
			    // 우편번호 찾기 화면을 넣을 element
			    var element_layer = document.getElementById('layer');
			
			    function closeDaumPostcode() {
			        // iframe을 넣은 element를 안보이게 한다.
			        element_layer.style.display = 'none';
			    }
			
			    function sample2_execDaumPostcode() {
			        new daum.Postcode({
			            oncomplete: function(data) {			            
			                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			
			                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
			                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			                var addr = ''; // 주소 변수
			                var extraAddr = ''; // 참고항목 변수
			
			                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
			                    addr = data.roadAddress;
			                } else { // 사용자가 지번 주소를 선택했을 경우(J)
			                    addr = data.jibunAddress;
			                }
			
			                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			                if(data.userSelectedType === 'R'){
			                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
			                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
			                        extraAddr += data.bname;
			                    }
			                    // 건물명이 있고, 공동주택일 경우 추가한다.
			                    if(data.buildingName !== '' && data.apartment === 'Y'){
			                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			                    }
			                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			                    if(extraAddr !== ''){
			                        extraAddr = ' (' + extraAddr + ')';
			                    }
			                    // 조합된 참고항목을 해당 필드에 넣는다.
			                    document.getElementById("sample2_extraAddress").value = extraAddr;
			                
			                } else {
			                    document.getElementById("sample2_extraAddress").value = '';
			                }
			
			                // 우편번호와 주소 정보를 해당 필드에 넣는다.
			                document.getElementById('sample2_postcode').value = data.zonecode;
			                document.getElementById("sample2_address").value = addr;
			                // 커서를 상세주소 필드로 이동한다.
			                document.getElementById("sample2_detailAddress").focus();
			
			                // iframe을 넣은 element를 안보이게 한다.
			                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
			                element_layer.style.display = 'none';
			            },
			            width : '100%',
			            height : '100%',
			            maxSuggestItems : 5
			        }).embed(element_layer);
			
			        // iframe을 넣은 element를 보이게 한다.
			        element_layer.style.display = 'block';
			
			        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
			        initLayerPosition();
			    }
			
			    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
			    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
			    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
			    function initLayerPosition(){
			        var width = 300; //우편번호서비스가 들어갈 element의 width
			        var height = 400; //우편번호서비스가 들어갈 element의 height
			        var borderWidth = 5; //샘플에서 사용하는 border의 두께
			
			        // 위에서 선언한 값들을 실제 element에 넣는다.
			        element_layer.style.width = width + 'px';
			        element_layer.style.height = height + 'px';
			        element_layer.style.border = borderWidth + 'px solid';
			        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
			        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
			        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
			    }
			</script>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
 

</main><!-- /.container -->

      
  </body>
</html>