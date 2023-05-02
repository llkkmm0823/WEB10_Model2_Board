package com.ezen.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.utill.Paging;


public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//게시물을 조회해서 ArrayList에 담고, 그를 다시 request에 담아서 main.jsp로 포워딩
		BoardDao bdao = BoardDao.getInstance();

		int page = 1; // 처음 게시판을 열려고 할 때 -> page값은 1
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page"));
		
		//Paging 객체를 만들고 멤버변수에 현재 페이지 번호를 저장
		Paging paging = new Paging();
		paging.setPage(page);
		
		//데이터베이스에 Access해서 총 레코드 갯수를 리턴받음
		int Count = bdao.getAllCount();
		//리턴받은 갯수를 Paging 객체의 total Count 변수에 저장
		paging.setTotalCount(Count); // paging()메서드가 같이 호출됨 ->각 멤버변수 값 계산
		
		
		
		// ArrayList<BoardDto> list = bdao.selectBoard(paging.getStartNum(),paging.getEndNum());
		ArrayList<BoardDto> list = bdao.selectBoard(paging);
		//어차피 paging안에 다있으니까 paging만 보내도 상관없음
		
		
		//다 조회해와서 10개를 나타내는것이 아니라 한번에 10개만 조회하는것
		
		request.setAttribute("bList", list);
		request.setAttribute("paging", paging);

		
		HttpSession session = request.getSession();
		session.removeAttribute("pass");
		
	
		RequestDispatcher dp = request.getRequestDispatcher("main.jsp");
		dp.forward(request, response);
		
		
		
		
	}

}
