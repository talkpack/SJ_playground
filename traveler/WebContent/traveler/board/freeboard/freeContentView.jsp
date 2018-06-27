<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./Resources/css/bootstrap.min.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="./Resources/css/bootstrap.min.js"></script>
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
						<i class="fa fa-laptop"></i>상세내용
					</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
						<li><i class="icon_document_alt"></i>게시판</li>
						<li><i class="fa fa-file-text-o"></i>자유 게시판</li>

					</ol>
				</div>
			</div>
			<form enctype="multipart/form-data" class="contentForm" >
				<table class="table">
					<tr>
						<th class="success" style="text-align:center">글번호</th>
						<th>${article.num }</th>
						<th class="success" style="text-align:center">조회수</th>
						<th>${article.readcount }</th>
					</tr>
					<tr>
						<th class="success" style="text-align:center">작성자</th>
						<th>${article.writer }</th>
						<th class="success" style="text-align:center">작성일</th>
						<th>${article.regdate }</th>
					</tr>

					<tr>
						<th class="success" style="text-align:center">제목</th>
						<th colspan="3">${article.subject }</th>
					</tr>

					<tr>
						<th class="success" style="text-align:center">내용</th>
						<th colspan="3">${article.content }</th>
					</tr>
					<c:if test="${article.depth == 0 }">
						<tr>
							<th class="success" style="text-align:center">첨부파일</th>
							<th colspan="3"><a
								href="${pageContext.request.contextPath }/freeBoard/freeDownload.b?filename=${article.filename }">${article.filename }</a></th>
						</tr>
					</c:if>
					<tr>
						<td colspan="4" class="text-center"><input type="button"
							class="btn btn-success" value="답글쓰기"
							onClick="location.href='${pageContext.request.contextPath}/freeBoard/freeWriteForm.b?num=${article.num }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">
							<input type="button" class="btn btn-warning" value="수정"
							onclick="document.location.href='${pageContext.request.contextPath}/freeBoard/freeModifyForm.b?num=${article.num}&pageNum=${pageNum }'">
							<input type="button" class="btn btn-danger" value="삭제"
							onclick="document.location.href='${pageContext.request.contextPath}/freeBoard/freeDeleteForm.b?num=${article.num}&pageNum=${pageNum }'">
							<input type="button" class="btn btn-primary" value="목록"
							onclick="location.href='${pageContext.request.contextPath}/freeBoard/freeList.b?pageNum=${pageNum }'">
						</td>
					</tr>
				</table>
			</form>
		</section>
	</section>
	<%@ include file="../../index/bootstrap.jsp"%>
	<%@ include file="../../index/bottom.jsp"%>
	<%@ include file="../../index/pathBottom.jsp"%>
	<%@ include file="../../index/script.jsp"%>
</body>
</html>
