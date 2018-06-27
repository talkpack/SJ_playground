<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>TRAVELER</title>
<%@include file="pathTop.jsp"%>

</head>
<body>
	<%@ include file="top.jsp"%>
	<%@ include file="side.jsp"%>
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


			<div class="row" style="float: left;">
				<div class="col-lg-9 col-md-12">

					<div class="panel panel-default"
						style="height: 560px; width: 980px;">
						<div class="panel-heading">
							<h2>
								<i class="fa fa-map-marker red"></i><strong>국가별</strong>
							</h2>
							<h2>
								<strong> (STEP1)</strong>
							</h2>
							<div class="panel-actions">
								<a href="index.html#" class="btn-setting"><i
									class="fa fa-rotate-right"></i></a> <a href="index.html#"
									class="btn-minimize"><i class="fa fa-chevron-up"></i></a> <a
									href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
							</div>
						</div>

						<div class="panel-body-map">
							<div id="map"
								style="padding-left: 0px; height: 560px; width: 980px;"></div>
						</div>

					</div>
				</div>
			</div>
			<div id="checkAjax">
				<div class="col-lg-6" style="width: 340px; margin-left: 6px;">
					<section class="panel"
						style="border: 1px solid #A6A6A6; background-color: #EAEAEA;">
						<header
							style="text-align: center; font-size: 35px; height: 25px; padding-top: 35px;">
							♣ CITY Chart
							<p>
							<div id="keywordTitle"></div>
						</header>
						<div class=" text-center" style="background-color: #EAEAEA;">
							<canvas id="doughnut" height="500" width="270"></canvas>
						</div>
						<div id="label"></div>
					</section>
				</div>
				<!-- 세중 처리 구간 -->
				<div class="row" style="float: left;">
					<div class="col-lg-9 col-md-12">
						<section class="panel" style="width: 980px;">
							<header class="panel-heading"> 국가를 먼저 선택해주세요 (STEP2)</header>
							<table class="table table-condensed table-check">
								<thead>
									<tr>
										<th width="80">카테고리</th>
										<th>항목</th>
										<th style="text-align: right">단위 (日)</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td class="check-category"
											style="color: #747474; font-weight: bold;">도시별</td>
										<td class="check-content"
											style="background-color: #FF5E00; color: #FFFFFF">나라를
											선택하세요.</td>
										<td class="check-content-2"><a href="javascript:void(0);"><span
												class="label label-warning">+</span>&nbsp;&nbsp;0개</a></td>
									</tr>
									<tr>
										<td class="check-category"
											style="color: #747474; font-weight: bold;">기간별</td>
										<td class="check-content"><input type="radio" name="term"
											value="1" disabled />무박&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" name="term" value="2" disabled />1박
											2일&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="term"
											value="3" disabled />2박 3일&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" name="term" value="30" disabled />한달&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="term" value="180" disabled />6개월&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td class="check-content-2">직접입력&nbsp;&nbsp; <input
											class="check-direct " type="text" placeholder="ex)29"
											disabled />&nbsp;&nbsp;일
										</td>
									</tr>
									<tr>
										<td class="check-category"
											style="color: #747474; font-weight: bold;">비용별</td>
										<td class="check-content"><input type="radio"
											name="money" value="10" disabled />10만원&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="money" value="20" disabled />20만원&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="money" value="30" disabled />30만원&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="money" value="50" disabled />50만원&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="money" value="100" disabled />100만원&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td class="check-content-2">직접입력&nbsp;&nbsp; <input
											class="check-direct " type="text" placeholder="ex)200000"
											disabled />&nbsp;&nbsp;원
										</td>
									</tr>
									<tr>
										<td class="check-category"
											style="color: #747474; font-weight: bold;">루트별</td>
										<td class="check-content"><input type="radio"
											name="route" value="2" disabled />2곳(A →
											B)&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="route"
											value="3" disabled />3곳(A → B → C)&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" name="route" value="4" disabled />4곳(A → B → C
											→ D)&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="route"
											value="5" disabled />5곳(A → B → C → D →
											E)&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td></td>
									</tr>
									<tr>
										<td colspan="3" class="check-btn"><input type="submit"
											class="btn btn-primary btn-sm" value="검색" /> <input
											type="submit" class="btn btn-danger btn-sm" value="작성" /></td>
									</tr>
								</tbody>
							</table>
						</section>
					</div>
				</div>
			</div>
			<div class="col-sm-3"
				style="float: left; margin-right: 50px; width: 500px;">
				<section class="panel">
					<header class="statistic-headline-2">
						☆ Content <font color="yellow">Ranking</font>
					</header>
					<table class="table table-striped table-statistic">
						<thead>
							<tr>
								<th>순위</th>
								<th>게시글 제목</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="0" end="4" step="1" var="content"
								varStatus="status" items="${contentRank}">
								<tr>
									<td>${status.count }</td>
									<td>${content }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</section>
				<section class="panel">
					<header class="statistic-headline-1">
						☆ Writer <font color="yellow">Ranking</font>
					</header>
					<table class="table table-striped table-statistic">
						<thead>
							<tr>
								<th>순위</th>
								<th>작성자 ID</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="0" end="4" step="1" var="writer"
								varStatus="status" items="${writerRank}">
								<tr>
									<td>${status.count }</td>
									<td>${writer }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</section>
			</div>

			<!-- 캘린더 -->
			<div class="col-md-6 portlets">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2>
							<strong>성수기/비수기</strong>
						</h2>
						<div class="panel-actions">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>

					</div>
					<br> <br> <br>
					<div class="panel-body">
						<div id="calendar"></div>
					</div>
				</div>
			</div>

			<!-- 세중 처리 구간 끝 -->
		</section>
	</section>
	<%@ include file="bottom.jsp"%>
	<%@ include file="pathBottom.jsp"%>
	<%@ include file="script.jsp"%>
</body>
</html>
