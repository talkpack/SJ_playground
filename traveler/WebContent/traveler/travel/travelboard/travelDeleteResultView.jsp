<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${check}">
	<meta http-equiv="Refresh"
		content="0;url=${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${pageNum}">
</c:if>
<c:if test="${!check}">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TRAVELER</title>
<%@ include file="../../index/pathTop.jsp"%>
</head>
<body>
	<%@ include file="../../index/top.jsp"%>
	<%@ include file="../../index/side.jsp"%>
	<section id="main-content"> <section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa-laptop"></i>여행 다나와
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a
					href="<%=request.getContextPath()%>/main.do">Home</a></li>
				<li><i class="fa fa-laptop"></i>여행다나와</li>
			</ol>
		</div>
	</div>
	<div class="col-lg-12">
		<section class="panel"> <header class="panel-heading">
		글 삭제 </header>
		<div class="panel-body">
			<div class="form">
				<div class="form-group" style="margin: 3px;">
					<b>비밀번호가 다릅니다.</b> <br>
				</div>
				<div class="form-group" style="margin: 3px;">
					<div style="text-align: center;">
						<input class="btn btn-info btn-sm" type="button"
							onclick="javascript:history.go(-1)" value="이전으로">
					</div>
				</div>
			</div>
		</div>
		</section>
	</div>
</c:if>
<%@ include file="../../index/bottom.jsp"%>
<%@ include file="../../index/pathBottom.jsp"%>
<%@ include file="../../index/script.jsp"%>
</body>
</html>
