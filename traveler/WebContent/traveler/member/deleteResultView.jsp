<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${res==true}">
		<script>
			alert("탈퇴성공")
			location.href="${pageContext.request.contextPath}/main.do"
		</script>
</c:if>
<c:if test="${res==false}">
		<script>
			alert("탈퇴실패")
			location.href="${pageContext.request.contextPath}/member/deleteForm.m"
		</script>
</c:if>
