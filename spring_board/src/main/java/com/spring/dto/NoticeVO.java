package com.spring.dto;

import java.util.Date;

public class NoticeVO {
	private int nno;          // 게시판번호
	private String title;     // 제목	
	private String writer;    // 작성자
	private String content;   // 내용
	private int viewcnt;      // 조회수
	private Date regDate;     // 등록날짜
	private Date startDate;   // 게시 시작일
	private Date endDate;     // 게시 종료일
	private int point;		  // 중요 여부 (1:중요, 0:일반)
	
	
	public NoticeVO() {}
	public NoticeVO(int nno, String title, String writer, String content, int viewcnt, Date regDate, Date startDate,
			Date endDate, int point) {
		super();
		this.nno = nno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewcnt = viewcnt;
		this.regDate = regDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.point = point;
	}
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "NoticeVO [nno=" + nno + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", viewcnt=" + viewcnt + ", regDate=" + regDate + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", point=" + point + "]";
	}
	
	
	
	
}
