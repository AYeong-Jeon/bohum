<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/member/top.jsp" %>
<style>
	.table{
		width:80%
	}
	.table table-hover{
		width:80%
	}
	a {
  		text-decoration: none;
  		color:black;
	}
</style>
		<center>
		<table class="table">
			<tr>
				<c:if test="${userState=='관리자' }">
				<td align="right">
					<input type="button" value="글쓰기" onclick="location.href='insert.bd'" class="btn btn-dark">
				</td>
				</c:if>
			</tr>
		</table>
		</center>	
		<center>
		<table class="table table-hover">
			<tr class="table-dark">
				<td align="center">번호</td>
				<td align="center">제목</td>
				<td align="center">작성일</td>
				<td align="center">조회</td>
			</tr>
			<c:set var="number" value="${number+1 }" />
			<!-- 4=>5 -->

			<c:forEach var="article" items="${boardList }">
				<tr
				<c:if test="${article.fix=='1' }">
					class="table-light"
				</c:if> 
				>
					<td align="center"><c:set var="number"
							value="${number-1 }" /> ${number }</td>
					<td align="center">
						<c:if test="${article.fix=='1' }">
							<a href="content.bd?bnum=${article.bnum}&pageNumber=${pageInfo.pageNumber}"><strong>${article.btitle }</strong></a>
						</c:if>
						<c:if test="${article.fix=='0' }">
							<a href="content.bd?bnum=${article.bnum}&pageNumber=${pageInfo.pageNumber}">${article.btitle }</a>
						</c:if>
					</td>
					<td align="center">
						<fmt:parseDate var="parseDateDay" value="${article.bdate }" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="formatDateDay" value="${parseDateDay }" pattern="yyyy-MM-dd"/>
						${formatDateDay }
					</td>
					<td align="center">${article.breadcount}</td>

				</tr>
			</c:forEach>
		</table>
		 ${pageInfo.pagingHtml}
		 <br><br>
		<form method="get" action="list.bd">
		<table>
		<tr>
			<td>
			<select name="whatColumn">
				<option value="전체검색">전체검색
				<option value="제목">제목
				<option value="내용">내용
			</select>
			<input type="text" name="keyword">
			<input type="submit" value="검색" class="btn btn-dark">
			</td>
		</tr>	
		</table>
		</form>
		 
</center>
<%@include file="/WEB-INF/member/bottom.jsp" %>
