<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
alert("작성 성공!\n 메인 화면으로 이동");
location.href="<%=request.getContextPath()%>/main.do";
</script>