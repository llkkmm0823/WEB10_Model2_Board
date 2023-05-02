package com.ezen.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.ReplyDto;
import com.ezen.board.utill.DBMan;
import com.ezen.board.utill.Paging;

public class BoardDao {

	private BoardDao() {
	}

	private static BoardDao itc = new BoardDao();

	public static BoardDao getInstance() {
		return itc;
	}

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<BoardDto> selectBoard(Paging paging) {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		//String sql = "select * from board order by num desc";
		String sql ="select *from( "
				+ " select *from( "
				+ " select rownum as rn , b.* from((select *from board order by num desc) b) "
				+ " ) where rn>=? "
				+ " )where rn<=? ";
		con = DBMan.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paging.getStartNum());
			pstmt.setInt(2, paging.getEndNum());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDto bdto = new BoardDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				list.add(bdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

		return list;
	}

	public void insertBoard(BoardDto bdto) {
		con = DBMan.getConnection();
		String sql = "insert into board(num, userid, pass, title,email, content) values(board_seq.nextVal,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getPass());
			pstmt.setString(3, bdto.getTitle());
			pstmt.setString(4, bdto.getEmail());
			pstmt.setString(5, bdto.getContent());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

	}

	public BoardDto getBoard(int num) {
		BoardDto bdto = new BoardDto();
		con = DBMan.getConnection();
//      String sql="update board set readcount=readcount+1 where num=?";
		// 이렇게 하면 원치 않는 순간에도 조회수가 올라감(수정폼으로 갈때, 수정 다하고 getBoard로 갈때, 댓글 적고 확인 눌러서
		// getBoard로 이동할때)
		String sql = "select * from board where num=?";

		try {
			// -------------------------
//         pstmt=con.prepareStatement(sql);  //조회수 올리기 용
//         pstmt.setInt(1, num);
//         pstmt.executeUpdate();
//         pstmt.close(); //-->써도 그만 안써도 그만
//         
//         sql="select * from board where num=?";
			// -------------------------추가된 부분
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

		return bdto;
	}

	public void plusOneReadcount(int num) {
		con = DBMan.getConnection();
		String sql = "update board set readcount=readcount+1 where num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
	}

	public ArrayList<ReplyDto> selectReply(int num) {
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		con = DBMan.getConnection();
		String sql = "select * from reply where boardnum=? order by replynum desc";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReplyDto rdto = new ReplyDto();
				rdto.setReplynum(rs.getInt("replynum"));
				rdto.setBoardnum(rs.getInt("boardnum"));
				rdto.setUserid(rs.getString("userid"));
				rdto.setWritedate(rs.getTimestamp("writedate"));
				rdto.setContent(rs.getString("content"));
				list.add(rdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}

		return list;
	}

	public void insertReply(ReplyDto rdto) {
		con = DBMan.getConnection();
		String sql = "insert into reply(replynum,boardnum,userid,content) "
							+ "values(reply_seq.nextVal,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getBoardnum());
			pstmt.setString(2, rdto.getUserid());
			pstmt.setString(3, rdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
	}

	public void deleteReply(String replynum) {
		con = DBMan.getConnection();
		String sql = "delete from reply where replynum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(replynum));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
		
	}

	public void updateBoard(BoardDto bdto) {
		con = DBMan.getConnection();
		String sql = "update  board set userid=?,pass=?,email=?,title=?,content=? where num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getPass());
			pstmt.setString(3, bdto.getEmail());
			pstmt.setString(4, bdto.getTitle());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, bdto.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
	}

	public void deleteBoard(int num) {
		con = DBMan.getConnection();
		String sql = "delete from board where num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
		
	}

	public void deleteReplyByboardnum(int num) {
		con = DBMan.getConnection();
		String sql = "delete from reply where boardnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
		
	}

	public int getAllCount() {
		int count=0;		
		con = DBMan.getConnection();
		String sql = "select count(*) as cnt from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				count=rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBMan.close(con, pstmt, rs);
		}
		return count;
	}

}