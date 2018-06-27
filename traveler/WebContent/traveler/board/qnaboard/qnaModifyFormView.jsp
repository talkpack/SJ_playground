<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script>
	function modifyCheck() {
		if (confirm("수정 하시겠습니까?")) {
			if (document.qnamodifyForm.subject.value == "") {
				alert("제목을 입력하세요.");
				document.qnamodifyForm.subject.focus();
				return false;
			}
			if (document.qnamodifyForm.content.value == "") {
				alert("내용을 입력하세요.");
				document.qnamodifyForm.content.focus();
				return false;
			}
			if (document.qnamodifyForm.writepwd.value == "") {
				alert("비밀번호를 입력하세요.");
				document.qnamodifyForm.writepwd.focus();
				return false;
			}
			location.href = "${pageContext.request.contextPath }/qnaBoard/qnaModifyResult.b?pageNum=${pageNum}";
		} else {
			alert("취소 되었습니다.");
			return false;
			return;
		}
	}
</script>
<%@include file="../../index/pathTop.jsp"%>
</head>
<body>
  <%@ include file="../../index/top.jsp" %>
  <%@ include file="../../index/side.jsp" %>
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-laptop"></i>수정</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
              <li><i class="icon_document_alt"></i>Q&A</li>
            </ol>
       <form method="post" name="qnamodifyForm" action="${pageContext.request.contextPath}/qnaBoard/qnaModifyResult.b?pageNum=${pageNum}"
            		onsubmit="return modifyCheck()">
            	<input type="hidden" name="num" value="${article.num}">
            	<input type="hidden" name="writer" value="${article.writer}">
		<table class="table">
        <tr>
            <th class="info" width="150" style="text-align:center">글번호</th>
            <td>${article.num}</td>
            <th class="info" style="text-align:center">조회수</th>
			<th>${article.readcount}</th>
        </tr>
        <tr>
            <th class="info" style="text-align:center">작성자</th>
            <td>${article.writer}
            </td>
            <th class="info" style="text-align:center">작성일</th>
            <td>${article.regdate}</td>
        </tr>
        
        <tr>
            <th class="info" style="text-align:center">제목</th>
            <td colspan="3"><input class="form-control" id="subject" name="subject" type="text" value="${article.subject}">
            </td>
        </tr>
        <tr>
            <th class="info" style="text-align:center">내용</th>
            <td colspan="3"><textarea class="form-control ckeditor" name="content" rows="6">${article.content}</textarea></td>
        </tr>
         <tr>
         	<th class="info" style="text-align:center">첨부파일</th>
         	<td colspan="3">${article.filename}</td>
        
        <tr>
            <th class=info style="text-align:center">비밀번호</th>
            <td colspan="3"><input class="form-control" id="writepwd" name="writepwd" type="password"></td>
        </tr>
        <tr>
            <td colspan="4" class="text-center">  
				<input type="submit" class="btn btn-warning" value="수정">          
				<input type="button" class="btn btn-primary" value="목록" onclick="location.href='${pageContext.request.contextPath}/qnaBoard/qnaList.b?pageNum=${pageNum}'">
            	<a href="javascript:history.go(-1)"><input type="button" class="btn btn-info" value="취소"></a>
            </td>
        </tr>
        </table>
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
