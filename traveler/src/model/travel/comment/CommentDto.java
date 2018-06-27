package model.travel.comment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentDto {
	private int num;
	private int listNum;
	private String writer;
	private String content;
	private String commentDate;
	private Timestamp time=null;
	private Date date=null;
	private SimpleDateFormat dateFormat=new SimpleDateFormat(" [yyyy .  MM .  dd  "+"  HH : mm  ]");
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		date=new Date(commentDate.getTime());
		this.commentDate=dateFormat.format(date);
	}
	
}
