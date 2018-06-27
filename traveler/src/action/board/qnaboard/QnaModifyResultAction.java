package action.board.qnaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.qnaboard.QnaBoardDao;
import model.board.qnaboard.QnaBoardDto;

public class QnaModifyResultAction implements CommandAction{
	@Override
	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		
		QnaBoardDto article = new QnaBoardDto();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setSubject(request.getParameter("subject"));
		article.setCategory(request.getParameter("category"));
		article.setWritepwd(request.getParameter("writepwd"));
		article.setContent(request.getParameter("content"));
		
		QnaBoardDao dbPro = QnaBoardDao.getInstance();
		int check = dbPro.updateArticle(article);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return CommandUri.qnaModifyResultView;
	}
}
