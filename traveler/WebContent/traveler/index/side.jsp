<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside>
	<div id="sidebar" class="nav-collapse ">
		<ul class="sidebar-menu">
			<li class="active"><a class=""
				href="<%=request.getContextPath()%>/main.do">
					<i class="icon_house_alt"></i> <span>여행 다나와</span>
			</a></li>
			<li class="sub-menu"><a href="javascript:;" class=""> <i
					class="icon_document_alt"></i> <span>게시판</span> <span
					class="menu-arrow arrow_carrot-right"></span>
			</a>
				<ul class="sub">
					<li><a class=""
						href="<%=request.getContextPath()%>/freeBoard/freeList.b">자유
							게시판</a></li>
					<li><a class=""
						href="<%=request.getContextPath()%>/travelBoard/travelList.t">여행
							게시판</a></li>
				</ul></li>
			<li><a class=""
				href="<%=request.getContextPath()%>/qnaBoard/qnaList.b">
					<i class="icon_genius"></i> <span>Q&A</span>
			</a></li>
		</ul>
	</div>
</aside>


