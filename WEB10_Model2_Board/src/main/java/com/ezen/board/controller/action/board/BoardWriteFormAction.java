package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.board.controller.action.Action;

public class BoardWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "board/boardWriteForm.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) url="member/loginForm.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		// 주석처리한 이유는 여기서 이미 처리를 해버렸기 때문
		//하지만 이 방법은 경로가 유출되면 페이지가 유출됨.
	}

}
