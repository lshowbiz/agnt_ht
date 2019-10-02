<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jpoMemberOrderAreaWeekStatistic.html" method="get" name="searchForm" id="searchForm">
<table class="detail" width="70%">
<tbody class="window" >
<input type="hidden" id="strAction" name="strAction" value="${strAction}"/> 
<input type="hidden" id="flag" name="flag" value="Y"/> 
<!--
<jecs:label key="report.type"/>
<jecs:list name="type" listCode="order.report.type" value="${param.type}" defaultValue="1"/>-->
	<c:choose>
		<c:when test="${strAction=='zhongcenum'}">
			
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label key="bdPeriod.fmonth"/></span></th>
				<td >
					<span class="textbox">
					<input name="yearMonth" type="text" id="query_yearMonth" value="${defaultYearMonth }" 
					size="6" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6" class="textbox-text"/>
					</span>
					(<jecs:label key="label.example"/>201501)	
				</td>
			</tr>
			<tr>		
				<td class="command" colspan="4" align="center">
					<button name="search" class="btn btn_sele" type="button" onclick="queryReport(document.searchForm,'zhongcenum')"><jecs:locale key="button.report"/></button>
					
					<button name="search" class="btn btn_sele" type="button" onclick="cancel();"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></button>
				</td>
			</tr>
			
			
		</c:when>
		
		
		<c:when test="${strAction=='dayPerformance'}">
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label key="common.startTime" /></span></th>
				<td >
					<span class="textbox">
						<input name="startLogTime" id="startLogTime" type="text" value="${startLogTime }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>
					</span>
				</td>
				<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime" /></span></th>
				<td >
					<span class="textbox">
						<input name="endLogTime" id="endLogTime" type="text" value="${endLogTime }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>
					</span>
				</td>
			</tr>
			
			<tr>		
				<td class="command" colspan="4" align="center">
					<button name="search" class="btn btn_sele" type="button" onclick="queryReport(document.searchForm,'dayPerformance')"><jecs:locale key="button.report"/></button>
					<button name="search" class="btn btn_sele" type="button" onclick="cancel();"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></button>
				</td>
			</tr>
		</c:when>
		<c:when test="${strAction=='province'}">
		
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label key="bdPeriod.fmonth"/></span></th>
				<td >
					<span class="textbox">
						<input name="yearMonth" type="text" id="query_yearMonth" value="${defaultYearMonth }" size="6" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6" class="textbox-text"/>(<jecs:label key="label.example"/>201501)
					</span>
				</td>
			</tr>
			<tr>		
				<td class="command" colspan="4" align="center">
					<button name="search" class="btn btn_sele" type="button" onclick="queryReport(document.searchForm,'province')"><jecs:locale key="button.report"/></button>
					<button name="search" class="btn btn_sele" type="button" onclick="cancel();"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></button>
				</td>
			</tr>
		</c:when>
		<c:when test="${strAction=='netLead' || strAction=='areaLead' || strAction=='recommendLead' || strAction=='bigJpomemberorder' || strAction=='huicuiProduct' || strAction=='yunnanchongxiao' || strAction=='daoheWineJpomemberorder' || strAction=='daoheWineJprrefund' || strAction=='jponetfee' || strAction=='shiyetilingdaoshoudan'}">
			<!-- 2.1~2.9  2015-07-16 -->
			<div class="searchBar">
				
			</div>
			<table class="detail" width="70%">
				<tbody class="window" >
				
					<tr class="edit_tr">
						<c:if test="${strAction=='netLead' || strAction=='areaLead' || strAction=='recommendLead'}">
						<th><span class="text-font-style-tc"><jecs:label key="date.type"/></span></th>
						<td>
							<span class="textbox">
							<jecs:list name="dateType" id="dateType" listCode="date.type" value="${param.dateType }" defaultValue="" styleClass="textbox-text"/>
							</span>
						</td>
						</c:if>
						<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wweek" />:</span></th>
						<td>
							<span class="textbox"><input type="text" name="formatedWeek" id="formatedWeek" size="8" class="textbox-text"/></span>
							<jecs:locale key="label.example"/>201502
						</td>
					</tr>
				
				<tr class="edit_tr">
					
					<th><span class="text-font-style-tc"><jecs:locale key="common.startTime" />:</span></th>
					<td>
			
					<span class="textbox"><input name="startDate" id="startDate" type="text" value="${param.startDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  class="textbox-text"/></span>
					</td>
					<th><span class="text-font-style-tc"><jecs:locale key="schedule.endTime" />:</span></th>
					<td>
					<span class="textbox"><input name="endDate" id="endDate" type="text" value="${param.endDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  class="textbox-text"/>	</span>
					</td>
				</tr>
				
				<tr class="edit_tr">
					<th><span class="text-font-style-tc"><jecs:locale key="bdPeriod.wyear" />:</span></th>
					<td>
						<span class="textbox"><input type="text" name="formatedYear" id="formatedYear" size="8" value="${param.formatedYear }" class="textbox-text"/></span>
					</td>
					<th><span class="text-font-style-tc"><jecs:locale key="bdPeriod.wmonth" />:</span></th>
					<td>
						<span class="textbox"><input type="text" name="formatedMonth" id="formatedMonth" size="8" value="${param.formatedMonth }" class="textbox-text"/></span>
					</td>
				</tr>
				<tr>		
					<td class="command" colspan="4" align="center">
						<button name="search" class="btn btn_sele" type="button" onclick="queryReport2(document.searchForm,'${strAction}')"><jecs:locale key="button.report"/></button>
						<button name="search" class="btn btn_sele" type="button" onclick="cancel();"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></button>
					</td>
				</tr>
				</tbody>
			</table>
			
		</c:when>
		<c:when test="${strAction=='batchMobileSend'}">
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label key="sysUser.mobiletele"/></span></th>
				<td>
					<span class="textbox"><input name="mobiles" type="text" id="mobiles" class="textbox-text"/></span>
				</td>
			</tr>
			<tr>		
				<td class="command" colspan="4" align="center">
					<button name="search" class="btn btn_sele" type="button" onclick="queryReport(document.searchForm,'batchMobileSend')"><jecs:locale key="operation.button.send"/></button>
					<button name="search" class="btn btn_sele" type="button" onclick="cancel();"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></button>
				</td>
			</tr>
			
		</c:when>
		<c:otherwise>
			empty!
		</c:otherwise>
	</c:choose>
</tbody>
</table>
</form>
<script type="text/javascript">
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
     
function queryReport2(theForm,strActionValue){
			/**var netType = document.getElementById("netType");
			if(strActionValue=='netLead' || strActionValue=='areaLead' || strActionValue=='recommendLead'){
				if(netType.value==null || netType.value==''){
					alert("<jecs:locale key='amMessage.discuss.select'/><jecs:locale key='miMember.netType'/>");
				}
			}**/

     		if(strActionValue=='netLead' || strActionValue=='areaLead' || strActionValue=='recommendLead' || strActionValue=='bigJpomemberorder' || strActionValue=='huicuiProduct' || strActionValue=='yunnanchongxiao' || strActionValue=='daoheWineJpomemberorder' || strActionValue=='daoheWineJprrefund' || strActionValue=='jponetfee' || strActionValue=='shiyetilingdaoshoudan'){
	     		var startDate = document.getElementById("startDate");
	     		var endDate = document.getElementById("endDate");
	     		var formatedWeek = document.getElementById("formatedWeek");
	     		var formatedYear = document.getElementById("formatedYear");
	     		var formatedMonth = document.getElementById("formatedMonth");
	     		if( (startDate.value==null || startDate.value=='' ||
	     		    endDate.value==null || endDate.value=='') &&
	     		    (formatedWeek.value==null || formatedWeek.value=='') &&
	     		    (formatedYear.value==null || formatedYear.value=='') &&
	     		    (formatedMonth.value==null || formatedMonth.value=='') ){
	     			alert("<jecs:locale key='please.input.search.condition'/>");
	     			return false;
	     		}
     		}
     		 waiting();
             theForm.strAction.value=strActionValue;
     		 theForm.submit();
     }


     function cancel(){
     	waitingEnd();
     }
</script>