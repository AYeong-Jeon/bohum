<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@include file="/WEB-INF/top_bottom/main_top.jsp"%> --%>
<%@include file="/WEB-INF/homeAdmin/top.jsp"%>
<%@include file="/WEB-INF/common/common_bar_graph.jsp"%>
<%@include file="/WEB-INF/common/common_line_graph.jsp"%>
<%@include file="/WEB-INF/common/common_pie_graph.jsp"%>



<main class="container">

<br>


<h1>회사 그래프</h1>

<input type="button" class="btn btn-outline-primary" value="나이대별 보험 갯수"
	onclick="getBarData('companyDiv','ageNBohum.gp','보험 갯수','나이대 별 보험 갯수')">
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="button" class="btn btn-outline-primary" value="보험별 특약 갯수"
	onclick="getBarData('companyDiv','mogNBohum.gp','보험 갯수','보험 별 특약 갯수')">
<br><br>

<input type="button" class="btn btn-outline-primary" value="회사별  보험 갯수"
	onclick="getBarData('companyDiv','companyNinsu.gp','보험 갯수','회사별 보험 갯수')">
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="button" class="btn btn-outline-primary" value="카테고리별  보험 갯수"
	onclick="getBarData('companyDiv','categoryNinsu.gp','보험 갯수','회사별 보험 갯수')">
<div id="companyDiv">
	<div style='position: relative; width:400px; height:500px;'>
		<canvas id="bar-chart-companyDiv" width="100%" height="500px"></canvas>
	</div>
</div>
<h1>일반 회원 그래프</h1>
<input type="button" class="btn btn-outline-primary" value="유저가 찜한 보험 갯수"
	onclick="getBarData('memberDiv','insunameNHeart.gp','보험 갯수','유저가 찜한 보험 갯수')">
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="button" class="btn btn-outline-primary" value="회원가입한 남여 비율"
	onclick="getPieData('memberDiv','gender.gp','남/여','남여 성별 비율')">

<div id="memberDiv" >
	<div style='position: relative; width:1000px; height:500px;'>
		<canvas id="bar-chart-memberDiv" ></canvas>
	</div>
</div>
<br><br>



<br>
</main>
<%@include file="/WEB-INF/homeAdmin/bottom.jsp"%>

<%-- <%@include file="/WEB-INF/top_bottom/main_bottom.jsp"%> --%>