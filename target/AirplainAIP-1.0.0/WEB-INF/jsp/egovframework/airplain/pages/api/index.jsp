<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
			function apiGet(){
				let tempData = {}
				$.ajax({
					type		: "POST",
					url			: "getAirPlainList.do",
					data		: tempData,
					contentType	: "application/json; charset=utf-8;",
					dataType	: "json",
					success		: function(data){
						console.log(data);
					},
					error		: function(err){
						console.log(err);
					}
				})
			}
		</script>
	</head>
	<body>
	
		<div>
			<input type="button" id="apiGetBtn" value="apiGetBtn" onclick="apiGet()" />
		</div>
	
	</body>
</html>