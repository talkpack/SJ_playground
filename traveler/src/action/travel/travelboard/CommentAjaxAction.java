package action.travel.travelboard;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.comment.CommentDao;
import model.travel.comment.CommentDto;

public class CommentAjaxAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//////////# [ 1. 기본 셋팅 ] //////////
		System.out.println("commentAjaxAction 들어옴");
		int flag = Integer.parseInt(request.getParameter("flag"));
		int listNum = Integer.parseInt(request.getParameter("listNum"));
		HttpSession session=request.getSession();
		String loginId=(String) session.getAttribute("loginId");
		System.out.println("commentAjaxAciton 에서 loginId : "+loginId);
		if(loginId==null) {
			loginId="익명자";
		}
		int count=0;
		List<CommentDto> commentList = null;
		
		System.out.println("flag : " + flag);
		System.out.println("listNum : " + listNum);
		System.out.println("loginId : " + loginId);

		
		//////////# [ 2. 댓글 ] //////////
		CommentDao commentDao = CommentDao.getInstance();
		CommentDto commentDto = new CommentDto();
		if (flag == 1) { // 댓글 가져오기
			
			count = commentDao.commentCount(listNum);
			commentList = commentDao.commentList(listNum);
			
			System.out.println("count : " + count);
			
		}else if(flag==2) { //댓글 저장하고 가져오기 
			String content=request.getParameter("content");
			commentDto.setListNum(listNum);
			commentDto.setWriter(loginId);
			commentDto.setContent(content);
			commentDao.commentInsert(commentDto);
			count = commentDao.commentCount(listNum);
			commentList = commentDao.commentList(listNum);
			flag=1;
			
			System.out.println("count : " + count);
			request.setAttribute("content",content);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("commentList", commentList);
		request.setAttribute("flag", flag);
		request.setAttribute("listNum", listNum);
		request.setAttribute("loginId", loginId);
		return CommandUri.commentAjaxView;
	}

}
