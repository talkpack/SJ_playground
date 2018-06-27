package action.board.freeboard;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;

public class FreeDownloadAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String fileName = request.getParameter("filename");
	    String savePath = "upload";
	    String sDownPath = request.getServletContext().getRealPath(savePath);
	    
	    System.out.println("다운로드 폴더 절대 경로 위치 : " + sDownPath);
	    System.out.println("fileName : " + fileName);
	    
	    String sFilePath = sDownPath + "\\" + fileName;
	    
	    System.out.println("sFilePath : " + sFilePath);

	    File outputFile = new File(sFilePath);
	    byte[] temp = new byte[1024*1024*10];
	    
	    FileInputStream in = new FileInputStream(outputFile);

	    String sMimeType = request.getServletContext().getMimeType(sFilePath);

	    System.out.println("유형 : " + sMimeType);

	    if ( sMimeType == null ){
	        sMimeType = "application.octec-stream";
	    }

	    response.setContentType(sMimeType);
	    String sEncoding = new String(fileName.getBytes("utf-8"),"8859_1");
	    String AA = "Content-Disposition";
	    String BB = "attachment;filename="+sEncoding;

	    response.setHeader(AA,BB);

	    ServletOutputStream out2 = response.getOutputStream();

	    int numRead = 0;

	    while((numRead = in.read(temp,0,temp.length)) != -1){ 
	        out2.write(temp,0,numRead); // temp배열에 있는 데이터의 0번째부터 최대 numRead만큼 출력한다.
	    }
	    out2.flush();
	    out2.close();
	    in.close();
		
	    return null;
	}

}
