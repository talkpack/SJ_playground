<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
  <meta name="author" content="GeeksLabs">
  <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
  <link rel="shortcut icon" href="img/favicon.png">
<title>TRAVELER</title>
<style>
      h1 {
        font-size: 20px;
      }
      .jb-center {
        text-align: center;
      }
</style>

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
						<i class="fa fa-laptop"></i>여행 다나와
					</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
						<li><i class="icon_document_alt"></i>게시판</li>
						<li><i class="fa fa-file-text-o"></i>자유 게시판</li>
					</ol>
				</div>
			</div>
	</section>
		<form>
			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading"> 여행 다나와 목록 </header>
							<c:if test = "${count == 0 }">
								<table class="table table-striped table-advance table-hover">
									<tr>
										<td>저장된 글이 없습니다.</td>
									</tr>
								</table>
							</c:if>
							<c:if test = "${count > 0 }">
								<table class="table table-striped table-advance table-hover">
									<tbody>
										<tr>
											<th style="text-align:center"><i class="icon_profile"></i> 번호</th>
											<th style="text-align:center"><i class="icon_mobile"></i> 제목</th>
											<th style="text-align:center"><i class="icon_pin_alt"></i> 작성자</th>
											<th style="text-align:center"><i class="icon_calendar"></i> 작성일</th>
											<th style="text-align:center"><i class="icon_cogs"></i> 조회수</th>
										</tr>
										<c:forEach var="article" items="${articleList }">
										<tr>
											<td align="center" width="100">
												<c:out value="${number }"/>
												<c:set var="number" value="${number - 1 }"/>
											</td>
											<td class="depthtd">
											<c:if test="${article.depth > 0 }">
											<img src="${pageContext.request.contextPath }/traveler/design/img/level.gif" width="${5 * article.depth }">
											&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
											<img src="${pageContext.request.contextPath }/traveler/design/img/reply.gif">
											</c:if>
											<c:if test="${article.depth == 0 }">
											<img src="${pageContext.request.contextPath }/traveler/design/img/level.gif" width="${5 * article.depth }">
											</c:if>
											<a href="${pageContext.request.contextPath }/freeBoard/freeContent.b?num=${article.num }&pageNum=${currentPage }">
											${article.subject }</a>
											<c:if test="${article.readcount >= 30 }">
											<img src="${pageContext.request.contextPath }/traveler/design/img/hot.gif">
											</c:if>
											</td>
											<td>${article.writer }</td>
											<td><fmt:formatDate value="${article.regdate }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
											<td>${article.readcount }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
									</c:if>
								<div class="jb-center">
									<ul class="pagination">
									<c:if test="${count > 0 }">
										<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }"/>
										<c:set var="pageCount" value="${count / pageSize + imsi }"/>
										<c:set var="pageBlock" value="${5 }"/>
										<fmt:parseNumber var="result" value="${currentPage / pageBlock }" integerOnly="true"/>
										<c:set var="startPage" value="${result * pageBlock + 1 }"/>
										<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
										
										<c:if test="${endPage > pageCount }">
											<c:set var="endPage" value="${pageCount }"/>
										</c:if>
											<li class="disabled"><a href="${pageContext.request.contextPath }/freeBoard/freeList.b?pageNum=${startPage - pageBlock }"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
										<c:forEach var="i" begin="${startPage }" end="${endPage }">
											<li><a href="${pageContext.request.contextPath }/freeBoard/freeList.b?pageNum=${i }">${i }</a></li>
										</c:forEach>
											<li><a href="${pageContext.request.contextPath }/freeBoard/freeList.b?pageNum=${startPage + pageBlock }"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
									</c:if>
								</ul>
							<a href="<%=request.getContextPath()%>/freeBoard/freeWriteForm.b" class="btn btn-primary pull-right">글쓰기</a>
						</div>
				</section>
			</div>
		</div>
	</form>
</section>
<%@ include file="../../index/bootstrap.jsp"%>
<%@ include file="../../index/bottom.jsp"%>
<%@ include file="../../index/pathBottom.jsp"%>
<%@ include file="../../index/script.jsp"%>
</body>
</html>
