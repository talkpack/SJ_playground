<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../../index/pathTop.jsp"%>
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
						<li><i class="icon_document_alt"></i>게시판</li>
						<li><i class="fa fa-file-text-o"></i>다나와 게시판</li>
					</ol>
				</div>
			</div>


			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading"> 여행 다나와 목록 (전체 글: ${count})
						</header>

						<table class="table table-striped table-advance table-hover">
							<tbody>
								<tr>
									<th style="width: 10%; text-align: center;"><i
										class="icon_profile"></i> 번호</th>
									<th style="width: 15%; text-align: center;"><i
										class="icon_genius"></i> 섬네일</th>
									<th style="width: 25%; text-align: center;"><i
										class="icon_calendar"></i> 제목</th>
									<th style="width: 10%; text-align: center;"><i
										class="icon_mail_alt"></i>작성자</th>
									<th style="width: 10%; text-align: center;"><i
										class="icon_pin_alt"></i> 작성일</th>
									<th style="width: 10%; text-align: center;"><i
										class="icon_mobile"></i> 조회</th>
									<th style="width: 10%; text-align: center;"><i
										class="icon_piechart"></i> 추천 수</th>
									<th style="width: 10%; text-align: center;"><i
										class="icon_cogs"></i> 수정/삭제</th>
								</tr>
								<c:forEach var="article" items="${articleList}">
									<tr>
										<%-- 번호 --%>
										<td style="width: 10%; text-align: center;"><c:out
												value="${number}" /> <c:set var="number"
												value="${number - 1}" /></td>
										<%-- 대표 이미지 --%>
										<td style="height: 130px; width: 15%; text-align: center;"><img
											src="/traveler/traveler/upload/travel_title/${article.fileName}"
											style="height: 130px; width: 180px;"></img></td>
										<%-- 제목 --%>
										<td
											style="height: 130px; width: 25%; text-align: left; font-size: 20px"><a
											href="${pageContext.request.contextPath}/travelBoard/travelContent.t?num=${article.num}&pageNum=${currentPage}">
												${article.subject} </a></td>
										<%-- 작성자 --%>
										<td style="width: 10%; text-align: center;">
											${article.writer}</td>
										<%-- 작성일 --%>
										<td style="width: 10%; text-align: center;">
											${article.regDate}</td>
										<%-- 조회 --%>
										<td style="width: 10%; text-align: center;">
											${article.readCount}</td>
										<%-- 추천 수 --%>
										<td style="width: 10%; text-align: center;">
											${article.boardGood}<span
											style="color: #CE3636; font-size: 15px;">♥</span>
										</td>
										<%-- 관리 컴포넌트 --%>
										<td style="width: 10%; text-align: center;">
											<div class="btn-group">
												<c:if test="${text==null}"><%-- 전체목록 수정/삭제 --%>
													<a class="btn btn-primary"
													href="${pageContext.request.contextPath}/travelBoard/travelModifyForm.t?num=${article.num}&pageNum=${currentPage}"><i
													class="icon_plus_alt2"></i></a><a class="btn btn-danger"
													href="${pageContext.request.contextPath}/travelBoard/travelDeleteForm.t?num=${article.num}&pageNum=${currentPage}"><i
													class="icon_close_alt2"></i></a>
												</c:if>
												<c:if test="${text!=null}"><%-- 검색목록 수정/삭제 --%>
													<a class="btn btn-primary"
													href="${pageContext.request.contextPath}/travelBoard/travelModifyForm.t?num=${article.num}&pageNum=${currentPage}&textSearch=${text}&howSearch=${how}"><i
													class="icon_plus_alt2"></i></a><a class="btn btn-danger"
													href="${pageContext.request.contextPath}/travelBoard/travelDeleteForm.t?num=${article.num}&pageNum=${currentPage}&textSearch=${text}&howSearch=${how}"><i
													class="icon_close_alt2"></i></a>
												</c:if>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</section>
				</div>
			</div>
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
			<section class="panel" style="height: 140px">
				<div class="panel-body" style="background-color: #D5D5D5;">
					<%-- 검색 --%>
					<div class="form-group text-center" style="height: 40px">
						<form class="form-inline" role="form"
							action="${pageContext.request.contextPath}/travelBoard/travelList.t">
							<select name="howSearch" class="form-control m-bot15"
								style="width: 140px; height: 36px; margin-bottom: 0px">
								<option value="1" selected="selected">제목</option>
								<option value="2">작성자</option>
							</select> <input type="text" class="form-control" name="textSearch"
								placeholder="검색" style="width: 300px; height: 36px;">
							<button type="submit" class="btn btn-primary">검색</button>
						</form>
					</div>

					<%-- 페이지 번호[전체] --%>
					<c:if test="${text == null}">
						<div class="text-center" style="height: 60px">
							<ul class="pagination pagination-lg" style="margin-top: 0px">
								<c:if test="${startPage > pageBlock}">
									<li><a
										href="${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${startPage - pageBlock }">«</a></li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li><a
										href="${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${i}">${i}</a></li>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									<li><a
										href="${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${startPage + pageBlock }">»</a></li>
								</c:if>
							</ul>
						</div>
					</c:if>

					<%-- 페이지 번호[검색] --%>
					<c:if test="${text != null}">
						<div class="text-center" style="height: 60px">
							<ul class="pagination pagination-lg" style="margin-top: 0px">
								<c:if test="${startPage > pageBlock}">
									<li><a
										href="${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${startPage - pageBlock }&textSearch=${text}&howSearch=${how}">«</a></li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li><a
										href="${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${i}&textSearch=${text}&howSearch=${how}">${i}</a></li>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									<li><a
										href="${pageContext.request.contextPath}/travelBoard/travelList.t?pageNum=${startPage + pageBlock }&textSearch=${text}&howSearch=${how}">»</a></li>
								</c:if>
							</ul>
						</div>
					</c:if>
				</div>
			</section>
		</section>
	</section>





	<%@ include file="../../index/bottom.jsp"%>
	<%@ include file="../../index/pathBottom.jsp"%>
	<%@ include file="../../index/script.jsp"%>
</body>
</html>

