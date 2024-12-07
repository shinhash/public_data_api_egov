<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="referrer" content="no-referrer-when-downgrade" />
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
		<script>
			
			$(function(){
				$(".schdulInfoChkClass").on("change", function(){
					let schdulCd = $(this)[0].id;
					let useYn = $(this)[0].value;
					
					if(!confirm("해당 스케줄러의 연계여부를 \n수정하시겠습니까?")){
						let schedulRadioBtnId = schdulCd.slice(0, -1);
						let schedulUseYnChk = schdulCd.charAt(schdulCd.length-1);
						if(schedulUseYnChk === "Y"){
							schedulRadioBtnId = "#" + schedulRadioBtnId + "N";
							$(schedulRadioBtnId)[0].checked = true;
						}else if(schedulUseYnChk === "N"){
							schedulRadioBtnId = "#" + schedulRadioBtnId + "Y";
							$(schedulRadioBtnId)[0].checked = true;
						}
						return;
					}
					
					let ajaxUrl = "/saveScheduleInfo.do";
					let ajaxData = {
							"schdulCd": schdulCd.slice(0, -4),
							"useYn": useYn
					};
					fnCmmnAjaxRequest(ajaxUrl, ajaxData);
				});
				$(".schdulAllChkClass").on("change", function(){
					let schdulAllChkId = $(this)[0].id;
					let useYn = $(this)[0].value;
					
					if(!confirm("전체 스케줄러의 연계여부를 \n수정하시겠습니까?")){
						let allSchdulYChecked = $("#allSchdulChkY")[0].checked;
						let allSchdulNChecked = $("#allSchdulChkN")[0].checked;
						if(allSchdulYChecked){
							$("#allSchdulChkY")[0].checked = false;
						}
						if(allSchdulNChecked){
							$("#allSchdulChkN")[0].checked = false;
						}
						return;
					};
					
					let ajaxUrl = "/saveScheduleInfo.do";
					let ajaxData = {
							"schdulCd": "ALL",
							"useYn": useYn
					};
					fnCmmnAjaxRequest(ajaxUrl, ajaxData);
				});
				
				function fnCmmnAjaxRequest(ajaxUrl, ajaxData){
					$.ajax({
						type        : "POST",
				        url         : ajaxUrl,
				        data        : JSON.stringify(ajaxData),
				        contentType	: "application/json; charset=UTF-8",
				        dataType	: "json",
				        beforeSend  : function(){},
				        success     : function (data) {
				        	let saveRstCd = data.saveResultCode;
				        	let saveRstMg = data.saveResultMessage;
				        	alert(saveRstMg);
				        	if(saveRstCd === "SUCCESS"){
				        		document.getElementById("scheduleManage").action = "/scheduleManage.do";
								document.getElementById("scheduleManage").method = "POST";
								document.getElementById("scheduleManage").submit();
				        	}
				        },
				        error       : function (error) {
				        	console.error(error);
				        }
					});
				}
			});
			
			
		</script>
	</head>
	<body>
		<div style="margin-bottom: 30px;"></div>
		<div id="rootHead" style="margin: auto; width: 800px;">
			<div style="margin-left: 88%; width: auto; font-size: 12px;">
				<span>관리자</span>
				<span> | </span>
				<span>LogOut</span>
			</div>
		</div>
		<div id="rootBody">
			<div style="margin-bottom: 50px;"></div>
			<div style="margin: auto; width: 800px;">
				<div style="font-size: 20px; font-weight: bold;">■ 스케줄러 관리</div>
				<div style="margin-bottom: 50px;"></div>
				<div>
					<table style="width: 800px;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 80%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr style="background-color: gray;">
								<td style="text-align: center; font-weight: bold;">번호</td>
								<td style="text-align: center; font-weight: bold;">API 명</td>
								<td style="text-align: center; font-weight: bold;">연계여부</td>
							</tr>
						</thead>
						<tbody>
							<tr style="background-color: gainsboro">
								<td style="text-align: center;">1</td>
								<td>전체 스케줄러</td>
								<td>
									<div style="text-align: center;">
										<input id="allSchdulChkY" type="radio" class="schdulAllChkClass" name="allSchdulChk" value="Y" />
										<label for="allSchdulChkY">Y</label>
										<input id="allSchdulChkN" type="radio" class="schdulAllChkClass" name="allSchdulChk" value="N" />
										<label for="allSchdulChkN">N</label>
									</div>
								</td>
							</tr>
						</tbody> 
					</table>
				</div>
				
				<div style="margin-bottom: 50px;"></div>
				
				<div>총 건수 : ${schedulerList.size()} 건</div>
				<div>
					<table style="width: 800px;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 60%;" />
							<col style="width: 20%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr style="background-color: gray;">
								<td style="text-align: center; font-weight: bold;">번호</td>
								<td style="text-align: center; font-weight: bold;">API 명</td>
								<td style="text-align: center; font-weight: bold;">생성일자</td>
								<td style="text-align: center; font-weight: bold;">연계여부</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${schedulerList.size() gt 0}">
									<c:forEach items="${schedulerList}" var="schedulInfo" varStatus="status">
										<tr style="background-color: gainsboro">
											<td style="text-align: center;">${status.count}</td>
											<td>${schedulInfo.schdulNm}</td>
											<td style="text-align: center;">
												<fmt:formatDate value="${schedulInfo.regDt}" pattern="yyyy-MM-dd"/>
											</td>
											<td style="text-align: center;">
												<div>
													<input id="${schedulInfo.schdulCd}ChkY" type="radio" class="schdulInfoChkClass" name="${schedulInfo.schdulCd}Chk" value="Y"
														<c:if test="${schedulInfo.useYn  eq 'Y'}">checked</c:if>
													/>
													<label for="${schedulInfo.schdulCd}ChkY">Y</label>
													<input id="${schedulInfo.schdulCd}ChkN" type="radio" class="schdulInfoChkClass" name="${schedulInfo.schdulCd}Chk" value="N" 
														<c:if test="${schedulInfo.useYn eq 'N'}">checked</c:if>
													 />
													<label for="${schedulInfo.schdulCd}ChkN">N</label>
												</div>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr style="background-color: gainsboro">
										<td colspan="4" style="text-align: center;">no data</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<form id="dataSendForm"></form>
		<form id="scheduleManage"></form>
	</body>
</html>