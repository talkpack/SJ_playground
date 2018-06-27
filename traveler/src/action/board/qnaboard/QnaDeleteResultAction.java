package action.board.qnaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.qnaboard.QnaBoardDao;

public class QnaDeleteResultAction implements CommandAction{
	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String writepwd = request.getParameter("writepwd");
		
		QnaBoardDao dbPro = QnaBoardDao.getInstance();
		int check = dbPro.deleteArticle(num, writepwd);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return CommandUri.qnaDeleteResultView;
	}
}
