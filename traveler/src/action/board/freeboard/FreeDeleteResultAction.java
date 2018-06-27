package action.board.freeboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.freeboard.FreeBoardDao;

public class FreeDeleteResultAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String writepwd = request.getParameter("writepwd");
		
		FreeBoardDao dao = FreeBoardDao.getInstance();
		int check = dao.deleteArticle(num, writepwd);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return CommandUri.freeDeleteResultView;
	}
	
}
