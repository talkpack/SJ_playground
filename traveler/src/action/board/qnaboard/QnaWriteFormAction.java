package action.board.qnaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.qnaboard.QnaBoardDao;
import model.board.qnaboard.QnaBoardDto;


public class QnaWriteFormAction implements CommandAction{
		public String requestPro(HttpServletRequest request, HttpServletResponse response)
									throws Throwable{
			request.setCharacterEncoding("utf-8");
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int num=0, ref=1, step=0, depth=0;
			try {
				if(request.getParameter("num") != null) {
					num = Integer.parseInt(request.getParameter("num"));
					ref = Integer.parseInt(request.getParameter("ref"));
					step = Integer.parseInt(request.getParameter("step"));
					depth = Integer.parseInt(request.getParameter("depth"));
				}
			}catch(Exception e) {e.printStackTrace();}
			
			request.setAttribute("num", new Integer(num));
			request.setAttribute("ref", new Integer(ref));
			request.setAttribute("step", new Integer(step));
			request.setAttribute("depth", new Integer(depth));
			request.setAttribute("pageNum", pageNum);
			
			return CommandUri.qnaWriteFormView;
		}
}