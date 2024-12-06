<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
			function saveApiData(){
				let tempData = {}
				$.ajax({
					type		: "POST",
					url			: "saveAirPlainList.do",
					data		: tempData,
					dataType	: "json",
					contentType	: "application/json; charset=utf-8;",
					success		: function(data){
						console.log(data.apiDataSaveRst);
					},
					error		: function(err){
						console.log(err);
					}
				});
			}
			
			function getData(){
				let tempData = {}
				$.ajax({
					type		: "POST",
					url			: "getAirPlainList.do",
					data		: tempData,
					dataType	: "json",
					contentType	: "application/json; charset=utf-8;",
					success		: function(data){
						const resultData = data.result;
						resultData.forEach( function(mapData){
							console.log("slctnYear : " + typeof mapData.slctnYear);
							console.log("ctrdCode : " + typeof mapData.ctrdCode);
							console.log("ctrdNm : " + typeof mapData.ctrdNm);
							console.log("signguCode : " + typeof mapData.signguCode);
							console.log("signguNm : " + typeof mapData.signguNm);
							console.log("vilageNm : " + typeof mapData.vilageNm);
							console.log("vilageDetailAdres : " + typeof mapData.vilageDetailAdres);
						});
					},
					error		: function(err){
						console.log(err);
					}
				});
			}
			
			function goScheduleManage(){
				document.getElementById("scheduleManage").action = "/scheduleManage.do";
				document.getElementById("scheduleManage").method = "GET";
				document.getElementById("scheduleManage").submit();
			}
		</script>
	</head>
	<body>
	
		<div>
			<input type="button" id="saveApiData" value="saveApiData" onclick="saveApiData()" />
			<input type="button" id="getData" value="getData" onclick="getData()" />
			<input type="button" id="goScheduleManage" value="goScheduleManage" onclick="goScheduleManage()" />
		</div>
		
		<form id="scheduleManage"></form>
	
	</body>
</html>