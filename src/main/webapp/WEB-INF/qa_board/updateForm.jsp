<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/member/top.jsp" %>
<br><br>
<style>
   .table{
      width:80%;
   }
   .err{
      color:red;
   }   
</style>

<%    String[] categoryArr = {"보험질문","고객의소리"};
%>
<c:set value="<%=categoryArr %>" var="categoryArr"/>

<center>
<div class="container">
<form:form method="post" commandName="QA_BoardBean" enctype="multipart/form-data" action="update.qa">
<input type="hidden" name="pageNumber" value="${pageNumber }">
<input type="hidden" name="no" value="${QA_BoardBean.no }">

<table class="table table-bordered">
   <tr> 
      <td align="center" class="table-light">작성자</td>
      <td>
         <input type="text" name="writer" class="form-control" value="${ loginInfo.id }" readonly>
         <form:errors path="writer" cssClass="err" />
      </td>
   </tr>
   <tr>
      <td align="center" class="table-light">카테고리</td>
      <td>
         <select name="category" class="form-select">
         <c:forEach var="category" items="${categoryArr }" varStatus="status">
            <option value="${category }"<c:if test="${category==QA_BoardBean.category }">selected</c:if>>${category }</option>
         </c:forEach>
         </select>
         <form:errors path="category" cssClass="err" />
      </td>
   </tr>
   <tr>
      <td align="center" class="table-light">보험사</td>
      <td>
         <select name="company" class="form-select">
            <option value="">보험사 선택</option>
         <c:forEach var="company" items="${companyList }" varStatus="status">
            <option value="${company.cname }"<c:if test="${company.cname==QA_BoardBean.company }">selected</c:if>>${company.cname }</option>
         </c:forEach>
         </select>
         <form:errors path="company" cssClass="err" />
      </td>
   </tr>   
   <tr>
      <td align="center" class="table-light">제목</td>
      <td>
         <input type="text" name="title" class="form-control" value="${ QA_BoardBean.title }">
         <form:errors path="title" cssClass="err"/>
      </td>
   </tr>
   <tr>
      <td align="center" class="table-light">내용</td>
      <td>
         <textarea name="content" class="form-control" rows="10" cols="40" style="resize:none">${ QA_BoardBean.content }</textarea>
         <form:errors path="content" cssClass="err" />
      </td>
   </tr>
   <tr>
      <td align="center" class="table-light">첨부파일</td>
      <td>
         <input type="file" name="upload" class="form-control">
         <input type="hidden" name="filename" value="${QA_BoardBean.filename }">
      </td>
   </tr>
   <tr>
      <td align="center" class="table-light">비밀번호</td>
      <td>
         <input type="password" name="password" class="form-control">
         <form:errors path="password" cssClass="err" />
      </td>
   </tr>
   <tr>
   <td class="table-light"></td>
      <td  align="center" class="table-light">
         <input type="submit" value="글수정" class="btn btn-success"> 
         <input type="reset" value="다시작성" class="btn btn-success"> 
         <input type="button" value="글목록" class="btn btn-success" onClick="location.href='list.qa?pageNumber=${pageNumber}'">
      </td>
   </tr>
</table>
</form:form>
</div>
</center>
<%@include file="/WEB-INF/member/bottom.jsp" %>