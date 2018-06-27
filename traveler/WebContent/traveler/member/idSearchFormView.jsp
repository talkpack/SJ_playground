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

		<form class="login-form" action="${pageContext.request.contextPath }/member/idSearchResult.m">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="login-title">아이디 찾기</div>
				<p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" placeholder="이름" name="name" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" name="year" class="form-control" placeholder="1992">

					<span class="input-group-addon"></span>
					<input type="password" name="month" class="form-control" placeholder="02">

					<span class="input-group-addon"></span>
					<input type="password" name="day" class="form-control" placeholder="25">
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
