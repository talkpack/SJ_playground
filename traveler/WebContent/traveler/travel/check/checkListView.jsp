<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<li><i class="fa fa-home"></i><a href="main.jsp">Home</a></li>
						<li><i class="fa fa-laptop"></i>여행다나와</li>
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
						<div class="count">${todayCount }</div>
						<div class="title">오늘 방문자 수</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<div class="info-box dark-bg">
						<i class="fa fa-thumbs-o-up"></i>
						<div class="count">${boardCount }</div>
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


			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading" style="height:50px;padding-top:8px; font-weight:bold; background-color:#D5D5D5;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;분류 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button name="country" class="btn btn-danger disabled check-category" style="background-color:#5CD1E5; border:1px solid #D5D5D5; "><span class="icon_pin_alt"></span>&nbsp;&nbsp;${country }</button>
						<button name="city" class="btn btn-danger disabled check-category"style="background-color:#BCE55C; border:1px solid #D5D5D5; "><span class="icon_pin_alt"></span>&nbsp;&nbsp;${city }</button>
						<button name="termSet" class="btn btn-danger disabled check-category"style="background-color:#D1B2FF; border:1px solid #D5D5D5; "><span class="icon_calendar"></span>&nbsp;&nbsp;${termSet }</button>
						<button name="moneySet" class="btn btn-danger disabled check-category"style="background-color:#FFA7A7; border:1px solid #D5D5D5; "><span class="icon_lightbulb_alt"></span>&nbsp;&nbsp;${moneySet }&nbsp;</button>
						<button name="routeSet" class="btn btn-danger disabled check-category"style="background-color:#F2CB61; border:1px solid #D5D5D5; "><span class="icon_house_alt"></span>&nbsp;&nbsp;${routeSet }&nbsp;</button>									
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
												<a class="btn btn-primary"
													href="${pageContext.request.contextPath}/travelBoard/travelModifyForm.t?num=${article.num}&pageNum=${currentPage}"><i
													class="icon_plus_alt2"></i></a><a class="btn btn-danger"
													href="${pageContext.request.contextPath}/travelBoard/travelDeleteForm.t?num=${article.num}&pageNum=${currentPage}"><i
													class="icon_close_alt2"></i></a>
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
			<c:set var="pageBlock" value="${3}" />
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
										
					<%-- 페이지 번호[전체] --%>
					<c:if test="${text == null}">
						<div class="text-center" style="height: 60px">
						<ul class="pagination pagination-lg" style="margin-top: 0px">
							<c:if test="${startPage > pageBlock}">
								<li><a
									href="${pageContext.request.contextPath}/check/checkList.t?pageNum=${startPage - pageBlock }&country=${country}&city=${city}&term=${term}&money=${money}&route=${route}">«</a></li>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<li><a
									href="${pageContext.request.contextPath}/check/checkList.t?pageNum=${i}&country=${country}&city=${city}&term=${term}&money=${money}&route=${route}">${i}</a></li>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<li><a
									href="${pageContext.request.contextPath}/check/checkList.t?pageNum=${startPage + pageBlock }&country=${country}&city=${city}&term=${term}&money=${money}&route=${route}">»</a></li>
							</c:if>
						</ul>
					</div>
					</c:if>
				</div>
			</section>
			<%@ include file="../../index/bottom.jsp"%>
			<%@ include file="../../index/pathBottom.jsp"%>
			<%@ include file="../../index/script.jsp"%>
</body>
</html>
