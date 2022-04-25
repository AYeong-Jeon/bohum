<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- company/company_infoList.jsp -->

<h2>우리 보험사 정보</h2>
<table border="1">
	<tr>
		<td rowspan="3">
			<input type="hidden" value="${bean.cnum }">
			<img src="<%=request.getContextPath() %>/resources/company/${bean.cimage}" width=100 height=100>
		</td>
	</tr>

	<tr>
		<td>회사명</td>
		<td>${bean.cname }</td>
	</tr>
	
	<tr>
		<td>사업자번호</td>
		<td>${bean.cregi_num }</td>
	</tr>
	
	<tr>
		<td colspan="2"><a href="">보험 리스트</a></td>
	</tr>

</table>
<a href="companyUpdate.cp">정보 수정</a>

