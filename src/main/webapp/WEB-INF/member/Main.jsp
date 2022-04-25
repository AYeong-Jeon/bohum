<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>


<main class="container">
<img src="<%=request.getContextPath() %>/resources/dd.jpg" class="img-fluid" >
      <span class="img-text"><h1 style="color: white">늘어나는 의료비 부담, 이제 걱정하지 마세요.</h1>
     <a href="bohumChoochun.bh" style="color: #22741C"><h4>나에게 꼭 맞는 보험 추천</h4></a></span>
      
  <div class="row mb-2">
    <div class="col-md-6">
      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <strong class="d-inline-block mb-2 text-primary">공지 사항</strong>
          <h3 class="mb-0">[공지]회원가입 안내</h3>
          <div class="mb-1 text-muted">2022-04-06</div>
          <p class="card-text mb-auto">회원 가입이 일시 중단 되었습니다. 가입을 원하시는 분은...</p>
          <a href="list.bd" class="stretched-link">더보기</a>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <strong class="d-inline-block mb-2 text-success">Q & A</strong>
          <h3 class="mb-0">실비보험 가입 팁</h3>
          <div class="mb-1 text-muted">2022-04-01</div>
          <p class="mb-auto">입원/통원 의료비 보장에서 비급여 진료항목은 제외되므로 ...</p>
          <a href="list.qa" class="stretched-link">더보기</a>
        </div>
      </div>
    </div>
  </div>

  <div class="row g-5">
    <div class="col-md-8">
      <h3 class="pb-4 mb-4 border-bottom">
        보험 상품 전체보기
      </h3>
      
      <article class="blog-post">
		<div class="table-responsive">
		  <table class="table align-middle">
		    <thead>
		      <tr align="center">
		        <th>번호</th>
		        <th>보험명</th>
		        <th>카테고리</th>  
		        <th>타입</th>
		        <th>보험료</th> 
		        <th>가입 나이</th>  
		        <th>보험 기간</th>
		        <th>납입 기간</th>
		        <th>납입 주기</th>
		      </tr>
		    </thead>  
		    <tbody>
				<c:forEach var="bh" items="${lists }">
				    <tr align="center">
				      <td scope="row">${bh.insu }</td>
				      <td><a href="http://${bh.link }">${bh.insuname }</a></td>
				      <td>${bh.category }</td>
				      <td>${bh.insutype }</td>
				      <%
				      String imgPath = request.getContextPath()+"/resources/insuprice/";
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
		</div>
	</article>
	
 <div class="col-md-4">
      <div class="position-sticky" style="top: 2rem;">
      </div>
    </div>
  </div>

 <div class="col-md-4">
      <div class="position-sticky" style="top: 2rem;">
        <div class="p-4 mb-3 bg-light rounded">
          <h4 class="fst">About</h4>
          <p class="mb-0">[select your 보험]에서는 각 보험 회사 상품에 대한 비교 정보만을 제공하며, 보험 계약을 체결하기 전에 상품 설명서 및 약관을 반드시 읽어보시기 바랍니다.</p>
        </div>

        <div class="p-4">
          <h4 class="fst">바로 가기</h4>
          <ol class="list-unstyled mb-0">
            <li><a href="bohumChoochun.bh">맞춤 추천 보험</a></li>
            <li><a href="list.ht">내 관심보험 보기</a></li>
            <li><a href="list.qa">보험 가입 팁</a></li>
            <li><a href="#">위로 가기</a></li>
          </ol>
        </div>

        <div class="p-4">
          <h4 class="fst">더 알아보기</h4>
          <ol class="list-unstyled">
            <li><a href="https://www.youtube.com">YouTube</a></li>
            <li><a href="https://www.twitter.com">Twitter</a></li>
            <li><a href="https://www.facebook.com">Facebook</a></li>
          </ol>
        </div>
      </div>
    </div>
  </div>


</main>

<div class="b-example-divider"></div>

<%@include file="bottom.jsp"%>