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

	<form method="post" action="" onsubmit="return validateOthers(this)"
		id="bdPeriodForm">
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
								prompt="" withAA='false' />
								
						</td>			
					</tr>
				</c:if>		
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="aiAgent.createTime" />(<jecs:locale key="start.word" />):
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input name="startCreateDate" id="startCreateDate" type="text" 
							value="${param.startCreateDate }" style="cursor: pointer;" class="textbox-text"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</span>
					</td>
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="aiAgent.createTime" />(<jecs:locale key="end.word" />):
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input name="endCreateDate" id="endCreateDate" type="text" 
							value="${param.endCreateDate }" style="cursor: pointer;" class="textbox-text"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</span>
					</td>
				</tr>
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
						 	<input name="startPayDate" id="startPayDate" type="text" 
							value="${param.startPayDate }" style="cursor: pointer;" class="textbox-text"
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
						 	<input name="endPayDate" id="endPayDate" type="text" 
							value="${param.endPayDate }" style="cursor: pointer;" class="textbox-text"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</span>
					</td>		
				</tr>
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="fiPayAdvice.accountCode" />
								:
							</label>
						</span>
					</th>
					<td colspan="3" >
						<span class="textbox">
						 	<select name="accountCode" class="textbox-text">
								<option value=""></option>
								<c:forEach items="${jfiPayBanks}" var="payBankVar">
									<option value="${payBankVar.accountCode }"
										<c:if test="${payBankVar.accountCode==param.accountCode }"> selected</c:if>>
										${payBankVar.bankName }
									</option>
								</c:forEach>
							</select>
						</span>
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<jecs:power powerCode="fiBankOutReport">
							<input type="submit" name="submit"
								value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
						</jecs:power>
						<input type="hidden" name="strAction" value="fiBankOutReport" />				
					</td>
				</tr>
			</tbody>
		</table>

	</form>
</c:if>

<c:if test="${isReporting=='1'}">
	<form>
		<div id="titleBar" class="noPrint">
			<jecs:power powerCode="fiBankOutReport">
				<input type="button" name="btnPrint"
					value="<jecs:locale key="button.print"/>" class="button"
					onclick="window.print()" />
			</jecs:power>
			<input type="button" name="btnCancel"
				value="<jecs:locale key="operation.button.cancel"/>" class="button"
				onclick="window.location='jfiBankOutReport.html'" />
		</div>

		<table width="100%" border="0" cellpadding="3" cellspacing="1"
			class="print">
			<tr>
				<td colspan="3" align="center" class="title">
					${alCompany.companyName}
					<jecs:locale key="fiBankOutReport.title" />
				</td>
			</tr>
			<tr>
				<td>
					<jecs:locale key="bdOutwardBank.bankName" />
					: ${fiPayBank.bankName }
				</td>
				<td>
					<jecs:locale key="aiAgent.createTime" />
					: ${param.startCreateDate } ~ ${param.endCreateDate }
				</td>
				<td>
					<jecs:locale key="comm.busi.deal.transaction.date" />
					: ${param.startPayDate } ~ ${param.endPayDate }
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellpadding="3" cellspacing="1"
			class="list">
			<tr>
				<th>
					<jecs:locale key="label.member.or.agent.code" />
				</th>
				<th>
					<jecs:locale key="bdOutwardBank.bankCode" />
				</th>
				<th>
					<jecs:locale key="bdOutwardBank.accountName" />
				</th>
				<th>
					<jecs:locale key="fiPayBank.accountNo" />
				</th>
				<th>
					<jecs:locale key="bdSendRemittanceReport.openCityCH" />
				</th>
				<th>
					<jecs:locale key="fiBankbookJournal.money" />
				</th>
				<th>
					<jecs:locale key="busi.order.amount" />
				</th>
				<th>
					<jecs:locale key="fiPayAdvice.arrivedMoney" />
				</th>
				<th>
					<jecs:locale key="fiPayAdvice.get.money" />
				</th>
				<th>
					<jecs:locale key="fiPayAdvice.adviceCode" />
				</th>
				<th>
					<jecs:locale key="busi.common.remark" />
				</th>
				<th>
					<jecs:locale key="title.index" />
				</th>
			</tr>
			<c:set var="totalPayMoney" value="0" />
			<c:set var="totalArrivedMoney" value="0" />
			<%
				com.joymain.jecs.util.data.CommonRecord crm = com.joymain.jecs.util.web.RequestUtil
							.toCommonRecord(request);
			%>
			<c:forEach items="${fiPayAdviceGroups}" var="fiPayAdviceGroupVar">
				<%
					String userCode = ((java.util.Map) pageContext
									.getAttribute("fiPayAdviceGroupVar")).get(
									"user_code").toString();
							crm.addField(new com.joymain.jecs.util.data.CustomField(
									"userCode", 1111, userCode));
							crm.addField(new com.joymain.jecs.util.data.CustomField(
									"status", 1111, "1,4"));
							com.joymain.jecs.fi.service.JfiPayAdviceManager jfiPayAdviceManager = (com.joymain.jecs.fi.service.JfiPayAdviceManager) request
									.getAttribute("jfiPayAdviceManager");
							java.util.List jfiPayAdvices = jfiPayAdviceManager
									.getJfiPayAdvicesStat(crm);
							request.setAttribute("fiPayAdvices", jfiPayAdvices);
				%>
				<c:forEach items="${fiPayAdvices}" var="fiPayAdviceVar"
					varStatus="fiPayAdviceStatus">
					<tr>
						<c:if test="${fiPayAdviceGroupVar.total_advice>1}">
							<c:if test="${fiPayAdviceStatus.index==0}">
								<td rowspan="${fiPayAdviceGroupVar.total_advice }">
									${fiPayAdviceGroupVar.user_code }
									${fiPayAdviceGroupVar.user_name }
								</td>
							</c:if>
						</c:if>
						<c:if test="${fiPayAdviceGroupVar.total_advice==1}">
							<td>
								${fiPayAdviceGroupVar.user_code }
								${fiPayAdviceGroupVar.user_name }
							</td>
						</c:if>
						<td>
							${fiPayAdviceVar.account_code }
						</td>
						<td>
							${fiPayAdviceVar.account_name }
						</td>
						<td>
							${fiPayAdviceVar.account_no }
						</td>
						<td>
							${fiPayAdviceVar.bank_city }
						</td>
						<td align="right">
							<fmt:formatNumber pattern="###,##0.00">${fiPayAdviceVar.pay_money }</fmt:formatNumber>
						</td>

						<c:if test="${fiPayAdviceGroupVar.total_advice>1}">
							<c:if test="${fiPayAdviceStatus.index==0}">
								<td rowspan="${fiPayAdviceGroupVar.total_advice }" align="right">
									<fmt:formatNumber pattern="###,##0.00">${fiPayAdviceGroupVar.total_pay_money }</fmt:formatNumber>
								</td>
							</c:if>
						</c:if>
						<c:if test="${fiPayAdviceGroupVar.total_advice==1}">
							<td align="right">
								<fmt:formatNumber pattern="###,##0.00">${fiPayAdviceGroupVar.total_pay_money }</fmt:formatNumber>
							</td>
						</c:if>
						<td align="right">
							<fmt:formatNumber pattern="###,##0.00">${fiPayAdviceVar.arrived_money }</fmt:formatNumber>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							${fiPayAdviceVar.advice_code }
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</c:forEach>
				<c:set var="totalPayMoney"
					value="${totalPayMoney+fiPayAdviceGroupVar.total_pay_money}" />
				<c:set var="totalArrivedMoney"
					value="${totalArrivedMoney+fiPayAdviceGroupVar.total_arrived_money}" />
			</c:forEach>
			<tr>
				<td colspan="6" align="right">
					<jecs:locale key="report.allTotal" />
				</td>
				<td align="right">
					<b><fmt:formatNumber pattern="###,##0.00">${totalPayMoney }</fmt:formatNumber>
					</b>
				</td>
				<td align="right">
					<b><fmt:formatNumber pattern="###,##0.00">${totalArrivedMoney }</fmt:formatNumber>
					</b>
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0" cellpadding="3" cellspacing="1"
			class="print">
			<tr>
				<td align="right">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<jecs:locale key="report.financial" />
							</td>
							<td width="100" class="bottomLine">
								&nbsp;
							</td>
							<td>
								<jecs:locale key="report.cashier" />
							</td>
							<td width="40" class="bottomLine" align="center">
								/
							</td>
							<td>
								<jecs:locale key="report.createPaper" />
							</td>
							<td width="60" class="bottomLine" align="center">
								/
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right">
					${sessionScope.sessionLogin.userName} / ${currentTime}
				</td>
			</tr>
		</table>
	</form>
</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.startCreateDate.value==""){
		alert("<jecs:locale key="please.input.startCreateDate"/>");
		return false;
	}
	if(theForm.endCreateDate.value==""){
		alert("<jecs:locale key="please.input.endCreateDate"/>");
		return false;
	}
	
	return true;
}
</script>