<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">
	<input type="button" name="button" value="<jecs:locale key="pd.out"/>"  onclick="exportReport('showOutDetailReport')" class="button"/>
	<input type="button" name="button" value="<jecs:locale key="pd.return"/>"  onclick="exportReport('showReturnDetailReport')" class="button"/>
	<input type="button" name="button" value="<jecs:locale key="pd.enter"/>"  onclick="exportReport('showEnterDetailReport')" class="button"/>
	<input type="button" name="button" value="<jecs:locale key="pd.flit"/>"  onclick="exportReport('showFlitDetailReport')" class="button"/>
</c:set>


<form method="post" action="pdDetailReports.html" onsubmit="return validateOthers(this)" id="pdDetailReport">
	<div id="titleBar">
	
	
	
	<input type="hidden" name="strAction" id="strAction" value="${param.strAction}"/>
	<input type="hidden" name="showFlag" id="showFlag" value=""/>

	</div>

<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label key="pd.createTime"/></span></th>
			<td>
				<span class="textbox"  style="width:100px;">
					<input name="startTime" id="startTime" type="text" value="${requestParaMap.startTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>				
				</span>
				- 
				<span class="textbox"  style="width:100px;">
					<input name="okEndTime" id="okEndTime" type="text" value="${requestParaMap.okEndTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  class="textbox-text"/>	
				</span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label key="pd.okTime"/></span></th>
			<td>
				<span class="textbox"  style="width:100px;">
					<input name="okStartTime" id="okStartTime" type="text" value="${requestParaMap.okStartTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>
				</span>
				-
				<span class="textbox"  style="width:100px;">
					<input name="okEndTime" id="okEndTime" type="text" value="${requestParaMap.okEndTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>	
				</span>
			</td>
			
		</tr>
		
					
		<tr>		
			<td class="command" colspan="4" align="center">
				<button name="button" class="btn btn_sele"  onclick="exportReport('showOutDetailReport')" >
					<jecs:locale key="pd.out"/>
				</button>
				<button name="button" class="btn btn_sele"  onclick="exportReport('showReturnDetailReport')" >
					<jecs:locale key="pd.return"/>
				</button>
				<button name="button" class="btn btn_sele"  onclick="exportReport('showEnterDetailReport')" >
					<jecs:locale key="pd.enter"/>
				</button>
				<button name="button" class="btn btn_sele"  onclick="exportReport('showFlitDetailReport')" >
					<jecs:locale key="pd.flit"/>
				</button>
			</td>
		</tr>
	</tbody>
</table>

</form>



<script type="text/javascript">
	
function exportReport(flag){
		$('showFlag').value=flag;
		$('pdDetailReport').submit();
	}

</script>