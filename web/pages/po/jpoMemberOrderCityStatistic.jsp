<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jpoMemberOrderCityStatistic.html" method="get" name="searchForm" id="searchForm">

	<table class='detail' width="70%">
<tbody class="window" >
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"> <jecs:label  key="fiBillAccount.province"/></span></th>
			
			<td>
				<span class="textbox">
				<select name="province" id="province" class="textbox-text">
				<c:forEach items="${province }" var="pr">
					<option value="${pr.stateProvinceId }">${pr.stateProvinceName }</option>
				</c:forEach>
				</select>
				</span>
			</td>
		</tr>
		
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"> <jecs:label  key="common.startTime"/></span></th>
			<td>
				<span class="textbox">
					<input id="createBTime" name="createBTime" type="text" size="10" maxlength="10" value="${param.createBTime }" class="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</span>
			</td>
			
			
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td>
				<span class="textbox">
					<input id="createETime" name="createETime" type="text" size="10" maxlength="10" value="${param.createETime }" class="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</span>
			</td>
		</tr>
		<tr>		
			<td class="command" colspan="4" align="center">
				<button name="search" class="btn btn_sele" type="submit" value="<jecs:locale key="button.report"/>"><jecs:locale key="button.report"/></button>
			</td>
		</tr>
	</tbody>
	</table>
</form>