<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>   
<%@ include file="top.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select your 보험(회사 페이지)</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="form-validation.css" rel="stylesheet">
</head>
<br><br><br>
<center>
<body class="bg-light">
<br>
<div class="container">
<form action="list.bh">
	<input type="submit" class="btn btn-outline-success" value="검색" style="float: right; height: 30px;">
	<input type="text" class="form-control" name="keyword" style="width:200px; height: 30px; float: right">
	<select class="form-select form-select-sm" aria-label=".form-select-sm example" name="whatColumn" style="width: 105px; float: right">
		<option name="all">전체 검색</option>
		<option name="bname">보험명</option>
		<option name="bcate">카테고리</option>
	</select>
<br><br>
<table class="table table-hover">
<caption>(로그인한 회사 이름) 총 ${total}건</caption>
  <thead>
    <tr align="center">
      <th scope="col">번호</th>
      <th scope="col">보험명</th>
      <th scope="col">카테고리</th>
      <th scope="col">보험 타입</th>
      <th scope="col">보험료</th>
      <th scope="col">가입 나이</th>
      <th scope="col">보험 기간</th>
      <th scope="col">납입 기간</th>
      <th scope="col">납입 주기</th>
      <th scope="col">특약 가입</th>
    </tr>
  </thead>
  <tbody align="center">
<c:forEach var="bh" items="${lists }">
    <tr align="center">
      <th scope="row">${bh.insu }</th>
      <td>${bh.insuname }</td>
      <td>${bh.insucate }</td>
      <td>${bh.insutype }</td>
      <%
      String imgPath = request.getContextPath()+"/resources/";
      %>
      <td><img src="<%=imgPath %>${bh.insuprice }" width="200" height="100"></td>
      <td>${bh.insuage }</td>
      <td>${bh.insuper }</td>
      <td>${bh.payper }</td>
      <td>${bh.paycyc }</td>
      <td>${bh.spccont }</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
${pageInfo.pagingHtml }
</div>
</center>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
 <br><br><br><br>
    
<%@ include file="bottom.jsp"%>