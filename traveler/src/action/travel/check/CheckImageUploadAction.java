package action.travel.check;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.command.CommandAction;
import action.command.CommandUri;

public class CheckImageUploadAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("checkImageUploadAction 들어옴");
		
		String sFunc=request.getParameter("CKEditorFuncNum");
		//CKEditorFuncNum은 CKEditor에서 업로드하는 이미지에 부여하는 일련번호
		System.out.println("sFunc : "+sFunc);
		String uploadPath=request.getServletContext().getRealPath("/traveler/upload/travel_image");
		System.out.println("uploadPath : "+uploadPath);
		//파일의 최대 사이즈
		int size=10*1024*1024;
		//이름과 제목을 저장할 변수
		String name="";
		String subject="";
		//업로드 된 파일 이름을 저장할 변수
		String filename="";
		//업로드 되기 전의 원본 파일 이름을 저장할 변수
		String originfilename="";
		MultipartRequest multi=null;
		Enumeration<String> files=null;
		String file=null;
		try {
			multi=new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());
			
			//파일업로드 폼의 파라미터는 request로는 읽을 수 없고 MultipartRequest를 가지고 읽어야 합니다.
			name=multi.getParameter("name");
			System.out.println("name : "+name);
			subject=multi.getParameter("subject");
			System.out.println("subject : "+subject);
			
			//업로드된 파일명과 원본 파일명을 저장
			//업로드된 모든 파일에 접근할 수 있는 Enumerator 가져오기
			files=multi.getFileNames();
			//첫번째 파일에 접근
			file=files.nextElement();
			//업로드 된 파일명 저장
			filename=multi.getFilesystemName(file);
			System.out.println("filename : "+filename);
			//원본 파일명 저장
			originfilename=multi.getOriginalFileName(file);
			System.out.println("originfilename : "+originfilename);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//D:\IT\6. JSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\lecture07\kg_lecture07\UPload에서 확인가능
		
		if(filename==null) {
			filename="";
		}
		
		String urlPath="/traveler/traveler/upload/travel_image/"+filename; 
		System.out.println("urlPath : "+urlPath); //화면에서 해당 이미지를 불러올 수 있다. localhost:8080/traveler/traveler/upload/이미지 이름 
		/*FileDTO fileDto=new FileDTO(); //데이터를 처리할 빈
		System.out.println("num : "+multi.getParameter("num"));
		fileDto.setNum(Integer.parseInt(multi.getParameter("num")));
		fileDto.setSubject(multi.getParameter("subject"));
		fileDto.setContent(multi.getParameter("content"));
		fileDto.setWritepwd(multi.getParameter("writepwd"));
		fileDto.setWriter(multi.getParameter("writer"));
		fileDto.setRegdate(new Timestamp(System.currentTimeMillis()));
		fileDto.setRef(Integer.parseInt(multi.getParameter("ref")));
		fileDto.setStep(Integer.parseInt(multi.getParameter("step")));
		fileDto.setContent(multi.getParameter("content"));
		fileDto.setFilename(filename);
		
		FileDAO fileDao=FileDAO.getInstance(); //DB연결
		boolean result=fileDao.writeInsert(fileDto);
		
		request.setAttribute("result", result);*/
		
		request.setAttribute("sFunc", sFunc);
		request.setAttribute("urlPath", urlPath);
		//http://zzznara2.tistory.com/443
		return CommandUri.checkImageUploadView ;
	}

}
