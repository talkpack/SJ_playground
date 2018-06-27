<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check == 1 }">
	<script> 
		self.window.alert("삭제 하였습니다."); 
		location.href="${pageContext.request.contextPath}/freeBoard/freeList.b?pageNum=${pageNum }"; 
	</script> 
</c:if>
<c:if test="${check == 0 }">
	<script> 
		self.window.alert("비밀번호가 다릅니다."); 
		location.href="javascript:history.back()"; 
	</script> 
</c:if>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
</body>
</html>