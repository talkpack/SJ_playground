<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../index/pathTop.jsp"%>

<c:if test="${!exception}">
	<form id="keywordForm" method="post" action="#">
		<input type="hidden" name="termFlag" value="1" /> <input
			type="hidden" name="moneyFlag" value="1" /> <input type="hidden"
			id="value1" value="${value1 }" /> <input type="hidden" id="value2"
			value="${value2 }" /> <input type="hidden" id="value3"
			value="${value3 }" /> <input type="hidden" id="value4"
			value="${value4 }" /> <input type="hidden" id="value5"
			value="${value5 }" />
		<div class="col-lg-6" style="width: 340px; margin-left: 6px;">
			<section class="panel"
				style="border: 1px solid #A6A6A6; background-color: #EAEAEA;">
				<header
					style="text-align: center; font-size: 35px; height: 25px; padding-top: 40px;">
					♣ CITY Chart
					<p style="font-size: 20px;">[ ${regionName } ]
					<p>
				</header>
				<div class=" text-center"
					style="background-color: #EAEAEA; padding-top: 50px;">
					<canvas id="doughnut" height="380" width="270"></canvas>
				</div>
				<div
					style="text-align: center; font-weight: bold; font-size: 18px; padding-bottom: 15px;">
					<font color="#F7464A">${city1 }</font> ${per1 }%
					<p>
					<p>
						<font color="#46BFBD">${city2 }</font> ${per2 }% <font
							color="#FDB45C">${city3 }</font> ${per3 }%
					<p>
						<font color="#6B9900">${city4 }</font> ${per4 }% <font
							color="#A566FF">${city5 }</font> ${per5 }%
				</div>

			</section>
		</div>
		<!-- 세중 처리 구간 -->
		<div class="row" style="float: left;">
			<section class="panel" style="width: 980px;">
				<input id="regionN" name="country" type="hidden"
					value="${regionName}" />
				<header id="region" class="panel-heading"> ${regionName} 를
					선택하셨습니다. </header>
				<table class="table table-condensed table-check">
					<thead>
						<tr>
							<th width="80">카테고리</th>
							<th>항목</th>
							<th style="text-align: right">단위 (日)</th>
						</tr>
					</thead>
					<tbody>
						<tr id="plusMinus">
							<td class="check-category"
								style="color: #747474; font-weight: bold;">도시별</td>
							<td class="check-content"><c:forEach begin="0" end="5"
									step="1" var="cities" items="${cityList}">
									<input type="radio" name="city" value="${cities}" />${cities}&nbsp;&nbsp;&nbsp;&nbsp;		
						</c:forEach></td>
							<td class="check-content-2"><a
								href="javascript:checkPlus();"> <span
									class="label label-warning">+</span>&nbsp;&nbsp;${citySize-6}개
							</a></td>
						</tr>
						<tr>
							<td class="check-category"
								style="color: #747474; font-weight: bold;">기간별</td>
							<td class="check-content"><input type="radio" name="term"
								value="1" onClick="termRadioF();" />무박&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="term" value="2"
								onClick="termRadioF();" />1박 2일&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="term" value="3" onClick="termRadioF();" />2박
								3일&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="term"
								value="30" onClick="termRadioF();" />한달&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="term" value="180"
								onClick="termRadioF();" />6개월&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="check-content-2">직접입력&nbsp;&nbsp; <input
								class="check-direct " name="termDirect" onClick="termDirectF();"
								type="text" placeholder="ex)29" />&nbsp;&nbsp;일
							</td>
						</tr>
						<tr>
							<td class="check-category"
								style="color: #747474; font-weight: bold;">비용별</td>
							<td class="check-content"><input type="radio" name="money"
								value="100000" onClick="moneyRadioF();" required />10만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="200000"
								onClick="moneyRadioF();" required />20만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="300000"
								onClick="moneyRadioF();" required />30만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="500000"
								onClick="moneyRadioF();" required />50만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="1000000"
								onClick="moneyRadioF();" required />100만원&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="check-content-2">직접입력&nbsp;&nbsp; <input
								class="check-direct " type="text" name="moneyDirect"
								onClick="moneyDirectF();" placeholder="ex)200000" />&nbsp;&nbsp;원
							</td>
						</tr>
						<tr>
							<td class="check-category"
								style="color: #747474; font-weight: bold;">루트별</td>
							<td class="check-content"><input type="radio" name="route"
								value="2" required />2곳(A → B)&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="route" value="3" required />3곳(A → B →
								C)&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="route"
								value="4" required />4곳(A → B → C → D)&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="route" value="5" required />5곳(A → B
								→ C → D → E)&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3" class="check-btn"><input type="button"
								onClick="checkList(this.form)" class="btn btn-primary btn-sm"
								value="검색" /> <input type="button"
								onClick="checkWrite(this.form);" class="btn btn-danger btn-sm"
								value="작성" /></td>
						</tr>
					</tbody>
				</table>
			</section>
		</div>
	</form>
</c:if>
<!-- 예외 발생 시 -->
<c:if test="${exception}">
	<div class="col-lg-6" style="width: 340px; margin-left: 6px;">
		<section class="panel"
			style="border: 1px solid #A6A6A6; background-color: #EAEAEA;">
			<header
				style="text-align: center; font-size: 15px; height: 25px; padding-top: 35px; background-color: #353535; color: ##FFFFFF;">
				♣ CITY Chart <br>(예외!)<br><br>
				해당 나라의 정보는<br>
				DB에 없습니다.<br><br>
				현재 구현된 나라 : USA, South Korea, China, Japan, France
				<div id="keywordTitle"></div>
			</header>
			<div class=" text-center" style="background-color: #EAEAEA;">
				<canvas id="doughnut" height="500" width="270"></canvas>
			</div>
			<div id="label"></div>
		</section>
	</div>
	<div class="row" style="float: left;">
		<div class="col-lg-9 col-md-12">
			<section class="panel" style="width: 980px;">
				<header class="panel-heading"
					style="background-color: #FF0000; color: #FFE400;"> 현재 구현된
					나라는 'USA', 'South Korea', 'China', 'Japan', 'France' 입니다.</header>
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
								style="background-color: #FF5E00; color: #FFFFFF">해당 나라의
								정보는 DB에 없습니다.</td>
							<td class="check-content-2"><a href="javascript:void(0);"><span
									class="label label-warning">+</span>&nbsp;&nbsp;0개</a></td>
						</tr>
						<tr>
							<td class="check-category"
								style="color: #747474; font-weight: bold;">기간별</td>
							<td class="check-content"><input type="radio" name="term"
								value="1" onClick="termRadioF();" />무박&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="term" value="2"
								onClick="termRadioF();" />1박 2일&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="term" value="3" onClick="termRadioF();" />2박
								3일&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="term"
								value="30" onClick="termRadioF();" />한달&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="term" value="180"
								onClick="termRadioF();" />6개월&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="check-content-2">직접입력&nbsp;&nbsp; <input
								class="check-direct " name="termDirect" onClick="termDirectF();"
								type="text" placeholder="ex)29" />&nbsp;&nbsp;일
							</td>
						</tr>
						<tr>
							<td class="check-category"
								style="color: #747474; font-weight: bold;">비용별</td>
							<td class="check-content"><input type="radio" name="money"
								value="100000" onClick="moneyRadioF();" required />10만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="200000"
								onClick="moneyRadioF();" required />20만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="300000"
								onClick="moneyRadioF();" required />30만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="500000"
								onClick="moneyRadioF();" required />50만원&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="money" value="1000000"
								onClick="moneyRadioF();" required />100만원&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="check-content-2">직접입력&nbsp;&nbsp; <input
								class="check-direct " type="text" name="moneyDirect"
								onClick="moneyDirectF();" placeholder="ex)200000" />&nbsp;&nbsp;원
							</td>
						</tr>
						<tr>
							<td class="check-category"
								style="color: #747474; font-weight: bold;">루트별</td>
							<td class="check-content"><input type="radio" name="route"
								value="2" required />2곳(A → B)&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="route" value="3" required />3곳(A → B →
								C)&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="route"
								value="4" required />4곳(A → B → C → D)&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="route" value="5" required />5곳(A → B
								→ C → D → E)&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3" class="check-btn"><input type="button"
								onClick="alert('먼저 구현된 나라를 선택해주세요.');"
								class="btn btn-primary btn-sm" value="검색" /> <input
								type="button" onClick="alert('먼저 구현된 나라를 선택해주세요.');"
								class="btn btn-danger btn-sm" value="작성" /></td>
						</tr>
					</tbody>
				</table>
			</section>
		</div>
	</div>
</c:if>


