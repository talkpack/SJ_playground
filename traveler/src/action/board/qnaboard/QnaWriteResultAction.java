package action.board.qnaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.command.CommandAction;
import action.command.CommandUri;
import model.board.qnaboard.QnaBoardDao;
import model.board.qnaboard.QnaBoardDto;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;

public class QnaWriteResultAction implements CommandAction{
	
	@Override
	public String requestPro(
				HttpServletRequest request,
				HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("UTF-8");
		
		MultipartRequest multi = null;
		int filesize = 10*1024*1024;
		String uploadPath = request.getServletContext().getRealPath("/upload");
		
		try {
			multi=new MultipartRequest(request, uploadPath, filesize, "UTF-8", new DefaultFileRenamePolicy());
			
			Enumeration files = multi.getFileNames();
			String filename=multi.getFilesystemName("filename");
			String num = multi.getParameter("num");
			String category = multi.getParameter("category");
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String writepwd = multi.getParameter("writepwd");
			String ref = multi.getParameter("ref");
			String step = multi.getParameter("step");
			String depth = multi.getParameter("depth");
			String content = multi.getParameter("content");
			
			
			while(files.hasMoreElements()){
	            String file1 = (String)files.nextElement();
	            File file = multi.getFile(file1);
	        }
			
			QnaBoardDto article = new QnaBoardDto();
			article.setNum(Integer.parseInt(num));
			article.setCategory(category);
			article.setWriter(writer);
			article.setSubject(subject);
			article.setWritepwd(writepwd);
			article.setRef(Integer.parseInt(ref));
			article.setStep(Integer.parseInt(step));
			article.setDepth(Integer.parseInt(depth));
			article.setRegdate(new Timestamp(System.currentTimeMillis()));
			article.setContent(content);
			article.setFilename(filename);
			
			QnaBoardDao dbPro = QnaBoardDao.getInstance();
			dbPro.insertArticle(article);
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Download Exception : " + e.getMessage());
		}
		
		return CommandUri.qnaWriteResultView;
	}
}
