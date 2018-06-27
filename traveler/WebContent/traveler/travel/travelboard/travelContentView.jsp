<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../../index/pathTop.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
						<li><i class="fa fa-home"></i><a href="<%=request.getContextPath()%>/main.do">Home</a></li>
						<li><i class="fa fa-laptop"></i>여행다나와</li>
					</ol>
				</div>
			</div>
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 글 내용 </header>
					<div class="panel-body">
						<div class="form">					
							<div><label class="travel-content-subject "  style="margin: 3px;">${subject} </label>	<p><p><p><p></div>
							<div style="text-align:center;">
								<button class="btn btn-danger disabled check-category" style="background-color:#5CD1E5; border:1px solid #ffffff; "><span class="icon_pin_alt"></span>&nbsp;&nbsp;${travelBoardDto.country }</button>
								<button class="btn btn-danger disabled check-category"style="background-color:#BCE55C; border:1px solid #ffffff; "><span class="icon_pin_alt"></span>&nbsp;&nbsp;${travelBoardDto.city }</button>
								<button class="btn btn-danger disabled check-category"style="background-color:#D1B2FF; border:1px solid #ffffff; "><span class="icon_calendar"></span>&nbsp;&nbsp;${termSet }</button>
								<button class="btn btn-danger disabled check-category"style="background-color:#FFA7A7; border:1px solid #ffffff; "><span class="icon_lightbulb_alt"></span>&nbsp;&nbsp;${moneySet }&nbsp;</button>
								<button class="btn btn-danger disabled check-category"style="background-color:#F2CB61; border:1px solid #ffffff; "><span class="icon_house_alt"></span>&nbsp;&nbsp;${routeSet }&nbsp;</button>	
							</div>
							<hr color="#191919" width="100%"  noshade />
							<div class="travel-content">${travelBoardDto.content}</div>
							<div  style="margin: 3px;">
								<div id="favorite" style=" text-align: right;">
									<a href="javascript:favoritePlus();"> <span style="color:#747474; font-size:20px; ">♡</span> </a>
									<input class="travel-content-button" type="button" onclick="location='${pageContext.request.contextPath}/travelBoard/travelModifyForm.t?num=${listNum}&pageNum=${pageNum}'" value="수정"/>|
									<input class="travel-content-button" type="button" onclick="location='${pageContext.request.contextPath}/travelBoard/travelDeleteForm.t?num=${listNum}&pageNum=${pageNum}'" value="삭제"/>
								</div>
							</div>
							
							<div id="comment"  style="text-align:right;">
								<input type="hidden" id="listNum" value="${listNum }"/>
								<input type="button" class="comment-button" onClick="comment(1);" value="댓글 ▼"/>
							</div>
						</div>
					</div>
				</section>
			</div>
			<%@ include file="../../index/bottom.jsp"%>
			<%@ include file="../../index/pathBottom.jsp"%>
			<%@ include file="../../index/script.jsp"%>
</body>
</html>
