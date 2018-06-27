<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${pwd!=null }">
		<script>
			alert("${id}님의 비밀번호는 ${pwd}입니다.")
			location.href="${pageContext.request.contextPath}/member/loginForm.m"
		</script>
</c:if>
<c:if test="${pwd==null }">
		<script>
			alert("${id}님의 비밀번호는 존재하지 않습니다.")
			location.href="${pageContext.request.contextPath}/member/pwSearchForm.m"
		</script>
</c:if>
