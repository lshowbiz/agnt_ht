<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="aiAgent.title" /></title>
<content tag="heading">
<jecs:locale key="aiAgent.heading" />
</content>
<meta name="menu" content="AiAgentMenu" />

<link rel="stylesheet" href="styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="scripts/calendar/lang.jsp"> </script> 

<form name="formAiFeat" method="post" action="jpoRecommendStatistic.html" >
	<table class="detail" width="100%">
		<tr>
			<th><label><jecs:locale key="saFiIncomeReport.dataSection"/></label></th>
			<td>
				<input id="createBTime" name="createBTime" type="text" size="10" maxlength="10" value="${param.createBTime }"/>
				<img src="images/calendar/calendar7.gif" id="img_startOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createBTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startOperatorTime", 
					singleClick    :    true
					}); 
				</script> - 
				<input id="createETime" name="createETime" type="text" size="10" maxlength="10" value="${param.createETime }"/>
				<img src="images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createETime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endOperatorTime", 
					singleClick    :    true
					}); 
				</script>
				<input type="submit" name="submit" onclick="javascript:this.form.action='jpoRecommendStatistic.html'" value="<jecs:locale key="operation.button.create"/>" class="button"/>
			</td>
		</tr>
	</table>
</form>