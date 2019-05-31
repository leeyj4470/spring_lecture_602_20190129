package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dao.NoticeDAO;
import com.spring.dto.NoticeVO;
import com.spring.request.Criteria;
import com.spring.request.PageMaker;

public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO=noticeDAO;		
	}

	@Override
	public Map<String,Object> getNoticeList(Criteria cri) throws SQLException {
		List<NoticeVO> noticeList = noticeDAO.selectSearchNoticeList(cri);
		List<NoticeVO> pointNoticeList = noticeDAO.selectPointNoticeList(cri);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(noticeDAO.selectSearchNoticeListCount(cri));
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("noticeList", noticeList);
		dataMap.put("pointNoticeList", pointNoticeList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public  List<NoticeVO> getPointNoticeList(Criteria cri) throws SQLException {
		List<NoticeVO> noticeList = noticeDAO.selectPointNoticeList(cri);
		return noticeList;
	}

	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		NoticeVO notice = noticeDAO.selectNoticeByNno(nno);
		return notice;
	}

	@Override
	public void regist(NoticeVO notice) throws SQLException {
		
		int nno = noticeDAO.selectNoticeSeqNext();
		notice.setNno(nno);
		
		noticeDAO.insertNotice(notice);
	}

	@Override
	public void modify(NoticeVO notice) throws SQLException {
		noticeDAO.updateNotice(notice);		
	}

	@Override
	public void remove(int nno) throws SQLException {
		noticeDAO.deleteNotice(nno);
	}

}
