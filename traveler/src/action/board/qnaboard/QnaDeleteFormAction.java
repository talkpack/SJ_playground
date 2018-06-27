package action.board.qnaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.qnaboard.QnaBoardDao;
import model.board.qnaboard.QnaBoardDto;

public class QnaDeleteFormAction implements CommandAction{
	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
		
		QnaBoardDao dao = QnaBoardDao.getInstance();
		QnaBoardDto article = dao.deletegetArticle(num);
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return CommandUri.qnaDeleteFormView;
	}
}
