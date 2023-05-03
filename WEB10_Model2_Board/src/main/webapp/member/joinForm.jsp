<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinForm.jsp</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script src="script/board.js"></script>
</head>
<body>
<div id="wrap" align="center"></div>
	<h1>사용자 등록</h1>' * '표시 항목은 필수 입력 항목입니다.
	<form action="board.do" method="post" name="frm">
		<input type="hidden" name="command" value="join">
	<table>
		<tr><th>아이디</th><td><input type="text" name="userid" size="20">&nbsp;*
			<input type="button" value="중복체크" onClick="idCheck();"/>
			<input type="hidden" value="" name="reid"/>
		</td></tr>
		<tr><th>이름</th><td><input type="text" name="name" size="20">&nbsp;*</td></tr>
		<tr><th>비밀번호</th><td><input type="password" name="pwd" size="20">&nbsp;*</td></tr>
		<tr><th>비밀번호 확인</th><td><input type="password" name="pwd_check" size="20"/>&nbsp;*</td></tr>
		<tr><th>이메일</th><td><input type="text" name="email" size="30"></td></tr>
		<tr><th>전화번호</th><td><input type="text" name="phone" size="20"></td></tr>
		<tr><th>등급</th>
			<td><input type="radio" name="admin" value="0" checked="checked">일반회원&nbsp;
				<input type="radio" name="admin" value="1">관리자</td></tr>
		</table><br><br>
		<input type="submit" value="등록"onClick="return joinCheck()"/>
		<input type="reset" value="다시 작성">
		<input type="button" value="로그인페이지로" onClick="location href='board.do?command=loginForm'">
</form>
</body>
</html>