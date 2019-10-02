<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<form method="post" action="jpoOrderFMonthReport.html" id="bdPeriodForm" onsubmit="return validateOthers(this)">
<table class="detail" width="70%">
	<tbody class="window" >
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="bdReconsumMoneyReport.companyCH"/>:</span></th>
			<td>
				<span class="textbox">
				<select name="companyCode" class="textbox-text">
					<c:forEach items="${alCompanys}" var="alCompanyVar">
						<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==param.companyCode}"> selected</c:if>>${alCompanyVar.companyName }</option>
					</c:forEach>
				</select>
				</span>
			</td>
		</tr>
		</c:if>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.sweek" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="sWeek" id="sWeek" value="" class="textbox-text"/></span> (<jecs:locale key="label.example"/>:200801)
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.eweek" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="eWeek" id="eWeek" value="" class="textbox-text"/></span> (<jecs:locale key="label.example"/>:200801)
			</td>
		</tr>
		
		<tr>
			<td class="command" colspan="4" align="center">
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
			</td>
		</tr>
	</tbody>
	</table>
	
</form>

<script type="text/javascript">
function validateOthers(theForm){
	
	var sWeek = document.getElementById('sWeek').value;
	var eWeek = document.getElementById('eWeek').value;
	
	if(sWeek == '' || sWeek == undefined) {
		alert('<jecs:locale key="input.startweek" />');
		return false;
	}
	if(eWeek == '' || eWeek == undefined) {
		alert('<jecs:locale key="input.endweek" />');
		return false;
	}
	return true;
	
}
</script>