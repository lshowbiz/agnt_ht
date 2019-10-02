<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="exportJfiInvoiceDeposit.html" method="get"
	name="searchForm" id="searchForm">

	<input type="hidden" id="strAction" name="strAction"
		value="${strAction}" />
	<input type="hidden" id="flag" name="flag" value="Y" />

	 
	
	<table class='detail' width="70%">
		<tbody class="window">				
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><label>
							<jecs:locale key="bdCaculateLog.cretaeTime" />(<jecs:locale key="start.word" />):
						</label>
					</span>
				</th>
				<td>
					<span class="textbox">
					 	<input name="startDate" id="startDate" type="text" class="textbox-text"
						value="${param.startDate }" style="cursor: pointer;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><label>
							<jecs:locale key="bdCaculateLog.cretaeTime" />(<jecs:locale key="end.word" />):
						</label>
					</span>
				</th>
				<td>
					<span class="textbox">
						<input name="endDate" id="endDate" type="text" class="textbox-text"
						value="${param.endDate }" style="cursor: pointer;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</span>
				</td>
			</tr>	
			<tr>		
				<th>
					<span class="text-font-style-tc">
						<label>
							<jecs:locale key="bdPeriod.wyear" />:
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
					 	<input type="text" name="wyear" id="wyear" size="8"
							value="${param.wyear}" class="textbox-text"/>
					</span>
				</td>			
			</tr>
			<c:if test="${strAction=='depositExport' || strAction=='invoiceExport' }">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="miMember.memberNo" />
								:
							</label>
						</span>
					</th>
					<td colspan="3" >
						<span class="textbox">
						 	<input type="text" name="userCode" id="userCode" size="12" class="textbox-text"
							value="${param.userCode}" />
						</span>
					</td>			
				</tr>
			</c:if>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="button" name="button1"
					value="<jecs:locale key="button.report"/>"
					onclick="queryReport(document.searchForm,'${strAction}')"
					style="cursor: pointer;" class="btn btn_sele"/>
				</td>
			</tr>
		</tbody>
	</table>
	<%-- 			<font onclick="cancel();" color="blue" style="cursor: pointer;"><jecs:locale key="operation.button.cancel"/><jecs:locale key="button.report"/></font>
 --%>
</form>
<script type="text/javascript">

function queryReport(theForm,strActionValue){

 		var wyear = document.getElementById("wyear");
 		var wweeks = document.getElementById("startDate");
 		var wweeke = document.getElementById("endDate");
 		var usercode = document.getElementById("userCode");
 		
 		/* if( (startDate.value==null || startDate.value=='' ||
 		    endDate.value==null || endDate.value=='') &&
 		    (formatedWeek.value==null || formatedWeek.value=='') &&
 		    (formatedYear.value==null || formatedYear.value=='') &&
 		    (formatedMonth.value==null || formatedMonth.value=='') ){
 			alert("<jecs:locale key='please.input.search.condition'/>");
 			return false;
 		} */
 		
 		if(strActionValue == 'depositInvoiceExport'){
 			if((wweeks.value=='' || wweeks.value == null || wweeke.value=='' || wweeke.value == null)
 	 				&& (wyear.value=='' || wyear.value == null) ){
 	 			alert("<jecs:locale key='please.input.search.condition'/>");
 	 			return false;
 	 		}
 		}else{
 			if((wweeks.value=='' || wweeks.value == null || wweeke.value=='' || wweeke.value == null)
 	 				&& (wyear.value=='' || wyear.value == null) && (usercode.value == '' || usercode.value == null)){
 	 			alert("<jecs:locale key='please.input.search.condition'/>");
 	 			return false;
 	 		}
 		}
 		
	 waiting();
     theForm.strAction.value=strActionValue;
	 theForm.submit();
	 waitingEnd();
}

function cancel(){
	waitingEnd();
 }
 
 
</script>

