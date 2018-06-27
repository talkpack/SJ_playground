<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

			

			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading tab-bg-info">
							<ul class="nav nav-tabs">
								<li class=""><a href="<%=request.getContextPath()%>/member/myPage.m">
										<i class="icon-user"></i> 내 정보
								</a></li>
								<li><a data-toggle="tab" href="#list"> 
										<i class="icon-home"></i> 내 활동내역
								</a></li>
								<li class=""><a href="<%=request.getContextPath()%>/member/modifyForm.m">
										<i class="icon-envelope"></i> 정보 수정
								</a></li>
								<li class="active"><a href="<%=request.getContextPath()%>/member/deleteForm.m">
										<i class="icon-envelope"></i> 정보 탈퇴
								</a></li>
							</ul>
						</header>
						<div class="panel-body">
							<div class="tab-content">

								<p>
								<p>
								<div id="recent-activity" class="tab-pane active">
									<section class="panel">
										<div class="panel-body bio-graph-info">
											<h1>Delete Info</h1>
											<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/member/deleteResult.m">
												<div class="form-group">
													<label class="col-lg-2 control-label">password</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" id="pwd" name="pwd"
															placeholder=" "/><p><p>
														<button class="btn btn-primary" type="submit" >삭제</button>
													</div>
												</div>
											</form>
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
