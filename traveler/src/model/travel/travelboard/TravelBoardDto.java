package model.travel.travelboard;

import java.sql.Timestamp;

public class TravelBoardDto {
	private int num;
	private String country;
	private String city;
	private String money;
	private String route;
	private String term;
	private String writer;
	private String subject;
	private int boardGood;
	private String writePwd;
	private int readCount;
	private Timestamp regDate;
	private String content;
	private String fileName;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getBoardGood() {
		return boardGood;
	}
	public void setBoardGood(int boardGood) {
		this.boardGood = boardGood;
	}
	public String getWritePwd() {
		return writePwd;
	}
	public void setWritePwd(String writePwd) {
		this.writePwd = writePwd;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}
