<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${flag==1 }">
<input type="hidden" id="listNum" value="${listNum }"/>
<input type="button" class="comment-button" onClick="comment(3);" value="댓글 ▲"/>
<hr color="#191919" width="100%"  noshade />
	<c:forEach var="comment" items="${commentList }" begin="0" step="1" end="${count }">
<table class="comment-table">
	<tr >
		<td style=" text-align:center; width:200px; padding:20px; font-size:18px;"><i class="icon_profile"></i>&nbsp;&nbsp;${comment.writer } 님</td>
		<td>|</td>
		<td rowspan="2" style=" text-align:left; width:1300px; margin:10px; padding:20px; "> ${comment.content }</td>
		<td style=" text-align:right; width:300px; margin:10px; padding:20px; ">${comment.commentDate } </td>
	</tr>
	
</table>
	</c:forEach>
	<input type="hidden" id="loginId" value="${loginId }"/>
	<input type="hidden" id="listNum" value="${listNum }"/>
	<textarea class="comment-write" id="content"rows="6"></textarea><p>
	<input onClick="comment(2);" type="button" value="등록" class="comment-button"/><p>	

</c:if>


<c:if test="${flag==3 }">
<input type="hidden" id="listNum" value="${listNum }"/>
<input type="button" class="comment-button" onClick="comment(1);" value="댓글 ▼"/>
</c:if>
