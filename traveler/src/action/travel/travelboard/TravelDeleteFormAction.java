package action.travel.travelboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.travelboard.TravelBoardDao;

public class TravelDeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//////////# [ 1. 기본 셋팅 : 세션, 로그인 체크, 검색이면 관련 속성 세팅] //////////
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String loginId=(String) session.getAttribute("loginId");
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		if (request.getParameter("howSearch") != null) {
			String text = request.getParameter("textSearch");
			int how = Integer.parseInt(request.getParameter("howSearch"));
			request.setAttribute("text", text);
			request.setAttribute("how", how);
		}
		TravelBoardDao dbPro = TravelBoardDao.getInstance();
		
		//////////# [ 2. 로그인 체크] //////////
		System.out.println("num : "+num);
		System.out.println("pageNum : "+pageNum);
		System.out.println("loginId : "+loginId);
		String dbPwd=dbPro.passWriter(num,loginId);
		System.out.println("작성자 권한 비밀번호 : "+dbPwd);
	
	
		//////////# [ 3. 속성 ] //////////
	
		request.setAttribute("dbPwd", dbPwd);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		return CommandUri.travelDeleteFormView;
	}

}
