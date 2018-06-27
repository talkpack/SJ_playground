<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@ include file="../index/pathTop.jsp"%>
</head>
<body>
	<%@ include file="../index/top.jsp"%>
	<%@ include file="../index/side.jsp"%>
	<section id="main-content">
		<section class="wrapper">
		
			<!-- 상단 -->
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">
						<i class="fa fa-laptop"></i>마이페이지
					</h3>
				</div>
			</div>
			
			
			
			<div class="row">
				<div class="col-lg-12">
					<div class="profile-widget profile-widget-info">
						<div class="panel-body">
						
							<div class="col-lg-2 col-sm-2">
								<h4>${MemberDto.getId()}</h4>
								<div class="follow-ava">
									<c:if test="${MemberDto.getGender()=='man'}">
										<img src="<%=request.getContextPath() %>/traveler/design/img/man.jpg" alt="">
									</c:if>
									<c:if test="${MemberDto.getGender()=='woman'}">
										<img src="<%=request.getContextPath() %>/traveler/design/img/woman.jpg" alt="">
									</c:if>
								</div>
								<h6>${MemberDto.getName()}</h6>
							</div>
							<div class="col-lg-4 col-sm-4 follow-info">
								<p>안녕하세요. 저는 ${MemberDto.getId()}입니다.</p>
								<h6>
									<span><i class="icon_clock_alt"></i>${time }</span> <span>
									<i class="icon_calendar"></i>${day }</span> <span>
									<i class="icon_pin_alt"></i>부산</span>
								</h6>
							</div>
							
						</div>
					</div>
				</div>
			</div>


			<!-- 탭 -->
			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading tab-bg-info">
							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab"  href="#recent-activity"><i class="icon-user"></i> 내 정보</a>
								</li>
								<li><a data-toggle="tab" href="#list"><i class="icon-home"></i> 내 활동내역</a>
								</li>
								<li class=""><a href="${pageContext.request.contextPath }/member/modifyForm.m">
										<i class="icon-envelope"></i> 정보 수정</a>
								</li>
								<li class=""><a href="${pageContext.request.contextPath }/member/deleteForm.m">
										<i class="icon-envelope"></i> 정보 탈퇴</a>
								</li>
							</ul>
						</header>
						
						
						<!-- 내정보 -->
						<div class="panel-body">
							<div class="tab-content">
								<p>
								<p>
								<div id="recent-activity" class="tab-pane active">
									<section class="panel">
										<div class="bio-graph-heading">Hello I’m ${MemberDto.getName()},
											a leading expert in interactive and creative design
											specializing in the mobile medium. My graduation from Massey
											University with a Bachelor of Design majoring in visual
											communication.</div>
										<div class="panel-body bio-graph-info">
											<h1>Information</h1>
											<div class="row">
											
											
												<div class="bio-row">
													<p>
														<span>아이디 </span>: ${MemberDto.getId()}
													</p>
												</div>
												<div class="bio-row">
													<p>
														<span>이름 </span>: ${MemberDto.getName()}
													</p>
												</div>
												<div class="bio-row">
													<p>
														<span>생년월일</span>: ${MemberDto.getYear()}년 ${MemberDto.getMonth()}월 ${MemberDto.getDay()}일
													</p>
												</div>
												<div class="bio-row">
													<p>
														<span>가입날짜 </span>: ${regDate}
													</p>
												</div>
												<div class="bio-row">
													<p>
														<span>이메일</span>: ${MemberDto.getEmail1()}@${MemberDto.getEmail2()}
													</p>
												</div>
												<div class="bio-row">
													<p>
														<span>휴대폰 번호</span>: ${MemberDto.getPh1()} - ${MemberDto.getPh2()} - ${MemberDto.getPh3()}
													</p>
												</div>
												
												
											</div>
										</div>
									</section>
								</div>
												
						<!-- 목록 -->
							 	<div id="list" class="tab-pane">
                  				 	<div class="col-lg-12">
										<section class="panel">
											<table class="table table-striped table-advance table-hover">
												<tbody>
													<tr>
														<th style="width: 10%; text-align: center;"><i
															class="icon_profile"></i> 번호</th>
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
														
													</tr>
												 <c:forEach var="article" items="${articleList}">
														<tr>
															<td style="width: 10%; text-align: center;">
															<c:out value="${count}" /> 
															<c:set var="count" value="${count - 1}" /></td>
															<td
																style="width: 25%; text-align: left; font-size: 20px">																	${article.subject} </td>
															<td style="width: 10%; text-align: center;">
																${article.writer}</td>
															<td style="width: 10%; text-align: center;">
																${article.regDate}</td>
															<td style="width: 10%; text-align: center;">
																${article.readCount}</td>
															<td style="width: 10%; text-align: center;">
																${article.boardGood}<span
																style="color: #CE3636; font-size: 15px;">♥</span>
															</td>
															
														</tr>
													</c:forEach> 
												</tbody>
											</table>
										</section>
									</div>
                 				 </div>		
                 				 
                 				 
							</div>
						</div>
					</section>
				</div>
			</div>
					
							
			<%@ include file="../index/bottom.jsp"%>
			<%@ include file="../index/pathBottom.jsp"%>
			<%@ include file="../index/script.jsp"%>
</body>
</html>
