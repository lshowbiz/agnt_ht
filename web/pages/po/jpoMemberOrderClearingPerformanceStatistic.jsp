<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript">
/*
	function checkPeriod(strAction){
		
		if(strAction == 'leaderTeamBonus'){
			var period = document.getElementById("formatedWeek").value;
			//考虑年份从1900-2099这个范围的日期
			//var parttern = /((19|20)\d{2})(0[1-9]|1[0-2])/;
			var parttern = new RegExp("^((19|20)\\d{2})(0[1-9]|1[0-2])$","g");
			if(period)		//(period != undefined && period != null && period != '')
			{
				if(parttern.test(period)){
					return true;
				}else{
					alert("请输入正确的期别格式:XXXX（年）XX（月）!例如:（201601）");
					document.getElementById("formatedWeek").value = '';
					document.getElementById("formatedWeek").focus();
					return false;
				}
			}else{
				alert("期别不能为空，请输入正确的期别格式:XXXX（年）XX（月）!例如:（201601）");
				document.getElementById("formatedWeek").value = '';
				document.getElementById("formatedWeek").focus();
				return false;
			}
		}else{
			return true;
		}
		
	}
	*/
</script>



<form action="jpoMemberOrderClearingPerformanceStatistic.html" method="get" name="searchForm" id="searchForm">
	<input type="hidden" id="strAction" name="strAction" value="${strAction}"/> 
	<input type="hidden" id="flag" name="flag" value="Y"/> 
	<!-- 2.1~2.9  2015-07-16 -->
	
	<c:choose>	
		<c:when test="${strAction=='leaderNetClearingPerformance' || strAction=='companyClearingPerformanceQuery' }">
			<table class="detail" width="70%">
			<tbody class="window" >
					<tr class="edit_tr">

						<th><span class="text-font-style-tc"><jecs:label key="common.startTime" /></span></th>
						<td>
						<span class="textbox"><input name="startDate" id="startDate" type="text" value="${param.startDate }" style="cursor: pointer;" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/></span>
							
						</td>
						<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime" /></span></th>
						<td>
							<span class="textbox"><input name="endDate" id="endDate" type="text" value="${param.endDate }" style="cursor: pointer;" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/></span>
						</td>
					</tr>
					
					<tr>		
						<td class="command" colspan="4" align="center">
							<button type="button" name="button1" class="btn btn_sele" onclick="queryReport2(document.searchForm,'${strAction}')" /><jecs:locale key="button.report"/></button>
						</td>
					</tr>
				
				</tbody>
			</table>
		</c:when>	
			
		<c:when test="${strAction=='leaderTeamBonus'}">
			<table class="detail" width="70%">
				<tbody class="window" >
				<tr>
					<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wweek" />(<jecs:locale key="bdPeriod.fweek"/>):</span></th>
					<td>
						<span class="textbox">
						<input type="text" name="formatedWeek" id="formatedWeek" size="6" maxlength="6" style="ime-mode:disabled;" class="textbox-text"
								onpaste="return false;"
								onkeypress="if(event.keyCode < 48 || event.keyCode > 57) {event.returnValue = false;}"
								oninput="this.value=this.value.replace(/\D/g,'');"/>
						</span>
						<jecs:locale key="label.example"/>201601
					</td>
				</tr>
				<tr>		
						<td class="command" colspan="4" align="center">
							<button type="button" name="button1" class="btn btn_sele" onclick="queryReport2(document.searchForm,'${strAction}')" /><jecs:locale key="button.report"/></button>
						</td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			empty!
		</c:otherwise>
		
	</c:choose>
<%-- 	<font onclick="cancel();" color="blue" style="cursor: pointer;"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></font>	--%>
</form>
<script type="text/javascript">
/*
     function queryReport(theForm,strActionValue){
     		if(strActionValue=='zhongcenum' || strActionValue=='province'){
	     		var query_yearMonth = document.getElementById("query_yearMonth");
	     		if(query_yearMonth.value==null || query_yearMonth.value=='' || query_yearMonth.value.length!=6){
	     			alert("<jecs:locale key='operation.notice.yearmonth'/>");
	     			return false;
	     		}
     		}
     		 waiting();
             theForm.strAction.value=strActionValue;
     		 theForm.submit();
     }
     */
function queryReport2(theForm,strActionValue){
			/**var netType = document.getElementById("netType");
			if(strActionValue=='netLead' || strActionValue=='areaLead' || strActionValue=='recommendLead'){
				if(netType.value==null || netType.value==''){
					alert("<jecs:locale key='amMessage.discuss.select'/><jecs:locale key='miMember.netType'/>");
				}
			}**/

     		if(strActionValue=='leaderNetClearingPerformance' || strActionValue=='companyClearingPerformanceQuery'){
	     		var startDate = document.getElementById("startDate");
	     		var endDate = document.getElementById("endDate");
	     		//两个日期
	     		//var date1 = '2013-03-26';
	     		//var date2 = '2011-01-10';
	     		// 拆分年月日
	     		var date1 = startDate.value;
	     		var date2 = endDate.value;
	     		date1 = date1.split('-');
	     		// 得到月数
	     		date1 = parseInt(date1[0]) * 12 + parseInt(date1[1]);
	     		// 拆分年月日
	     		date2 = date2.split('-');
	     		// 得到月数
	     		date2 = parseInt(date2[0]) * 12 + parseInt(date2[1]);
	     		var m = Math.abs(date1 - date2);
	     		
	     		if( (startDate.value==null || startDate.value=='' || endDate.value==null || endDate.value=='')){
	     			alert("<jecs:locale key='please.input.search.condition'/>");
	     			return false;
	     		}
	     		
	     		if(m > 1){
	     			alert("<jecs:locale key='maximum.time.interval.over.one.month'/>");
	     			startDate.value = '';
	     			endDate.value = '';
	     			return false;
	     		}
     		}
			if(strActionValue=='leaderTeamBonus'){
				var formatedWeek = document.getElementById("formatedWeek");
				if(formatedWeek.value==null || formatedWeek.value==''){
					alert("<jecs:locale key='please.input.search.condition'/>");
	     			return false;
				}else{
					var period = document.getElementById("formatedWeek").value;
					//考虑年份从1900-2099这个范围的日期
					//var parttern = /((19|20)\d{2})(0[1-9]|1[0-2])/;
					var parttern = new RegExp("^((19|20)\\d{2})(0[1-9]|1[0-2])$","g");		
					if(!parttern.test(period)){
						alert("<jecs:locale key='please.input.correct.period.layout'/>");
						document.getElementById("formatedWeek").value = '';
						document.getElementById("formatedWeek").focus();
						return false;
					}
				}
			}
     		// waiting();
             theForm.strAction.value=strActionValue;
     		 theForm.submit();
     }

/*
     function cancel(){
     	waitingEnd();
     }
*/     
</script>
