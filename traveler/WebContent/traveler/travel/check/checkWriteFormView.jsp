<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../../index/pathTop.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${loginId == null}">
	<script>
		alert("로그인 먼저 하셔야 합니다.\n 로그인페이지로 이동~")
		location.href="<%=request.getContextPath()%>/member/loginForm.m";
	</script>
</c:if> 
</head>
<body>
	<%@ include file="../../index/top.jsp"%>
	<%@ include file="../../index/side.jsp"%>
	<section id="main-content">
		<section class="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">
						<i class="fa fa-laptop"></i>여행 다나와
					</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a
							href="<%=request.getContextPath()%>/main.do">Home</a></li>
						<li><i class="fa fa-laptop"></i><a
							href="<%=request.getContextPath()%>/main.do">여행다나와</a></li>
					</ol>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<div class="info-box blue-bg">
						<i class="fa fa-cloud-download"></i>
						<div class="count">${totalCount }</div>
						<div class="title">누적 방문자 수</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<div class="info-box brown-bg">
						<i class="fa fa-cloud-download"></i>
						<div class="count">${todayCount}</div>
						<div class="title">오늘 방문자 수</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<div class="info-box dark-bg">
						<i class="fa fa-thumbs-o-up"></i>
						<div class="count">${boardCount}</div>
						<div class="title">게시물 수</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<div class="info-box green-bg">
						<i class="fa fa-cubes"></i>
						<div class="count">${memberCount }</div>
						<div class="title">회원가입 수</div>
					</div>
				</div>
			</div>

			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 글 작성 </header>
					<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/check/checkWriteResult.t" class="form-horizontal">
						<input type="hidden" name="writer" value="${loginId}" />
						<input type="hidden" name="country" value="${country}" />
						<input type="hidden" name="city" value="${city}" />
						<input type="hidden" name="term" value="${term}" />
						<input type="hidden" name="money" value="${money}" />
						<input type="hidden" name="route" value="${route}" />
						<div class="panel-body">
							<div class="form">
								<div class="form-group" style="margin: 3px;">
									<label class="control-label col-sm-2" style="font-weight:bold;">분류</label>
									<div class="col-sm-10 ">
										<button class="btn btn-danger disabled check-category" style="background-color:#5CD1E5; border:1px solid #ffffff; "><span class="icon_pin_alt"></span>&nbsp;&nbsp;${country }</button>
										<button class="btn btn-danger disabled check-category"style="background-color:#BCE55C; border:1px solid #ffffff; "><span class="icon_pin_alt"></span>&nbsp;&nbsp;${city }</button>
										<button class="btn btn-danger disabled check-category"style="background-color:#D1B2FF; border:1px solid #ffffff; "><span class="icon_calendar"></span>&nbsp;&nbsp;${termSet }</button>
										<button class="btn btn-danger disabled check-category"style="background-color:#FFA7A7; border:1px solid #ffffff; "><span class="icon_lightbulb_alt"></span>&nbsp;&nbsp;${moneySet }&nbsp;</button>
										<button class="btn btn-danger disabled check-category"style="background-color:#F2CB61; border:1px solid #ffffff; "><span class="icon_house_alt"></span>&nbsp;&nbsp;${routeSet }&nbsp;</button>									
									</div>
								</div>
								<div class="form-group" style="margin: 3px;">
									<label class="control-label col-sm-2" style="font-weight:bold;">제목</label>
									<div class="col-sm-10 ">
										<input type="text" name="subject" class="check-write" placeholder="Subject" required>
									</div>
								</div>
								<div class="form-group" style="margin: 3px;">
									<label class="control-label col-sm-2" style="font-weight:bold;">내용</label>
									<div class="col-sm-10">
										<textarea id="contentEditor" class="form-control ckeditor" name="content" rows="6"></textarea>
									</div>
								</div>
								<div class="form-group" style="margin: 3px;">
									<label class="control-label col-sm-2" style="font-weight:bold;">비밀번호</label>
									<div class="col-sm-10">
										<input type="password" name="writePwd" class="check-write " placeholder="Password" required>
									</div>
								</div>
								<div class="form-group" style="margin: 3px;">
									<label class="control-label col-sm-2" style="font-weight:bold;">대표 이미지</label>
									 <div class="col-sm-10" style="padding-top:7px;">
										<input type="file" id="exampleInputFile" name="filename">
									</div> 
									
								</div>
								<div class="form-group" style="margin: 3px;">
									<div style="text-align: center;">
										<input class="btn btn-primary btn-sm" type="submit" value="작성">
										<input class="btn btn-info btn-sm" type="button" value="취소">
									</div>
								</div>
							</div>
						</div>
					</form>
				</section>
			</div>

			<%@ include file="../../index/bottom.jsp"%>
			<%@ include file="../../index/pathBottom.jsp"%>
			<%@ include file="../../index/script.jsp"%>
</body>
</html>
