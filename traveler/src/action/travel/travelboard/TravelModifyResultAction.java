package action.travel.travelboard;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class TravelModifyResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		TravelBoardDao dbPro = TravelBoardDao.getInstance();
		
		// [ 갱신 최신 파일 저장 ] //
		// 업로드할 폴더의 경로를 생성
		String uploadPath = request.getServletContext().getRealPath("/traveler/upload/travel_title");
		System.out.println("uploadPath : " + uploadPath);
		// 파일의 최대 사이즈
		int size = 10 * 1024 * 1024;

		// 이름과 제목을 저장할 변수
		String name = "";
		// 업로드 된 파일 이름을 저장할 변수
		String newFileName = "";
		// 업로드 되기 전의 원본 파일 이름을 저장할 변수
		String originFileName = "";
		MultipartRequest multi = null;
		Enumeration<String> files = null;
		String file = null;
		
		try {
			multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());

			// 업로드된 파일명과 원본 파일명을 저장
			// 업로드된 모든 파일에 접근할 수 있는 Enumerator 가져오기
			files = multi.getFileNames();
			// 첫번째 파일에 접근
			file = files.nextElement();
			// 업로드 된 파일명 저장

			newFileName = multi.getFilesystemName(file);
			System.out.println("수정될 최신 파일 newFileName : " + newFileName);
			// 원본 파일명 저장
			originFileName = multi.getOriginalFileName(file);
			System.out.println("원본 파일 이름 originFileName : " + originFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newFileName == null) {
			newFileName = "";
		}
		
		// [ 패스 절차 ] //
		String dbPwd=multi.getParameter("dbPwd");
		String writePwd=multi.getParameter("writePwd");
		boolean check = false;
		boolean result = false;
		System.out.println("dbPwd : "+dbPwd);
		System.out.println("writePwd : "+writePwd);
		if(dbPwd.equals(writePwd)) {
			result=true;
		}
		if(result) {	
			// 패스 절차 통과 시
			TravelBoardDto travelBoardDto = new TravelBoardDto();
			travelBoardDto.setNum(Integer.parseInt(multi.getParameter("num")));
			travelBoardDto.setSubject(multi.getParameter("subject"));
			travelBoardDto.setContent(multi.getParameter("content"));
			travelBoardDto.setFileName(newFileName);
			
			// [ 수정 전의 파일 삭제 ] //
			TravelBoardDto deleteDto = dbPro.getContent(Integer.parseInt(multi.getParameter("num")));
			String oldFileName = deleteDto.getFileName();
			System.out.println("삭제될 기존 파일 oldFileName : " + oldFileName + "\n");
			String uploadOldFileName = request.getServletContext().getRealPath("/traveler/upload/travel_title") + "/"
					+ oldFileName;
			File deleteOldFile = new File(uploadOldFileName);
			if (deleteOldFile.exists() && deleteOldFile.isFile()) {
				deleteOldFile.delete(); // 파일 삭제
			}
			
			// [ 업데이트 진행 ] //
			check = dbPro.writeUpdate(travelBoardDto);
			
		} else {	
			// 패스 절차 실패 시(최신 저장 시켰던 파일 삭제)
			String uploadNewFileName = request.getServletContext().getRealPath("/traveler/upload/travel_title") + "/"
					+ newFileName;
			File deleteNewFile = new File(uploadNewFileName);
			if (deleteNewFile.exists() && deleteNewFile.isFile()) {
				deleteNewFile.delete(); // 파일 삭제
			}
		}
		
		String pageNum = multi.getParameter("pageNum");
		System.out.println("pageNum : "+pageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);

		return CommandUri.travelModifyResultView;
	}

}
