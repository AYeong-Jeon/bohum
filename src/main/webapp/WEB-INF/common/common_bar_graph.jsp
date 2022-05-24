<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
<!-- 차트 js에 연결 -->

<script type="text/javascript">
	var backgroundColorArr = [
        //색상 
        'rgba(54, 162, 235, 1)',
		'rgba(255, 99, 132, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)' ];
	var myColors = new Array(); 
	function myColorsArr(myLabels) {
		//받은 라벨스의 길이만큼 색상을 돌려 씀
		myColors = new Array(); 
		for(var i=0;i<myLabels.length;i++){
			myColors[i] = backgroundColorArr[i%(backgroundColorArr.length)];
		}
	}
	
	function removeBarGraph(myDiv) {
		var reDiv = "#"+myDiv;
		// 내가 작성할 차트의 캔버스 초기화
		// 차트는 캔버스를 통해 작성이 됨
		$(reDiv).html("<div style='position: relative; width:800px; height:500px;  margin: auto;'><canvas id='bar-chart-"+myDiv+"' class='form-control' ></canvas></div>");
	}
	
	function drawBarGraph(myDiv,myLabels,myLabel,myData,myText) {
		removeBarGraph(myDiv);
		new Chart(document.getElementById("bar-chart-"+myDiv), {
			// 위에 괄호 안에 들어가는건 내가 막대 그래프 그릴 곳
		    type: 'bar',// 막대 그래프
		    data: {
		      labels: myLabels,// 밑에서 ajax를 통해 json으로 받아 온 x축 값 아래에 또 나옴
		      datasets: [
		        {
		          label: myLabel, // 막대 그래프 위에 커서 대면 나오는 설명들
		          backgroundColor: myColors, // 위에서  받은 라벨스의 길이만큼 색상을 돌려 씀이라고 한거
		          data: myData // 밑에서 ajax를 통해 json으로 받아 온 y축 값 아래에 또 나옴
		        }
		      ]
		    },
		    options: {
		      legend: { display: false },
		      title: {
		        display: true,
		        text: myText
		      },
		      scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						}
					}]
				},
		    }
		});
	}

	function getBarData(myDiv,myUrl,myLabel,myText) {
		$.ajax({
			url : myUrl,// 내가 요청할 컨트롤러의 url
		    dataType : "json", // json타입을 반환받는다.
		    contentType: "application/json; charset=utf-8", // utf-8처리
		    success : function(data) {		    	
		    	$.each(data, function(index, item) { // 데이터 =item
					myLabels = item.myLabels;		// json으로 받은 item에 myLabels추출
					myData = item.myData;			// json으로 받은 item에 myData추출
					myColorsArr(myLabels);			// 이건 위에서 색상 돌린거
					drawBarGraph(myDiv,myLabels, myLabel, myData,myText);
				});
		    },//success
			error : function(data) {
				alert('error! : ' + data.value);
			}//success
		});
	}
	
</script>

