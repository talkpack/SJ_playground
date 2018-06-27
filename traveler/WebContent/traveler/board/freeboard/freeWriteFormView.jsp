<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script>
	function writeCheck() {
		if (confirm("등록 하시겠습니까?")) {
			if (document.writeForm.writer.value == "") {
				alert("작성자를 입력하세요.");
				document.writeForm.writer.focus();
				return false;
			}
			if (document.writeForm.subject.value == "") {
				alert("제목을 입력하세요.");
				document.writeForm.subject.focus();
				return false;
			}
			if (CKEDITOR.instances.content.getData() == '') {
				alert("내용을 입력하세요.");
				CKEDITOR.instances.content.focus();
				return false;
			}
			if (document.writeForm.writepwd.value == "") {
				alert("비밀번호를 입력하세요.");
				document.writeForm.writepwd.focus();
				return false;
			}
			location.href = "${pageContext.request.contextPath }/freeBoard/freeWriteResult.b";
		} else {
			alert("취소 되었습니다.");
			return false;
		}
	}
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<!-- <script src="traveler/freeBoard/freeboard/script.js"></script> -->
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
				<i class="fa fa-laptop"></i>글쓰기
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
				<li><i class="icon_document_alt"></i>게시판</li>
				<li><i class="fa fa-file-text-o"></i>자유 게시판</li>

			</ol>
		</div>
	</div>

	<form name="writeForm" method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/freeBoard/freeWriteResult.b"
		onsubmit="return writeCheck()" style="text-align: center">
			<div class="form">
				<input type="hidden" name="num" value="${num }"> 
				<input type="hidden" name="ref" value="${ref }"> 
				<input type="hidden" name="step" value="${step }"> 
				<input type="hidden" name="depth" value="${depth }">
				<div class="table table-responsive">
					<table class="table">
						<tr>
							<th class="success" style="text-align:center">작성자</th>
							<td colspan="3"><input class="form-control" id="writer"
								name="writer" type="text"></td>
						</tr>
						<tr>
							<th class="success" style="text-align:center">제목</th>
							<td colspan="3"><c:if test="${num == 0 }">
									<input class="form-control" id="subject" name="subject"
										type="text">
								</c:if> <c:if test="${num != 0 }">
									<input class="form-control" id="subject" name="subject"
										type="text" value="[답변]">
								</c:if></td>
						</tr>

						<tr>
							<th class="success" style="text-align:center">내용</th>
							<td colspan="3"><textarea class="form-control ckeditor" id="content" name="content" rows="6"></textarea></td>
						</tr>

						<tr>
							<c:if test="${num == 0 }">
								<th class="success" style="text-align:center">첨부파일</th>
								<td colspan="3"><input type="file" name="filename"></td>
							</c:if>
						</tr>
						<tr>
							<th class="success" style="text-align:center">비밀번호</th>
							<td colspan="3"><input class="form-control" id="writepwd"
								name="writepwd" type="password"></td>
						</tr>
					</table>
					<div>
					<button class="btn btn-primary" type="submit">등록</button>
					<a href="javascript:history.go(-1)"><input type="button"
						class="btn btn-info" value="취소"></a>
					</div>
					<!-- <div class="credits">
						<a href="https://bootstrapmade.com/">Free Bootstrap Templates</a>
						by <a href="https://bootstrapmade.com/">BootstrapMade</a>
					</div> -->
				</div>
			</div>
	</form>
	</section> </section>
	<%@ include file="../../index/bootstrap.jsp"%>
	<%@ include file="../../index/bottom.jsp"%>
	<%@ include file="../../index/pathBottom.jsp"%>
	<%@ include file="../../index/script.jsp"%>
</body>
</html>
