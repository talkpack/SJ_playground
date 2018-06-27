package action.board.freeboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.freeboard.FreeBoardDao;
import model.board.freeboard.FreeBoardDto;

public class FreeModifyResultAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		
		FreeBoardDto article = new FreeBoardDto();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setWritepwd(request.getParameter("writepwd"));
		
		FreeBoardDao dao = FreeBoardDao.getInstance();
		int check = dao.modifyArticle(article);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return CommandUri.freeModifyResultView;
	}
	
}
