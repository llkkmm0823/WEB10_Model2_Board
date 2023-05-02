package com.ezen.board.dto;

import java.sql.Timestamp;

public class BoardDto {
	
	private int num;
	private String pass;
	private String userid;
	private String email;
	private String title;
	private String content;
	private int readcount;
	private Timestamp writedate;
	private int replycnt;  // 현재 게시물 댓글의 갯수를 저장할 변수
	
	//java.sql.date : 날짜와 시간형식사용
	//java.sql.timestamp : 날짜, 시간, 밀리초형식사용
	//좀 더 세밀한 시간 사용을 위해 timestamp사용
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	


}
