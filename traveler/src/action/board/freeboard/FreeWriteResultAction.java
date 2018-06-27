package action.board.freeboard;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.freeboard.FreeBoardDao;
import model.board.freeboard.FreeBoardDto;

public class FreeWriteResultAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("/upload");
	    
		System.out.println("절대경로 : " + uploadPath + "<br/>");

		int maxSize = 1024 * 1024 * 10; 

	    MultipartRequest multi = null;
	    try{
	        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
	        Enumeration files = multi.getFileNames();
	        
	        int num = Integer.parseInt(multi.getParameter("num"));
	        String writer = multi.getParameter("writer");
	        String subject = multi.getParameter("subject");
	        String content = multi.getParameter("content");
	        String writepwd = multi.getParameter("writepwd");
	        int ref = Integer.parseInt(multi.getParameter("ref"));
	        int step = Integer.parseInt(multi.getParameter("step"));
	        int depth = Integer.parseInt(multi.getParameter("depth"));
	        String filename = multi.getFilesystemName("filename");
	        
	        while(files.hasMoreElements()){
	            String file1 = (String)files.nextElement();
	            File file = multi.getFile(file1);
	        }
			FreeBoardDto article = new FreeBoardDto();
			article.setNum(num);
			article.setWriter(writer);
			article.setSubject(subject);
			article.setWritepwd(writepwd);
			article.setRef(ref);
			article.setStep(step);
			article.setDepth(depth);
			article.setRegdate(new Timestamp(System.currentTimeMillis()));
			article.setContent(content);
			article.setFilename(filename);
			
			FreeBoardDao dao = FreeBoardDao.getInstance();
			dao.insertArticle(article);
		
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		return CommandUri.freeWriteResultView;
		
	}
	
}
