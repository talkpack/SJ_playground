<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../index/pathTop.jsp"%>
<script>
	function writeCheck() {
		if (confirm("등록 하시겠습니까?")) {
			if (document.qnawriteForm.category.value == "") {
				alert("카테고리를 선택하세요.");
				document.qnawriteForm.category.focus();
				return false;
			}
			if (document.qnawriteForm.writer.value == "") {
				alert("작성자를 입력하세요.");
				document.qnawriteForm.writer.focus();
				return false;
			}
			if (document.qnawriteForm.subject.value == "") {
				alert("제목을 입력하세요.");
				document.qnawriteForm.subject.focus();
				return false;
			}
			if (document.qnawriteForm.writepwd.value == "") {
				alert("비밀번호를 입력하세요.");
				document.qnawriteForm.writepwd.focus();
				return false;
			}
			if (CKEDITOR.instances.content.getData() == '') {
				alert("내용을 입력하세요.");
				CKEDITOR.instances.content.focus();
				return false;
			}
		} else {
			alert("취소 되었습니다.");
			return false;
		}
	}
</script>
</head>
<body>
  <%@ include file="../../index/top.jsp" %>
  <%@ include file="../../index/side.jsp" %>
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-laptop"></i>글쓰기</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
              <li><i class="icon_document_alt"></i>Q&A</li>
            </ol>
       <form method="post" name="qnawriteForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/qnaBoard/qnaWriteResult.b"
            		onsubmit="return writeCheck()" style="text-align: center">
            <div class="form">
            	<input type="hidden" name="num" value="${num}"> 
				<input type="hidden" name="ref" value="${ref}"> 
				<input type="hidden" name="step" value="${step}"> 
				<input type="hidden" name="depth" value="${depth}">
				<div class="table table-responsive">
		<table class="table">
        <tr>
        <th class="info" style="text-align:center" width="220">카테고리</th>
            <td><select name="category" class="form-control">
            	<option value="">======SELECT======</option>
            	<option value="일반 문의">일반 문의</option>
            	<option value="게시물 관련 문의">게시물 관련 문의</option>
            	<option value="회원 관련 문의">회원 관련 문의</option>
            	<option value="버그 문의">버그 문의</option>
            	<option value="기타 문의">기타 문의</option>
            </select></td>
            <th class="info" style="text-align:center">작성자</th>
            <td colspan="3"><input class="form-control" id="writer" name="writer" type="text"></td>
        </tr>
        <tr>
            <th class="info" style="text-align:center">제목</th>
            <td colspan="3">
            <c:if test="${num == 0 }">
				<input class="form-control" id="subject" name="subject"	type="text">
			</c:if> 
			<c:if test="${num != 0 }">
				<input class="form-control" id="subject" name="subject"	type="text" value="[답변]">
			</c:if>
            </td>
        </tr>
        <tr>
            <th class="info" style="text-align:center">내용</th>
            <td colspan="3"><textarea class="form-control ckeditor" name="content" rows="6"></textarea></td>
        </tr>
         <tr>
         	<th class="info" style="text-align:center">첨부파일</th>
         	<td colspan="3"><input type="file" name="filename"></td>
        
        <tr>
            <th class="info" style="text-align:center">비밀번호</th>
            <td colspan="3"><input class="form-control" id="writepwd" name="writepwd" type="password"></td>
        </tr>
        <tr>
            <td colspan="4" class="text-center">  
				<input type="submit" class="btn btn-warning" value="등록">          
				<input type="button" class="btn btn-primary" value="목록" onclick="location.href='${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${pageNum}'">
            	<a href="javascript:history.go(-1)"><input type="button" class="btn btn-info" value="취소"></a>
            </td>
        </tr>
        </table>
        </div>
        </div>
			</form>
          </div>
        </div>
        </section>
        </section>
   		    <%@ include file="../../index/bootstrap.jsp" %>
			<%@ include file="../../index/bottom.jsp"%>
			<%@ include file="../../index/pathBottom.jsp"%>
			<%@ include file="../../index/script.jsp"%>
</body>
</html>
