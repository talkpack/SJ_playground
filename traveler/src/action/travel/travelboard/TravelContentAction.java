package action.travel.travelboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.comment.CommentDao;
import model.travel.comment.CommentDto;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class TravelContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//////////# [ 1. 기본 셋팅 ] //////////
		int listNum=Integer.parseInt(request.getParameter("num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		//////////# [ 2. 내용 가져오기] //////////
		TravelBoardDao travelBoardDao=TravelBoardDao.getInstance();
		TravelBoardDto travelBoardDto=travelBoardDao.content(listNum);
		System.out.println("testDto subject : "+travelBoardDto.getSubject());
		System.out.println("testDto content : "+travelBoardDto.getContent());
		
		//////////# [ 3. 키워드/제목] //////////
		TravelBoardDto dto=travelBoardDao.keywordSetting(travelBoardDto.getTerm(),travelBoardDto.getMoney(),travelBoardDto.getRoute());
		String termSet=dto.getTerm();
		String moneySet=dto.getMoney();
		String routeSet=dto.getRoute();
		String subject="\"  "+travelBoardDto.getSubject()+"  \"";
		
		System.out.println("subject : "+subject);
		
		
		//////////# [ 3. 속성 ] //////////
		request.setAttribute("travelBoardDto", travelBoardDto);
		request.setAttribute("subject", subject);
		request.setAttribute("termSet", termSet);
		request.setAttribute("moneySet", moneySet);
		request.setAttribute("routeSet", routeSet);
		request.setAttribute("listNum", listNum);
		request.setAttribute("pageNum", pageNum);
		return CommandUri.travelContentView;
	}
}
