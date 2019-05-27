package com.spring.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.request.Criteria;
import com.spring.service.BoardService;
import com.spring.service.PdsService;
import com.spring.service.ReplyService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	@Autowired
	private ReplyService rService;
	@Autowired
	private PdsService pService;
	
	@ModelAttribute("subMenuList")
	public List<String[]> subMenuModel(){
		List<String[]> subMenuList=new ArrayList<String[]>();
		subMenuList.add(new String[]{"통합검색","search"});
		subMenuList.add(new String[]{"최근글목록",""});
		subMenuList.add(new String[]{"공지사항","notice/list"});
		subMenuList.add(new String[]{"자유게시판","free/list"});
		subMenuList.add(new String[]{"자료실","pds/list"});
		return subMenuList;
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String boardMain() {
		String url="board/board_main";				
		return url;
	}
	
	@RequestMapping("/free/list")
	public ModelAndView freeList(Criteria cri,ModelAndView modelnView) 
							 		throws SQLException{
		
		String url="board/free/free_list";
		
		Map<String,Object> dataMap = bService.getBoardList(cri);
		
		modelnView.addObject("dataMap",dataMap);
		modelnView.setViewName(url);
		
		return modelnView;		
	}
	
}







