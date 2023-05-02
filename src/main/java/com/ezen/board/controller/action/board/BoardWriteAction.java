package com.ezen.board.controller.action.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dao.MemberDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.MemberDto;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//전송된 항목들은 BoardDto에 담아 BoardDao의 insertBoard 메서드로 레코드 추가
		BoardDto bdto = new BoardDto();
		
		bdto.setUserid(request.getParameter("userid"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setTitle(request.getParameter("title"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setContent(request.getParameter("content"));

		
		BoardDao bdao = BoardDao.getInstance();
		bdao.insertBoard(bdto);
	
		response.sendRedirect("board.do?command=main");
		
	}

}
