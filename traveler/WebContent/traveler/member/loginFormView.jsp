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
		<form class="login-form" action="${pageContext.request.contextPath }/member/loginResult.m">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="login-title">로그인</div>
				<p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" name="id" placeholder="Id" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" class="form-control" name="pwd" placeholder="Password">
				</div>
				<label class="checkbox"> <input type="checkbox"
					value="remember-me"> 아이디 기억하기 <span class="pull-right">
						<a href="${pageContext.request.contextPath }/member/idSearchForm.m">ID/</a>
						<a href="${pageContext.request.contextPath }/member/pwSearchForm.m">PW찾기</a>
				</span>
				</label>
				<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
				<button class="btn btn-info btn-lg btn-block" type="button"
					onClick="location.href='${pageContext.request.contextPath }/member/joinForm.m'">가입하기</button>
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
