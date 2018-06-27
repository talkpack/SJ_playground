<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${result==1 }">
		<script>
			alert("${id }님 로그인 성공!")
			location.href="${pageContext.request.contextPath }/main.do"
		</script>
	</c:if>
	<c:if test="${result==0 }">
		<script>
			alert("비밀번호 불일치")
			location.href="${pageContext.request.contextPath }/member/loginForm.m"
		</script>
	</c:if>
	<c:if test="${result==-1 }">
		<script>
			alert("아이디가 존재하지 않습니다.")
			location.href="${pageContext.request.contextPath }/member/loginForm.m"
		</script>
	</c:if>
