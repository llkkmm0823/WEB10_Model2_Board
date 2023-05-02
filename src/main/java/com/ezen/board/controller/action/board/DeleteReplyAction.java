package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;

public class DeleteReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String replynum = request.getParameter("replynum");
		String boardnum = request.getParameter("boardnum");
		
		//replynum으로 댓글 삭제, boardnum으로 원래 페이지 이동
		BoardDao bdao = BoardDao.getInstance();
		bdao.deleteReply(replynum);
		
		//boardnum으로 원래 보던 게시물 페이지로 돌아감
		String url="board.do?command=boardViewNocount&num=" + boardnum;
		
		RequestDispatcher rd=request.getRequestDispatcher("board/boardView.jsp");
	     rd.forward(request, response);

	}

}
