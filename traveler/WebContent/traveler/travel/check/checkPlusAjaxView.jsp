<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.travel.travelboard.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../index/pathTop.jsp"%>
<%-- <% 
	String regionName = (String)request.getAttribute("regionName");
	System.out.println("뷰에서의 네임 : "+regionName);
	request.setAttribute("regionName", regionName); 
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input id="regionN" type="hidden" value="${regionName}" />

	<td class="check-category">도시별</td>

	<td class="check-content">
		<c:forEach begin="0" end="5" step="1" var="cities" items="${cityList}">
			<input type="radio" name="city" value="${cities}" />${cities}&nbsp;&nbsp;&nbsp;&nbsp;		
		</c:forEach> 
		<br> 
		<c:forEach begin="6" end="${citySize-1}" step="1" var="cities" items="${cityList}">
			<input type="radio" name="city" value="${cities}" />${cities}&nbsp;&nbsp;&nbsp;&nbsp;		
		</c:forEach>
	</td>

	<td class="check-content-2"><a href="javascript:checkMinus();"><span
			class="label label-warning">-</span>&nbsp;&nbsp;닫기</a></td>
</body>
</html>