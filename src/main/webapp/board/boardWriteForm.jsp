<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--반드시 로그인이 되어있는 상태로 이용해야하는 페이지만 추가해주면 되는 옵션 _ 주석처리한 이유은 action페이지로
<c:if test="${empty loginUser}">
	<jsp:forward page='../board.do?command=loginForm'/>
</c:if>
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWriteForm.jsp</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script src="script/board.js"></script>
</head>
<body>

<div id="wrap" align="center"></div>
	<h1>게시글 등록</h1>' * '표시 항목은 필수 입력 항목입니다.
	<form action="board.do" method="post" name="frm">
		<input type="hidden" name="command" value="boardWrite"/>
	<table>
		<tr><th>작성자</th><td>${loginUser.userid}
			<input type="hidden" name="userid" value="${loginUser.userid}"></td></tr>
		<tr><th>비밀번호</th><td>
			<input type="password" name="pass">&nbsp;* (게시물 수정 삭제시 필요)</td></tr>
		<tr><th>이메일</th><td>
			<input type="text" name="email" value="${loginUser.email}"></td></tr>
		<tr><th>제목</th>
		<td><input type="text" name="title" size="70">&nbsp;* </td></tr>
		<tr><th>내용</th><td>
		<textarea cols="70" rows="15" name="content"></textarea>&nbsp;*</td></tr>
		</table>
	<input type="submit" value="등록"onClick="return boardCheck()"/>
	<input type="reset" value="다시 작성">
	<input type="button" value="목록으로" onClick="location href='board.do?command=main'">
</form>


</body>
</html>