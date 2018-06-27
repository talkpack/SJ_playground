<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result==true}">
		<script>
		alert("회원가입을 축하드립니다.")
		location.href='${pageContext.request.contextPath}/member/loginForm.m'
		</script>
		
</c:if>
<c:if test="${result==false}">
		<script>
		alert("다시 입력해 주십시오")
		location.href='${pageContext.request.contextPath}/member/joinForm.m'
		</script>
</c:if>