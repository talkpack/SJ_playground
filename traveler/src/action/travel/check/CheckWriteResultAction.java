package action.travel.check;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class CheckWriteResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("checkWriteResultAction 들어옴");
		////////// # [ 1. 기본 셋팅 ] //////////
		request.setCharacterEncoding("UTF-8");

		////////// # [ 2. 파일 업로드 ] //////////
		String uploadPath = request.getServletContext().getRealPath("/traveler/upload/travel_title");// 업로드할 폴더의 경로를 생성
		System.out.println("uploadPath : " + uploadPath);
		int size = 10 * 1024 * 1024;// 파일의 최대 사이즈
		String name = "";// 이름과 제목을 저장할 변수
		String filename = "";// 업로드 된 파일 이름을 저장할 변수
		String originfilename = "";// 업로드 되기 전의 원본 파일 이름을 저장할 변수
		MultipartRequest multi = null;
		Enumeration<String> files = null;
		String file = null;
		try {
			multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());// 업로드된 파일명과 원본 파일명을 저장
			// 업로드된 모든 파일에 접근할 수 있는 Enumerator 가져오기
			files = multi.getFileNames();// 첫번째 파일에 접근
			file = files.nextElement();// 업로드 된 파일명 저장
			filename = multi.getFilesystemName(file);
			System.out.println("filename : " + filename);
			originfilename = multi.getOriginalFileName(file);// 원본 파일명 저장
			System.out.println("originfilename : " + originfilename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filename == null) {
			filename = "";
		}
		
		//////////# [ 3. 내용 저장 ] //////////
		System.out.println("writeResultAction에 불러온 country : \"" + multi.getParameter("country")+"\"");
		System.out.println("writeResultAction에 불러온 city : \"" + multi.getParameter("city")+"\"");
		System.out.println("writeResultAction에 불러온 term : \"" + multi.getParameter("term")+"\"");
		TravelBoardDao travelBoardDao = TravelBoardDao.getInstance();
		TravelBoardDto travelBoardDto = new TravelBoardDto();
		travelBoardDto.setCountry(multi.getParameter("country"));
		travelBoardDto.setCity(multi.getParameter("city"));
		travelBoardDto.setMoney(multi.getParameter("money"));
		travelBoardDto.setTerm(multi.getParameter("term"));
		travelBoardDto.setRoute(multi.getParameter("route"));
		travelBoardDto.setSubject(multi.getParameter("subject"));
		travelBoardDto.setContent(multi.getParameter("content"));
		travelBoardDto.setWritePwd(multi.getParameter("writePwd"));
		travelBoardDto.setFileName(filename);
		travelBoardDto.setWriter(multi.getParameter("writer"));
		travelBoardDao.writeInsert(travelBoardDto);

		return CommandUri.checkWriteResultView;
	}

}
