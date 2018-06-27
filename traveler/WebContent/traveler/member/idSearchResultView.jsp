<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${id!=null}">
		<script>
			alert("${name}님의 아이디는 ${id}입니다.")
			location.href="${pageContext.request.contextPath}/member/loginForm.m"
		</script>
</c:if>
<c:if test="${id==null}">
		<script>
			alert("${name}님의 아이디는 존재하지 않습니다.")
			location.href="${pageContext.request.contextPath}/member/idSearchForm.m"
		</script>
</c:if>