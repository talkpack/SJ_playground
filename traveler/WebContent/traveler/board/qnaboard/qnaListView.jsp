<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<title>TRAVELER</title>
<style>
h1 {
	font-size: 20px;
}

.jb-center {
	text-align: center;
}
</style>
<%@include file="../../index/pathTop.jsp"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<%
	String cat = request.getParameter("category");
%>
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
						<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
						<li><i class="icon_document_alt"></i>Q&A</li>
					</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<section>
						<header class="panel-heading">여행 다나와 목록 &nbsp;</header>
						<c:if test="${count == 0 }">
							<table class="table table-striped table-advance table-hover"
								style="text-align: center; border: 1px solid #dddddd">
								<tbody>
									<tr>
										<th style="text-align: center" width="220"><select
											name="cat" id="cat" class="form-control" style="height: 35px"
											onchange="category()">
												<%
													if (cat != null && !cat.equals("카테고리(전체)")) {
												%>
												<option value="${category}" selected>${category}</option>
												<option value="${category}">======SELECT======</option>
												<option value="카테고리(전체)">카테고리(전체)</option>
												<%
													} else {
												%>
												<option value="" selected>카테고리(전체)</option>
												<%
													}
												%>
												<option value="일반 문의">일반 문의</option>
												<option value="게시물 관련 문의">게시물 관련 문의</option>
												<option value="회원 관련 문의">회원 관련 문의</option>
												<option value="버그 문의">버그 문의</option>
												<option value="기타 문의">기타 문의</option>
										</select></th>
										<th style="text-align: center" width="150"><i
											class="icon_cogs"></i>번호</th>
										<th style="text-align: center" width="500"><i
											class="icon_mail_alt"></i> 제목</th>
										<th style="text-align: center"><i class="icon_profile"></i>
											작성자</th>
										<th style="text-align: center"><i class="icon_calendar"></i>
											작성일</th>
										<th style="text-align: center"><i class="icon_profile"></i>
											조회수</th>
									</tr>
								<tr>
									<td colspan="6">저장된 글이 없습니다.</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${count > 0}">
							<table class="table table-striped table-advance table-hover"
								style="text-align: center; border: 1px solid #dddddd">
								<tbody>
									<tr>
										<th style="text-align: center" width="220"><select
											name="cat" id="cat" class="form-control" style="height: 35px"
											onchange="category()">
												<%
													if (cat != null && !cat.equals("카테고리(전체)")) {
												%>
												<option value="${category}" selected>${category}</option>
												<option value="${category}">======SELECT======</option>
												<option value="카테고리(전체)">카테고리(전체)</option>
												<%
													} else {
												%>
												<option value="" selected>카테고리(전체)</option>
												<%
													}
												%>
												<option value="일반 문의">일반 문의</option>
												<option value="게시물 관련 문의">게시물 관련 문의</option>
												<option value="회원 관련 문의">회원 관련 문의</option>
												<option value="버그 문의">버그 문의</option>
												<option value="기타 문의">기타 문의</option>
										</select></th>
										<th style="text-align: center" width="150"><i
											class="icon_cogs"></i>번호</th>
										<th style="text-align: center" width="500"><i
											class="icon_mail_alt"></i> 제목</th>
										<th style="text-align: center"><i class="icon_profile"></i>
											작성자</th>
										<th style="text-align: center"><i class="icon_calendar"></i>
											작성일</th>
										<th style="text-align: center"><i class="icon_profile"></i>
											조회수</th>
									</tr>
									<c:forEach var="article" items="${articleList}">
										<tr>
											<td>${article.category}</td>
											<td align="center" width="50"><c:out value="${number}" />
												<c:set var="number" value="${number - 1}" /></td>
											<td class="titletd"><c:if test="${article.depth > 0}">
													<img
														src="${pageContext.request.contextPath}/traveler/design/img/level.gif"
														width="${5 * article.depth}">
													<img
														src="${pageContext.request.contextPath}/traveler/design/img/re.gif">
												</c:if> <c:if test="${article.depth == 0}">
													<img
														src="${pageContext.request.contextPath}/traveler/design/img/level.gif"
														width="${5 * article.depth}">
												</c:if> <a
												href="${pageContext.request.contextPath}/qnaBoard/qnaContent.b?num=${article.num}&pageNum=${currentPage}">
													<c:if test="${article.readcount >= 50}">
														<img
															src="${pageContext.request.contextPath}/traveler/design/img/hot.gif">
													</c:if>${article.subject}</a></td>
											<td>${article.writer}</td>
											<td>${article.regdate}</td>
											<td>${article.readcount}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>

						<%-- 페이지 세팅 구간 시작 --%>
						<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }" />
						<c:set var="pageCount" value="${count / pageSize + imsi }" />
						<c:set var="pageBlock" value="${5}" />
						<fmt:parseNumber var="result" value="${currentPage / pageBlock}"
							integerOnly="true" />
						<c:set var="startPage" value="${result * pageBlock + 1 }" />
						<c:set var="endPage" value="${startPage + pageBlock - 1 }" />
						<c:if test="${endPage > pageCount}">
							<c:set var="endPage" value="${pageCount}" />
						</c:if>
						<%-- 페이지 세팅 구간 끝 --%>


						<div class="jb-center">
							<ul class="pagination">
								<div class="text-center" style="height: 60px">
									<ul class="pagination pagination-lg" style="margin-top: 0px">
										<%-- 페이지 번호[전체] --%>
										<c:if test="${category == null}">
											<c:if test="${startPage > pageBlock}">
												<li><a
													href="${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${startPage - pageBlock }">«</a></li>
											</c:if>
											<c:forEach var="i" begin="${startPage}" end="${endPage}">
												<li><a
													href="${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${i}">${i}</a></li>
											</c:forEach>
											<c:if test="${endPage < pageCount}">
												<li><a
													href="${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${startPage + pageBlock }">»</a></li>
											</c:if>
										</c:if>
										<%-- 페이지 번호[카테고리 선택] --%>
										<c:if test="${category!=null}">
											<c:if test="${startPage > pageBlock}">
												<li><a
													href="${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${startPage - pageBlock }&category=${category}">«</a></li>
											</c:if>
											<c:forEach var="i" begin="${startPage}" end="${endPage}">
												<li><a
													href="${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${i}&category=${category}">${i}</a></li>
											</c:forEach>
											<c:if test="${endPage < pageCount}">
												<li><a
													href="${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${startPage + pageBlock }&category=${category}">»</a></li>
											</c:if>
										</c:if>
									</ul>
								</div>
							</ul>
							<a href="<%=request.getContextPath()%>/qnaBoard/qnaWriteForm.b?pageNum=${currentPage}"
								class="btn btn-primary pull-right">글쓰기</a>
						</div>
					</section>
				</div>
			</div>
		</section>
	</section>
	<script>
		function category() {
			var name = document.getElementById("cat");
			var data = name.options[name.selectedIndex].value;
			console.log(data);
			document.location.href = "${pageContext.request.contextPath}/qnaBoard/qnaList.b?category="
					+ data;
		}
	</script>
	<%@ include file="../../index/bootstrap.jsp"%>
	<%@ include file="../../index/bottom.jsp"%>
	<%@ include file="../../index/pathBottom.jsp"%>
	<%@ include file="../../index/script.jsp"%>
</body>
</html>
