<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<script type="text/javascript" src="./scripts/validate.js"> </script>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/print.css'/>" />
<style media="print">
.noPrint {
	display: none;
}
</style>

<c:if test="${isReporting!='1'}">

	<!-- 装载日历JS -->
	<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
	<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
	<script type="text/javascript"
		src="./scripts/calendar/calendar-setup.js"> </script>
	<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

	<form method="post" action="jfiPayDataStatusReport.html"
		onsubmit="return validateOthers(this)" id="bdPeriodForm">
		
		<table class='detail' width="70%">
			<tbody class="window">		
				<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">	
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc">
								<label>
									<jecs:locale key="bdReconsumMoneyReport.companyCH" />
									:
								</label>
							</span>
						</th>
						<td colspan="3" >
						 	<jecs:company selected="${param.companyCode }" name="companyCode"
							withAA='false' />
						</td>			
					</tr>		
				</c:if>	
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="comm.busi.deal.transaction.date" />(<jecs:locale key="start.word" />):
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input name="startDealDate" id="startDealDate" type="text" 
							value="${param.startDealDate }" style="cursor: pointer;" class="textbox-text"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</span>
					</td>	
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="comm.busi.deal.transaction.date" />(<jecs:locale key="end.word" />):
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
							<input name="endDealDate" id="endDealDate" type="text" 
							value="${param.endDealDate }" style="cursor: pointer;" class="textbox-text"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</span>
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<jecs:power powerCode="fiPayDataStatusReport">
							<input type="submit" name="submit"
									value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
						</jecs:power>
						<input type="hidden" name="strAction" value="fiPayDataStatusReport" />					
					</td>
				</tr>
			</tbody>
		</table>

	</form>
</c:if>

<c:if test="${isReporting=='1'}">
	<form>
		<div id="titleBar" class="noPrint">
			<jecs:power powerCode="fiPayDataStatusReport">
				<input type="button" name="btnPrint"
					value="<jecs:locale key="button.print"/>" class="button"
					onclick="window.print()" />
			</jecs:power>
			<input type="button" name="btnCancel"
				value="<jecs:locale key="operation.button.cancel"/>" class="button"
				onclick="window.location='jfiPayDataStatusReport.html'" />
		</div>
		<br>
		<table width="100%" border="0" cellpadding="2" cellspacing="1"
			class="print">
			<tr>
				<td colspan="2" align="center" class="title">
					${alCompany.regName}
					<jecs:locale key="menu.fi.fiPayDataStatusReport" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<jecs:locale key="comm.busi.deal.transaction.date" />
					: ${param.startDealDate } ~ ${param.endDealDate }
				</td>
				<td align="right">
					<jecs:locale key="title.reporter" />
					: ${sessionScope.sessionLogin.userName}&nbsp;&nbsp;&nbsp;
					<jecs:locale key="title.print.time" />
					: ${currentTime}
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="3" cellspacing="1"
			class="list">
			<tr>
				<th rowspan="2">
					<jecs:locale key="fiPayData.accountCode" />
				</th>
				<th colspan="2">
					<jecs:locale key="title.item" />
					<jecs:locale key="report.allTotal" />
				</th>
				<th colspan="2">
					<jecs:locale key="fiPayAdvice.status.1" />
					<jecs:locale key="report.allTotal" />
				</th>
				<th colspan="2">
					<jecs:locale key="fiPayAdvice.status.2.nocolor" />
					<jecs:locale key="report.allTotal" />
				</th>
			</tr>
			<tr>
				<th>
					<jecs:locale key="report.bdSend.Statistic.count" />
				</th>
				<th>
					<jecs:locale key="busi.finance.amount" />
				</th>
				<th>
					<jecs:locale key="report.bdSend.Statistic.count" />
				</th>
				<th>
					<jecs:locale key="busi.finance.amount" />
				</th>
				<th>
					<jecs:locale key="report.bdSend.Statistic.count" />
				</th>
				<th>
					<jecs:locale key="busi.finance.amount" />
				</th>
			</tr>
			<c:set var="totalMoney1" value="0" />
			<c:set var="totalCount1" value="0" />
			<c:set var="totalMoney2" value="0" />
			<c:set var="totalCount2" value="0" />
			<c:forEach items="${jfiPayDatas}" var="fiPayDataVar">
				<c:if
					test="${not empty fiPayDataVar.count_1 or not empty fiPayDataVar.income_money_1 or not empty fiPayDataVar.count_2 or not empty fiPayDataVar.income_money_2}">
					<tr>
						<td>
							${fiPayDataVar.account_code }
						</td>
						<td align="right">
							${fiPayDataVar.count_1+fiPayDataVar.count_2 }
						</td>
						<td align="right">
							<fmt:formatNumber pattern="###,##0.00">${fiPayDataVar.income_money_1 + fiPayDataVar.income_money_2}</fmt:formatNumber>
						</td>
						<td align="right">
							${fiPayDataVar.count_1+0 }
						</td>
						<td align="right">
							<fmt:formatNumber pattern="###,##0.00">${fiPayDataVar.income_money_1 +0}</fmt:formatNumber>
						</td>
						<td align="right">
							${fiPayDataVar.count_2+0}
						</td>
						<td align="right">
							<fmt:formatNumber pattern="###,##0.00">${fiPayDataVar.income_money_2 +0}</fmt:formatNumber>
						</td>
					</tr>
					<c:set var="totalMoney1"
						value="${totalMoney1+fiPayDataVar.income_money_1}" />
					<c:set var="totalCount1"
						value="${totalCount1+fiPayDataVar.count_1}" />
					<c:set var="totalMoney2"
						value="${totalMoney2+fiPayDataVar.income_money_2}" />
					<c:set var="totalCount2"
						value="${totalCount2+fiPayDataVar.count_2}" />
				</c:if>
			</c:forEach>
			<tr>
				<td align="right">
					<b><jecs:locale key="report.allTotal" />
					</b>
				</td>
				<td align="right">
					<b>${totalCount1+totalCount2 }</b>
				</td>
				<td align="right">
					<b><fmt:formatNumber pattern="###,##0.00">${totalMoney1+totalMoney2 }</fmt:formatNumber>
					</b>
				</td>
				<td align="right">
					<b>${totalCount1 }</b>
				</td>
				<td align="right">
					<b><fmt:formatNumber pattern="###,##0.00">${totalMoney1 }</fmt:formatNumber>
					</b>
				</td>
				<td align="right">
					<b>${totalCount2 }</b>
				</td>
				<td align="right">
					<b><fmt:formatNumber pattern="###,##0.00">${totalMoney2 }</fmt:formatNumber>
					</b>
				</td>
			</tr>

		</table>
	</form>
</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.startDealDate.value==""){
		alert("<jecs:locale key="please.input.startPayDate"/>");
		return false;
	}
	if(theForm.endDealDate.value==""){
		alert("<jecs:locale key="please.input.endPayDate"/>");
		return false;
	}
	
	return true;
}
</script>