<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">
<script>
function deleteCheck(){ 
	if (confirm("삭제 하시겠습니까?")){ 
		if(document.qnadeleteForm.writepwd.value == ""){
			alert("비밀번호를 입력하세요.");
			document.qnadeleteForm.writepwd.focus();
			return false;
		}
		location.href="${pageContext.request.contextPath }/qnaBoard/qnaDeleteResult.b?pageNum=${pageNum}";
	}else{ 
		alert("취소 되었습니다."); 
		return false; 
	} 
} 
</script>
<%@ include file="../../index/pathTop.jsp"%>
</head>
<body>
	<%@ include file="../../index/top.jsp"%>
	<%@ include file="../../index/side.jsp"%>
	<section id="main-content">
		<section class="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">
						<i class="fa fa-laptop"></i>삭제
					</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
						<li><i class="icon_document_alt"></i>Q&A</li>
					</ol>
				</div>
			</div>
			<form name="qnadeleteForm" action="${pageContext.request.contextPath }/qnaBoard/qnaDeleteResult.b?pageNum=${pageNum}" onsubmit="return deleteCheck()" method="post">
				<div class="table table-responsive">
					<table class="table">
						<tr>
							<th class="info" style="text-align:center">글번호</th>
							<th>${article.num }</th>
							<th class="info" style="text-align:center">조회수</th>
							<th>${article.readcount }</th>
						</tr>
						<tr>
							<th class="info" style="text-align:center">작성자</th>
							<th>${article.writer }</th>
							<th class="info" style="text-align:center">작성일</th>
							<th>${article.regdate }</th>
						</tr>
						<tr>
							<th class="info" style="text-align:center">제목</th>
							<th colspan="3">${article.subject }</th>
						</tr>
						<tr>
							<th class="info" style="text-align:center">비밀번호</th>
							<th colspan="3"><input class="form-control" id="writepwd"
								name="writepwd" type="password"></th>
						</tr>
						<tr>
							<td colspan="4" class="text-center"><input type="hidden"
								value="${article.num }" name="num"> <input type="submit"
								value="삭제" class="btn btn-danger"> <input type="button"
								class="btn btn-primary" value="목록"
								onclick="location.href='${pageContext.request.contextPath}/qnaBoard/qnalist.b?pageNum=${pageNum }'">
							<a href="javascript:history.go(-1)"><input type="button" class="btn btn-info" value="취소"></a></td>
						</tr>
					</table>
				</div>
			</form>
		</section>
	</section>
	<%@ include file="../../index/bootstrap.jsp"%>
	<%@ include file="../../index/bottom.jsp"%>
	<%@ include file="../../index/pathBottom.jsp"%>
	<%@ include file="../../index/script.jsp"%>
</body>
</html>
