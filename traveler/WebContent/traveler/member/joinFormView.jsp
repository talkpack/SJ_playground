<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../index/pathTop.jsp"%>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

function getCookie( cookieName )
{
 var search = cookieName + "=";
 var cookie = document.cookie;

 // 현재 쿠키가 존재할 경우
 if( cookie.length > 0 )
 {
  // 해당 쿠키명이 존재하는지 검색한 후 존재하면 위치를 리턴.
  startIndex = cookie.indexOf( cookieName );

  // 만약 존재한다면
  if( startIndex != -1 )
  {
   // 값을 얻어내기 위해 시작 인덱스 조절
   startIndex += cookieName.length;

   // 값을 얻어내기 위해 종료 인덱스 추출
   endIndex = cookie.indexOf( ";", startIndex );

   // 만약 종료 인덱스를 못찾게 되면 쿠키 전체길이로 설정
   if( endIndex == -1) endIndex = cookie.length;

   // 쿠키값을 추출하여 리턴
   return unescape( cookie.substring( startIndex + 1, endIndex ) );
  }
  else
  {
   // 쿠키 내에 해당 쿠키가 존재하지 않을 경우
   return false;
  }
 }
 else
 {
  // 쿠키 자체가 없을 경우
  return false;
 }
}

function idCheck(){
	var userID = $('#id').val();
	$.ajax({
		type: 'POST',
		url: 'idCheck.m',
		data: {id: userID},
		success: function(result){
			if(getCookie('mycookie') == "0"){
				$('#idcheckMessage').html('사용할 수 있는 아이디입니다.');
				$('#id').css("background-color", "#B0F6AC");
				
			}
			else{
				$('#idcheckMessage').html('사용할 수 없는 아이디입니다.');
				$('#id').css("background-color", "#FFCECE");
				$('#joinbutton').prop("disabled", true);
			}
		}
	})
}
//재입력 비밀번호 체크하여 가입버튼 비활성화 또는 맞지않음을 알림.
function checkPwd() {
    var inputed = $('#pwd').val();
    var reinputed = $('#confirm_password').val();
    if(reinputed=="" && (inputed != reinputed || inputed == reinputed)){
        $('#joinbutton').prop("disabled", true);
        $('#pwdcheckMessage').html('비밀번호가 일치하지 않습니다.');
        $('#joinbutton').css("background-color", "#aaaaaa");
        $('#confirm_password').css("background-color", "#FFCECE");
    }
    else if (inputed == reinputed) {
        $('#confirm_password').css("background-color", "#B0F6AC");
        pwdCheck = 1;
        if(pwdCheck == 1) {
            $('#joinbutton').prop("disabled", false);
            $('#pwdcheckMessage').html('');
            $('#joinbutton').css("background-color", "#4CAF50");
            signupCheck();
        }
    } else if (inputed != reinputed) {
        pwdCheck = 0;
        $('#joinbutton').prop("disabled", true);
        $('#pwdcheckMessage').html('비밀번호가 일치하지 않습니다.');
        $('#joinbutton').css("background-color", "#aaaaaa");
        $('#confirm_password').css("background-color", "#FFCECE");
    }
}
//닉네임과 이메일 입력하지 않았을 경우 가입버튼 비활성화
function signupCheck() {
    var nickname = $("#fullname").val();
    var email = $("#email").val();
    if(nickname=="" || email=="") {
        $("#joinbutton").prop("disabled", true);
        $("#joinbutton").css("background-color", "#aaaaaa");
    } else {
    	$("#joinbutton").prop("disabled", false);
    }
}
</script>
</head>
<body>
	<%@ include file="../index/top.jsp"%>
	<%@ include file="../index/side.jsp"%>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!--overview start-->
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">
						<i class="fa fa-laptop"></i>가입하기
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<section class="panel panel-join">
						<header class="panel-heading"> Join </header>
						<div class="panel-body">
							<div class="form" align="left">
								<form class="form-validate form-horizontal" id="register_form"
									method="post" action="${pageContext.request.contextPath }/member/joinResult.m">
									<div class="form-group ">
										<label for="fullname"
											class="control-label col-lg-2 join-label">이름 <span
											class="required">*</span></label>
										<div class="col-lg-10">
											<input class=" form-control" id="fullname" name="name"
												type="text" />
										</div>
									</div>
									<div class="form-group">
										<label for="username"
											class="control-label col-lg-2 join-label">아이디 <span
											class="required">*</span></label>
										<div class="col-lg-10">
											<input class="form-control join-idcheck" id="id"
												name="id" oninput="idCheck()" type="text" />&nbsp;&nbsp;&nbsp; 
											<span id="idcheckMessage">ID를 입력하세요.</span>
										</div>
									</div>
									<div class="form-group">
										<label for="email" class="control-label col-lg-2 join-label">이메일
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="join-email " id="email" name="email1" type="text" />@ 
											<select class="join-email" name="email2">
												<option>hanmail.net</option>
												<option>naver.com</option>
												<option>gamil.com</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="password"
											class="control-label col-lg-2 join-label">비밀번호 <span
											class="required">*</span></label>
										<div class="col-lg-10">
											<input class="form-control " id="pwd" name="pwd"
												type="password" oninput="checkPwd()" />
										</div>
									</div>

									<div class="form-group">
										<label for="confirm_password"
											class="control-label col-lg-2 join-label">비밀번호 재확인 <span
											class="required">*</span></label>
										<div class="col-lg-10">
											<input class="form-control" id="confirm_password"
												name="confirm_password" type="password" oninput="checkPwd()" />
											<span id="pwdcheckMessage">비밀번호를 확인해 주세요.</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-2 join-label">성별 <span
											class="required">*</span></label>

										<div class="col-lg-10">
											<input type="radio" name="gender">남&nbsp;&nbsp;&nbsp;
											<input type="radio" name="gender">여
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-lg-2 join-label">생년월일
											<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="join-birth " id="year" name="year"
												type="text" />년 <input class="join-birth " id="Month1"
												name="month" type="text" />월 <input class="join-birth "
												id="day" name="day" type="text" />일
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-2 join-label">폰번호 <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="join-ph" name="ph1" type="text" />&nbsp;&nbsp;-&nbsp;&nbsp;
											<input class="join-ph " name="ph2" type="text" />&nbsp;&nbsp;-&nbsp;&nbsp;
											<input class="join-ph" name="ph3" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-2 join-label">질문 <span class="required">*</span></label> 
										<select name="question" class="join-question">
											<option>제일 좋아하는 색깔은?</option>
											<option>제일 좋아하는 강아지는?</option>
											<option>제일 좋아하는 고양이는?</option>
										</select>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-2 join-label">답변 <span
											class="required">*</span></label>
										<div class="col-lg-10">
											<input class="form-control" name="answer" type="text" />
										</div>
									</div>
									<div class="form-group">
										<label for="agree" class="control-label col-lg-2 col-sm-3 join-label">약관동의
											<span class="required">*</span>
										</label>
										<div class="col-lg-10 col-sm-9">
											<input type="checkbox" style="width: 20px" class="checkbox form-control" id="agree" name="agree" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button class="btn btn-primary" id="joinbutton" type="submit" disabled="disabled">가입</button>
											<button class="btn btn-default" type="button"
												onClick="location.href='${pageContext.request.contextPath}/main.do'">취소</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</section>
				</div>
			</div>
		</section>
	</section>
			<%@ include file="../index/bottom.jsp"%>
			<%@ include file="../index/pathBottom.jsp"%>
			<%@ include file="../index/script.jsp"%>
</body>
</html>
