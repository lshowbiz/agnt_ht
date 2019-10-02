<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript"> 
		function butExe(){
		var createBTime = document.getElementById('createBTime').value;
		var createETime = document.getElementById('createETime').value;
		
		if(null==createBTime || createBTime==''){
			alert('<jecs:locale key="bdPeriod.startTime.required"/>');
			return;
		}
		if(null==createETime || createETime==''){
			alert('<jecs:locale key="bdPeriod.endTime.required"/>');
			return;
		}
		document.getElementById('searchForm').submit();
	}
</script> 
<form action="jpoMemberOrderProductWeekStatistic.html" method="get" name="searchForm" id="searchForm">

<table class="detail" width="70%">
<input type="hidden" name="search" id="search" value="search"/>
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="report.type"/></span></th>
			<td >
				<span class="textbox">
				<jecs:list name="type" listCode="product.report.type" value="${param.type}" defaultValue="1" styleClass="textbox-text" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;"/>
				</span>
			</td>
			<th>
			<span class="text-font-style-tc"><jecs:label key="date.type"/></span></th>
			<td >
				<span class="textbox">
				 <jecs:list name="dateType" id="dateType" listCode="date.type" value="${param.dateType }" defaultValue="" styleClass="textbox-text"/>
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label key="common.startTime"/></span></th>
			<td >
				<span class="textbox">
				 <input name="createBTime" id="createBTime" type="text" value="${param.createBTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td >
				<span class="textbox">
				<input name="createETime" id="createETime" type="text" value="${param.createETime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  class="textbox-text"/>
				</span>
			</td>
		</tr>
		<tr>		
			<td class="command" colspan="4" align="center">
				
				<button name="searchbut" class="btn btn_sele" type="button" onClick="butExe();" value="<jecs:locale key="button.report"/>"><jecs:locale key="button.report"/></button>
			</td>
		</tr>
	</tbody>
</table>
</form>