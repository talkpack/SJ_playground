<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../index/pathTop.jsp"%>
</head>
<body class="login-img3-body">
	<%@ include file="../index/top.jsp"%>
	<%@ include file="../index/side.jsp"%>
	<div class="container">

		<form class="login-form" action="${pageContext.request.contextPath }/member/pwSearchResult.m">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="login-title">비밀번호 찾기</div>
				<p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" name="id" placeholder="아이디" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="text" class="form-control" name="ph1"  placeholder="3자리"
						autofocus>
					<span class="input-group-addon"></span>
					<input type="text" class="form-control" name="ph2" placeholder="4자리"
						autofocus>
					<span class="input-group-addon"></span>
					<input type="text" class="form-control" name="ph3" placeholder="4자리"
						autofocus>
				</div>
				<div class="input-group" >
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<select name="question" class="join-question"  style="width: 254px; display: inline-block;">
											<option>제일 좋아하는 색깔은?</option>
											<option>제일 좋아하는 강아지는?</option>
											<option>제일 좋아하는 고양이는?</option>
					</select>
					
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="text" class="form-control" name="answer" placeholder="응답"
						autofocus>
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="submit">찾기</button>
				<button class="btn btn-info btn-lg btn-block" type="button"
					onClick="location.href='${pageContext.request.contextPath }/member/loginForm.m'">취소</button>
			</div>
		</form>
	</div>
	<p>
	<p>
	<p>
	<p>
	<p>
	<p>
		<%@ include file="../index/bottom.jsp"%>
		<%@ include file="../index/pathBottom.jsp"%>
		<%@ include file="../index/script.jsp"%>
</body>
</html>
