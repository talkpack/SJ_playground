package action.travel.travelboard;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class TravelDeleteResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// [ 패스 절차 ] //
		TravelBoardDao dbPro = TravelBoardDao.getInstance();
		String dbPwd=request.getParameter("dbPwd");
		String writePwd=request.getParameter("writePwd");
		int num=Integer.parseInt(request.getParameter("num"));
		boolean check = false;
		boolean result = false;
		System.out.println("dbPwd : "+dbPwd);
		System.out.println("writePwd : "+writePwd);
		if(dbPwd.equals(writePwd)) {
			result=true;
		}
		if(result) {	
			// 패스 절차 통과 시
			TravelBoardDto travelBoardDto = dbPro.getContent(num);
	        String fileName = travelBoardDto.getFileName();
	        String uploadFileName = request.getServletContext().getRealPath("/traveler/upload/travel_title") +"/"+ fileName;
	        File deleteFile = new File (uploadFileName);
			if (deleteFile.exists() && deleteFile.isFile()) {
				deleteFile.delete(); // 파일 삭제 후
			}
			check = dbPro.deleteArticle(num); // DB 레코드 삭제
		}
		
		//뷰에서 사용할 속성
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return CommandUri.travelDeleteResultView;
	}
}
