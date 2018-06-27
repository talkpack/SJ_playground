<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../../index/pathTop.jsp"%>
<c:if test="${dbPwd == ''}">
	<script>
		alert("해당 권한이 없습니다.\n 작성자로 로그인해주세요~")
		location.href="<%=request.getContextPath()%>
		/member/loginForm.m";
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
						<li><i class="fa fa-laptop"></i>여행다나와</li>
					</ol>
				</div>
			</div>
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 글 삭제 </header>
					<form
						action="${pageContext.request.contextPath}/travelBoard/travelDeleteResult.t"
						class="form-horizontal">
						<input type="hidden" name="num" value="${num}" /> <input
							type="hidden" name="pageNum" value="${pageNum}" /> <input
							type="hidden" name="dbPwd" value="${dbPwd}" />
						<div class="panel-body">
							<div class="form">

								<div class="form-group" style="margin: 3px;">
									<label class="control-label col-sm-2">비밀번호</label>
									<div class="col-sm-10">
										<input type="password" name="writePwd" class="check-write "
											placeholder="Password">
									</div>
								</div>
								<div class="form-group" style="margin: 3px;">
									<div style="text-align: center;">
										<input class="btn btn-primary btn-sm" type="submit" value="삭제">
										<c:if test="${text==null}">
											<input class="btn btn-info btn-sm" type="button"
												onclick="location='${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${pageNum}'"
												value="취소">
										</c:if>
										<c:if test="${text!=null}">
											<input class="btn btn-info btn-sm" type="button"
												onclick="location='${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${pageNum}&textSearch=${text}&howSearch=${how}'"
												value="취소">
										</c:if>

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
