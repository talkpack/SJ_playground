<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="container" class="">
	<header class="header dark-bg">
		<div class="toggle-nav">
			<div class="icon-reorder tooltips"
				data-original-title="Toggle Navigation" data-placement="bottom">
				<i class="icon_menu"></i>
			</div>
		</div>
		<a href="<%=request.getContextPath()%>/main.do" class="logo">TRAVELER</a>
		<div class="nav search-row" id="top_menu">
			<ul class="nav top-menu">
				<li>
					<form class="navbar-form"
						action="${pageContext.request.contextPath}/travelBoard/travelList.t">
						<input class="form-control" placeholder="Search" type="text"
							name="textSearch"> <input type="hidden" name="howSearch"
							value="1">
					</form>
				</li>
			</ul>
		</div>

		<div class="top-nav notification-row">
			<ul class="nav pull-right top-menu">

				<c:if test="${loginId !=null}">
					<li id="task_notificatoin_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
							class="icon-task-l"></i> <span class="badge bg-important">${articleCount}</span>
					</a>
						<ul class="dropdown-menu extended logout">
							<li class="eborder-top"><a
								href="${pageContext.request.contextPath }/member/myPage.m">
									<i class="icon_profile"></i> 작성한 '게시물' 확인
							</a></li>
						</ul>
					<li id="alert_notificatoin_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle"
						href="${pageContext.request.contextPath }/member/myPage.m"> <i
							class="icon-bell-l"></i> <span class="badge bg-important">${goodCount}</span>
					</a>
						<ul class="dropdown-menu extended logout">
							<li class="eborder-top"><a
								href="${pageContext.request.contextPath }/member/myPage.m">
									<i class="icon_profile"></i> 새로운 '좋아요' 확인
							</a></li>
						</ul>
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle"
						href="${pageContext.request.contextPath }/member/myPage.m"> <span
							class="profile-ava"> <img alt=""
								src="img/avatar1_small.jpg">
						</span> <span class="username">${loginId } 님</span><b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<li class="eborder-top"><a
								href="${pageContext.request.contextPath }/member/myPage.m">
									<i class="icon_profile"></i> 마이페이지
							</a></li>

							<li><a
								href="${pageContext.request.contextPath }/member/logOut.m">
									<i class="icon_key_alt"></i> 로그아웃
							</a></li>
						</ul></li>

				</c:if>

				<c:if test="${loginId == null}">
					<li class="dropdown"><a class="dropdown-toggle"
						href="${pageContext.request.contextPath }/member/loginForm.m">
							<span class="username">LOGIN</span>
					</a></li>
				</c:if>
			</ul>
		</div>
	</header>
</section>