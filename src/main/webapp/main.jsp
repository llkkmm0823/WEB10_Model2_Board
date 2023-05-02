<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:if test="${emptyloginUser}">
	<jsp:forward page="board.do?command=loginForm"></jsp:forward>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script src="script/board.js"></script>

</head>
<body>

	<div id="wrap" align="center">
		<h1>게시글 리스트</h1>
		<table>
			<tr>
				<td colspan="5" style="border: white;">
					<div style="float: left;">${loginUser.name}(${loginUser.userid})님 로그인&nbsp;&nbsp;</div> 
					
					<input type="button" value="회원정보수정"
					onClick="location.href='board.do?command=updateMemberForm'"/> 
					<input type="button" value="로그아웃"
					onClick="location.href='board.do?command=logout'"/>
					
					<div style="float: right;">
						<a href="board.do?command=boardWriteForm">게시글 등록</a>
					</div>
				</td>
			</tr>

			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${bList}" var="board">
				<tr align="center">
					<td width="100">${board.num}</td>
					<td align="left">
						<a href="board.do?command=boardView&num=${board.num}">
						${board.title}
					</a>
					</td>
					<td width="100">${board.userid}</td>
					<td width="200"><fmt:formatDate value="${board.writedate}"/></td>
					<td width="100">${board.readcount}</td>
				</tr>
			</c:forEach>
		</table><br><br>
		<!--여기서부터 페이지 표시-->
	<div id="paging" style="margin 0 auto; font-size:110%; font-weight:bold">
		<!--페이지가 클릭될때마다 이동할 링크 기본 경로를 JSTL변수에 저장  -->
		<c:url var="action" value="board.do?command=main"/>
		
		<c:if test="${paging.prev}">
			<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
		</c:if>
		<!--링크되는 주소 -> board.do?command=main&page=??-->
		<!--맨 왼쪽 페이지 (beginPage보다 1페이지 작은 페이지)로 이동-->
		
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
		
		<!--현재 페이지와 페이지 수가 같으면 []안에, 그렇지 않으면 숫자만 표시  -->
			<c:choose>
				<c:when test="${index==paging.page}">[${index}]&nbsp;</c:when>
				<c:otherwise><a href="${action}&page=${index}">${index}&nbsp;</a></c:otherwise>
			</c:choose>
		</c:forEach>
		
		<!--next표시 : 전달된 paging의 next변수가 true이면 표시, false이면 표시하지 않음  -->
		<c:if test="${paging.next}">
			<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
		</c:if>
		<!--맨 오른쪽 페이지(endPage보다 1페이지 큰 페이지로 이동  -->
	</div>	
</div>
</body>
</html>